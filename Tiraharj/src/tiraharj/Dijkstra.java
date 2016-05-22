package tiraharj;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra {

    static PriorityQueue<Node> prioq;
    static boolean[] visited;
    static int[] distance;
    static int[] route;
    static boolean emptyR;

    public static void findRoute(Graph graph, Node start, Node end) {

        visited = new boolean[graph.getNodeAmount() + 1];
        prioq = new PriorityQueue();
        distance = new int[graph.getNodeAmount() + 1];
        route = new int[graph.getNodeAmount() + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start.getValue()] = 0;
        emptyR = false;

        prioq.add(start);

        while (!prioq.isEmpty()) {

            Node current = prioq.poll();

            if (current.getValue() == end.getValue()) {
                break;
            }

            if (visited[current.getValue()] == false) {
                
                visited[current.getValue()] = true;
                
                for (Node next : graph.getNeighbors(graph, current)) {

                    if (visited[next.getValue()] == false) {

                        if (next.getDistance() + current.getDistance() < distance[next.getValue()]) {
                            distance[next.getValue()] = next.getDistance() + current.getDistance();
                        }
                        prioq.add(new Node(next.getX(), next.getY(), distance[next.getValue()]));
                        route[next.getValue()] = current.getValue();
                    }

                }
            }

        }
        emptyR = (distance[end.getValue()] == Integer.MAX_VALUE);

    }

  
    public static void printRoute(int start, int end) {

        if (!emptyR) {
            Stack<Integer> stack = new Stack();
            int in;
            in = route[end];
            while (in != start) {
                stack.push(in);
                in = route[in];
            }
            System.out.println("Shortest route: ");

            while (!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
        } else {
            System.out.println("Verkko ei ollut yhtenäinen ja loppusolmu ei saavutettu");
        }
    }

}
