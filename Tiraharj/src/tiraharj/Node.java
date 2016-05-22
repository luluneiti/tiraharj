package tiraharj;

public class Node implements Comparable<Node> {

    private int x;
    private int y;
    private int distance;

    public Node(int x, int y, int distance) {

        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        } else {
            //heitä poikkeus
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {

        String s = "" + x + "" + y;
        return Integer.parseInt(s);
    }

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
        return "rivi: " + this.getX() + " sarake:" + this.getY() + " ," + this.distance;
    }

}
