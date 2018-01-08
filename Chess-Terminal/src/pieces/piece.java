package pieces;
import board.board;
/**
 * The piece class is the super class of the pieces that is needed for the chess game
 * @author Jami Nicastro and Ran Sa
 *
 */
public abstract class piece implements Cloneable{
	/**
	 * x: the piece's position on the x axis
	 * y: the piece's position on the y axis
	 * index: all the piece are stored in an array, index tells the position of this piece in the array
	 * c: indicate castling 1 for can be used for castling 0 for not 
	 * a: type of the piece
	 *   v: virtual piece, indicate that if a piece is in a simulation or in a real board
	 */
	int x, y, color, index, c,v;
	type a;
	/**
	 * Initialize a piece with its position,color and position of the piece in the data structure that hold it 
	 * @param xPosition the piece's position on the x axis
	 * @param yPosition the piece's position on the y axis
	 * @param color color of the piece, 1 for white and 0 for black
	 * @param index all the piece are stored in an array, index tells the position of this piece in the array
	 */
	public piece(int xPosition, int yPosition, int color, int index){
		this.x = xPosition;
		this.y = yPosition;
		this.color = color;
		this.index = index;
		c=0;
		v=0;
		a=type.NULL;
	}
	/**
	 * Getter method for x
	 * @return return the return the position of this piece on the x axis
	 */
	public int getXPosition(){
		return x;
	}
	/**
	 * Getter method for y
	 * @return  return the position of this piece on the y axis
	 */
	public int getYPosition(){
		return y;
	}
	/**
	 * Getter method for index
	 * @return return the index of this piece in the array
	 */
	public int getIndex(){
		return index;
	}
	/**
	 * Setter method for the index
	 * @param i index number
	 */
	public void setIndex(int i){
		index = i;
	}
	/**
	 * Move a piece from it original position to the position (xp,yp)
	 * @param xp the x axis value for the position user want to move to
	 * @param yp the y axis value for the position user want to move to
	 */
	public void setPosition(int xp, int yp){
		x=xp;
		y=yp;
	}
	/**
	 * Getter method for color
	 * @return return the color of the piece, 1 for white piece 0 for black piece
	 */
	public int getColor(){
		return color;
	}
	/**
	 * Getter method for type
	 * @return return the type of the piece as a enum
	 */
	public type getType(){
		return a;
	}
	/**
	 * getter method for castling
	 * @return return the value for castling
	 */
	public int getC(){
		return c;
	}
	/**
	 * Setter method for castling
	 * if the king or the rook moves, castling should be set to 0
	 * @param i 1 or 0, 1 for never moved, 0 for already moved
	 */
	public void setC(int i){
		c=i;
	}
	/**
	 * Clone a piece for simulate a move or testing purposes
	 * @return a cloned piece as an object
	 */
	@Override
	public Object clone(){
		piece p = null;
		try{  
            p = (piece)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return p;  
	}
	/**
	 * Check if it is legal for a piece to move from its original position to (xEnd,yEnd) on a board b
	 * if it is legal, move the piece if not return false
	 * @param xEnd the x axis value for the position user want to move to
	 * @param yEnd the y axis value for the position user want to move to
	 * @param b the board that this move is made
	 * @return return true if the move is legal false if not
	 */
	public abstract boolean validMove(int xEnd, int yEnd, board b);
	/**
	 * Test to see if the king from the opponent side is in this piece's attack range 
	 * @param xEnd the x axis value for the position of the opponent's king
	 * @param yEnd the y axis value for the position of the opponent's king
	 * @param b the board that this move is tested on
	 * @return return true if the king is in the attack range
	 */
	public abstract boolean testMove(int xEnd, int yEnd, board b);
}