package tiraharj.tools;

/**
 * Käytetään satunnaisten lukujen luomiseen testissä ja käyttöliittymälle
 * @author Ulla
 */
public class RandomNumber {

    /**
     * Palauttaa satunnaisen int tyyppisen luvun, joka väliltä 1-limit arvo
     * @param limit satunnaisen luvun maksimiarvo
     * @return satunnainen luku
     */
    public static int getNumber(int limit) {
        int random = (int) (Math.random() * limit + 1);
        return random;
    }

}
