package tiraharj;

public class Manhattan implements Heuristic {

    @Override
    public int getToEnd(Node a, Node b) {

        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());

    }

}
