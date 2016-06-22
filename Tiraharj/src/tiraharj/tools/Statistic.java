package tiraharj.tools;

/**
 * Tarjoaa laskurin ja ajanotto palvelun
 *
 * @author Ulla
 */
public class Statistic {

    private long counter;
    private long timeInBegin;
    private long timeInEnd;

    public Statistic() {
        this.counter = 0;
        this.timeInBegin = 0;
        this.timeInEnd = 0;
    }

    /**
     * Palauttaa laskurin arvon. Testiä varten.
     *
     * @return laskurin arvo
     */
    public long getCounter() {
        return counter;
    }

    /**
     * Palauttaa aloitusajankohdan. Testiä varten.
     *
     * @return aloitusajankohta
     */
    public long getTimeInBegin() {
        return timeInBegin;
    }

    /**
     * Palauttaa lopetusajankohdan. Testiä varten.
     *
     * @return lopetusajankohta
     */
    public long getTimeInEnd() {
        return timeInEnd;
    }

    /**
     * Lisää laskuria
     */
    public void addCounter() {
        this.counter++;
    }

    /**
     * Asettaa aloitusajankohdan
     */
    public void startClock() {
        this.timeInBegin = System.currentTimeMillis();
    }

    /**
     * Asettaa lopetusajankohdan
     */
    public void stopClock() {
        this.timeInEnd = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Operaatioon kului aikaa: " + (this.timeInEnd - this.timeInBegin) + " ms ja käsiteltyjen solmujen lkm: " + this.counter;
    }

}
