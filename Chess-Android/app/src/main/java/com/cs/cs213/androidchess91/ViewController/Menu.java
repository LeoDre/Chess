package com.cs.cs213.androidchess91.ViewController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cs.cs213.androidchess91.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playB = findViewById(R.id.sButton);
        Button replayB = findViewById(R.id.rButton);
        playB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chess = new Intent(getApplicationContext(),Chess.class);
                startActivity(chess);
            }
        });
        replayB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent rp = new Intent(getApplicationContext(), Replay.class);
                startActivity(rp);
            }
        });

    }
}
