package com.example.strange.tempnote;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NoteAdapter mAdapter;
    private FloatingActionButton fab;
    BaseFun fun = new BaseFun(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        RecyclerView rv = findViewById(R.id.oneRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NoteAdapter(this,fun.getAllByCursor());
        rv.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Write.class));
            }
        });
    }
}
