package com.cs.cs213.androidchess91.ViewController;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cs.cs213.androidchess91.Data.playBack;
import com.cs.cs213.androidchess91.Data.playBackList;
import com.cs.cs213.androidchess91.R;
import com.cs.cs213.androidchess91.board.ruleController;

import java.io.IOException;
import java.util.ArrayList;

public class mPlay extends AppCompatActivity {
    ArrayList<playBack> al;
    ruleController r;
    String in;
    int turn;
    int index;
    TextView t;
    TextView tc;
    playBack pb;
    TextView ts;
    private final ImageButton[][] boardDisplay;

    public mPlay() {
        boardDisplay = new ImageButton[8][8];
        in="";
        turn = 1;
        index=0;
        r = new ruleController();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_play);
        ts = findViewById(R.id.Tstatus);
        Bundle b = getIntent().getExtras();
        String name = b.getString("select");
        Button next = findViewById(R.id.nxt);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });
        t = findViewById(R.id.Tturn);
        tc = findViewById(R.id.Tcheck);
        al = null;
        try {
            al = playBackList.loadData(mPlay.this).getList();
        } catch (IOException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Something went Wrong");
            builder.setMessage("Please try to restart the game");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent menu = new Intent(getApplicationContext(),Menu.class);
                    startActivity(menu);
                }
            });
            AlertDialog dialogg = builder.create();
            dialogg.show();
        } catch (ClassNotFoundException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Something went Wrong");
            builder.setMessage("Please try to restart the game");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent menu = new Intent(getApplicationContext(),Menu.class);
                    startActivity(menu);
                }
            });
            AlertDialog dialogg = builder.create();
            dialogg.show();
        }
        for(int i = 0; i<al.size(); i++){
            if(al.get(i).getName().equals(name)){
                pb = al.get(i);
                break;
            }
        }
        for(int x=0;x<=7;x++){
            char c = (char) ('a'+x);
            for(int y=0; y<=7; y++){
                String s = c + Integer.toString(8-y);
                int rid = getResources().getIdentifier(s,"id",getPackageName());
                boardDisplay[x][y] = findViewById(rid);
                final ImageButton tmp = boardDisplay[x][y];
            }
        }

        update();

    }
    public void move(){
        int i = r.move(in,turn);
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
        update();
    }
    public void update(){
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
    public void next(){
        if(index<pb.getList().size()){
            if(index==pb.getList().size()-1){
                String s = pb.getList().get(index);
                ts.setTextColor(Color.parseColor("#ff0000"));
                ts.setText(s);
            }else{
                in = pb.getList().get(index);
                move();
                index++;
            }
        }
    }
}
