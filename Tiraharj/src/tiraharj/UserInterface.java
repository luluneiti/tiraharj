package tiraharj;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tiraharj.tools.RandomNumber;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private JPanel grid;
    private JTable table;
    private DefaultTableModel def;
    private ApplicationLogic logic;
    private static UserInterface g;

    /**
     * Käyttöliittymän pääohjelma
     * @param args
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

        logic.setUI(g);
        JButton kohde = (JButton) e.getSource();

        if (kohde.getText().contains("Arvo")) {

            setDistances();

        }
        if (kohde.getText().contains("Etsi")) {

            this.setRed(Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()));
            this.setRed(Integer.parseInt(goalX.getText()), Integer.parseInt(goalY.getText()));
            startAlgorithm();
            JOptionPane.showMessageDialog(null, logic.showPath(Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()), Integer.parseInt(goalX.getText()), Integer.parseInt(goalY.getText())));
            this.repaint();

        }
    }

    /**
     * Lyhimmän polun merkitsemiseksi ruutu värjätään 
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public void setRed(int x, int y) {

        Component comp = table.findComponentAt(x, y);
        comp.setForeground(Color.red);

    }
    
    private void startAlgorithm() throws NumberFormatException, HeadlessException {

        try {
            boolean[] obstacles = new boolean[table.getWidth() * table.getHeight()];
            int[][] matrix = getTableData(table, def);

            String heapName = "";
            if (bheap.isSelected()) {
                heapName = "bheap";
            } else {
                heapName = "theap";
            }

            String heuristicName = "";
            if (manhattan.isSelected()) {
                heuristicName = "manhattan";
            }

            String algorithmName = "";
            if (dijkstra.isSelected()) {
                algorithmName = "Dijkstra";
                JOptionPane.showMessageDialog(null, algorithmName + ":"
                        + logic.runAlgorithm(matrix, obstacles, algorithmName, heuristicName, heapName, Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()), Integer.parseInt(goalX.getText()), Integer.parseInt(goalY.getText())));

            }
            if (astar.isSelected()) {
                algorithmName = "Astar";
                JOptionPane.showMessageDialog(null, algorithmName + ":"
                        + logic.runAlgorithm(matrix, obstacles, algorithmName, heuristicName, heapName, Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()), Integer.parseInt(goalX.getText()), Integer.parseInt(goalY.getText())));

            }

            if (idastar.isSelected()) {
                algorithmName = "Idastar";
                JOptionPane.showMessageDialog(null, algorithmName + ":"
                        + logic.runAlgorithm(matrix, obstacles, algorithmName, heuristicName, heapName, Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()), Integer.parseInt(goalX.getText()), Integer.parseInt(goalY.getText())));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sovelluksessa on tapahtunut virhe.");
        }

    }

    private void setDistances() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int k = RandomNumber.getNumber(9);
                table.setValueAt(k, i, j);
            }
        }
        this.repaint();
    }

    private int[][] getTableData(JTable table, DefaultTableModel def) {
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

    private void createGUI() {

        logic = new ApplicationLogic();
        this.setLayout(new FlowLayout());
        this.setSize(9000, 9000);
        createComponents();
        table.setRowHeight(20);
        table.setSize(10, 10);
        Integer k = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                table.setValueAt(k, i, j);
            }
        }
        setComponents();
        distance.addActionListener(this);
        run.addActionListener(this);

    }

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
        heap = new ButtonGroup();
        algorithm = new ButtonGroup();
        heuristic = new ButtonGroup();
        algorithm.add(dijkstra);
        algorithm.add(astar);
        algorithm.add(idastar);
        heap.add(bheap);
        heap.add(theap);
        heuristic.add(manhattan);
        String col[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Object[] objs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        def = new DefaultTableModel(col, 10);
        table = new JTable(def);
    }

    private void setComponents() {
        this.add(table);
        this.add(start);
        this.add(startX);
        this.add(startY);
        this.add(goal);
        this.add(goalX);
        this.add(goalY);
        this.add(dijkstra);
        this.add(astar);
        this.add(bheap);
        this.add(theap);
        this.add(manhattan);
        this.add(distance);
        this.add(run);
    }

}
