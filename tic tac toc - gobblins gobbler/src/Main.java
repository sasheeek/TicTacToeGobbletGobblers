import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        Main ttt = new Main();
    }

    public Main() throws IOException {
        System.out.println("Welcome to 2 player Tic Tac Toe!!!");
        System.out.println("----------------------------------");
        initializeBoard();

        do {
            System.out.println("Current board layout:");
            printBoard();

            do {
                step();
            } while (!placeMark(row, column, thimbles));

            changePlayer();
        } while (!isBoardFull() && !checkForWin());


        if (isBoardFull() && !checkForWin()) {
            System.out.println("The game was TIE!!!");
            printBoard();
        } else {
            System.out.println("Final border layout:");
            printBoard();
            changePlayer();
            System.out.println("The winner is: " + currentPlayerMark);
        }
    }

    private int SIZE = 3;
    private String[][] board = new String[SIZE][SIZE];
    private String currentPlayerMark = "X";
    private int[] playerXthimbles = new int[6];
    private int[] playerOthimbles = new int[6];

    int row;
    int column;
    int thimbles;


    public void initializeBoard() {
        for (int a = 0; a < SIZE; a++) {
            for (int b = 0; b < SIZE; b++) {
                board[a][b] = "  ";
            }
        }

        playerXthimbles[0] = 1;
        playerXthimbles[1] = 1;
        playerXthimbles[2] = 2;
        playerXthimbles[3] = 2;
        playerXthimbles[4] = 3;
        playerXthimbles[5] = 3;

        playerOthimbles[0] = 1;
        playerOthimbles[1] = 1;
        playerOthimbles[2] = 2;
        playerOthimbles[3] = 2;
        playerOthimbles[4] = 3;
        playerOthimbles[5] = 3;
    }

    public void step() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Player " + getCurrentPlayerMark() + ", enter an empty row and column to place your mark!");
        System.out.print("ROW: ");
        row = parseInt(reader.readLine()) - 1;
        System.out.print("COLUMN: ");
        column = parseInt(reader.readLine()) - 1;
        System.out.print("THIMBLES(1-3): ");
        thimbles = parseInt(reader.readLine());

//
//        for (int a = 0; a < 3; a++) {
//            if (currentPlayerMark.equals("X")) {
//                if (!Arrays.asList(playerXthimbles).contains(thimbles)) {
//                    break;
//                } else {
//                    takeOfThimble = false;
//                    step();
//                }
//            } else {
//                if (!Arrays.asList(playerOthimbles).contains(thimbles)) {
//                    break;
//                } else {
//                    takeOfThimble = false;
//                    step();
//                }
//            }
//        }
    }

    public boolean isBoardFull() {
        int count = 0;

        for (int a = 0; a < SIZE; a++) {
            for (int b = 0; b < SIZE; b++) {
                if (!board[a][b].equals("  ")) {
                    count++;
                }

                if (count == 9) {
                    return true;
                }
            }
        }
        return false;
    }

    public void changePlayer() {
        if (currentPlayerMark.equals("X")) {
            currentPlayerMark = "O";
        } else {
            currentPlayerMark = "X";
        }
    }

    public String getCurrentPlayerMark() {
        return currentPlayerMark;
    }

    public boolean placeMark(int row, int column, int thimbles) throws IOException {
        if (row >= 0 && row < 3) {
            if (column >= 0 && column < 3) {
                if (thimbles >= 1 && thimbles <= 3) {
                    if (board[row][column].equals("  ")) {
                        board[row][column] = currentPlayerMark + thimbles;
                        removeThimble();
                        return true;
                    } else if (board[row][column].equals("X1") && (thimbles == 2 || thimbles == 3)) {
                        if (currentPlayerMark.equals("O")) {
                            board[row][column] = currentPlayerMark + thimbles;
                            removeThimble();
                            return true;
                        }
                    } else if (board[row][column].equals("X2") && thimbles == 3) {
                        if (currentPlayerMark.equals("O")) {
                            board[row][column] = currentPlayerMark + thimbles;
                            removeThimble();
                            return true;
                        }
                    } else if (board[row][column].equals("O1") && (thimbles == 2 || thimbles == 3)) {
                        if (currentPlayerMark.equals("X")) {
                            board[row][column] = currentPlayerMark + thimbles;
                            removeThimble();
                            return true;
                        }
                    } else if (board[row][column].equals("O2") && thimbles == 3) {
                        if (currentPlayerMark.equals("X")) {
                            board[row][column] = currentPlayerMark + thimbles;
                            removeThimble();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void removeThimble() {
        if (currentPlayerMark.equals("X")) {
            for (int a = 0; a < 6; a++) {
                if (playerXthimbles[a] == thimbles) {
                    playerXthimbles[a] = 0;
                    break;
                }
            }
        } else {
            for (int a = 0; a < 6; a++) {
                if (playerOthimbles[a] == thimbles) {
                    playerOthimbles[a] = 0;
                    break;
                }
            }
        }
    }

    public void printBoard() {
        System.out.println("/--------------\\");
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("|--------------|");
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("|--------------|");
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println("\\--------------/");

        System.out.print("X: ");
        for (int a = 0; a < 6; a++) {
            System.out.print(playerXthimbles[a] + " ");
        }
        System.out.println("");

        System.out.print("O: ");
        for (int a = 0; a < 6; a++) {
            System.out.print(playerOthimbles[a] + " ");
        }
        System.out.println("");
    }

    public boolean checkForWin() {
        if ((board[0][0].equals("X1") || board[0][0].equals("X2") || board[0][0].equals("X3")) &&
                (board[0][1].equals("X1") || board[0][1].equals("X2") || board[0][1].equals("X3")) &&
                (board[0][2].equals("X1") || board[0][2].equals("X2") || board[0][2].equals("X3")) &&
                !board[0][0].equals("  ")) { return true; }
        if ((board[0][0].equals("O1") || board[0][0].equals("O2") || board[0][0].equals("O3")) &&
                (board[0][1].equals("O1") || board[0][1].equals("O2") || board[0][1].equals("O3")) &&
                (board[0][2].equals("O1") || board[0][2].equals("O2") || board[0][2].equals("O3")) &&
                !board[0][0].equals("  ")) { return true; }

        if ((board[1][0].equals("X1") || board[1][0].equals("X2") || board[1][0].equals("X3")) &&
                (board[1][1].equals("X1") || board[1][1].equals("X2") || board[1][1].equals("X3")) &&
                (board[1][2].equals("X1") || board[1][2].equals("X2") || board[1][2].equals("X3")) &&
                !board[1][0].equals("  ")) { return true; }
        if ((board[1][0].equals("O1") || board[1][0].equals("O2") || board[1][0].equals("O3")) &&
                (board[1][1].equals("O1") || board[1][1].equals("O2") || board[1][1].equals("O3")) &&
                (board[1][2].equals("O1") || board[1][2].equals("O2") || board[1][2].equals("O3")) &&
                !board[1][0].equals("  ")) { return true; }

        if ((board[2][0].equals("X1") || board[2][0].equals("X2") || board[2][0].equals("X3")) &&
                (board[2][1].equals("X1") || board[2][1].equals("X2") || board[2][1].equals("X3")) &&
                (board[2][2].equals("X1") || board[2][2].equals("X2") || board[2][2].equals("X3")) &&
                !board[2][0].equals("  ")) { return true; }
        if ((board[2][0].equals("O1") || board[2][0].equals("O2") || board[2][0].equals("O3")) &&
                (board[2][1].equals("O1") || board[2][1].equals("O2") || board[2][1].equals("O3")) &&
                (board[2][2].equals("O1") || board[2][2].equals("O2") || board[2][2].equals("O3")) &&
                !board[2][0].equals("  ")) { return true; }


        if ((board[0][0].equals("X1") || board[0][0].equals("X2") || board[0][0].equals("X3")) &&
                (board[1][0].equals("X1") || board[1][0].equals("X2") || board[1][0].equals("X3")) &&
                (board[2][0].equals("X1") || board[2][0].equals("X2") || board[2][0].equals("X3")) &&
                !board[0][0].equals("  ")) { return true; }
        if ((board[0][0].equals("O1") || board[0][0].equals("O2") || board[0][0].equals("O3")) &&
                (board[1][0].equals("O1") || board[1][0].equals("O2") || board[1][0].equals("O3")) &&
                (board[2][0].equals("O1") || board[2][0].equals("O2") || board[2][0].equals("O3")) &&
                !board[0][0].equals("  ")) { return true; }

        if ((board[0][1].equals("X1") || board[0][1].equals("X2") || board[0][1].equals("X3")) &&
                (board[1][1].equals("X1") || board[1][1].equals("X2") || board[1][1].equals("X3")) &&
                (board[2][1].equals("X1") || board[2][1].equals("X2") || board[2][1].equals("X3")) &&
                !board[0][1].equals("  ")) { return true; }
        if ((board[0][1].equals("O1") || board[0][1].equals("O2") || board[0][1].equals("O3")) &&
                (board[1][1].equals("O1") || board[1][1].equals("O2") || board[1][1].equals("O3")) &&
                (board[2][1].equals("O1") || board[2][1].equals("O2") || board[2][1].equals("O3")) &&
                !board[0][1].equals("  ")) { return true; }

        if ((board[0][2].equals("X1") || board[0][2].equals("X2") || board[0][2].equals("X3")) &&
                (board[1][2].equals("X1") || board[1][2].equals("X2") || board[1][2].equals("X3")) &&
                (board[2][2].equals("X1") || board[2][2].equals("X2") || board[2][2].equals("X3")) &&
                !board[0][2].equals("  ")) { return true; }
        if ((board[0][2].equals("O1") || board[0][2].equals("O2") || board[0][2].equals("O3")) &&
                (board[1][2].equals("O1") || board[1][2].equals("O2") || board[1][2].equals("O3")) &&
                (board[2][2].equals("O1") || board[2][2].equals("O2") || board[2][2].equals("O3")) &&
                !board[0][2].equals("  ")) { return true; }


        if ((board[0][0].equals("X1") || board[0][0].equals("X2") || board[0][0].equals("X3")) &&
                (board[1][1].equals("X1") || board[1][1].equals("X2") || board[1][1].equals("X3")) &&
                (board[2][0].equals("X1") || board[2][0].equals("X2") || board[2][0].equals("X3")) &&
                !board[0][0].equals("  ")) { return true; }
        if ((board[0][2].equals("O1") || board[0][2].equals("O2") || board[0][2].equals("O3")) &&
                (board[1][1].equals("O1") || board[1][1].equals("O2") || board[1][1].equals("O3")) &&
                (board[2][0].equals("O1") || board[2][0].equals("O2") || board[2][0].equals("O3")) &&
                !board[0][2].equals("  ")) { return true; }

        return false;
    }
}