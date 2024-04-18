package comprehensive;



import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class Generator {

    private File sourceFile;
    private String seed;
    private int k;
    private Hashtable<String, LinkedList<Node>> wordMap;
    private ArrayList<String> newText;
    public Generator(String filename, String seed, String k) {
        this.seed = seed;
        this.k = Integer.parseInt(k);
        this.sourceFile = new File(filename);
        this.wordMap = new Hashtable<>();
        this.newText = new ArrayList<>();
    }



    public void generateText() {
        readSourceFile();
        newText.add(seed);
        for (int i = 0; i < k; i++) {
            newText.add(generateNextWord(newText.get(i)));
        }
        for (String word : newText) {
            if(word != null)
                 System.out.print(word + " ");
        }
    }

    /**
     * Reads the source file and creates a hashtable of linked lists to store the words and their
     * probabilities
     */
    private void readSourceFile() {
        // Read the source file and create the wordMap
        try {
            Scanner fileTransfer = new Scanner(new FileReader(sourceFile));
            String word = fileTransfer.next();
            String nextWord = fileTransfer.next();
            while (fileTransfer.hasNext()) {
                if (wordMap.containsKey(word)) {
                    LinkedList<Node> list = wordMap.get(word);
                    Node pointer = list.getFirst();
                    while (pointer != null) {
                        if (pointer.word.equals(nextWord)) {
                            pointer.count++;
                            break;
                        }
                        pointer = pointer.next;
                    }
                    if (pointer == null) {
                        Node newNode = new Node(nextWord);
                        list.add(newNode);
                    }
                } else {
                    LinkedList<Node> list = new LinkedList<>();
                    Node newNode = new Node(nextWord);
                    list.add(newNode);
                    wordMap.put(word, list);
                }
                word = nextWord;
                nextWord = fileTransfer.next();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find the file!");
        }

    }

    /**
     * Generates the next random word based on looking at a seed value, then choosing the next
     * word based on a random number generator and the probability of words that came after in the
     * original file
     * @param seed - Seed word to choose the next word for in the random text
     * @return - The next word in the random order based on probability and the seed
     */
    private String generateNextWord(String seed) {
        LinkedList<Node> wordList = wordMap.get(seed);
        if(wordList == null) {
            return null;
        }
        Node current = wordList.getFirst();
        double probability = 0;
        double totalNodes = wordList.size();
        double choice = Math.random();

        while (current.next != null) {
            probability += (double)current.count / totalNodes;
            if (choice < probability) {
                return current.word;
            }
            current = current.next;
        }

        return current.word;
    }



    private class Node { // Node class for the linked list
        String word;
        Node next;
        int count;

        public Node(String word) {
            this.word = word;
            this.next = null;
        }
    }












}
