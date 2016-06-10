package tiraharj.algorithm;

import java.util.Arrays;
import java.util.Stack;
import tiraharj.Graph;
import tiraharj.Node;
import tiraharj.tools.StackO;
import tiraharj.tools.Statistic;

public class IDAStar implements ShortestPath {

    private boolean emptyRoute;
    private Statistic statistic;
    private Heuristic heuristic;
    private int[] path;

    @Override
    public void setStatistic(Statistic stat) {
        statistic = stat;
    }

    /**
     * Etsii lyhimmän polun verkossa lähtösolmusta maalisolmuun
     *
     * @param graph verkko, josta lyhintä polkua etsitään
     * @param start lähtösolmu, josta etsintä aloitetaan
     * @param goal maalisolmu, johon etsintä päättyy
     * @param heur
     */
    @Override
    public void findPath(Graph graph, Node start, Node goal, Heuristic heur) {

        path = new int[graph.getNodeAmount() + 1];
        this.heuristic = heur;
        Integer bound = heuristic.getToEnd(start, goal);
        System.out.println("bound: " + bound);
        Integer t = 0;

        while (t != -1) {
            statistic.addCounter();
            t = search(start, 0, bound, goal, graph);
            System.out.println("t1: " + t);
            if (t == -1) {
                emptyRoute = false;
                break;
            }
            if (t == Integer.MAX_VALUE) {
                emptyRoute = true;
            }
            bound = t;
        }
    }

    private Integer search(Node current, Integer g, Integer bound, Node goal, Graph graph) {

        Integer f = g + this.heuristic.getToEnd(current, goal);
        System.out.println("f2: " + f);
        if (f > bound) {
            return f;
        }
        if (graph.getPointId(current.getX(), current.getY()) == graph.getPointId(goal.getX(), goal.getY())) {
            return -1;
        }
        Integer min = Integer.MAX_VALUE;
        Node[] nexts = graph.getNeighbors(graph, current, heuristic);

        for (Node next : nexts) {
            if (next != null) {
                statistic.addCounter();
                System.out.println("next: " + next);
                Integer t = search(next, g + next.getDistance(), bound, goal, graph);
                System.out.println("t2: " + t);
                path[graph.getPointId(next.getX(), next.getY())] = graph.getPointId(current.getX(), current.getY());
                if (t == -1) {
                    return -1;
                }
                if (t < min) {
                    min = t;
                }

            }
        }
        return min;
    }

    @Override
    public void findPath(Graph graph, Node start, Node goal) {
        throw new UnsupportedOperationException("Not supported.");
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
        return this.emptyRoute == true;
    }

    public void print(Graph graph, Node start, Node goal) {
        for (int i = 0; i < path.length; i++) {
            System.out.println(i + ":" + path[i]);
        }
    }
}
