package com.example.strange.tempnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Write extends AppCompatActivity {

    EditText e1,e2,e3;
    String title,note;
    Button b1,b2;
    BaseFun fun = new BaseFun(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        e1 = (EditText) findViewById(R.id.titleET);
        e2 = (EditText) findViewById(R.id.noteET);
        b1 = (Button) findViewById(R.id.okBT);
        b2 = (Button) findViewById(R.id.cancelBT);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = e1.getText().toString();
                note = e2.getText().toString();
                boolean x = fun.addData(title,note,"infinity");
                if(x) Log.d("say","dhukse");
                else Log.d("say","dhuke nai");
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
