package tiraharj;

/**
 * Verkon solmu, joka tuntee sijaintinsa ja etäisyyden
 * @author Ulla
 */
public class Node implements Comparable<Node> {

    private int x;
    private int y;
    private int distance;

    /**
     * Luodaan uusi solmu
     *
     * @param x koordinaatin x-arvo
     * @param y koordinaatin y-arvo
     * @param distance etäisyys
     */
    public Node(int x, int y, int distance) {

        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        } else {
            //heitä poikkeus jatkossa
            System.out.println("ongelmia!");
        }
    }

    /**
     * Palauttaa solmu-olion x-koordinaatin arvon
     *
     * @return x-koordinaatin arvo
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa solmu-olion y-koordinaatin arvon
     *
     * @return y-koordinaatin arvo
     */
    public int getY() {
        return y;
    }

    /**
     * Palauttaa solmu-olion etäisyyden
     *
     * @return etäisyys
     */
    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Node comp) {

        int paluu = 0;
        if (this.distance < comp.distance) {
            paluu = -1;
        }
        if (this.distance > comp.distance) {
            paluu = 1;
        }
        return paluu;
    }

    @Override
    public String toString() {
        return "rivi: " + this.x + " sarake:" + this.y + " , etäisyys: " + this.distance; 
    }

}
