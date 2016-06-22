package tiraharj.tools;

import tiraharj.Node;

/**
 * Kuvaa keon tarjoamat palvelut
 * @author Ulla
 */
public interface Heap {

    /**
     * Kertoo onko keko tyhjä
     *
     * @return true: tyhjä, false: ei tyhjä
     */
    public boolean isEmpty();

    /**
     * Kertoo onko keko täynnä
     *
     * @return true: täynnä, false: ei täynnä
     */
    public boolean isFull();

    /**
     * Palauttaa keon koon
     *
     * @return keon koko
     */
    public int getHeapSize();

    /**
     * Palauttaa i indeksin kohdassa olevan alkion vanhemman indeksin
     *
     * @param i kenen vanhempaa etsitään
     * @return vanhemman indeksi
     */
    public int parent(int i);

    /**
     * Palauttaa i indeksin kohdassa olevan alkion vasemman lapsen indeksin
     *
     * @param i kenen vasenta lasta etsitään
     * @return vasemman lapsen indeksi
     */
    public int left(int i);

    /**
     * Palauttaa i indeksin kohdassa olevan alkion keskimmäiset lapsen indeksin (3-keko)
     *
     * @param i kenen keskimmäistä lasta etsitään
     * @return keskimmäisen lapsen indeksi
     */
    public int middle(int i);

    /**
     * Palauttaa i indeksin kohdassa olevan alkion oikean lapsen indeksin
     *
     * @param i kenen oikeaa lasta etsitään
     * @return oikean lapsen indeksi
     */
    public int right(int i);

    /**
     * Poistaa minimikeosta pienimmän alkion
     *
     * @return poistettavan pienimmän alkion
     */
    public Node poll();

    /**
     * Lisää parametrina annetun alkion kekoon
     *
     * @param node alkio lisätään kekoon
     */
    public void add(Node node);

    /**
     * Tulostaa keon sisällön
     */
    public void print();

    /**
     * Tyhjää keon
     */
    public void clean();

    /**
     * Palauttaa keon taulukon indeksissä olevan alkion
     *
     * @param i indeksi
     * @return keon taulukon indeksissä olevan alkion
     */
    public Node getData(int i);

}
