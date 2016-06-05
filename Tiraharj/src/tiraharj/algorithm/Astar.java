package tiraharj.algorithm;

import tiraharj.tools.Statistic;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import tiraharj.Graph;
import tiraharj.tools.Heap;
import tiraharj.Node;

public class Astar {

    //static PriorityQueue<Node> prioq;
    private static Heap prioq;
    private static boolean[] visited;
    private static int[] toStart;
    private static int[] path;
    private static boolean emptyRoute;
    private static Statistic statistic;

    public static void setStatistic(Statistic stat) {
        statistic = stat;
    }

    /**
     * Etsii lyhinnän polun verkossa lähtösolmusta maalisolmuun
     *
     * @param graph verkko, josta lyhintä polkua etsitään
     * @param start lähtösolmu, josta etsintä aloitetaan
     * @param goal maalisolmu, johon etsintä päättyy
     */
    public static void findPath(Graph graph, Node start, Node goal, Heuristic heuristic) {

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

                for (Node next : graph.getNeighbors(graph, current)) {

                    if (next != null) {
                        if (visited[graph.getPointId(next.getX(), next.getY())] == false) {

                            if (toStart[graph.getPointId(next.getX(), next.getY())] > toStart[graph.getPointId(next.getX(), next.getY())] + next.getDistance()) {
                                toStart[graph.getPointId(next.getX(), next.getY())] = toStart[graph.getPointId(next.getX(), next.getY())] + next.getDistance();
                            }
                            prioq.add(new Node(next.getX(), next.getY(), toStart[graph.getPointId(next.getX(), next.getY())] + heuristic.getToEnd(next, goal)));
                            path[graph.getPointId(next.getX(), next.getY())] = graph.getPointId(current.getX(), current.getY());
                        }
                    }
                }

            }
        }
        emptyRoute = (toStart[graph.getPointId(goal.getX(), goal.getY())] == Integer.MAX_VALUE);
    }

    public static void initialize(Graph graph, Node start) {
        //prioq = new PriorityQueue();
        prioq = new Heap(graph.getNodeAmount() + 1);
        visited = new boolean[graph.getNodeAmount() + 1];
        toStart = new int[graph.getNodeAmount() + 1];
        path = new int[graph.getNodeAmount() + 1];
        emptyRoute = false;
        //init
        Arrays.fill(toStart, Integer.MAX_VALUE);
        toStart[graph.getPointId(start.getX(), start.getY())] = 0;
    }

    /**
     * Tulostaa etsityn lyhimmän polun lähtösolmusta maalisolmuun
     *
     * @param graph verkko
     * @param start lähtösolmu
     * @param goal maalisolmu
     */
    public static void printPath(Graph graph, Node start, Node goal) { //tuplakoodia, vie yhteen paikkaan

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
        }
    }

    public static Stack<Integer> getPathInStack(Graph graph, Node start, Node goal) {

        Stack<Integer> stack = new Stack();
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
}
