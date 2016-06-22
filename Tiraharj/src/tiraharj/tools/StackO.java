package tiraharj.tools;

import tiraharj.Node;

/**
 * Pino tietorakenne
 * @author Ulla
 */
public class StackO {

    private int[] data; 
    private int top;
    private int sizeReservation;

    /**
     * Konstruktori pinon luomiseksi. Luokka ei sisällä metodia tilavarauksen kasvattamiseksi.
     * @param sizeReservation tilavaatimus
     */
    public StackO(int sizeReservation) {
        top = -1;
        data = new int[sizeReservation];
        this.sizeReservation = sizeReservation;
    }

    /**
     * Palauttaa pinon päällä olevan, mutta ei poista sitä
     * @return pinon päällimmmäinen
     */
    public int getFirst() { //testiä varten
        return data[top];
    }

    /**
     * Poistaa ja palauttaa pinon päällä olevan verkon solmun
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
     * Lisää uuden luvun pinon päälle
     *
     * @param newOne lisättävä luku
     */
    public void push(int newOne) {
        if (!isFull()) {
            top++;
            data[top] = newOne;
        }
    }

    /**
     * Kertoo onko pino tyhjä
     *
     * @return true: tyhjä, false: ei tyhjä 
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Kertoo onko pino täysi (taulukko täynnä)
     *
     * @return true: täynnä, false: ei täynnä
     */
    public boolean isFull() {
        return top == data.length - 1;
    }

}
