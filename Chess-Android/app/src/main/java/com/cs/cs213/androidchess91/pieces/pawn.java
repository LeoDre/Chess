package com.cs.cs213.androidchess91.pieces;

import com.cs.cs213.androidchess91.board.board;
/**
 * Pawn class is a subclass of piece class which implements all the basic logic and rules for Pawn's movement and attack
 * @author Jami Nicastro and Ran Sa
 *
 */
public class pawn extends piece{

	/**
	 * fm indicate pawn's first move
	 */
	int fm;
	/**
	 * Initialize a pawn with its position,color and position of the piece in the data structure that hold it 
	 * @param xPos the pawn's position on the x axis
	 * @param yPos the pawn's position on the y axis
	 * @param color color of the pawn, 1 for white and 0 for black
	 * @param index all the piece are stored in an array, index tells the position of this pawn in the array
	 */
	public pawn(int xPos, int yPos, int color, int index) {
		super(xPos, yPos, color, index);
		a = type.PAWN;
		fm=0;
	}
	
	/**
	 * Check if it is legal for a pawn to move from its original position to (xEnd,yEnd) on a board b
	 */
	@Override
	public boolean validMove(int xEnd, int yEnd, board b){
		if(b.occupied(xEnd, yEnd)&&b.getPiece(xEnd, yEnd).getColor()==color){
			return false;
		}
		if(b.p!=0){
			if(color==0){
				if(yEnd!=7){
					return false;
				}
			}else{
				if(yEnd!=0){
					return false;
				}
			}
		}
		if(color==1){
			if(y-2==yEnd&&x==xEnd&&fm==0&&b.occupied(x, y-1)==false&&b.occupied(x, y-2)==false){
				fm=1;
				//set the enpass flage
				b.setFlag(1, xEnd, yEnd);
				this.setPosition(xEnd, yEnd);
				b.update();
				this.promotion(b);
				return true;
			}else if(y-1==yEnd&&x==xEnd&&b.occupied(x, y-1)==false){
				//set the enpass flage back
				b.setFlag(1, 100, 100);
				this.setPosition(xEnd, yEnd);
				b.update();
				fm=1;
				this.promotion(b);
				return true;
			}else if(y-1==yEnd&&x-1==xEnd&&b.occupied(x-1, y-1)==true){
				//set the enpass flage back
				b.setFlag(1, 100, 100);
				b.recycle(xEnd, yEnd);
				this.setPosition(xEnd, yEnd);
				b.update();
				fm=1;
				this.promotion(b);
				return true;
			}else if(y-1==yEnd&&x+1==xEnd&&b.occupied(x+1, y-1)==true){
				//set the enpass flage back
				b.setFlag(1, 100, 100);
				b.recycle(xEnd, yEnd);
				this.setPosition(xEnd, yEnd);
				b.update();
				fm=1;
				this.promotion(b);
				return true;
			}else if(x+1==xEnd&&y-1==yEnd){
				int[] flage = b.getFlag();
				if(flage[2]==x+1&&flage[3]==y){
					//set the enpass flage back
					b.setFlag(1, 100, 100);
					b.recycle(flage[2], flage[3]);
					this.setPosition(xEnd, yEnd);
					b.update();
					fm=1;
					this.promotion(b);
					return true;
				}else{
					return false;
				}
			}else if(x-1==xEnd&&y-1==yEnd){
				int[] flage = b.getFlag();
				if(flage[2]==x-1&&flage[3]==y){
					//set the enpass flage back
					b.setFlag(1, 100, 100);
					b.recycle(flage[2], flage[3]);
					this.setPosition(xEnd, yEnd);
					b.update();
					fm=1;
					this.promotion(b);
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			if(y+2==yEnd&&x==xEnd&&fm==0&&b.occupied(x, y+1)==false&&b.occupied(x, y+2)==false){
				fm=1;
				//set the enpass flage
				b.setFlag(0, xEnd, yEnd);
				this.setPosition(xEnd, yEnd);
				b.update();
				this.promotion(b);
				return true;
			}else if(y+1==yEnd&&x==xEnd&&b.occupied(x, y+1)==false){
				//set the enpass flage back
				b.setFlag(0, 100, 100);
				this.setPosition(xEnd, yEnd);
				b.update();
				fm=1;
				this.promotion(b);
				return true;
			}else if(y+1==yEnd&&x-1==xEnd&&b.occupied(x-1, y+1)==true){
				//set the enpass flage back
				b.setFlag(0, 100, 100);
				b.recycle(xEnd, yEnd);
				this.setPosition(xEnd, yEnd);
				b.update();
				fm=1;
				this.promotion(b);
				return true;
			}else if(y+1==yEnd&&x+1==xEnd&&b.occupied(x+1, y+1)==true){
				//set the enpass flage back
				b.setFlag(0, 100, 100);
				b.recycle(xEnd, yEnd);
				this.setPosition(xEnd, yEnd);
				b.update();
				fm=1;
				this.promotion(b);
				return true;
			}else if(x+1==xEnd&&y+1==yEnd){
				int[] flage = b.getFlag();
				if(flage[0]==x+1&&flage[1]==y){
					//set the enpass flage back
					b.setFlag(0, 100, 100);
					b.recycle(flage[0], flage[1]);
					this.setPosition(xEnd, yEnd);
					b.update();
					fm=1;
					this.promotion(b);
					return true;
				}else{
					return false;
				}
			}else if(x-1==xEnd&&y+1==yEnd){
				int[] flage = b.getFlag();
				if(flage[0]==x-1&&flage[1]==y){
					//set the enpass flage back
					b.setFlag(0, 100, 100);
					b.recycle(flage[0], flage[1]);
					this.setPosition(xEnd, yEnd);
					b.update();
					fm=1;
					this.promotion(b);
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
	}
	/**
	 * A helper method for validMove, ask user when they want their pawn become when there is a promotion for pawn
	 * @param b the board which this game is played on
	 */
	public void promotion(board b){
		if((color==1&&y==0)||(color==0&&y==7)){
			if(b.p==5||b.p==0){
				queen q = new queen(this.getXPosition(),this.getYPosition(),this.getColor(),this.getIndex());
				b.replace(this, q);
				b.update();
			}else if(b.p==7){
				rook q = new rook(this.getXPosition(),this.getYPosition(),this.getColor(),this.getIndex());
				q.setC(0);
				b.replace(this, q);
				b.update();
			}else if(b.p==6){
				bishop q = new bishop(this.getXPosition(),this.getYPosition(),this.getColor(),this.getIndex());
				b.replace(this, q);
				b.update();
			}else if(b.p==4){
				knight q = new knight(this.getXPosition(),this.getYPosition(),this.getColor(),this.getIndex());
				b.replace(this, q);
				b.update();
			}
		}
	}
	/**
	 *  Test to see if the king from the opponent side is in pawn's attack range 
	 */
	@Override
	public boolean testMove(int xEnd, int yEnd, board b){
		if(color==1){
			if(y-1==yEnd&&x-1==xEnd&&b.occupied(x-1, y-1)==true){
				return true;
			}else return y - 1 == yEnd && x + 1 == xEnd && b.occupied(x + 1, y - 1) == true;
		}else{
			if(y+1==yEnd&&x-1==xEnd&&b.occupied(x-1, y+1)==true){
				return true;
			}else return y + 1 == yEnd && x + 1 == xEnd && b.occupied(x + 1, y + 1) == true;
		}
	}
	/**
	 * Print out bishop on the board
	 */
	@Override
	public String toString(){
		if(this.getColor()==1){
			return "wp";
		}else{
			return "bp";
		}
	}
	public static int tp(){

	    return 1;
    }
}