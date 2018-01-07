package com.cs.cs213.androidchess91.ViewController;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cs.cs213.androidchess91.Data.CBD;
import com.cs.cs213.androidchess91.Data.CBN;
import com.cs.cs213.androidchess91.Data.playBack;
import com.cs.cs213.androidchess91.Data.playBackList;
import com.cs.cs213.androidchess91.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Replay extends AppCompatActivity {
    private ListView listView;
    ArrayList<playBack> al;
    ArrayAdapter<playBack> pba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);
        listView = findViewById(R.id.listView);
        al = null;
        playBackList pbl = null;
        try{
            pbl = playBackList.loadData(Replay.this);
        } catch (IOException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Replay.this);
            builder.setTitle("Nothing to see here");
            builder.setMessage("No game has been saved, please play some rounds first");
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
            AlertDialog.Builder builder = new AlertDialog.Builder(Replay.this);
            builder.setTitle("Nothing to see here");
            builder.setMessage("No game has been saved, please play some rounds first");
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
        TextView t = findViewById(R.id.Tturn);
        if(pbl==null||pbl.getList().size()<1){
            System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
            AlertDialog.Builder builder = new AlertDialog.Builder(Replay.this);
            builder.setTitle("Nothing to see here");
            builder.setMessage("No game has been saved, please play some rounds first");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent menu = new Intent(getApplicationContext(),Menu.class);
                    startActivity(menu);
                }
            });
            AlertDialog dialogg = builder.create();
            dialogg.show();
            return;
        }
        al = pbl.getList();
        Collections.sort(al,new CBN());
        pba = new ArrayAdapter<playBack>(this,android.R.layout.simple_list_item_1,al);
        listView.setAdapter(pba);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = al.get(i).getName();
                showGame(s);
            }
        });
        Button sbn = findViewById(R.id.sbn);
        Button sbd = findViewById(R.id.sbd);
        sbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(al, new CBN());
                pba.notifyDataSetChanged();
            }
        });
        sbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(al, new CBD());
                pba.notifyDataSetChanged();
            }
        });
    }
    public void showGame(String s){
        Intent intent = new Intent(getApplicationContext(), mPlay.class);
        intent.putExtra("select",s );
        startActivity(intent);
    }
}
