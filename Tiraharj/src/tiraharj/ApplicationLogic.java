package tiraharj;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import tiraharj.Graph;
import tiraharj.Location;
import tiraharj.Node;
import tiraharj.algorithm.Astar;
import tiraharj.algorithm.Dijkstra;
import tiraharj.algorithm.Heuristic;
import tiraharj.algorithm.IDAStar;
import tiraharj.algorithm.Manhattan;
import tiraharj.algorithm.ShortestPath;
import tiraharj.tools.BinaryHeap;
import tiraharj.tools.Heap;
import tiraharj.tools.StackO;
import tiraharj.tools.Statistic;
import tiraharj.tools.TernaryHeap;

public class ApplicationLogic {

    private ShortestPath algorithm;
    private Graph graph;
    private UserInterface ui;

    public ApplicationLogic() {

    }

    public String runAlgorithm(int[][] matrix, boolean[] obstacles, String algorithmName, String heuristicName, String heapName, int startX, int startY, int goalX, int goalY) {

        //tarkista matriisi ennen ettei neg arvoja
        graph = new Graph(matrix);
        graph.setObstacles(obstacles);
        Statistic stat = new Statistic();

        Heap heap = createHeap(heapName, graph);
        createAlgorithm(algorithmName, heap, stat, graph, startX, startY, goalX, goalY);

        return stat.toString();
    }

    public void createAlgorithm(String algorithmName, Heap heap, Statistic stat, Graph graph, int startX, int startY, int goalX, int goalY) {
        if (algorithmName.equals("Dijkstra")) {
            algorithm = new Dijkstra(heap);
            runDijkstra(heap, stat, graph, startX, startY, goalX, goalY);
        }
        if (algorithmName.equals("Astar")) {
            algorithm = new Astar(heap);
            runAstar(heap, stat, graph, startX, startY, goalX, goalY);
        }
        if (algorithmName.equals("Idastar")) {
            algorithm = new IDAStar();
            runIDAstar(heap, stat, graph, startX, startY, goalX, goalY);
        }
    }

    public Heap createHeap(String heapName, Graph graph) {
        Heap heap;
        if (heapName == "bheap") {
            heap = new BinaryHeap(graph.getNodeAmount() + 1);
        } else {
            heap = new TernaryHeap(graph.getNodeAmount() + 1);
        }
        return heap;
    }

    private void runDijkstra(Heap heap, Statistic stat, Graph graph, int startX, int startY, int goalX, int goalY) {

        Heuristic heur = new Manhattan();
        algorithm.setStatistic(stat);
        stat.startClock();
        algorithm.findPath(graph, new Node(startX, startY, 0), new Node(goalX, goalY, 0), heur);
        stat.stopClock();
    }

    private void runAstar(Heap heap, Statistic stat, Graph graph, int startX, int startY, int goalX, int goalY) {
        Heuristic heur = new Manhattan();
        algorithm.setStatistic(stat);
        stat.startClock();
        algorithm.findPath(graph, new Node(startX, startY, 0), new Node(goalX, goalY, 0), heur);
        stat.stopClock();
    }

    private void runIDAstar(Heap heap, Statistic stat, Graph graph, int startX, int startY, int goalX, int goalY) {
        Heuristic heur = new Manhattan();
        algorithm.setStatistic(stat);
        stat.startClock();
        algorithm.findPath(graph, new Node(startX, startY, 0), new Node(goalX, goalY, 0), heur);
        stat.stopClock();
    }

    public void setUI(UserInterface ui) {
        this.ui = ui;
    }

    public String showPath(int startX, int startY, int goalX, int goalY) {

        String ret = "";
        if (!algorithm.emptyPath()) {
            StackO stack = algorithm.getPathInStack(this.graph, new Node(1, 2, 0), new Node(6, 6, 0));

            int i = 0;
            String coord = "";

            while (!stack.isEmpty()) {
                coord = graph.getXYByPointId(stack.pop());
                String[] xy = coord.split(",");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);
                if (ret.isEmpty()) {
                    ret = x + ":" + y;
                } else {
                    ret = ret + ", " + x + ":" + y;
                }

                //ui.setRed(x, y);
            }

        }
        return ret;
    }

}
