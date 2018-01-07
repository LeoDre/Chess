package com.cs.cs213.androidchess91.pieces;

import com.cs.cs213.androidchess91.board.board;
/**
 * Rook class is a subclass of piece class which implements all the basic logic and rules for Rook's movement and attack
 * @author Jami Nicastro and Ran Sa
 *
 */
public class rook extends piece{
	/**
	 * Initialize a rook with its position,color and position of the piece in the data structure that hold it 
	 * @param xPos the rook's position on the x axis
	 * @param yPos the rook's position on the y axis
	 * @param color color of the rook, 1 for white and 0 for black
	 * @param index all the piece are stored in an array, index tells the position of this rook in the array
	 */
	public rook(int xPos, int yPos, int color, int index) {
		super(xPos, yPos, color, index);
		a = type.ROOK;
		c=1;
	}
	/**
	 * Check if it is legal for a rook to move from its original position to (xEnd,yEnd) on a board b
	 */
	@Override
	public boolean validMove(int xEnd, int yEnd,board b){
		if(b.occupied(xEnd, yEnd)&&b.getPiece(xEnd, yEnd).getColor()==color){
			return false;
		}
		if(y==yEnd){
			if(x>xEnd){
				for(int i = x-1; i>=xEnd;i--){
				if(b.occupied(i, y)&&i!=xEnd){
						return false;
					}
				}
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
			}else if(x<xEnd){
				for(int i = x+1; i<=xEnd;i++){
					if(b.occupied(i, y)&&i!=xEnd){
						return false;
					}
				}
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
			}else{
				return false;
			}
		}else if(x==xEnd){
			if(y>yEnd){
				for(int i = y-1; i>=yEnd;i--){
					if(b.occupied(x, i)&&i!=yEnd){
						return false;
					}
				}
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
			}else if(y<yEnd){
				for(int i = y+1; i<=yEnd;i++){
					if(b.occupied(x, i)&&i!=yEnd){
						return false;
					}
				}
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
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**
	 *  Test to see if the king from the opponent side is in rook's attack range 
	 */
	@Override
	public boolean testMove(int xEnd, int yEnd, board b){
		if(y==yEnd){
			if(x>xEnd){
				for(int i = x-1; i>=xEnd;i--){
				if(b.occupied(i, y)&&i!=xEnd){
						return false;
					}
				}
				return true;
			}else if(x<xEnd){
				for(int i = x+1; i<=xEnd;i++){
					if(b.occupied(i, y)&&i!=xEnd){
						return false;
					}
				}
				return true;
			}else{
				return false;
			}
		}else if(x==xEnd){
			if(y>yEnd){
				for(int i = y-1; i>=yEnd;i--){
					if(b.occupied(x, i)&&i!=yEnd){
						return false;
					}
				}
				return true;
			}else if(y<yEnd){
				for(int i = y+1; i<=yEnd;i++){
					if(b.occupied(x, i)&&i!=yEnd){
						return false;
					}
				}
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * Print out rook on the board
	 */
	@Override
	public String toString(){
		if(this.getColor()==1){
			return "wR";
		}else{
			return "bR";
		}
	}
}