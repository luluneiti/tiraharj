package tiraharj;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        int[] obstacles = {35, 45, 55, 65};
        Graph graph = new Graph(10, 10, obstacles, 1);
        Dijkstra.findRoute(graph, new Node(1,2, 1), new Node(6,7, 1));
        Dijkstra.printRoute(12, 67);
    }

}
