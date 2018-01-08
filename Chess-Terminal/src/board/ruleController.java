package board;

import pieces.piece;

/**
 * ruleController class is a class that communicate between chess and board
 * class
 * 
 * @author Jami Nicastro and Ran Sa
 *
 */
public class ruleController {
    /**
     * b: main board used for this game bc: check if the black king is in check
     * position 0 for no, 1 for yes wc: check if the white king is in check
     * position 0 for no, 1 for yes
     */
    board b;
    int bc, wc;

    /**
     * initialize a chess board that can be used for the chess class
     */
    public ruleController() {
        this.b = new board();
        this.b.update();
        this.bc = 0;
        this.wc = 0;
    }

    /**
     * pass the input from the chess class to board class for moving and
     * checking
     * 
     * @param fr
     *            input from the chess class
     * @param color
     *            who's turn is it, 0 for black, 1 for white
     * @return 0 if move invalid 10 resign black win 20 resign white win 30
     *         confirm draw 40 black lose 50 white lose
     */
    public int move(String fr, int color) {
        int[] co = new int[5];
        co = this.b.inputCheck(fr, color);
        if (co == null) {
            return 0;
        }
        if (co[4] == 1 && color == 0) {
            return 20;
        } else if (co[4] == 1 && color == 1) {
            return 10;
        } else if (co[4] == 3) {
            return 30;
        }
        if (this.wc == 1 && color == 1) {
            if (!this.simulate(fr, color)) {
                return 0;
            } else {
                this.wc = 0;
            }
        } else if (this.bc == 1 && color == 0) {
            if (!this.simulate(fr, color)) {
                return 0;
            } else {
                this.bc = 0;
            }
        }
        piece[][] chessBoard = this.b.get();
        piece p = chessBoard[co[0]][co[1]];
        if (!p.validMove(co[2], co[3], this.b)) {
            return 0;
        }
        if (this.b.check(1 - color)) {
            if (!this.saveKing(1 - color)) {
                System.out.println("Checkmate");
                if (color - 1 == 0) {
                    return 40;
                } else {
                    return 50;
                }
            } else {
                if (color - 1 == 0) {
                    this.bc = 1;
                    return 1;
                } else {
                    this.wc = 1;
                    return 11;
                }
            }
        }
        return 3;
    }

    /**
     * when king is under check, see if next move (from user input) can save
     * king from check
     * 
     * @param fr
     *            user input of next move
     * @param color
     *            which king is in check 1 for white and 0 for black
     * @return return true if the king can be saved false other wise
     */
    public boolean simulate(String fr, int color) {
        //create a new board for testing
        board nb = new board();
        //clone the old board to the new board
        this.b.copy(nb);
        int[] co = new int[5];
        co = nb.inputCheck(fr, color);
        if (co == null) {
            return false;
        }
        piece[][] chessBoard = nb.get();
        piece p = chessBoard[co[0]][co[1]];
        if (!p.validMove(co[2], co[3], nb)) {
            return false;
        }
        if (nb.check(color)) {
            return false;
        }
        return true;
    }

    /**
     * see if there is any way to safe king if king is under check
     * 
     * @param color
     *            which king to check, 0 for black and 1 for white
     * @return true if the king can be saved, false otherwise(checkmate)
     */
    public boolean saveKing(int color) {
        piece[] p;
        if (color == 0) {
            p = this.b.bp;
        } else {
            p = this.b.wp;
        }
        for (int i = 0; i <= 15; i++) {
            if (p[i] != null) {
                for (int ex = 0; ex <= 7; ex++) {
                    for (int ey = 0; ey <= 7; ey++) {
                        String s = this.b.reconvert(p[i].getXPosition(),
                                p[i].getYPosition(), ex, ey);
                        if (this.simulate(s, color)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Print out the chess board
     */
    @Override
    public String toString() {
        return this.b.toString();
    }
}
