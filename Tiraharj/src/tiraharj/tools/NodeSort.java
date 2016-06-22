package tiraharj.tools;

import tiraharj.Node;

/**
 * Käytetään solmujen järjestämiseen
 * @author Ulla
 */
public class NodeSort {

    public NodeSort() {

    }

    /**
     * Järjestää node-olioita sisältävän taulukon nousevaan järjestykseen Noden compareTo-metodin mukaisesti
     * @param nodes node olio-taulukko
     * @return nousevaan järjestykseen järjestetty node-taulukko
     */
    public static Node[] sort(Node[] nodes) {

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
