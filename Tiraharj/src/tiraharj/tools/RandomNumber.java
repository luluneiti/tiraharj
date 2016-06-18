package tiraharj.tools;

public class RandomNumber {

    public static int getNumber(int limit) {
        int random = (int) (Math.random() * limit + 1);
        return random;
    }

}
