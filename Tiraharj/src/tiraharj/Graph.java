package tiraharj;

public class Graph {

    private int width;
    private int height;
    private int[] obstacles;
    private Node[] neighbors;
    private int distance;

    public Graph(int width, int height, int[] obstacles, int distance) {
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
        neighbors = new Node[4]; //kun liikutaan ylös/alas ja vasen/oikea
        this.distance = 1;
    }

    public boolean isReachable(int x, int y) {

        return x >= 0 && x <= this.width && y >= 0 && y <= this.height;
    }

    public boolean isTraversable(int value) {

        //esteet ei vielä käytössä
        //esteet taulukkoon value:lla ja silloin voi kysyä suoraan?
        return obstacles[value] == 1;
    }

    public int getNodeAmount() {
        return width * height;
    }

    public Node[] getNeighbors(Graph graph, Node node) {
        
        //eri koordinaateilla eri määrä naapureita
        //jos astar, niin missä tämä log. on, kun on heuristikka käytössä

        createNeighbors(node.getY(), node.getY());
        return this.neighbors;
    }

    public void createNeighbors(int x, int y) {

        int ind = 0;
        if (isReachable(x + 1, y)) {
            neighbors[ind] = new Node(x + 1, y, this.distance);
            ind++;
        }
        if (isReachable(x - 1, y)) {
            neighbors[ind] = new Node(x - 1, y, this.distance);
            ind++;
        }
        if (isReachable(x, y + 1)) {
            neighbors[ind] = new Node(x, y + 1, this.distance);
            ind++;
        }
        if (isReachable(x, y - 1)) {
            neighbors[ind] = new Node(x, y - 1, this.distance);
            ind++;
        }

    }

}
