package tiraharj;

import tiraharj.algorithm.Heuristic;

/**
 * Tarjoaa verkkoon liittyviä palveluita algoritmeille
 * @author Ulla
 */
public class Graph {

    private int width;
    private int height;
    private boolean[] obstacles;
    private int[][] matrix;

    public Graph(int[][] matrix) {
        this.matrix = matrix;
        this.width = matrix[0].length;
        this.height = matrix.length;
    }

    /**
     * Asettaa esteet koordinaatistoon (ei voi kulkea ko. koordinaatistopisteen kautta)
     *
     * @param obstacles esteet
     */
    public void setObstacles(boolean[] obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * Palautta koordinaatiston leveyden
     *
     * @return koordinaatiston leveyden
     */
    public int getWidth() {
        return width;
    }

    /**
     * Palauttaa koordinaatiston korkeuden
     *
     * @return koordinaatiston korkeuden
     */
    public int getHeight() {
        return height;
    }

    /**
     * Kertoo onko parametrina annettu piste (x,y) saavutettavissa
     * @param x koordinaatin x-arvo
     * @param y koordinaatin y-arvo
     * @return true: piste saavutettavissa, false: piste ei saavutettavissa
     */
    public boolean isReachable(int x, int y) {

        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    /**
     * Kertoo onko parametrina annetussa pisteessä (x,y) estettä
     *
     * @param x koordinaatin x-arvo
     * @param y koordinaatin y-arvo
     * @return true: on este, false: ei estettä
     */
    public boolean isTraversable(int x, int y) {

        if (obstacles == null) {
            return true;
        }
        if (isReachable(x, y)) {
            return obstacles[getPointId(x, y)] == false;
        }
        return false;
    }

    /**
     * Palauttaa verkon solmujen kokonaismäärän
     *
     * @return solmujen määrä
     */
    public int getNodeAmount() {
        return width * height;
    }

    /**
     * Palauttaa parametrina annetun solmun naapurisolmut Huom: myös Dijkstra
     * käyttää heuristiikkaa naapuripäättelyssä (ketkä naapureita).
     *
     * @param graph verkko, jossa solmu sijaitsee
     * @param node solmu, jonka naapurit etsitään (solmu tuntee oman
     * xy-sijaintinsa)
     * @param heuristic käytettävä heuristiikka
     * @return taulukko, jossa naapurisolmut
     */
    public Node[] getNeighbors(Graph graph, Node node, Heuristic heuristic) {

        //eri koordinaateilla eri määrä naapureita
        return createNeighbors(node.getX(), node.getY(), heuristic);
    }

    /**
     * Luo naapurisolmut. Saa koordinaatit heuristiikka oliolta, joka tietää
     * minne liikutaan. Tarkistaa, että onko solmut saavutettavissa
     * koordinaatistossa ja ei ole estettä.
     *
     * @param x solmun x-koordinaatti, jolle naapurisolmut luodaan
     * @param y solmun y-koordinaatti, jolle naapurisolmut luodaan
     */
    private Node[] createNeighbors(int x, int y, Heuristic heuristic) {

        Location[] coord = heuristic.getNeighborsCoordinates(x, y); //missä muodossa
        int ind = 0;
        Node[] neighbors = new Node[getSize(coord)];

        for (Location loc : coord) {
            if (isReachable(loc.getX(), loc.getY()) && isTraversable(loc.getX(), loc.getY())) {
                neighbors[ind] = new Node(loc.getX(), loc.getY(), this.matrix[loc.getX()][loc.getY()]);
                ind++;
            }
        }

        return neighbors;
    }

    //estää nullit taulukossa
    private int getSize(Location[] coord) {

        int count = 0;
        for (Location loc : coord) {
            if (isReachable(loc.getX(), loc.getY()) && isTraversable(loc.getX(), loc.getY())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Palauttaa yksilöllisen tunnuksen koordinaatiston pisteelle (x * this.width + y)
     *
     * @param x koordinaatin x-arvo
     * @param y koordinaatin y-arvo
     * @return koordinaatiston pisteen yksilöivä tunnus
     */
    public int getPointId(int x, int y) {
        return x * this.width + y;
    }

    /**
     * Palauttaa x- ja y- koordinaatit koordinaatiston pisteen tunnuksella
     * @param id koordinaattipisteen id
     * @return x- ja y-koordinaatti merkkijonona
     */
    public String getXYByPointId(int id) {
        int a = id / this.width;
        int b = id - a * this.width;
        return "" + a + "," + b;
    }
}
