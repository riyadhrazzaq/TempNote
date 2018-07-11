package com.example.strange.tempnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class display extends AppCompatActivity {

    EditText e1, e2;
    TextView t1;
    Button b1, b2;
    BaseFun fun = new BaseFun(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String note = intent.getStringExtra("note");
        String min = intent.getStringExtra("min");
        final String id = intent.getStringExtra("id");

        e1 = findViewById(R.id.titleDisplayET);
        e2 = findViewById(R.id.noteDisplayET);
        t1 = findViewById(R.id.clockDisplayTV);

        e1.setText(title);
        e2.setText(note);
        t1.setText(min + " min");
        b1 = (Button) findViewById(R.id.okDisplayBT);
        b2 = (Button) findViewById(R.id.cancelDisplayBT);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fun.update(id, e1.getText().toString(), e2.getText().toString());
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

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "ddafuc", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
