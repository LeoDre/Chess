# Chess
## General Information 
**Author**: Ran Sa, Jami Nicastro  
**Chess-Terminal**: A terminal version of chess game with **NO** GUI  
**Chess-Andriod**: A much refined version of Chess-Terminal. It is made spcifically for Android 6.0 (Marshmallow) (API level 23). It has been tested on Nexus 4 (4.7 inch, 768x1280, xhdpi) device emulator. This version of chess has a GUI, but it **ONLY** works in landscape.   
**Rules**: The rules of these chess games are based on Chess's rules on [Wikipedia](https://en.wikipedia.org/wiki/Chess). All rules are properly implemented **EXCEPT**:  
**Win**: A player can win a game **ONLY** by "Checkmate" or "Resignation"   
**Draw**: A game can end in a draw **ONLY** by "Draw by agreement" 
  
##  Chess-Terminal
**Moving**: Source Destination e.g. move from h2 to h4: h2 h4  
**Draw Request**:Normal move + draw? e.g. want to draw the game after moving h2 to h4: h2 h4 draw?
**Draw Acception**:Type in draw to accept the draw request e.g. draw  
**Draw Decline**:Enter your normal move to decline the draw request e.g. h2 h4  
**Resign**:Type in resign and your opponent will win automatically e.g. resign
**Promotion**:Enter your normal move with a postfix(B: bishop; K: knight; Q:queen; R:rook), if there is no postfix, the pawn will automatically promote to a queen e.g. promote a pawn to a bishop: b7 c8 B  
**Any input which doesn't follow the above rules will be trested as illegal move**
