package tiraharj;

import tiraharj.tools.Statistic;
import java.util.Stack;
import tiraharj.algorithm.Astar;
import tiraharj.algorithm.Dijkstra;
import tiraharj.Graph;
import tiraharj.algorithm.Heuristic;
import tiraharj.algorithm.Manhattan;
import tiraharj.Node;
import tiraharj.algorithm.IDAStar;
import tiraharj.algorithm.ShortestPath;
import tiraharj.tools.BinaryHeap;
import tiraharj.tools.Heap;
import tiraharj.tools.TernaryHeap;

public class Main {

    public static void main(String[] args) {

        int[][] matrix = {{2, 8, 4, 6, 3, 7, 6, 8, 9, 1}, {7, 6, 8, 9, 1, 5, 5, 6, 1, 1}, {5, 5, 6, 1, 1, 8, 1, 1, 2, 2}, {7, 6, 8, 9, 1, 8, 1, 1, 2, 2}, {2, 8, 4, 6, 3, 7, 6, 8, 9, 1}, {7, 6, 8, 9, 1, 2, 8, 4, 6, 3}, {2, 8, 4, 6, 3, 5, 5, 6, 1, 1}, {2, 8, 4, 6, 3, 8, 1, 1, 2, 2}, {5, 5, 6, 1, 1, 8, 1, 1, 2, 2}, {2, 8, 4, 2, 8, 4, 6, 3, 6, 3}};
//        int[][] matrix = {{2, 8, 4, 1, 1}, {7, 6, 8, 9, 1}, {5, 5, 1, 1, 1}, {8, 1, 1, 2, 2}, {8, 1, 1, 2, 2}};
//        int[][] matrix = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1} };
//        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        boolean[] obstacles = new boolean[99];

        Graph graph = new Graph(matrix);
        obstacles[graph.getPointId(2, 2)] = true;
        obstacles[graph.getPointId(4, 5)] = true;
        obstacles[graph.getPointId(5, 5)] = true;
        obstacles[graph.getPointId(6, 5)] = true;
        graph.setObstacles(obstacles);

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println("");
        }

//        runDijkstra(graph);
//        runAstar(graph);
        runIDAStar(graph);
    }

    public static void runDijkstra(Graph graph) {

        Statistic statistic = new Statistic();
        /**
         * *********************************************
         */
        Heap heap = new BinaryHeap(graph.getNodeAmount() + 1);
        ShortestPath dijkstra = new Dijkstra(heap);
        Heuristic heuristic = new Manhattan();
//        ShortestPath dijkstra = new Dijkstra(new TernaryHeap(graph.getNodeAmount()+1));
        //prioriteettijono pitää editoida ohjelmassa
        /**
         * *********************************************
         */
        dijkstra.setStatistic(statistic);
        statistic.startClock();
        dijkstra.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        statistic.stopClock();
        dijkstra.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), dijkstra);
        System.out.println("Dijkstra: " + statistic);
        heap.clean();
    }

    public static void runAstar(Graph graph) {

        Statistic statistic = new Statistic();
        /**
         * *********************************************
         */
        Heap heap = new BinaryHeap(graph.getNodeAmount() + 1);
        ShortestPath astar = new Astar(heap);
//        ShortestPath astar = new Astar(new TernaryHeap(graph.getNodeAmount()+1));
        //prioriteettijono pitää editoida ohjelmassa
        /**
         * *********************************************
         */
        astar.setStatistic(statistic);
        Heuristic heuristic = new Manhattan();
        statistic.startClock();
        astar.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        statistic.stopClock();
        astar.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), astar);
        System.out.println("Astar: " + statistic);
        heap.clean();
    }

    public static void runIDAStar(Graph graph) {
        Statistic statistic = new Statistic();
        ShortestPath ida = new IDAStar(); //toistaiseksi rajapinta pois!
        ida.setStatistic(statistic);
        Heuristic heuristic = new Manhattan();
        statistic.startClock();
        ida.findPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), heuristic);
        statistic.stopClock();
        ida.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), ida);
        System.out.println("IDAstar: " + statistic);
    }
}
