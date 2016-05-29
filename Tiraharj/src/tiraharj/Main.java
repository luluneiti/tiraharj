package tiraharj;

import java.util.Stack;

public class Main {
    
    public static void main(String[] args) {

        //int[][] matrix = {{2, 8, 4, 6, 3, 7, 6, 8, 9, 1}, {7, 6, 8, 9, 1, 5, 5, 6, 1, 1}, {5, 5, 6, 1, 1, 8, 1, 1, 2, 2}, {7, 6, 8, 9, 1, 8, 1, 1, 2, 2}, {2, 8, 4, 6, 3, 7, 6, 8, 9, 1}, {7, 6, 8, 9, 1, 2, 8, 4, 6, 3}, {2, 8, 4, 6, 3, 5, 5, 6, 1, 1}, {2, 8, 4, 6, 3, 8, 1, 1, 2, 2}, {5, 5, 6, 1, 1, 8, 1, 1, 2, 2}, {2, 8, 4, 2, 8, 4, 6, 3, 6, 3}};
        int[][] matrix = {{2, 8, 4, 1, 1}, {7, 6, 8, 9, 1}, {5, 5, 1, 1, 1}, {8, 1, 1, 2, 2}, {8, 1, 1, 2, 2}};
        //int[][] matrix = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1} };
        //int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];
        
        Graph graph = new Graph(matrix);
        graph.setObstacles(obstacles);
        obstacles[graph.getPointId(2, 2)] = true;
        obstacles[graph.getPointId(4, 5)] = true;
        obstacles[graph.getPointId(5, 5)] = true;
        obstacles[graph.getPointId(6, 5)] = true;
        
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println("");
        }
        
        runDijkstra(graph, obstacles);
        runAstar(graph, obstacles);
    }
    
    public static void runDijkstra(Graph graph, boolean[] obstacles) {
        
        Dijkstra.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0));
        Dijkstra.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0));
        
    }
    
    public static void runAstar(Graph graph, boolean[] obstacles) {
        
        Heuristic heuristic = new Manhattan();
        Astar.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        Astar.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0));
    }
}
