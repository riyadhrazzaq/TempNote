package com.example.strange.tempnote;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {
    public static NoteAdapter mAdapter;
    RecyclerView rv;
    BaseFun fun = new BaseFun(this);
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        //AlarmManager Setup

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {


            int perSec = 60;
            AlarmManager processTimer = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent = new Intent(this, processTimerReciever.class);
            PendingIntent pintent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            processTimer.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), perSec * 1000, pintent);


            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }


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
                mAdapter.swapCursor(fun.getAllByCursor());
                mAdapter.notifyDataSetChanged();

            }
        }).attachToRecyclerView(rv);
        rv.setNestedScrollingEnabled(true);

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }


}
