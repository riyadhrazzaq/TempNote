package com.example.strange.tempnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Write extends AppCompatActivity {

    EditText e1, e2, e3;
    String title, note;
    Button b1, b2, b3, b4;
    TextView t1;
    BaseFun fun = new BaseFun(this);
    private int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        t1 = (TextView) findViewById(R.id.clockTV);
        e1 = (EditText) findViewById(R.id.titleET);
        e2 = (EditText) findViewById(R.id.noteET);
        b1 = (Button) findViewById(R.id.okBT);
        b2 = (Button) findViewById(R.id.cancelBT);
        b3 = (Button) findViewById(R.id.upBT);
        b4 = (Button) findViewById(R.id.downBT);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = e1.getText().toString();
                note = e2.getText().toString();
                String exp = t1.getText().toString();
                String min = exp;
                if (!exp.equalsIgnoreCase("Infinity")) {
                    long time = System.currentTimeMillis() + (long) ((Integer.parseInt(exp)) * 60000);
                    boolean x = fun.addData(title, note, String.valueOf(time), min);
                } else {
                    boolean x = fun.addData(title, note, exp, min);
                }
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (time < 10)
                    t1.setText(++time + "");

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (time <= 1) {
                    t1.setText("Infinity");
                    time = 0;
                } else t1.setText(--time + "");
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}
