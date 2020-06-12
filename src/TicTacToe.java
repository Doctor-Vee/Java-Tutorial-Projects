import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();
    static List<List> winningCombinations = setWinningConditions();

    public static void main(String[] args) {
        System.out.println("Hello World");
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter your placement (1-9): ");
            int playerPos = scan.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Enter a space that has not been taken");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");
            printGameBoard(gameBoard);
            String result = checkWinner();
            System.out.println(result);
            if (!result.equals("")) break;
            System.out.println("-------------------------------------------------");

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);
            result = checkWinner();
            System.out.println(result);
            if (!result.equals("")) break;
            System.out.println("-------------------------------------------------");
        }

        System.out.println("Game over");

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                System.out.println("Enter the correct number please");
        }
    }

    public static String checkWinner() {
        for (List l : winningCombinations) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations you won! :)";
            } else if (cpuPositions.containsAll(l)) {
                return "Sorry ... The CPU won :( ";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Wow ... A Draw :|";
            }
        }
        return "";
    }

    public static List<List> setWinningConditions(){
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winningCombinations = new ArrayList<List>();
        winningCombinations.add(topRow);
        winningCombinations.add(midRow);
        winningCombinations.add(botRow);
        winningCombinations.add(leftCol);
        winningCombinations.add(midCol);
        winningCombinations.add(rightCol);
        winningCombinations.add(cross1);
        winningCombinations.add(cross2);
        return winningCombinations;
    }
}
