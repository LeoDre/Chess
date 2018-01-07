package pieces;

import board.board;

/**
 * Knight class is a subclass of piece class which implements all the basic
 * logic and rules for Knight's movement and attack
 * 
 * @author Jami Nicastro and Ran Sa
 *
 */
public class knight extends piece {
    /**
     * Initialize a knight with its position,color and position of the piece in
     * the data structure that hold it
     * 
     * @param xPos
     *            the knight's position on the x axis
     * @param yPos
     *            the knight's position on the y axis
     * @param color
     *            color of the knight, 1 for white and 0 for black
     * @param index
     *            all the piece are stored in an array, index tells the position
     *            of this knight in the array
     */
    public knight(int xPos, int yPos, int color, int index) {
        super(xPos, yPos, color, index);
        this.a = type.KNIGHT;
    }

    /**
     * Check if it is legal for a knight to move from its original position to
     * (xEnd,yEnd) on a board b
     */
    @Override
    public boolean validMove(int xEnd, int yEnd, board b) {
        if ((this.x - 2 == xEnd && (this.y + 1 == yEnd || this.y - 1 == yEnd))
                || (this.x + 2 == xEnd
                        && (this.y + 1 == yEnd || this.y - 1 == yEnd))
                || (this.x - 1 == xEnd
                        && (this.y + 2 == yEnd || this.y - 2 == yEnd))
                || (this.x + 1 == xEnd
                        && (this.y + 2 == yEnd || this.y - 2 == yEnd))) {
            if (b.occupied(xEnd, yEnd)) {
                if (b.getPiece(xEnd, yEnd).getColor() == this.color) {
                    return false;
                } else {
                    b.recycle(xEnd, yEnd);
                    this.setPosition(xEnd, yEnd);
                    b.update();
                    b.setFlag(this.color, 100, 100);
                    return true;
                }
            } else {
                this.setPosition(xEnd, yEnd);
                b.update();
                b.setFlag(this.color, 100, 100);
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Test to see if the king from the opponent side is in knight's attack
     * range
     */
    @Override
    public boolean testMove(int xEnd, int yEnd, board b) {
        if ((this.x - 2 == xEnd && (this.y + 1 == yEnd || this.y - 1 == yEnd))
                || (this.x + 2 == xEnd
                        && (this.y + 1 == yEnd || this.y - 1 == yEnd))
                || (this.x - 1 == xEnd
                        && (this.y + 2 == yEnd || this.y - 2 == yEnd))
                || (this.x + 1 == xEnd
                        && (this.y + 2 == yEnd || this.y - 2 == yEnd))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Print out knight on the board
     */
    @Override
    public String toString() {
        if (this.getColor() == 1) {
            return "wN";
        } else {
            return "bN";
        }
    }
}
