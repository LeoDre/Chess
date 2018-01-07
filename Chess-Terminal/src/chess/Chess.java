package chess;

import java.util.Scanner;

import board.ruleController;

/**
 * The chess class manages the whole chess game by calling functions and methods
 * from other class when it is necessary
 * 
 * @author Jami Nicastro and Ran Sa
 *
 */
public class Chess {

    /**
     * main method of the class, execute, manage and end the game
     * 
     * @param args
     *            the argument input for main method
     */
    public static void main(String[] args) {
        ruleController chessBoard = new ruleController();
        System.out.println(chessBoard);
        int turn = 1;
        Scanner scanner = new Scanner(System.in);
        int ind = 1;
        while (ind != 0) {
            if (turn == 1) {
                System.out.print("White's move: ");
            } else if (turn == 0) {
                System.out.print("Black's move: ");
            }
            String in = "";
            in = scanner.nextLine();
            int i = chessBoard.move(in, turn);
            if (i == 0) {
                System.out.println("Illegal move, try again");
                continue;
            } else if (i == 20) {
                System.out.println("White wins");
                break;
            } else if (i == 10) {
                System.out.println("Black wins");
                break;
            } else if (i == 30) {
                System.out.println("Draw");
                break;
            } else if (i == 40) {
                System.out.println("White wins");
                break;
            } else if (i == 50) {
                System.out.println("Black wins");
                break;
            }
            if (turn == 1) {
                turn = 0;
            } else {
                turn = 1;
            }
            System.out.println("\n" + chessBoard + "\n");
            if (turn == 1 && i == 11) {
                System.out.println("Check");
            } else if (turn == 0 && i == 1) {
                System.out.println("Check");
            }
        }
        scanner.close();
    }
}
