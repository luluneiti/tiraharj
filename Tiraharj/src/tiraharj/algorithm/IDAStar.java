package tiraharj.algorithm;

import java.util.Arrays;
import java.util.Stack;
import tiraharj.Graph;
import tiraharj.Node;
import tiraharj.tools.NodeSort;
import tiraharj.tools.StackO;
import tiraharj.tools.Statistic;

public class IDAStar implements ShortestPath {

    private boolean emptyRoute;
    private Statistic statistic;
    private Heuristic heuristic;
    private int[] path;
    private boolean[] visited;

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
     * @param heuristic
     */
    @Override
    public void findPath(Graph graph, Node start, Node goal, Heuristic heuristic) {

        path = new int[graph.getNodeAmount() + 1];
        this.heuristic = heuristic;
        Integer t = 0;

        //asetetaan maxsyvyys
        Integer bound = heuristic.getToEnd(start, goal);

        while (t != -1) {
            statistic.addCounter();
            visited = new boolean[graph.getNodeAmount() + 1]; //nopeuttaa algoritmia
            t = search(start, 0, bound, goal, graph);

            if (t == -1) {
                emptyRoute = false;
            }
            if (t == Integer.MAX_VALUE) {
                emptyRoute = true;
            }
            bound = t; //muutetaan maxsyvyyttä
        }

    }

    private Integer search(Node current, Integer g, Integer bound, Node goal, Graph graph) {

        visited[graph.getPointId(current.getX(), current.getY())] = true;

        Integer f = g + this.heuristic.getToEnd(current, goal);

        if (f > bound) {
            return f;
        }
        if (this.heuristic.getToEnd(current, goal) == 0) {
            return -1;
        }

        Integer min = Integer.MAX_VALUE;
        Node[] nextsorg = graph.getNeighbors(graph, current, heuristic);
        //Arrays.sort(nexts); 
        Node[] nexts = NodeSort.sort(nextsorg); //nopeuttaa algoritmia

        for (Node next : nexts) {
            if (next != null) { ///jos esteitä solmun ympärillä, ei naapureita
                if (visited[graph.getPointId(next.getX(), next.getY())] == false) {
                    statistic.addCounter();
                    Integer t = search(next, g + next.getDistance(), bound, goal, graph);
                    path[graph.getPointId(next.getX(), next.getY())] = graph.getPointId(current.getX(), current.getY());

                    if (t == -1) {
                        return -1;
                    }
                    if (t < min) {
                        min = t;
                    }
                }
            }
        }

        return min;
    }

    @Override
    public StackO getPathInStack(Graph graph, Node start, Node goal
    ) {
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
}
