package comprehensive;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

/**
 * This class contains the constructors and private helper methods for a Generator object. This includes
 * creating a new Generator object using one of two constructors depending on the task desired (generating
 * k amount of words based on a markov chain for probability, generating k next words using only the most
 * probable words, and generating every possible word that could come after the given seed word ranked by
 * probability with ties broken by comparing lexicographically).
 *
 * Class: CS 2420
 * Assignment 11: Comprehensive Text Generator
 *
 * @author - Aiden De Boer and Christopher Hunter
 * @version - 4/20/2024
 */
public class Generator {

    private File filePath; //Private variable for the file to be read from
    private String seedWord; //Private variable for the seed word to start generating new words from
    private int k; //Private variable to keep track of how many words the user wants to generate after the seed
    private StringBuilder generatedText;

    private Hashtable<String, ArrayList<String>> wordMapList;
    private Hashtable<String, PriorityQueue<Map.Entry<String, Integer>>> wordMapSorted;

    /**
     * Private HashTable to keep track of the words from the read text file, including a HashMap at each entry to
     * keep track of all words that follow that word in the input file
     */
    private Hashtable<String, HashMap<String, Integer>> wordMap;

    /**
     * Private Hashtable that keeps track of the number of times a given word appears in the input file,
     * used for calculating the probability of each next word to be generated
     */
    private Hashtable<String, Integer> wordCount;

    private Comparator<Map.Entry<String, Integer>> cmp;

    /**
     * 3 Argument constructor for building a new Generator object given an input file's filepath as a string, the
     * seedword to be generated off of, and the number of words to be generated. Reads the given source file and will
     * always call generateTextNoCondition(), since there is no other option when there is no 4th argument
     *
     * @param filePath - String name for the file path of the file to be read and generated based off of
     * @param seedWord - Word to generate next words based off of
     * @param k - Number of words to be generated
     */
    public Generator(String filePath, String seedWord, String k) {
        this.filePath = new File(filePath);
        this.seedWord = seedWord;
        this.k = Integer.parseInt(k);
        wordMapSorted = new Hashtable<>();
        wordMap = new Hashtable<>();
        wordCount = new Hashtable<>();
        generatedText = new StringBuilder();

        this.cmp = (word1, word2) -> {
            if (word1.getValue() > word2.getValue())
                return -1;
            else if (word1.getValue() < word2.getValue())
                return 1;
            else
                return word1.getKey().compareTo(word2.getKey());
        };

        readSourceFile();

        generateTextNoCondition();

        System.out.print(generatedText.toString());
    }

    /**
     * 4 Argument constructor for building a new Generator object
     * @param filePath
     * @param seedWord
     * @param k
     * @param condition
     */
    public Generator(String filePath, String seedWord, String k, String condition){
        this.filePath = new File(filePath);
        this.seedWord = seedWord;
        this.k = Integer.parseInt(k);
        wordMapSorted = new Hashtable<>();
        wordMapList = new Hashtable<>();
        wordMap = new Hashtable<>();
        wordCount = new Hashtable<>();
        generatedText = new StringBuilder();

        this.cmp = (word1, word2) -> {
            if (word1.getValue() > word2.getValue())
                return -1;
            else if (word1.getValue() < word2.getValue())
                return 1;
            else
                return word1.getKey().compareTo(word2.getKey());
        };

        readSourceFile();

        if (condition.equals("all")){

            generateTextWithConditionAll();
        }
        else{

            generateTextWithConditionOne();
        }


        System.out.print(generatedText.toString());
    }

//    private void readSourceFileAll(){
//        try {
//
//            Scanner fileRead = new Scanner(filePath);
//            String currentWord = null;
//            String nextWord = null;
//            while (fileRead.hasNext()){
//
//                if (currentWord == null){
//                    currentWord = fileRead.next();
//                    if (fileRead.hasNext()){
//                        nextWord = fileRead.next();
//                    } else {
//                        break;
//                    }
//
//                    while (true) {
//                        currentWord = wordFormatter(currentWord);
//                        if (currentWord.isEmpty()) {
//                            currentWord = nextWord;
//                            if (fileRead.hasNext())
//                                nextWord = fileRead.next();
//                            else
//                                break;
//                        } else {
//                            break;
//                        }
//                    }
//
//
//                } else {
//                    currentWord = nextWord;
//                    nextWord = fileRead.next();
//                }
//
//                while (true) {
//                    nextWord = wordFormatter(nextWord);
//                    if (nextWord.isEmpty()) {
//                        if (fileRead.hasNext())
//                            nextWord = fileRead.next();
//                        else
//                            break;
//                    } else {
//                        break;
//                    }
//                }
//
//                if (!wordMap.containsKey(currentWord)){
//                    HashMap<String, Integer> chain = new HashMap<>();
//                    wordMap.put(currentWord, chain);
//                }
//
//                if (wordMap.containsKey(currentWord)){
//                    if (wordMap.get(currentWord).containsKey(nextWord)){
//                        wordMap.get(currentWord).put(nextWord, wordMap.get(currentWord).get(nextWord) + 1);
//                    } else if (!currentWord.isEmpty()){
//                        wordMap.get(currentWord).put(nextWord, 1);
//                    }
//                }
//
////                if (!wordMapList.containsKey(currentWord)){
////                    ArrayList<String> chain = new ArrayList<>();
////                    chain.add(nextWord);
////                    wordMapList.put(currentWord, chain);
////                } else {
////                    wordMapList.get(currentWord).add(nextWord);
////                }
//
//                if (!currentWord.isEmpty())
//                    addWordCount(currentWord);
//
//            }
//
//        } catch (FileNotFoundException e) {
//
//            System.out.println("Don't have da file");
//
//        }
//    }

    private void readSourceFile() {
        try {

            Scanner fileRead = new Scanner(filePath);
            String currentWord = null;
            String nextWord = null;
            while (fileRead.hasNext()){

                if (currentWord == null){
                    currentWord = fileRead.next();
                    if (fileRead.hasNext()){
                        nextWord = fileRead.next();
                    } else {
                        break;
                    }

                    while (true) {
                        currentWord = wordFormatter(currentWord);
                        if (currentWord.isEmpty()) {
                            currentWord = nextWord;
                            if (fileRead.hasNext())
                                nextWord = fileRead.next();
                            else
                                break;
                        } else {
                            break;
                        }
                    }


                } else {
                    currentWord = nextWord;
                    nextWord = fileRead.next();
                }

                while (true) {
                    nextWord = wordFormatter(nextWord);
                    if (nextWord.isEmpty()) {
                        if (fileRead.hasNext())
                            nextWord = fileRead.next();
                        else
                            break;
                    } else {
                        break;
                    }
                }

                if (!wordMap.containsKey(currentWord)){
                    HashMap<String, Integer> chain = new HashMap<>();
                    wordMap.put(currentWord, chain);
                }

                if (wordMap.containsKey(currentWord)){
                    if (wordMap.get(currentWord).containsKey(nextWord)){
                        wordMap.get(currentWord).put(nextWord, wordMap.get(currentWord).get(nextWord) + 1);
                    } else if (!currentWord.isEmpty()){
                        wordMap.get(currentWord).put(nextWord, 1);
                    }
                }

                if (!currentWord.isEmpty())
                    addWordCount(currentWord);

            }

        } catch (FileNotFoundException e) {

            System.out.println("Don't have da file");

        }
    }

    private String wordFormatter(String currentWord){
        currentWord = currentWord.toLowerCase();
        for (int i = 0; i < currentWord.length(); i++) {
            if (!String.valueOf(currentWord.charAt(i)).matches("[a-z0-9_]+")) {
                currentWord = currentWord.substring(0, i);
                return currentWord;
            }
        }
        return currentWord;
    }

    private void addWordCount(String currentWord){

        if (wordCount.containsKey(currentWord)) {
            wordCount.put(currentWord, wordCount.get(currentWord) + 1);
        } else {
            wordCount.put(currentWord, 1);
        }

    }

    private void generateTextNoCondition(){
        String currentWord = seedWord;

        PriorityQueue<Map.Entry<String, Integer>> list = new PriorityQueue<>(cmp);

        list.addAll(wordMap.get(currentWord).entrySet());

        for (int i = 0; i < k; i++) {
            if (!list.isEmpty())
                generatedText.append(list.poll().getKey()).append(" ");
            else
                break;
        }
    }

    private void generateTextWithConditionAll() {
        String currentWord = seedWord;


        generatedText.append(seedWord).append(" ");

        for (int i = 0; i < k - 1; i++) {
            if (!wordMap.containsKey(currentWord)){
                currentWord = seedWord;

                generatedText.append(currentWord).append(" ");
            } else {


                Random rand = new Random();
                int wordNum = wordCount.get(currentWord);
                int prob = rand.nextInt(wordNum);

                isInList(currentWord);

                currentWord = wordMapList.get(currentWord).get(prob);


                generatedText.append(currentWord).append(" ");

            }

        }
    }

    private void isInList(String currentWord){
        if (wordMapList.containsKey(currentWord))
            return;
        else {
            ArrayList<String> list = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : wordMap.get(currentWord).entrySet())
                for (int i = 0; i < entry.getValue(); i++)
                    list.add(entry.getKey());
            wordMapList.put(currentWord, list);
        }
    }

    private void generateTextWithConditionOne() {
        String currentWord = seedWord;


        generatedText.append(seedWord).append(" ");

        for (int i = 0; i < k - 1; i++) {
            if (!wordMap.containsKey(currentWord)){
                currentWord = seedWord;

                generatedText.append(currentWord).append(" ");
            } else {

                isOrdered(currentWord);

                PriorityQueue<Map.Entry<String, Integer>> list = wordMapSorted.get(currentWord);


                generatedText.append(list.peek().getKey()).append(" ");

                if (list.peek() != null)
                    currentWord = list.peek().getKey();
            }

        }

    }

    private void isOrdered(String word){
        if (wordMapSorted.containsKey(word))
            return;
        wordMapSorted.put(word, generateOrderedQueue(word));
    }

    private PriorityQueue<Map.Entry<String, Integer>> generateOrderedQueue(String currentWord){
        PriorityQueue<Map.Entry<String, Integer>> list = new PriorityQueue<>(cmp);

        list.addAll(wordMap.get(currentWord).entrySet());

        return list;
    }


}
