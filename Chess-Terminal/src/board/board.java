package board;
import java.lang.Integer;
import pieces.bishop;
import pieces.king;
import pieces.knight;
import pieces.pawn;
import pieces.piece;
import pieces.queen;
import pieces.rook;
import java.lang.Character;
/**
 * Board class is the data structure holds all the pieces, manage pieces' movement and updates pieces movements when a turn is over
 * @author Jami Nicastro and Ran Sa
 *
 */
public class board{
	/**
	 * chessBoard: the 2D array holds the pieces
	 * wp: the array stores all the white pieces
	 * bp: the array stores all the black pieces
	 * enpassFlage: tells if a pawn can be enpassed
	 * dflages:tells if the player from the last turn asked for draw
	 * p: tells what type of piece the users want the pawn promot into
	 */
	piece[][] chessBoard;
	//group all the white pieces and black pieces into array
	piece[] wp;
	piece[] bp;
	int[] enpassFlage;
	int dflage;
	public int p;
	/**
	 * create all the pieces for black and white and store them in their corresponding aray
	 */
	public board(){
		//create a new 2D array as the board 
		chessBoard = new piece[8][8];
		//create all the white pieces
		wp = new piece[16];
		wp[0] = new rook(0,7,1,0); 
		wp[1]= new knight(1,7,1,1);
		wp[2] = new bishop(2,7,1,2);
		wp[3] = new queen(3,7,1,3);
		wp[4] = new king(4,7,1,4);
		wp[5] = new bishop(5,7,1,5);
		wp[6] = new knight(6,7,1,6);
		wp[7] = new rook(7,7,1,7);
		for(int i=8; i<=15; i++){
			wp[i] = new pawn(i-8,6,1,i);
		}
		//create all the black pieces
		bp = new piece[16];
		bp[0] = new rook(0,0,0,0); 
		bp[1]= new knight(1,0,0,1);
		bp[2] = new bishop(2,0,0,2);
		bp[3] = new queen(3,0,0,3);
		bp[4] = new king(4,0,0,4);
		bp[5] = new bishop(5,0,0,5);
		bp[6] = new knight(6,0,0,6);
		bp[7] = new rook(7,0,0,7);
		for(int i=8; i<=15; i++){
			bp[i] = new pawn(i-8,1,0,i);
		}
		//the enpass flag indicate the piece that can be enpassed, first two is for white, last two for black
		//wx,wy,bx,by
		//if nothing can be enpassed, the flag is set to 100
		enpassFlage = new int[4]; 
		enpassFlage[0] = 100;
		enpassFlage[1] = 100;
		enpassFlage[2] = 100;
		enpassFlage[3] = 100;
		dflage=0;
		p=0;
	}
	
	/**
	 * Set enpass flage for the pawn that can be enpassed
	 * @param color the color of the pawn, 0 for black 1 for white
	 * @param x the position of the pawn on the x axis
	 * @param y the position of the pawn on the y axis
	 */
	public void setFlag(int color, int x, int y){
		if(color==1){
			enpassFlage[0] = x;
			enpassFlage[1] = y;
		}else{
			enpassFlage[2] = x;
			enpassFlage[3] = y;
		}
	}
	/**
	 * Getter method for the enpass flage
	 * @return an array of enpass flage in the form of  {white-x,white-y,black-x,black-y}
	 */
	public int[] getFlag(){
		return enpassFlage;
	}
	/**
	 * Getter method for the 2D array
	 * @return the 2D array that used as a chess board
	 */
	public piece[][] get(){
		return chessBoard;
	}
	/**
	 * Add a piece to its corresponding array
	 * @param color the color of the piece 0 for black 1 for white
	 * @param i the index of the array that user want to add the new piece to
	 * @param p the piece that will be added
	 */
	public void setIndex(int color,int i, piece p){
		if(color==0){
			bp[i]=p;
		}else{
			wp[i]=p;
		}
	}
	/**
	 * Get a piece which is on (x,y) from the chess board
	 * @param x the x aixs of the piece that the user want to get
	 * @param y the y aixs of the piece that the user want to get
	 * @return the piece that the user want to get
	 */
	public piece getPiece(int x, int y){
		return chessBoard[x][y];
	}
	/**
	 * when a piece is attacked, delete this piece from the chess board and the array that is holding it
	 * @param x x axis of the piece that want to be deleted
	 * @param y y axis of the piece that want to be deleted
	 */
	public void recycle(int x, int y){
		piece p = chessBoard[x][y];
		int color = p.getColor();
		if(color==1){
			for(int i=0;i<=15;i++){
				if(wp[i]!=null){
					if(wp[i].getXPosition()==x&&wp[i].getYPosition()==y){
						wp[i]=null;
						break;
					}
				}
			}
		}else{
			for(int i=0;i<=15;i++){
				if(bp[i]!=null){
					if(bp[i].getXPosition()==x&&bp[i].getYPosition()==y){
						bp[i]=null;
						break;
					}
				}
			}
		}
	}
	/**
	 * Replace an old piece with a new piece
	 * @param oldp the old piece
	 * @param newp the new piece
	 */
	public void replace(piece oldp, piece newp){
		int i = oldp.getIndex();
		if(oldp.getColor()==1){
			wp[i] = null;
			wp[i] = newp;
		}else{
			bp[i] = null;
			bp[i] = newp;
		}
	}
	/**
	 * After a turn is finished, update the chess board based on pieces' (x,y) value
	 */
	public void update(){
		//first set null to all slot
		for(int y=0;y<=7;y++){
			for(int x=0;x<=7;x++){
				chessBoard[x][y]=null;
			}
		}
		//put black and white piece to the board
		for(int i=0;i<=15;i++){
			if(wp[i]!=null){
				int x =wp[i].getXPosition(); 
				int y =wp[i].getYPosition();
				chessBoard[x][y]=wp[i];
			}
			if(bp[i]!=null){
				int x=bp[i].getXPosition();
				int y = bp[i].getYPosition();
				chessBoard[x][y] = bp[i];
			}
			
		}
	}
	/**
	 * check a specific position on the board to see if there is a piece on it
	 * @param x the x axis value of the position that wanted to be checked
	 * @param y the x axis value of the position that wanted to be checked
	 * @return return true if there is a piece on the place false otherwise
	 */
	public boolean occupied(int x, int y){
		if(x>7||y>7||x<0||y<0){
			return false;
		}else if(chessBoard[x][y]==null){
			return false;
		}
		return true;
	}
	/**
	 * check a if it is dark tile or a white tile
	 * 
	 * @param x the x axis value of the position that wanted to be checked
	 * @param y the y axis value of the position that wanted to be checked
	 * @return 0 for black and 1 for white
	 */
	public int getColor(int x, int y){
		if((x%2==1&&y%2==0)||(x%2==0&&y%2==1)){
			return 0;
		}else{
			return 1;
		}
	}
	/**
	 * covert chess board location to 2D array position
	 * for example a1 to (0,7)
	 * @param p the chess board position as a string
	 * @return an array length of two that stores the x and y value
	 */
	public int[] convert(String p){
		char c = p.charAt(0);
		int x = (int)c-97;
		c = p.charAt(1);
		int y = 8-Character.getNumericValue(c);
		int[] r = {x,y};
		return r;
	}
	/**
	 * convert 2D array position to chess board location
	 * for example (0,7) to a1
	 * @param sx initial x axis value of the position
	 * @param sy initial y axis value of the position
	 * @param ex final x axis value of the position
	 * @param ey final y axis value of the position
	 * @return return a string in chess board loaction
	 */
	public String reconvert(int sx,int sy,int ex,int ey){
		String s = "";
		String a = Character.toString((char)(sx+97));
		String b = Integer.toString(8-sy);
		String c =" ";
		String d = Character.toString((char)(ex+97));
		String e = Integer.toString(8-ey);
		s = a+b+c+d+e;
		return s;
	}
	
	/**
	 * check if the input is legal
	 * @param in the input as a String
	 * @param co which one's turn, 1 for white and 0 for black
	 * @return return an array of chess board position if valid return null when not valid At the end of the array 0 normal move 1 resign 2 request draw 3 confirm draw 4 promote to knight 5 to queen 6 bishop 7 rook
	 */
	public int[] inputCheck(String in, int co){
		//check if the length of the input is valid
		int len = in.length();
		int[] r = new int[5];
		if(len==6&&in.equals("resign")){
			r[4]=1;
			p=0;
			return r;
		}else if(len==7 && in.charAt(6)=='N'){
			r[4]=4;
			p=4;
		}else if(len==7 && in.charAt(6)=='Q'){
			r[4]=5;
			p=5;
		}else if(len==7 && in.charAt(6)=='B'){
			r[4]=6;
			p=6;
		}else if(len==7 && in.charAt(6)=='R'){
			r[4]=7;
			p=7;
		}else if(len==4&&in.equals("draw")){
			r[4]=3;
			if(dflage!=1){
				return null;
			}
			p=0;
			return r;
		}else if(len==5){
			r[4] = 0;
		}else if(len==11&&in.substring(6).equals("draw?")){
			r[4] =2;
		}else{
			return null;
		}
		//check if the input is not out of the range
		//or if the input doesn't move anything
		char a =in.charAt(0); 
		int b =Character.getNumericValue(in.charAt(1));
		char c = in.charAt(3);
		int d = Character.getNumericValue(in.charAt(4));
		char e = in.charAt(2);
		if(a>'h'||a<'a'||b>8||b<1|c>'h'||c<'a'||d>8||d<1||e!=' '||(a==c&&b==d)){
			return null;
		}else{
			int[] p = this.convert(in.substring(0, 2));
			r[0]=p[0];
			r[1] = p[1];
			p = this.convert(in.substring(3, 5));
			r[2] = p[0];
			r[3] = p[1];
		}
		//check if there is a piece there
		//and if the piece is the right color to move
		if(this.occupied(r[0], r[1])){
			if(this.getPiece(r[0], r[1]).getColor()==co){
				if(r[4]==2){
					dflage=1;
				}else{
					dflage=0;
				}
				if(r[4]<4){
					p=0;
				}
				return r;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	/**
	 * check if the king is in check position
	 * @param c the color of the king that want to be checked
	 * @return true if the king is in check position, false otherwise
	 */
	public boolean check(int c){
		if(c==0){
			int x = bp[4].getXPosition();
			int y = bp[4].getYPosition();
			return this.checkhealper(x, y, 1);
		}else{
			int x = wp[4].getXPosition();
			int y = wp[4].getYPosition();
			return this.checkhealper(x, y, 0);
		}
	}
	/**
	 * helper method for check
	 * @param x x axis value of the position of the king
	 * @param y y axis value of the position of the king
	 * @param c color of the king
	 * @return true if the king is in check position, false otherwise
	 */
	public boolean checkhealper(int x, int y, int c){
		if(c==0){
			for(int i = 0; i<=15;i++){
				if(bp[i]!=null){
					boolean b = bp[i].testMove(x, y, this);
					if(b==true){
						return true;
					}
				}
			}
			return false;
		}else{
			for(int i = 0; i<=15;i++){
				if(wp[i]!=null){
					boolean b = wp[i].testMove(x, y, this);
					if(b==true){
						return true;
					}
				}
			}
			return false;
		}
	}
	/**
	 * copy this board to a new board for testing
	 * @param nb the new board that the user want to copy to
	 */
	public void copy(board nb){
		for(int i=0;i<=15;i++){
			if(bp[i]==null){
				nb.setIndex(0, i, null);
			}else{
				nb.setIndex(0, i, (piece)bp[i].clone());
			}
			if(wp[i]==null){
				nb.setIndex(1, i, null);
			}else{
				nb.setIndex(1, i, (piece)wp[i].clone());
			}
		}
		nb.setFlag(1, enpassFlage[0], enpassFlage[1]);
		nb.setFlag(0, enpassFlage[2], enpassFlage[3]);
		nb.update();
	}
	/**
	 * print out the chess board
	 */
	@Override
	public String toString(){
		String r = "";
		for(int y=0;y<=7;y++){
			for(int x=0;x<=7;x++){
				if(this.occupied(x, y)==true){
					//print the place where there is a piece on it
					r = r+chessBoard[x][y].toString()+" ";
				}else{
					//print the empty places
					if(this.getColor(x, y)==0){
						r = r+"## ";
					}else{
						r=r+"   ";
					}
				}
			}
			r = r+Integer.toString(8-y)+"\n";
		}
		r=r+" a  b  c  d  e  f  g  h\n";
		return r;
	}
}
