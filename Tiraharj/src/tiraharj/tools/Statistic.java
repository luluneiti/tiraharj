package tiraharj.tools;

public class Statistic {

    private long operCounter;
    private long timeInBegin;
    private long timeInEnd;

    public Statistic() {
        this.operCounter = 0;
        this.timeInBegin = 0;
        this.timeInEnd = 0;
    }

    /**
     * Lisää laskuria
     */
    public void addCounter() {
        this.operCounter++;
    }

    /**
     * Asettaa aloitusajan
     */
    public void startClock() {
        this.timeInBegin = System.currentTimeMillis();
    }

    /**
     * Asettaa loppuajan
     */
    public void stopClock() {
        this.timeInEnd = System.currentTimeMillis();
    }

    public String toString() {
        return "Operaatioon kului aikaa: " + (this.timeInEnd - this.timeInBegin) + " ms ja käsiteltyjen solmujen lkm: " + this.operCounter;
    }

}
