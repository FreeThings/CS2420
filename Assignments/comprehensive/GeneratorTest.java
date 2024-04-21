package comprehensive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorTest {

    @Test
    void testSmallFileNoArg(){
        Generator gen = new Generator("src/comprehensive/helloWorld.txt", "hello", "5");
    }

    @Test
    void testBiggerSmallFileNoArg(){
        Generator gen = new Generator("src/comprehensive/abc.txt", "a", "5");
    }

    @Test
    void testSmallFileAll() {
        Generator gen = new Generator("src/comprehensive/helloWorld.txt", "hello", "5", "all");
    }

    @Test
    void testSmallFileOne(){
        Generator gen = new Generator("src/comprehensive/helloWorld.txt", "hello", "5", "one");
    }

    @Test
    void testBiggerSmallFileOne(){
        Generator gen = new Generator("src/comprehensive/small.txt", "hello", "10", "one");
    }

    @Test
    void testBigFileOne(){
        Generator gen = new Generator("src/comprehensive/theSeaHorror.txt", "eyes", "50", "one");
    }

    @Test
    void testBiggerFileOne(){
        Generator gen = new Generator("src/comprehensive/voyage.txt", "voyage", "50", "one");
    }

    @Test
    void testTrickyFormatFileOne(){
        Generator gen = new Generator("src/comprehensive/trickyFormatting.txt", "this", "12", "one");
    }

    @Test
    void testTrickFormatFileNoArg(){
        Generator gen = new Generator("src/comprehensive/trickyFormatting.txt", "this", "12");
    }

    @Test
    void testTrickyFormatFileAll(){
        Generator gen = new Generator("src/comprehensive/trickyFormatting.txt", "this", "12", "all");

    }


    @Test
    void testSmallFileAll2(){
        Generator gen = new Generator("src/comprehensive/small.txt", "hello", "10", "all");

    }

    @Test
    void testBiggerFileAll(){
        Generator gen = new Generator("src/comprehensive/voyage.txt", "voyage", "50", "all");
    }


    @Test
    void testBiggerFileNoArg(){
        Generator gen = new Generator("src/comprehensive/voyage.txt", "voyage", "50");
    }


}

