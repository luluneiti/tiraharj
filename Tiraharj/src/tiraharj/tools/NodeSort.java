package tiraharj.tools;

import tiraharj.Node;

public class NodeSort {

    public NodeSort() {

    }

    /**
     * Sorttaa node-taulukon nousevaan järjestykseen
     * @param nodes node-taulukko
     * @return nousevaan järjestykseen järjestetty node-taulukko
     */
    public static Node[] nodeSort(Node[] nodes) {

        for (int i = 0; i < nodes.length - 1; i++) {
            for (int j = i + 1; j < nodes.length; j++) {

                if (nodes[i].compareTo(nodes[j]) == 1) {
                    Node temp = nodes[i];
                    nodes[i] = nodes[j];
                    nodes[j] = temp;
                }
            }
        }
        return nodes;
    }

}
