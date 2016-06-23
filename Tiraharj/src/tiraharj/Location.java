package tiraharj;

/**
 * Käytetään koordinaatistopiste (x, y) -joukon välittämiseen eri luokkien välillä
 * @author Ulla
 */
public class Location {

    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Palauttaa x-koordinaatin
     * @return x-koordinaatin
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa y-koordinaatin
     * @return y-koordinaatti
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return ""+this.x+" "+this.y;
    }
}
