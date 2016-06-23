package tiraharj;

import java.util.Scanner;
import tiraharj.tools.RandomNumber;

/**
 * Pääohjelma, jonka kautta voi myös ajaa algoritmeja. 
 * Pääohjelma kysyy käyttäjältä tarvittavat tiedot. Matriisin kokoa ei ole rajoitettu.
 *
 * @author Ulla
 */
public class Main {

    private static int matrixSize;
    private static int[][] matrix;
    private static Location[] obstacles;
    private static int maxdistance;
    private static int startX;
    private static int startY;
    private static int goalX;
    private static int goalY;
    private static ApplicationLogic logic;
    private static boolean errorStatus;
    private static Scanner lukija = new Scanner(System.in);
    private static int algorithm;
    private static int heap;

    /**
     * Pääohjelma, jonka kautta voi ajaa algoritmeja.
     *
     * @param args
     */
    public static void main(String[] args) {

        errorStatus = false;
        logic = new ApplicationLogic();
        showMenu();

        checkMatrix();

        if (errorStatus == false) {
            runAlgoritm();
        }
    }

    private static void showMenu() {

        try {
            askAlgorithm();
            if (algorithm == 1 || algorithm == 2) {
                askHeap();
            }
            askMatrixRelatedIssues();
            askStartAndGoal();

            System.out.println("Haluatko syöttää esteitä (K=kyllä, E=ei)? ");
            String vastaus = lukija.nextLine();
            if (vastaus.equals("K") || vastaus.equals("k")) {
                askObstacles();
            }
            if (vastaus.equals("E") || vastaus.equals("e")) {
                obstacles = new Location[1];
            }
        } catch (Exception e) {
            System.out.println("Sovelluksessa on tapahtunut virhe: " + e.getMessage());
        }

    }

    private static void askAlgorithm() throws NumberFormatException {
        boolean ok = false;
        while (!ok) {
            System.out.println("Minkä algoritmin haluat ajaa? (1 = Dijkstra, 2 = A*, 3 = IDA*");
            algorithm = Integer.parseInt(lukija.nextLine());
            if (algorithm == 1 || algorithm == 2 || algorithm == 3) {
                ok = true;
            }
        }

    }

    private static void askHeap() throws NumberFormatException {
        boolean ok = false;
        while (!ok) {
            System.out.println("Kummalla kekototeuksella? (1 = 2-heap, 2 = 3-heap");
            heap = Integer.parseInt(lukija.nextLine());
            if (heap == 1 || heap == 2) {
                ok = true;
            }
        }
    }

    private static void askMatrixRelatedIssues() throws NumberFormatException {
        boolean ok = false;
        while (!ok) {
            System.out.println("Anna koordinaatiston koko: ");
            matrixSize = Integer.parseInt(lukija.nextLine());
            System.out.println("Anna solmujen maksimietäisyys: ");
            maxdistance = Integer.parseInt(lukija.nextLine());
            if (matrixSize > 0 && maxdistance > 0) {
                ok = true;
            }
        }
        createMatrix();
    }

    private static void askStartAndGoal() throws NumberFormatException {
        do {
            System.out.println("Anna lähtösolmun x-koordinaatti: ");
            startX = Integer.parseInt(lukija.nextLine());
            System.out.println("Anna lähtösolmun y-koordinaatti: ");
            startY = Integer.parseInt(lukija.nextLine());
            System.out.println("Anna maalisolmun x-koordinaatti: ");
            goalX = Integer.parseInt(lukija.nextLine());
            System.out.println("Anna maalisolmun y-koordinaatti: ");
            goalY = Integer.parseInt(lukija.nextLine());
            if (!checkCoordinates()) {
                System.out.println("Syötteet eivät olleet validit. Kokeile uudelleen");
            }
        } while (!checkCoordinates());
    }

    private static void askObstacles() throws NumberFormatException {
        System.out.println("Kuinka monta estettä?");
        int count = Integer.parseInt(lukija.nextLine());
        obstacles = new Location[count];

        for (int i = 0; i < count; i++) {
            System.out.println("Anna esteen x-koordinaatti: ");
            int x = Integer.parseInt(lukija.nextLine());
            System.out.println("Anna esteen y-koordinaatti: ");
            int y = Integer.parseInt(lukija.nextLine());
            boolean check = checkObstacle(x, y);
            if (check) {
                obstacles[i] = new Location(x, y);
            } else {
                System.out.println("Esteen koordinaatit eivät voi olla samat kuin lähdön tai maalin");
            }
        }
    }

    private static boolean checkObstacle(int x, int y) {
        if ((x == startX && y==startY) || (x== goalX && y == goalY) || x >= matrixSize || y >= matrixSize) {
            return false;
        }
        return true;
    }

    private static boolean checkCoordinates() {
        if (startX > 0 && startY > 0 && goalX > 0 && goalY > 0 && startX < matrixSize && startY < matrixSize && goalX < matrixSize && goalY < matrixSize) {
            if (startX != goalX && startY != goalX) {
                return true;
            }
        }
        return false;
    }

    private static void createMatrix() {

        matrix = new int[matrixSize][matrixSize];

        RandomNumber random = new RandomNumber(maxdistance);
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = random.nextInt();
            }
        }
    }

    private static void checkMatrix() {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (logic.distanceOk(matrix[i][j])) {
                    if (i == startX && j == startY) {
                        System.out.print("\033[34m" + " " + matrix[i][j]);
                    } else if (i == goalX && j == goalY) {
                        System.out.print("\033[34m" + " " + matrix[i][j]);
                    } else {
                        System.out.print("\033[0m" + " " + matrix[i][j]);
                    }
                } else {
                    System.out.println("Tarkista sisältö: " + i + ":" + j);
                    errorStatus = true;
                    break;
                }
            }
            System.out.println("");
        }
    }

    private static void runAlgoritm() {
        System.out.println("Lähtösolmusta " + startX + ":" + startY + " maalisolmuun " + goalX + ":" + goalY);
        System.out.println("Esteet:");
        for (Location loc: obstacles) {
            System.out.println(loc.getX()+":"+loc.getY());
        }
        if (algorithm == 1 && heap == 1) {
            System.out.println(logic.runAlgorithm(matrix, obstacles, "Dijkstra", " ", "bheap", startX, startY, goalX, goalY));
        }
        if (algorithm == 2 && heap == 1) {
            System.out.println(logic.runAlgorithm(matrix, obstacles, "Astar", " ", "bheap", startX, startY, goalX, goalY));
        }
        if (algorithm == 3) {
            System.out.println(logic.runAlgorithm(matrix, obstacles, "Idastar", " ", " ", startX, startY, goalX, goalY));
        }
        if (algorithm == 1 && heap == 2) {
            System.out.println(logic.runAlgorithm(matrix, obstacles, "Dijkstra", " ", "theap", startX, startY, goalX, goalY));
        }
        if (algorithm == 2 && heap == 2) {
            System.out.println(logic.runAlgorithm(matrix, obstacles, "Astar", " ", "theap", startX, startY, goalX, goalY));
        }

        System.out.println(logic.showPath(startX, startY, goalX, goalY));;

    }
}
