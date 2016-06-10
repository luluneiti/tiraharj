package tiraharj;

//käytetään x, y koordinaatti -joukon välittämiseen eri luokkien välillä
public class Location {

    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
