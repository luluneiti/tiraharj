package tiraharj;

public class Graph {

    private int width;
    private int height;
    private boolean[] obstacles;
    private int[][] matrix;

    public Graph(int[][] matrix, boolean[] obstacles) {
        this.matrix = matrix;
        this.width = matrix[0].length;
        this.height = matrix.length;
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

        return obstacles[getPointId(x, y)] == false;
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
     *
     * @param graph verkko, jossa solmu sijaitsee
     * @param node solmu, jonka naapurit etsitään (solmu tuntee oman
     * xy-sijaintinsa)
     * @return taulukko, jossa naapurisolmut
     */
    public Node[] getNeighbors(Graph graph, Node node) {

        //eri koordinaateilla eri määrä naapureita
        return createNeighbors(node.getX(), node.getY());
    }

    /**
     * Luo naapurisolmut. Päättelee naapuruuden koordinaattien avulla.
     *
     * @param x solmun x-koordinaatti, jolle naapurisolmut luodaan
     * @param y solmun y-koordinaatti, jolle naapurisolmut luodaan
     */
    private Node[] createNeighbors(int x, int y) {

        int ind = 0;
        Node[] neighbors = new Node[getAmountOfNeighbors(x, y)];

        if (isReachable(x + 1, y) && isTraversable(x + 1, y)) {
            neighbors[ind] = new Node(x + 1, y, this.matrix[x + 1][y]);
            ind++;
        }
        if (isReachable(x - 1, y) && isTraversable(x - 1, y)) {
            neighbors[ind] = new Node(x - 1, y, this.matrix[x - 1][y]);
            ind++;
        }
        if (isReachable(x, y + 1) && isTraversable(x, y + 1)) {
            neighbors[ind] = new Node(x, y + 1, this.matrix[x][y + 1]);
            ind++;
        }
        if (isReachable(x, y - 1) && isTraversable(x, y - 1)) {
            neighbors[ind] = new Node(x, y - 1, this.matrix[x][y - 1]);
        }

        return neighbors;
    }

    /**
     * Palauttaa ko. koordinaatisto pisteen mahdollisten naapureiden määrän
     *
     * @param x koordinaatin x-arvo
     * @param y koordinaatin y-arvo
     * @return naapureiden määrä
     */
    public int getAmountOfNeighbors(int x, int y) {

        if (x >= 1 && x < this.width - 1 && y >= 1 && y < this.height - 1) { //keskellä
            return 4;
        } else if ((x == 0 && y == 0) || (x == 0 && y == this.height - 1) || (x == this.height - 1 && y == 0) || (x == this.height - 1 && y == this.width - 1)) {  //kulmat
            return 2;
        } else {   //reunat, mutta ei kulmia
            return 3;
        }
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