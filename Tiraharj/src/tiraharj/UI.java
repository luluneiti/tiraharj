package tiraharj;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import tiraharj.algorithm.Dijkstra;
import tiraharj.algorithm.ShortestPath;
import tiraharj.tools.BinaryHeap;
import tiraharj.tools.Statistic;

public class UI extends JFrame implements ActionListener {

    private JButton distance;
    private JButton run;
    private JLabel start;
    private JLabel goal;
    private JLabel statistic;
    private JTextField startX;
    private JTextField startY;
    private JTextField goalX;
    private JTextField goalY;
    private JRadioButton astar;
    private JRadioButton dijkstra;
    private JPanel grid;
    private ButtonGroup group;
    private JTable table;
    private DefaultTableModel def;

    public void createGUI() {

        this.setLayout(new FlowLayout());
        this.setSize(9000, 9000);
        start = new JLabel("Lähtö:", 10);
        startX = new JTextField("", 3);
        startY = new JTextField("", 3);
        goal = new JLabel("Maali:", 10);
        goalX = new JTextField("", 3);
        goalY = new JTextField("", 3);
        astar = new JRadioButton("A*");
        dijkstra = new JRadioButton("Dijkstra");
        distance = new JButton("Arvo etäisyydet");
        run = new JButton("Etsi lyhin polku");
        statistic = new JLabel("Tilasto: ", 10);
        grid = new JPanel();
        group = new ButtonGroup();
        group.add(dijkstra);
        group.add(astar);
        String col[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Object[] objs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        def = new DefaultTableModel(col, 10);
        table = new JTable(def);
        table.setRowHeight(10);
        table.setSize(10, 10);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //.add(this.add(new Button(" O ")));
            }
        }
        this.add(table);
        this.add(start);
        this.add(startX);
        this.add(startY);
        this.add(goal);
        this.add(goalX);
        this.add(goalY);
        this.add(dijkstra);
        this.add(astar);
        this.add(distance);
        this.add(run);
        this.add(statistic);
        distance.addActionListener(this);
        run.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton kohde = (JButton) e.getSource();

        if (kohde.getText().contains("Arvo")) {

            //vie numeron arpominen testiluokasta arvo luokkaan tools:n alle
            //ei ole tilaa näyttää etäisyyttä -- teksti kun vie kursorin päälle
        }
        if (kohde.getText().contains("Etsi")) {
            
            table.setValueAt(1, 1, 1);
            table.setValueAt(2, 2, 2);
            table.setValueAt(3, 3, 3);
            table.setValueAt(4, 4, 4);
            Component comp = table.findComponentAt(1, 1);
            comp.setForeground(Color.red);

//            boolean[] obstacles = new boolean[99];
//            int[][] matrix = new int[10][10];
//            getTableData(table);
//            Graph graph = new Graph(matrix);
//            obstacles[graph.getPointId(2, 2)] = true;
//            obstacles[graph.getPointId(4, 5)] = true;
//            obstacles[graph.getPointId(5, 5)] = true;
//            obstacles[graph.getPointId(6, 5)] = true;
//            graph.setObstacles(obstacles);
//            Statistic stat = new Statistic();
//
//            if (dijkstra.isSelected()) {
//                /**
//                 * *********************************************
//                 */
//                ShortestPath dijkstra = new Dijkstra(new BinaryHeap(graph.getNodeAmount() + 1));
////              ShortestPath dijkstra = new Dijkstra(new TernaryHeap(graph.getNodeAmount()+1));
//                //prioriteettijono pitää editoida ohjelmassa
//                /**
//                 * *********************************************
//                 */
//                dijkstra.setStatistic(stat);
//                stat.startClock();
//                dijkstra.findPath(graph, new Node(Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText()), 0), new Node(Integer.parseInt(goalX.getText()), Integer.parseInt(goalY.getText()), 0));
//                stat.stopClock();
//                dijkstra.printPath(graph, new Node(1, 2, 0), new Node(3, 3, 0), dijkstra);
//                statistic.setText("Dijkstra: " + stat.toString());
//
//            }
            if (astar.isSelected()) {

            }

            //kun valmis, tulosta grid uudelleen ja polun buttonit punaisiksi
            //tulosta tilasto
           

            this.repaint();
        }
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UI g = new UI();
                g.createGUI();
                g.setTitle("Etsi lyhin polku");
                g.pack();
                g.setVisible(true);
                g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    public int[][] getTableData(JTable table) {
        int[][] matrix = new int[10][10];
        int nRow = def.getRowCount(), nCol = def.getColumnCount();
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                matrix[i][j] = Integer.parseInt(def.getValueAt(i, j).toString());
            }
        }
        return matrix;
    }
}
