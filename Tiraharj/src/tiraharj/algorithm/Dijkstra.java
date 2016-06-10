package tiraharj.algorithm;

import tiraharj.tools.Statistic;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import tiraharj.Graph;
import tiraharj.tools.BinaryHeap;
import tiraharj.Node;
import tiraharj.tools.Heap;
import tiraharj.tools.StackO;
import tiraharj.tools.TernaryHeap;

public class Dijkstra implements ShortestPath { //miten estää ettei graph:ssa negatiivisia?

    //private PriorityQueue<Node> prioq;
    private Heap prioq;
    private boolean[] visited;
    private int[] distance;
    private int[] path;
    private boolean emptyRoute;
    private Statistic statistic;
    private Heuristic heuristic; // käyttää manhattanin algoritmia naapurisolmujen löytämiseen

    public Dijkstra(Heap heap) {
        this.prioq = heap;
    }

    public Dijkstra(Graph graph) {

//        prioq = new PriorityQueue();
        prioq = new BinaryHeap(graph.getNodeAmount() + 1);
//        prioq = new TernaryHeap(graph.getNodeAmount() + 1);

    }

    /**
     * Etsii lyhimmän polun verkossa lähtösolmusta maalisolmuun
     *
     * @param graph verkko, josta lyhintä polkua etsitään
     * @param start lähtösolmu, josta etsintä aloitetaan
     * @param goal maalisolmu, johon etsintä päättyy
     */
    @Override
    public void findPath(Graph graph, Node start, Node goal) {

        initialize(graph, start);
        prioq.add(start);

        while (!prioq.isEmpty()) {

            Node current = prioq.poll();
            statistic.addCounter();
            //            System.out.println("current: " + current);

            if (graph.getPointId(current.getX(), current.getY()) == graph.getPointId(goal.getX(), goal.getY())) {
                break;
            }

            if (visited[graph.getPointId(current.getX(), current.getY())] == false) {

                visited[graph.getPointId(current.getX(), current.getY())] = true;

                for (Node next : graph.getNeighbors(graph, current, heuristic)) {

                    if (next != null) {
//                        System.out.println(next);

                        if (visited[graph.getPointId(next.getX(), next.getY())] == false) {

                            if (next.getDistance() + current.getDistance() < distance[graph.getPointId(next.getX(), next.getY())]) {
                                distance[graph.getPointId(next.getX(), next.getY())] = next.getDistance() + current.getDistance();
                            }
                            prioq.add(new Node(next.getX(), next.getY(), distance[graph.getPointId(next.getX(), next.getY())]));
                            path[graph.getPointId(next.getX(), next.getY())] = graph.getPointId(current.getX(), current.getY());
                        }
                    }
                }

            }
//            prioq.print();
        }

        emptyRoute = (distance[graph.getPointId(goal.getX(), goal.getY())] == Integer.MAX_VALUE);
    }

    @Override
    public void findPath(Graph graph, Node start, Node goal, Heuristic heuristic) {
        throw new UnsupportedOperationException("Not supported.");
    }

    private void initialize(Graph graph, Node start) {
        visited = new boolean[graph.getNodeAmount() + 1];
        distance = new int[graph.getNodeAmount() + 1];
        path = new int[graph.getNodeAmount() + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[graph.getPointId(start.getX(), start.getY())] = 0;
        emptyRoute = false;
        heuristic = new Manhattan();
    }

    /**
     * Tulostaa etsityn lyhimmän polun lähtösolmusta maalisolmuun
     *
     * @param graph
     * @param start lähtösolmu
     * @param goal maalisolmu
     */
    public void printPath(Graph graph, Node start, Node goal) { //tuplakoodia, vie yhteen paikkaan
 /*
         if (!emptyRoute) {
         Stack<Integer> stack = new Stack();
         int in;
         in = path[graph.getPointId(goal.getX(), goal.getY())];
         while (in != graph.getPointId(start.getX(), start.getY())) {
         stack.push(in);
         in = path[in];
         }
         System.out.println("Shortest route: ");

         while (!stack.isEmpty()) {
         System.out.println(graph.getXYByPointId(stack.pop()));
         }
         } else {
         System.out.println("Verkko ei ollut yhtenäinen ja maalisolmua ei saavutettu");
         }*/
    }

    @Override
    public StackO getPathInStack(Graph graph, Node start, Node goal) {

        StackO stack = new StackO(graph.getNodeAmount() + 1);
        if (!emptyRoute) {
            int in;
            in = path[graph.getPointId(goal.getX(), goal.getY())];
            while (in != graph.getPointId(start.getX(), start.getY())) {
                stack.push(in);
                in = path[in];
            }
        }

        return stack;
    }

    @Override
    public boolean emptyPath() {
        return emptyRoute == true;
    }

    @Override
    public void setStatistic(Statistic stat) {
        this.statistic = stat;
    }

}
