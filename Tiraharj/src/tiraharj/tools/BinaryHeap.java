package tiraharj.tools;

import tiraharj.Node;

public class BinaryHeap implements Heap {

    private Node[] data;
    private int heapSize;
    private final int FIRST = 1;
    private int sizeReservation;

    public BinaryHeap(int sizeReservation) {
        data = new Node[sizeReservation];
        heapSize = 0;
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

        if (right(i) <= heapSize) {
            if (data[left(i)].compareTo(data[right(i)]) == 1) {
                indexOfSmallest = right(i);
            } else {
                indexOfSmallest = left(i);
            }
            if (data[i].compareTo(data[indexOfSmallest]) == 1) {
                swap(i, indexOfSmallest);
                heapify(i);
            }
        } else if (left(i) == heapSize && data[i].compareTo(data[left(i)]) == 1) {
            swap(i, left(i));
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
            data[heapSize] = node;
            int i = heapSize;
            while (i > 1 && data[parent(i)].compareTo(data[i]) == 1) {
                swap(i,parent(i));
                i = parent(i);
            }
            
        }
    }

    @Override
    public int parent(int i) {
        return i / 2;
    }

    @Override
    public int left(int i) {
        return 2 * i;
    }

    @Override
    public int middle(int i) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int right(int i) {
        return 2 * i + 1;
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
