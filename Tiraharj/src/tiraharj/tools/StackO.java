package tiraharj.tools;

import tiraharj.Node;

public class StackO {

    private int[] data; //voisi olla object täällä ja heap:ssä! 
    private int top;
    private int sizeReservation;

    public StackO(int sizeReservation) {
        top = -1;
        data = new int[sizeReservation];
        this.sizeReservation = sizeReservation;
    }

    public int getFirst() { //testiä varten
        return data[top];
    }

    /**
     * Lisää verkon solmun pinon päälle
     *
     * @return verkon solmu
     */
    public int pop() {

        if (!this.isEmpty()) {
            Integer removed = data[top];
            top--;
            return removed;
        } else {
            return -1;
        }
    }

    /**
     * Poistaa ja palauttaa pinon päällä olevan verkon solmun
     *
     * @param newOne
     */
    public void push(int newOne) {
        if (!isFull()) {
            top++;
            data[top] = newOne;
        }
    }

    /**
     * Palauttaa true/false riippuen siitä onko pino tyhjä
     *
     * @return true/false riippuen siitä onko pino tyhjä
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * true/false riippuen siitä onko pino täysi (taulukko täynnä)
     *
     * @return true/false riippuen siitä onko pino täysi
     */
    public boolean isFull() {
        return top == data.length - 1;
    }

    public void clean() {

    }
}
