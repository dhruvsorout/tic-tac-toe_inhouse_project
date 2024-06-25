import java.util.Scanner;

public class Game {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        while (true) {
            makeMove(currentPlayer);
            displayBoard();
            if (checkForWin()) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (checkForDraw()) {
                System.out.println("It's a draw!");
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
        }
    }

    // Initialize the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Display the current state of the board
    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Make a move on the board
    private static void makeMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("Player " + player + ", enter row (1-3) and column (1-3) separated by space: ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '-');
        board[row][col] = player;
    }

    // Check if there is a win
    private static boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // Check rows for a win
    private static boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    // Check columns for a win
    private static boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    // Check diagonals for a win
    private static boolean checkDiagonalsForWin() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    // Check if all three values are the same (and not empty) indicating a win
    private static boolean checkRowCol(char c1, char c2, char c3) {
        return (c1 != '-' && c1 == c2 && c2 == c3);
    }

    // Check if the board is full, indicating a draw
    private static boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // There is an empty space, not a draw yet
                }
            }
        }
        return true; // Board is full, it's a draw
    }
}
