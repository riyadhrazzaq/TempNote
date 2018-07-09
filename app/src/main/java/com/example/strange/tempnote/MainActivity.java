package com.example.strange.tempnote;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    BaseFun fun = new BaseFun(this);
    private NoteAdapter mAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView Initiate
        rv = findViewById(R.id.oneRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NoteAdapter(this, fun.getAllByCursor());

        rv.setAdapter(mAdapter);



        //FloatingActionBar initiate
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Write.class));
            }
        });
        //Remove on swipe for RecyclerView
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                fun.removeById(Integer.parseInt(viewHolder.itemView.getTag().toString()));
                mAdapter.notifyDataSetChanged();

                recreate();

            }
        }).attachToRecyclerView(rv);



    }
}
