package com.cs.cs213.androidchess91.pieces;

import com.cs.cs213.androidchess91.board.board;
/**
 * King class is a subclass of piece class which implements all the basic logic and rules for king's movement and attack
 * @author Jami Nicastro and Ran Sa
 *
 */
public class king extends piece{
	/**
	 * Fileds for king:
	 * 	c: castling, 0 for already moved, 1 for not moved yet
	 *
	 */
	int c;
	/**
	 * Initialize a king with its position,color and position of the piece in the data structure that hold it 
	 * @param xPos the king's position on the x axis
	 * @param yPos the king's position on the y axis
	 * @param color color of the king, 1 for white and 0 for black
	 * @param index all the piece are stored in an array, index tells the position of this king in the array
	 * 
	 */
	public king(int xPos, int yPos, int color, int index) {
		super(xPos, yPos, color, index);
		a = type.KING;
		c=1;
	}
	/**
	 * Check if it is legal for a king to move from its original position to (xEnd,yEnd) on a board b
	 */
	@Override
	public boolean validMove(int xEnd, int yEnd, board b){
		//try to land one same color piece
		if(b.occupied(xEnd, yEnd)&&b.getPiece(xEnd, yEnd).getColor()==color){
			return false;
		}
		if(v==0){
			if(!this.guard(xEnd, yEnd, b)){
				return false;
			}
		}
		//normal move
		if((xEnd==x-1&&yEnd==y-1)||(xEnd==x-1&&yEnd==y)||(xEnd==x-1&&yEnd==y+1)||(xEnd==x&&yEnd==y-1)||(xEnd==x&&yEnd==y+1)||(xEnd==x+1&&yEnd==y-1)||(xEnd==x+1&&yEnd==y)||(xEnd==x+1&&yEnd==y+1)){
			if(b.occupied(xEnd, yEnd)){
				//set the enpass flage back
				b.setFlag(color, 100, 100);
				b.recycle(xEnd, yEnd);
				this.setPosition(xEnd, yEnd);
				b.update();
				c=0;
				return true;
			}else{
				//set the enpass flage back
				b.setFlag(color, 100, 100);
				this.setPosition(xEnd, yEnd);
				b.update();
				c=0;
				return true;
			}
		}else if((xEnd==x-2&&yEnd==y)){ //castling
			piece r = b.getPiece(0, y);
			if(r==null){
				return false;
			}
			if(r.getC()==0||r.getColor()!=color||r.getType()!= type.ROOK||c==0){
				return false;
			}
			for(int i=3;i>0;i--){
				if(b.occupied(i, y)){
					return false;
				}
			}
			this.setPosition(xEnd, yEnd);
			r.setPosition(3,y);
			c=0;
			r.setC(0);
			b.setFlag(color, 100, 100);
			b.update();
			return true;
		}else if((xEnd==x+2&&yEnd==y)){
			piece r = b.getPiece(7, y);
			if(r==null){
				return false;
			}
			if(r.getC()==0||r.getColor()!=color||r.getType()!= type.ROOK||c==0){
				return false;
			}
			for(int i=5;i<=6;i++){
				if(b.occupied(i, y)){
					return false;
				}
			}
			this.setPosition(xEnd, yEnd);
			r.setPosition(5,y);
			c=0;
			r.setC(0);
			b.setFlag(color, 100, 100);
			b.update();
			return true;
		}else{
			return false;
		}
	}
	/**
	 *  Test to see if the king from the opponent side is in king's(from our side) attack range 
	 */
	@Override
	public boolean testMove(int xEnd, int yEnd, board b){
        return (xEnd == x - 1 && yEnd == y - 1) || (xEnd == x - 1 && yEnd == y) || (xEnd == x - 1 && yEnd == y + 1) || (xEnd == x && yEnd == y - 1) || (xEnd == x && yEnd == y + 1) || (xEnd == x + 1 && yEnd == y - 1) || (xEnd == x + 1 && yEnd == y) || (xEnd == x + 1 && yEnd == y + 1);
	}
	
	public boolean guard(int xEnd,int yEnd, board b){
		board nb = new board();
		b.copy(nb);
		piece p = nb.getPiece(x, y);
		p.v=1;
		if(!p.validMove(xEnd, yEnd, nb)){
			return false;
		}
        return !nb.check(color);
	}
	/**
	 * Print out king on the board
	 */
	@Override
	public String toString(){
		if(this.getColor()==1){
			return "wK";
		}else{
			return "bK";
		}
	}
}