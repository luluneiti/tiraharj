package tiraharj;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra { //miten estää ettei graph:ssa negatiivisia?

    static PriorityQueue<Node> prioq;
    static boolean[] visited;
    static int[] distance;
    static int[] path;
    static boolean emptyRoute;

    /**
     * Etsii lyhimmän polun verkossa lähtösolmusta maalisolmuun
     *
     * @param graph verkko, josta lyhintä polkua etsitään
     * @param start lähtösolmu, josta etsintä aloitetaan
     * @param goal maalisolmu, johon etsintä päättyy
     */
    public static void findPath(Graph graph, Node start, Node goal) {

        initialize(graph, start);

        prioq.add(start);

        while (!prioq.isEmpty()) {

            Node current = prioq.poll();

            if (graph.getPointId(current.getX(), current.getY()) == graph.getPointId(goal.getX(), goal.getY())) {
                break;
            }

            if (visited[graph.getPointId(current.getX(), current.getY())] == false) {

                visited[graph.getPointId(current.getX(), current.getY())] = true;

                for (Node next : graph.getNeighbors(graph, current)) {

                    if (next != null) {
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
        }

        emptyRoute = (distance[graph.getPointId(goal.getX(), goal.getY())] == Integer.MAX_VALUE);

    }

    private static void initialize(Graph graph, Node start) {
        visited = new boolean[graph.getNodeAmount() + 1];
        prioq = new PriorityQueue();
        distance = new int[graph.getNodeAmount() + 1];
        path = new int[graph.getNodeAmount() + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[graph.getPointId(start.getX(), start.getY())] = 0;
        emptyRoute = false;
    }

    /**
     * Tulostaa etsityn lyhimmän polun lähtösolmusta maalisolmuun
     *
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
