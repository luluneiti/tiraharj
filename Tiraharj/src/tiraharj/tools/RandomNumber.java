package tiraharj.tools;

/**
 * Käytetään satunnaisten lukujen luomiseen testissä ja käyttöliittymälle
 *
 * @author Ulla
 */
public class RandomNumber {

    private int limit;
    private int lastOne;

    public RandomNumber(int max) {
        this.limit = max;
        this.lastOne = (int) (System.currentTimeMillis() % this.limit);
    }

    /**
     * Palauttaa satunnaisen int tyyppisen luvun, joka väliltä 1-limit arvo ja max 32749.
     * @return satunnainen luku
     */
    public int nextInt() {
        this.lastOne = (this.lastOne * 32719 + 3) % 32749;
        if (this.lastOne % this.limit == 0) {
            return 1;
        } else {
            return this.lastOne % this.limit;
        }

    }

}
