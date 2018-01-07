package pieces;
import board.board;
/**
 * Queen class is a subclass of piece class which implements all the basic logic and rules for Queen's movement and attack
 * @author Jami Nicastro and Ran Sa
 *
 */
public class queen extends piece{
	/**
	 * Initialize a queen with its position,color and position of the piece in the data structure that hold it 
	 * @param xPos the queen's position on the x axis
	 * @param yPos the queen's position on the y axis
	 * @param color color of the queen, 1 for white and 0 for black
	 * @param index all the piece are stored in an array, index tells the position of this queen in the array
	 */
	public queen(int xPos, int yPos, int color, int index) {
		super(xPos, yPos, color, index);
		a = type.QUEEN;
	}
	/**
	 * Check if it is legal for a queen to move from its original position to (xEnd,yEnd) on a board b
	 */
	@Override
	public boolean validMove(int xEnd, int yEnd, board b){
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
					return true;
				}else{
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					this.setPosition(xEnd, yEnd);
					b.update();
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
					return true;
				}else{
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					this.setPosition(xEnd, yEnd);
					b.update();
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
					return true;
				}else{
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					this.setPosition(xEnd, yEnd);
					b.update();
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
					return true;
				}else{
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					this.setPosition(xEnd, yEnd);
					b.update();
					return true;
				}
			}else{
				return false;
			}
		}else if(x-xEnd == y-yEnd){
			if(x>xEnd){
				int tx=x;
				int ty=y-1;
				for(tx=x-1;tx>=xEnd;tx-- ){
					if(b.occupied(tx, ty)&&tx!=xEnd){
						return false;
					}
					ty--;
				}
				if(b.occupied(xEnd, yEnd)){
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					b.recycle(xEnd, yEnd);
					this.setPosition(xEnd, yEnd);
					b.update();
					return true;
				}else{
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					this.setPosition(xEnd, yEnd);
					b.update();
					return true;
				}
			}else if (x<xEnd){
				int tx=x;
				int ty=y+1;
				for(tx=x+1;tx<=xEnd;tx++ ){
					if(b.occupied(tx, ty)&&tx!=xEnd){
						return false;
					}
					ty++;
				}
				if(b.occupied(xEnd, yEnd)){
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					b.recycle(xEnd, yEnd);
					this.setPosition(xEnd, yEnd);
					b.update();
					return true;
				}else{
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					this.setPosition(xEnd, yEnd);
					b.update();
					return true;
				}
			}else{
				return false;
			}
		}else if(x+y==xEnd+yEnd){
			if(x>xEnd){
				int tt=x+y;
				int tx = x;
				int ty;
				for(tx=x-1;tx>=xEnd;tx--){
					ty = tt-tx;
					if(b.occupied(tx, ty)&&tx!=xEnd){
						return false;
					}
				}
				if(b.occupied(xEnd, yEnd)){
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					b.recycle(xEnd, yEnd);
					this.setPosition(xEnd, yEnd);
					b.update();
					return true;
				}else{
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					this.setPosition(xEnd, yEnd);
					b.update();
					return true;
				}
			}else if(x<xEnd){
				int tt=x+y;
				int tx = x;
				int ty;
				for(tx=x+1;tx<=xEnd;tx++){
					ty = tt-tx;
					if(b.occupied(tx, ty)&&tx!=xEnd){
						return false;
					}
				}
				if(b.occupied(xEnd, yEnd)){
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					b.recycle(xEnd, yEnd);
					this.setPosition(xEnd, yEnd);
					b.update();
					return true;
				}else{
					//set the enpass flage back
					b.setFlag(color, 100, 100);
					this.setPosition(xEnd, yEnd);
					b.update();
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
	 *  Test to see if the king from the opponent side is in queen's attack range 
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
		}else if(x-xEnd == y-yEnd){
			if(x>xEnd){
				int tx=x;
				int ty=y-1;
				for(tx=x-1;tx>=xEnd;tx-- ){
					if(b.occupied(tx, ty)&&tx!=xEnd){
						return false;
					}
					ty--;
				}
				return true;
			}else if (x<xEnd){
				int tx=x;
				int ty=y+1;
				for(tx=x+1;tx<=xEnd;tx++ ){
					if(b.occupied(tx, ty)&&tx!=xEnd){
						return false;
					}
					ty++;
				}
				return true;
			}else{
				return false;
			}
		}else if(x+y==xEnd+yEnd){
			if(x>xEnd){
				int tt=x+y;
				int tx = x;
				int ty;
				for(tx=x-1;tx>=xEnd;tx--){
					ty = tt-tx;
					if(b.occupied(tx, ty)&&tx!=xEnd){
						return false;
					}
				}
				return true;
			}else if(x<xEnd){
				int tt=x+y;
				int tx = x;
				int ty;
				for(tx=x+1;tx<=xEnd;tx++){
					ty = tt-tx;
					if(b.occupied(tx, ty)&&tx!=xEnd){
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
	 * Print out queen on the board
	 */
	@Override
	public String toString(){
		if(this.getColor()==1){
			return "wQ";
		}else{
			return "bQ";
		}
	}
}