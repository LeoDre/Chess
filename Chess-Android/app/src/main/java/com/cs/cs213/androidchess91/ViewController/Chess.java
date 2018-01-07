package com.cs.cs213.androidchess91.ViewController;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cs.cs213.androidchess91.Data.playBack;
import com.cs.cs213.androidchess91.Data.playBackList;
import com.cs.cs213.androidchess91.R;
import com.cs.cs213.androidchess91.board.board;
import com.cs.cs213.androidchess91.board.ruleController;
import com.cs.cs213.androidchess91.pieces.type;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Chess extends AppCompatActivity implements Serializable{
    private final ImageButton[][] boardDisplay;
    private int turn;
    private String in;
    private ruleController chessBoard;
    private TextView t;
    private TextView ts;
    private TextView tc;
    private ruleController current;
    private ruleController last;
    private int once;
    private playBack pb;
    private playBackList pbl;
    private Chess c;
    public Chess(){
        chessBoard = new ruleController();
        current = new ruleController();
        last = new ruleController();
        chessBoard.copy(current);
        current.copy(last);
        boardDisplay = new ImageButton[8][8];
        turn=1;
        in="";
        once = 0;
        pb = new playBack("defualt");
        pbl = new playBackList();
        c  = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);
        try{
            pbl = playBackList.loadData(Chess.this);
            if(pbl==null){
                pbl = new playBackList();
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e2){
            e2.printStackTrace();
        }
        Button ai = findViewById(R.id.ai);
        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Chess.this);
                builder.setTitle("Suggestion");
                builder.setMessage(AI());
                builder.setNegativeButton("Got It!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button undo = findViewById(R.id.ud);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undo();
            }
        });
        t = findViewById(R.id.Tturn);
        ts = findViewById(R.id.Tstatus);
        tc = findViewById(R.id.Tcheck);
        t.setText("White's move");
        Button resign = findViewById(R.id.rsg);
        resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in = "resign";
                move();
            }
        });
        Button draw = findViewById(R.id.nxt);
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Chess.this);
                if(turn==1){
                    builder.setTitle("White Request Draw");
                }else{
                    builder.setTitle("Black Request Draw");
                }
                builder.setMessage("Do you want to draw the game");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pb.add("Draw");
                        AlertDialog.Builder builder = new AlertDialog.Builder(Chess.this);
                        builder.setTitle("Draw");
                        builder.setMessage("Draw");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                savedia();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        for(int x=0;x<=7;x++){
            char c = (char) ('a'+x);
            for(int y=0; y<=7; y++){
                String s = c + Integer.toString(8-y);
                int rid = getResources().getIdentifier(s,"id",getPackageName());
                boardDisplay[x][y] = findViewById(rid);
                final ImageButton tmp = boardDisplay[x][y];
                //final int a = x;
               // final int b = y;
                tmp.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        ts.setText("");
                        String s = (String)tmp.getTag();
                        if(s.equals("empty")){
                            if(in.length()==3){
                                in = in+getResources().getResourceEntryName(tmp.getId());
                                move();
                            }
                        }else{
                            char c = s.charAt(s.length()-1);
                            if(c=='s'){
                                s = s.substring(0,s.length()-1);
                                int rid = getResources().getIdentifier(s,"drawable",getPackageName());
                                tmp.setImageResource(rid);
                                tmp.setTag(s);
                                in="";
                            }else{
                                s = s+"s";
                                int rid = getResources().getIdentifier(s,"drawable",getPackageName());
                                tmp.setImageResource(rid);
                                tmp.setTag(s);
                                if(in.length()!=3){
                                    in = in+getResources().getResourceEntryName(tmp.getId())+" ";
                                }else {
                                    in = in + getResources().getResourceEntryName(tmp.getId());
                                    move();
                                }
                            }
                        }
                    }
                });
            }
        }
        update(chessBoard);
    }
    public void update(ruleController r){
        String[][] s = new String[8][8];
        s = r.getDis();
        for(int x = 0; x<8; x++){
            for(int y=0; y<8; y++){
                if(s[x][y]!=null){
                    int rid = getResources().getIdentifier("i"+s[x][y].toLowerCase(),"drawable",getPackageName());
                    boardDisplay[x][y].setImageResource(rid);
                    boardDisplay[x][y].setTag("i"+s[x][y].toLowerCase());
                }else{
                    boardDisplay[x][y].setImageResource(R.drawable.empty);
                    boardDisplay[x][y].setTag("empty");
                }
            }
        }
    }
    public void move(){
        if(in.length()==5){
            board b = chessBoard.getBoard();
            int[] ia = new int[2];
            ia = b.convert(in.substring(0,2));
            if(b.getPiece(ia[0],ia[1]).getType()== type.PAWN) {
                int[] iaa = b.convert(in.substring(3));
                if ((turn == 1 && iaa[1] == 0)|| (turn==0&&iaa[1]==7)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Chess.this);
                    View view = getLayoutInflater().inflate(R.layout.promotion,null);
                    builder.setView(view);
                    final AlertDialog dialog = builder.create();
                    Button queen = view.findViewById(R.id.q);
                    queen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            in = in+" Q";
                            move();
                            System.out.println(in);
                            in="";
                            dialog.dismiss();
                        }
                    });
                    Button bishop = view.findViewById(R.id.b);
                    bishop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            in = in+" B";
                            move();
                            System.out.println(in);
                            in="";
                            dialog.dismiss();
                        }
                    });
                    Button knight = view.findViewById(R.id.n);
                    knight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            in = in+" N";
                            move();
                            System.out.println(in);
                            in="";
                            dialog.dismiss();
                        }
                    });
                    Button rook = view.findViewById(R.id.r);
                    rook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            in = in+" R";
                            move();
                            System.out.println(in);
                            in="";
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return;
                }
            }
        }
        int i = chessBoard.move(in,turn);
        if(i==0){
            ts.setTextColor(Color.parseColor("#ff0000"));
            ts.setText("Illegal move, try again");
            in = "";
            update(chessBoard);
            return;
        }else if(i==20){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Black Resigned");
            builder.setMessage("White Wins");
            pb.add("Black Resigned, White wins");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    savedia();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }else if(i==10){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("White Resigned");
            builder.setMessage("Black Wins");
            pb.add("White Resigned, Black wins");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   savedia();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }else if(i==30){
            return;
        }else if(i==40){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Checkmate");
            builder.setMessage("White Wins");
            pb.add("Checkmate, White wins");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    savedia();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }else if(i==50){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Checkmate");
            builder.setMessage("Black Wins");
            pb.add("Checkmate, Black wins");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    savedia();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        if(turn==1){
            tc.setText("");
            turn=0;
            t.setText("Black's Move");
            if(i==1){
                tc.setTextColor(Color.parseColor("#ff0000"));
                tc.setText("Check");
            }
        }else{
            tc.setText("");
            turn=1;
            t.setText("White's Move");
            if(i==11){
                tc.setTextColor(Color.parseColor("#ff0000"));
                tc.setText("Check");
            }
        }
        once = 0;
        current.copy(last);
        chessBoard.copy(current);
        pb.add(in);
        in = "";
        update(chessBoard);
    }

    public void undo(){
        if(once==1){
            return;
        }else{
            once=1;
        }
        last.copy(current);
        last.copy(chessBoard);
        if(turn==1){
            tc.setText("");
            turn = 0;
            t.setText("Black's Move");
            if(chessBoard.getBc()==1){
                tc.setTextColor(Color.parseColor("#ff0000"));
                tc.setText("Check");
            }
        }else{
            tc.setText("");
            turn=1;
            t.setText("White's Move");
            if(chessBoard.getWC()==1){
                tc.setTextColor(Color.parseColor("#ff0000"));
                tc.setText("Check");
            }
        }
        pb.remove(pb.getList().size()-1);
        update(chessBoard);
    }
    public String AI(){
        String s = chessBoard.Suggestion(turn);
        String r = "You can move from "+s.substring(0,2)+" to "+s.substring(3)+".";
        return r;
    }
    public boolean checkIn(String s){
        for(int i=0; i<pbl.getList().size(); i++){
            if(pbl.getList().get(i).getName().equals(s)){
                return false;
            }
        }
        return true;
    }
    public void savedia(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Chess.this);
        builder.setTitle("Save this game");
        builder.setMessage("Do you want to save this game?");
        final EditText input = new EditText(Chess.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = input.getText().toString();
                if(checkIn(s)){
                    pb.setName(s);
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String formattedDate = df.format(c.getTime());
                    pb.setDate(formattedDate);
                    pbl.getList().add(pb);
                    try {
                        playBackList.writeData(Chess.this,pbl);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(Chess.this);
                    builder.setTitle("Successfully Saved");
                    builder.setMessage("Successfully Saved");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent menu = new Intent(getApplicationContext(),Menu.class);
                            startActivity(menu);
                        }
                    });
                    AlertDialog dialogg = builder.create();
                    dialogg.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Chess.this);
                    builder.setTitle("Invalid Input");
                    builder.setMessage("Name already existed");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            savedia();
                        }
                    });
                    final AlertDialog dialogg = builder.create();
                    dialogg.show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent menu = new Intent(getApplicationContext(),Menu.class);
                startActivity(menu);
            }
        });

        builder.show();
    }
}
