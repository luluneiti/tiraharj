package tiraharj;

import tiraharj.tools.Statistic;
import java.util.Stack;
import tiraharj.algorithm.Astar;
import tiraharj.algorithm.Dijkstra;
import tiraharj.Graph;
import tiraharj.algorithm.Heuristic;
import tiraharj.algorithm.Manhattan;
import tiraharj.Node;

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

        runDijkstra(graph);
        runAstar(graph);
    }

    public static void runDijkstra(Graph graph) {

        Statistic statistic = new Statistic();
        Dijkstra.setStatistic(statistic);
        statistic.startClock();
        Dijkstra.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0));
        statistic.stopClock();
        Dijkstra.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0));
        System.out.println("Dijkstra: " + statistic);
    }

    public static void runAstar(Graph graph) {

        Statistic statistic = new Statistic();
        Astar.setStatistic(statistic);
        Heuristic heuristic = new Manhattan();
        statistic.startClock();
        Astar.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        statistic.stopClock();
        Astar.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0));
        System.out.println("Astar: " + statistic);
    }
}
