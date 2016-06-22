package tiraharj;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tiraharj.tools.RandomNumber;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * Graafinen käyttöliittymä, jonka kautta voi ajaa eri algoritmeja
 *
 * @author Ulla
 */
public class UserInterface extends JFrame implements ActionListener {

    private JButton distance;
    private JButton run;
    private JLabel start;
    private JLabel goal;
    private JTextField startX;
    private JTextField startY;
    private JTextField goalX;
    private JTextField goalY;
    private JRadioButton astar;
    private JRadioButton dijkstra;
    private JRadioButton idastar;
    private ButtonGroup algorithm;
    private JRadioButton theap;
    private JRadioButton bheap;
    private ButtonGroup heap;
    private JRadioButton manhattan;
    private ButtonGroup heuristic;
    private JLabel obst;
    private JTextField obst1X;
    private JTextField obst1Y;
    private JTextField obst2X;
    private JTextField obst2Y;
    private JTextField obst3X;
    private JTextField obst3Y;
    private JPanel grid;
    private JPanel search;
    private JTable table;
    private DefaultTableModel def;
    private ApplicationLogic logic;
    private static UserInterface g;
    private final int TABLESIZE = 10; //käyttöliittymän taulukon koko

    /**
     * Käyttöliittymän pääohjelma
     *
     * @param args args
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                g = new UserInterface();
                g.createGUI();
                g.setTitle("Etsi lyhin polku");
                g.pack();
                g.setVisible(true);
                g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton kohde = (JButton) e.getSource();

        if (kohde.getText().contains("Arvo")) {
            setDistances();
        }
        if (kohde.getText().contains("Etsi")) {

            if (coordinatesOK()) {
                startAlgorithm();
                JOptionPane.showMessageDialog(null, logic.showPath(Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()), Integer.parseInt(goalX.getText()), Integer.parseInt(goalY.getText())));
                this.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Tarkista lähtö- ja maalikoordinaatit sekä esteiden koordinaatit.");
            }

        }
    }

    private boolean coordinatesOK() {
        if (startX.getText().isEmpty() || startY.getText().isEmpty() || goalX.getText().isEmpty() || goalY.getText().isEmpty()) {
            return false;
        }
        if (obst1X.getText().equals(startX.getText()) && obst1Y.getText().equals(startY.getText())) {
            return false;
        }
        if (obst2X.getText().equals(startX.getText()) && obst2Y.getText().equals(startY.getText())) {
            return false;
        }
        if (obst3X.getText().equals(startX.getText()) && obst3Y.getText().equals(startY.getText())) {
            return false;
        }
        if (obst1X.getText().equals(goalX.getText()) && obst1Y.getText().equals(goalY.getText())) {
            return false;
        }
        if (obst2X.getText().equals(goalX.getText()) && obst2Y.getText().equals(goalY.getText())) {
            return false;
        }
        if (obst3X.getText().equals(goalX.getText()) && obst3Y.getText().equals(goalY.getText())) {
            return false;
        }
        return true;
    }

    /**
     * Alustaa algoritmin ajamiseen liittyvät muuttujat/oliot ja käynnistää
     * algoritmin
     *
     */
    private void startAlgorithm() throws NumberFormatException, HeadlessException {

        try {
            Location[] location = getObstacle();

            int[][] matrix = getMatrix(table, def);

            String heapName = "";
            if (bheap.isSelected()) {
                heapName = "bheap";
            } else {
                heapName = "theap";
            }

            String heuristicName = "manhattan";

            String algorithmName = "";
            if (dijkstra.isSelected()) {
                algorithmName = "Dijkstra";
            }
            if (astar.isSelected()) {
                algorithmName = "Astar";
            }
            if (idastar.isSelected()) {
                algorithmName = "Idastar";
            }

            JOptionPane.showMessageDialog(null, algorithmName + ":"
                    + logic.runAlgorithm(matrix, location, algorithmName, heuristicName, heapName, Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()), Integer.parseInt(goalX.getText()), Integer.parseInt(goalY.getText())));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sovelluksessa on tapahtunut virhe: " + e.getMessage());
        }
    }

    private Location[] getObstacle() throws NumberFormatException {
        Location[] location = new Location[3];
        int i = 0;
        if (!obst1X.getText().isEmpty() && !obst1Y.getText().isEmpty()) {
            location[i] = new Location(Integer.parseInt(obst1X.getText()), Integer.parseInt(obst1Y.getText()));
            i++;
        }
        if (!obst2X.getText().isEmpty() && !obst2Y.getText().isEmpty()) {
            location[i] = new Location(Integer.parseInt(obst2X.getText()), Integer.parseInt(obst2Y.getText()));
            i++;
        }
        if (!obst3X.getText().isEmpty() && !obst3Y.getText().isEmpty()) {
            location[i] = new Location(Integer.parseInt(obst3X.getText()), Integer.parseInt(obst3Y.getText()));
        }
        return location;
    }

    /**
     * Asettaa arvotut etäisyydet taulukkoon
     *
     */
    private void setDistances() {
        int nRow = def.getRowCount(), nCol = def.getColumnCount();
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                int k = RandomNumber.getNumber(9);
                table.setValueAt(k, i, j);
            }
        }
        this.repaint();
    }

    /**
     * Siirtää tiedot Jtablesta matriisiin
     *
     */
    private int[][] getMatrix(JTable table, DefaultTableModel def) {
        int nRow = def.getRowCount(), nCol = def.getColumnCount();
        int[][] matrix = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (logic.distanceOk((int) def.getValueAt(i, j))) {
                    matrix[i][j] = (int) def.getValueAt(i, j);
                } else {
                    JOptionPane.showMessageDialog(null, "Etäisyys ei ole validi kohteessa: " + i + ":" + j);
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    /**
     * Luo käyttöliittymän, sovelluslogiikka olion ja taulukon sekä asettaa
     * tapahtumankuuntelijan buttoneihin
     *
     */
    private void createGUI() {
        this.setLayout(new FlowLayout());
        this.setSize(10000, 20000);
        createComponents();
        table.setRowHeight(20);
        table.setSize(TABLESIZE, TABLESIZE);
        Integer k = 1;
        int nRow = def.getRowCount(), nCol = def.getColumnCount();
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                table.setValueAt(k, i, j);
            }
        }
        setComponents();
        logic = new ApplicationLogic();
        distance.addActionListener(this);
        run.addActionListener(this);

    }

    /**
     * Luo käyttöliittymäkomponentit
     *
     */
    private void createComponents() {
        start = new JLabel("Lähtö:", 10);
        startX = new JTextField("", 3);
        startY = new JTextField("", 3);
        goal = new JLabel("Maali:", 10);
        goalX = new JTextField("", 3);
        goalY = new JTextField("", 3);
        astar = new JRadioButton("A*");
        dijkstra = new JRadioButton("Dijkstra");
        idastar = new JRadioButton("IDA*");
        dijkstra.setSelected(true);
        theap = new JRadioButton("3-keko");
        bheap = new JRadioButton("2-keko");
        bheap.setSelected(true);
        manhattan = new JRadioButton("Manhattan");
        manhattan.setSelected(true);
        distance = new JButton("Arvo etäisyydet");
        run = new JButton("Etsi lyhin polku");
        grid = new JPanel();
        search = new JPanel();
        heap = new ButtonGroup();
        algorithm = new ButtonGroup();
        heuristic = new ButtonGroup();
        algorithm.add(dijkstra);
        algorithm.add(astar);
        algorithm.add(idastar);
        obst = new JLabel("Anna esteet:", 10);
        obst1X = new JTextField("", 3);
        obst1Y = new JTextField("", 3);
        obst2X = new JTextField("", 3);
        obst2Y = new JTextField("", 3);
        obst3X = new JTextField("", 3);
        obst3Y = new JTextField("", 3);
        heap.add(bheap);
        heap.add(theap);
        heuristic.add(manhattan);
        String col[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        def = new DefaultTableModel(col, 10);
        table = new JTable(def);
    }

    /**
     * Asettaa käyttöliittymäkomponentit näytölle
     *
     */
    private void setComponents() {
        grid.add(table);
        search.add(start);
        search.add(startX);
        search.add(startY);
        search.add(goal);
        search.add(goalX);
        search.add(goalY);
        search.add(dijkstra);
        search.add(astar);
        search.add(idastar);
        search.add(bheap);
        search.add(theap);
        search.add(manhattan);
        search.add(distance);
        search.add(obst);
        search.add(obst1X);
        search.add(obst1Y);
        search.add(obst2X);
        search.add(obst2Y);
        search.add(obst3X);
        search.add(obst3Y);
        search.add(run);
        this.add(search, BorderLayout.PAGE_START);
        this.add(grid, BorderLayout.PAGE_END);

    }

}
