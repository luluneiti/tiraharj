package tiraharj.tools;

import tiraharj.Node;

public class TernaryHeap implements Heap { // kokeile toimiiko!!!!

    private Node[] data;
    private int heapSize;
    private final int FIRST = 1;
    private int amountOfChildren;
    private int sizeReservation; 

    public TernaryHeap(int sizeReservation) {
        this.data = new Node[sizeReservation];
        this.heapSize = 0;
        this.amountOfChildren = 3;
        this.sizeReservation=sizeReservation;
    }

    /**
     * Palauttaa keon taulukon indeksissä olevan alkion (vain testiä varten)
     *
     * @param i indeksi
     * @return keon taulukon indeksissä olevan alkion
     */
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

    /**
     * Palauttaa keon koon (vain testiä varten)
     *
     * @return keon koko
     */
    public int getHeapSize() {
        return this.heapSize;
    }

    /**
     * Palauttaa true/false riippuen siitä onko keko tyhjä
     *
     * @return onko keko tyhjä
     */
    @Override
    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    /**
     * Poistaa minimikeosta pienimmän alkion
     *
     * @return poistettavan pienimmän alkion
     */
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

        int smallest = 0;

        if (right(i) <= heapSize) {
            if (data[left(i)].compareTo(data[right(i)]) == 1 ) {//&& data[middle(i)].compareTo(data[right(i)]) == 1) {
                smallest = right(i);
            }
            if (data[left(i)].compareTo(data[middle(i)]) == 1) { //&& data[right(i)].compareTo(data[middle(i)]) == 1) {
                smallest = middle(i);
            }
            else {
            //if (data[right(i)].compareTo(data[left(i)]) == 1 && data[middle(i)].compareTo(data[left(i)]) == 1) {
                smallest = left(i);
            }
            if (data[i].compareTo(data[smallest]) == 1) {
                swap(i, smallest);
            }
        } else if (left(i) == heapSize && data[i].compareTo(data[left(i)]) == 1) {
            swap(i, left(i));
        }
        else if (middle(i) == heapSize && data[i].compareTo(data[middle(i)]) == 1) {
            swap(i, middle(i));
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

    public boolean isFull() {
        return heapSize == data.length;
    }

    /**
     * Lisää parametrina annetun alkion kekoon
     *
     * @param node alkio lisätään kekoon
     */
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

    /**
     * Palauttaa i indeksin kohdassa olevan alkion vanhemman indeksin
     *
     * @param i kenen vanhempaa etsitään
     * @return vanhemman indeksi
     */
    public int parent(int i) {
//        if (i / this.amountOfChildren == 0) {
//            return i / this.amountOfChildren + 1;
//        } else {
        return (i + 1) / this.amountOfChildren;
//        }
    }

    /**
     * Palauttaa i indeksin kohdassa olevan alkion vasemman lapsen indeksin
     *
     * @param i kenen vasenta lasta etsitään
     * @return vasemman lapsen indeksi
     */
    public int left(int i) {
        return this.amountOfChildren * i - 1;
    }

    /**
     * Palauttaa i indeksin kohdassa olevan alkion keskimmäiset lapsen indeksin
     *
     * @param i
     * @return
     */
    public int middle(int i) {
        return this.amountOfChildren * i;
    }

    /**
     * Palauttaa i indeksin kohdassa olevan alkion oikean lapsen indeksin
     *
     * @param i kenen oikeaa lasta etsitään
     * @return oikean lapsen indeksi
     */
    public int right(int i) {
        return this.amountOfChildren * i + 1;
    }

    public void print() {
        for (int i = 1; i < data.length; i++) {
            if (data[i] != null) {
                System.out.println(i + " , " + data[i].toString());
            }
        }
    }

    @Override
    public void clean() {
        this.data = new Node[this.sizeReservation];
        this.heapSize = 0;
   }
}
