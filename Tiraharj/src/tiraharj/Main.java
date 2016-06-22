package tiraharj;

import tiraharj.tools.RandomNumber;

/**
 * Pääohjelma, jonka kautta voi myös ajaa algoritmeja.
 *
 * @author Ulla
 */
public class Main {

    private static int startX = 1;
    private static int startY = 2;
    private static int goalX = 19;
    private static int goalY = 10;

    /**
     * Pääohjelma, jonka kautta voi ajaa algoritmeja. 
     * Editoi matrix-matriisi (etäisyydet) tai anna parametrit ja anna ohjelman arpoa numerot. 
     * Editoi obstacles-taulukko (esteet). 
     * Valitse ajettava algoritmi ottamalla se pois kommenteista.
     *
     * @param args
     */
    public static void main(String[] args) {

        //************ETÄISYYDET
//        int[][] matrix
//                = {{2, 8, 4, 6, 3, 7, 6, 8, 9, 1, 7, 6, 8, 9, 1, 5, 5, 6, 1, 1},
//                {2, 8, 4, 6, 3, 7, 6, 8, 9, 1, 7, 6, 8, 9, 1, 2, 8, 4, 6, 3},
//                {2, 8, 4, 6, 3, 7, 6, 8, 9, 1, 7, 6, 8, 9, 1, 2, 8, 4, 6, 3},
//                {7, 6, 8, 9, 1, 8, 1, 1, 2, 2, 5, 5, 6, 1, 1, 8, 1, 1, 2, 2},
//                {2, 8, 4, 6, 3, 7, 6, 8, 9, 1, 7, 6, 8, 9, 1, 2, 8, 4, 6, 3},
//                {7, 6, 8, 9, 1, 5, 5, 6, 1, 1, 2, 8, 4, 6, 3, 7, 6, 8, 9, 1},
//                {7, 6, 8, 9, 1, 8, 1, 1, 2, 2, 5, 5, 6, 1, 1, 8, 1, 1, 2, 2},
//                {7, 6, 8, 9, 1, 8, 1, 1, 2, 2, 5, 5, 6, 1, 1, 8, 1, 1, 2, 2},
//                {2, 8, 4, 6, 3, 7, 6, 8, 9, 1, 7, 6, 8, 9, 1, 2, 8, 4, 6, 3},
//                {2, 8, 4, 6, 3, 7, 6, 8, 9, 1, 7, 6, 8, 9, 1, 2, 8, 4, 6, 3},
//                {5, 5, 6, 1, 1, 8, 1, 1, 2, 2, 7, 6, 8, 9, 1, 8, 1, 1, 2, 2},
//                {7, 6, 8, 9, 1, 8, 1, 1, 2, 2, 5, 5, 6, 1, 1, 8, 1, 1, 2, 2},
//                {2, 8, 4, 6, 3, 7, 6, 8, 9, 1, 7, 6, 8, 9, 1, 2, 8, 4, 6, 3},
//                {7, 6, 8, 9, 1, 2, 8, 4, 6, 3, 2, 8, 4, 6, 3, 7, 6, 8, 9, 1},
//                {2, 8, 4, 6, 3, 5, 5, 6, 1, 1, 2, 8, 4, 6, 3, 8, 1, 1, 2, 2},
//                {2, 8, 4, 6, 3, 8, 1, 1, 2, 2, 2, 8, 4, 6, 3, 8, 1, 1, 2, 2},
//                {7, 6, 8, 9, 1, 8, 1, 1, 2, 2, 5, 5, 6, 1, 1, 8, 1, 1, 2, 2},
//                {2, 8, 4, 6, 3, 7, 6, 8, 9, 1, 7, 6, 8, 9, 1, 2, 8, 4, 6, 3},
//                {5, 5, 6, 1, 1, 8, 1, 1, 2, 2, 2, 8, 4, 2, 8, 4, 6, 3, 6, 3},
//                {2, 8, 4, 2, 8, 4, 6, 3, 6, 3, 2, 8, 4, 2, 8, 4, 6, 3, 6, 3}};
//        int[][] matrix = {{2, 8, 4, 1, 1}, {7, 6, 8, 9, 1}, {5, 5, 1, 1, 1}, {8, 1, 1, 2, 2}, {8, 1, 1, 2, 2}};
//        int[][] matrix = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},{1, 1, 1, 1, 1, 1, 1, 1, 1, 1} };
//        int[][] matrix = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        
        final int MATRIXSIZE = 20;
        final int MAXDISTANCE = 9;
        int[][] matrix = new int[MATRIXSIZE][MATRIXSIZE];

        for (int i = 0; i < MATRIXSIZE; i++) {
            for (int j = 0; j < MATRIXSIZE; j++) {
                matrix[i][j] = RandomNumber.getNumber(MAXDISTANCE);
            }
        }
        ApplicationLogic logic = new ApplicationLogic();
        boolean virhe = false;

        //************ESTEET
        Location[] obstacles = new Location[4];
        obstacles[0] = new Location(1, 1);
        obstacles[0] = new Location(1, 2);
        obstacles[0] = new Location(2, 15);
        obstacles[0] = new Location(19, 19);

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (logic.distanceOk(matrix[i][j])) {
                    System.out.print(" " + matrix[i][j]);
                } else {
                    System.out.println("Tarkista sisältö: " + i + ":" + j);
                    virhe = true;
                }
            }
            System.out.println("");
        }

        if (virhe == false) {

            //************VALISE YKSI ALGORITMI OTTAMALLA SE POIS KOMMENTEISTA
            // 2-keolla
        System.out.println(logic.runAlgorithm(matrix, obstacles, "Dijkstra", "manhattan", "bheap", startX, startY, goalX, goalY));
//            System.out.println(logic.runAlgorithm(matrix, obstacles, "Astar", "manhattan", "bheap", startX, startY, goalX, goalY));
        // IDAstar ei käytä kekoa vaikka bheap parametreissä
//        System.out.println(logic.runAlgorithm(matrix, obstacles, "Idastar", "manhattan", "bheap", startX, startY, goalX, goalY));
            //3-keolla
//        System.out.println(logic.runAlgorithm(matrix, obstacles, "Dijkstra", "manhattan", "theap", startX, startY, goalX, goalY));
//        System.out.println(logic.runAlgorithm(matrix, obstacles, "Astar", "manhattan", "theap", startX, startY, goalX, goalY));

            //************TULOSTAA REITIN
            System.out.println(logic.showPath(startX, startY, goalX, goalY));;
        }
    }

}
