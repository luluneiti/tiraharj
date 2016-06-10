package tiraharj;

import tiraharj.algorithm.Heuristic;

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
     * Asettaa esteet koordinaatistoon
     *
     * @param obstacles
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
     * Kertoo onko parametrin xy-koordinaatti saavutettavissa / koordinaatiston
     * "sisällä"
     *
     * @param x koordinaatin x-arvo
     * @param y koordinaatin y-arvo
     * @return true tai false
     */
    public boolean isReachable(int x, int y) {

        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    /**
     * Kertoo onko parametrin koordinaatissa estettä
     *
     * @param x koordinaatin x-arvo
     * @param y koordinaatin y-arvo
     * @return true tai false
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
     * Palauttaa parametrina annetun solmun naapurisolmut 
     * Huom: myös Dijkstra käyttää heuristiikkaa sen päättelyssä, että minne voi liikkue.
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
    //kuormitettu metodi algoritmeille, jotka käyttävät heuristiikkaa
    private Node[] createNeighbors(int x, int y, Heuristic heuristic) {

        Location[] coord = heuristic.getNeighborsCoordinates(x, y); //missä muodossa
        int ind = 0;
        Node[] neighbors = new Node[coord.length];

        for (Location loc : coord) {
            if (isReachable(loc.getX(), loc.getY()) && isTraversable(loc.getX(), loc.getY())) {
                neighbors[ind] = new Node(loc.getX(), loc.getY(), this.matrix[loc.getX()][loc.getY()]);
                ind++;
            }
        }

        return neighbors;
    }

    /**
     * Palauttaa yksilöllisen tunnuksen koordinaatiston pisteelle
     *
     * @param x koordinaatin x-arvo
     * @param y koordinaatin y-arvo
     * @return koordinaatiston pisteen yksilöivä tunnus
     */
    public int getPointId(int x, int y) {
        return x * this.width + y;
    }

    /**
     *
     * @param id
     * @return
     */
    public String getXYByPointId(int id) {
        int a = id / this.width;
        int b = id - a * this.width;
        return "" + a + "," + b;
    }
}
