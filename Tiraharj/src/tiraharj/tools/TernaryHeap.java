package tiraharj.tools;

import tiraharj.Node;

public class TernaryHeap implements Heap {

    private Node[] data;
    private int heapSize;
    private final int FIRST = 1;
    private int amountOfChildren;
    private int sizeReservation;

    public TernaryHeap(int sizeReservation) {
        this.data = new Node[sizeReservation];
        this.heapSize = 0;
        this.amountOfChildren = 3;
        this.sizeReservation = sizeReservation;
    }

    @Override
    public Node getData(int i) {
        return data[i];
    }

    /**
     * Asettaa keon taulukkoon alkion annettuun indeksiin (vain testiä varten)
     *
     * @param node talletettava alkio
     * @param i indeksi, johon alkio talletetaan keon taulukossa
     */
    public void setData(Node node, int i) {
        this.data[i] = node;
    }

    @Override
    public int getHeapSize() {
        return this.heapSize;
    }

    @Override
    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    @Override
    public Node poll() {

        if (!isEmpty()) {
            Node smallest = data[FIRST];
            data[FIRST] = data[heapSize];
            heapSize--;
            heapify(FIRST);
            return smallest;
        } else {
            return null;
        }
    }

    /**
     * Korjaa keon kekoehtojen mukaiseksi
     *
     * @param i keon taulukon indeksi kertoo, tarkistettavan keon alkion
     * sijainnin
     */
    public void heapify(int i) {

        int indexOfSmallest = 0;

        if (left(i) <= heapSize) {
            indexOfSmallest = left(i);

            if (middle(i) <= heapSize && data[indexOfSmallest].compareTo(data[middle(i)]) == 1) {
                indexOfSmallest = middle(i);
            }
            if (right(i) <= heapSize && data[indexOfSmallest].compareTo(data[right(i)]) == 1) {
                indexOfSmallest = right(i);
            }
            if (data[i].compareTo(data[indexOfSmallest]) == -1) {
                heapify(indexOfSmallest);
            }
        }
    }

    /**
     * Vaihtaa parametrinä annettujen alkioiden paikkaa keon taulukkossa
     *
     * @param i keon taulukon indeksi, jossa sijaitseva alkio, siirretään j
     * indeksin kohtaan
     * @param j keon taulukon indeksi, jossa sijaitseva alkio, siirretään i
     * indeksin kohtaan
     */
    public void swap(int i, int j) {
        Node swap = data[i];
        data[i] = data[j];
        data[j] = swap;
    }

    @Override
    public boolean isFull() {
        return heapSize == data.length - 1;
    }

    @Override
    public void add(Node node) {

        if (!isFull()) {
            heapSize++;
            int i = heapSize;
            while (i > 1 && data[parent(i)].compareTo(node) == 1) {
                data[i] = data[parent(i)];
                i = parent(i);
            }
            data[i] = node;
        }
    }

    @Override
    public int parent(int i) {
        return (i + 1) / this.amountOfChildren;
    }

    @Override
    public int left(int i) {
        return this.amountOfChildren * i - 1;
    }

    public int middle(int i) {
        return this.amountOfChildren * i;
    }

    @Override
    public int right(int i) {
        return this.amountOfChildren * i + 1;
    }

    @Override
    public void print() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                System.out.println(i + " , " + data[i].toString());
            } else {
                System.out.println("null");
            }
        }
    }

    @Override
    public void clean() {
        this.data = new Node[this.sizeReservation];
        this.heapSize = 0;
    }
}
