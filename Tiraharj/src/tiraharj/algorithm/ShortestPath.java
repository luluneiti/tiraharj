package tiraharj.algorithm;

import java.util.Stack;
import tiraharj.Graph;
import tiraharj.Node;
import tiraharj.tools.StackO;
import tiraharj.tools.Statistic;

public interface ShortestPath {

    public abstract void setStatistic(Statistic stat);

    public abstract void findPath(Graph graph, Node start, Node goal);

    public abstract void findPath(Graph graph, Node start, Node goal, Heuristic heuristic);

    public abstract StackO getPathInStack(Graph graph, Node start, Node goal);

    public abstract boolean emptyPath();

    public default void printPath(Graph graph, Node start, Node goal, ShortestPath algortihm) {

        if (!algortihm.emptyPath()) {
            StackO stack = algortihm.getPathInStack(graph, start, goal);
            System.out.println("Shortest route: ");

            while (!stack.isEmpty()) {
                System.out.println(graph.getXYByPointId(stack.pop()));
            }
        } else {
            System.out.println("Verkko ei ollut yhtenäinen ja maalisolmua ei saavutettu");
        }
    }
}
