package comprehensive;

public class TextGenerator {

    public static void main(String[] args) {
        if (args.length == 3) {
            Generator generator = new Generator(args[0], args[1], args[2]);
        } else if (args.length == 4) {
            Generator generator = new Generator(args[0], args[1], args[2], args[3]);
        }
    }
}
