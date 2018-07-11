package com.example.strange.tempnote;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * Created by riyadh on 08-Jul-18.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> implements View.OnClickListener {

    public Context mContext;
    public Cursor mCursor;
    String title,note,exp;


    public NoteAdapter(Context context, Cursor cursor){
            mCursor = cursor;
            mContext = context;
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV, noteTV, expTV;

        public NoteViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCursor.moveToPosition(getAdapterPosition());
                    Toast.makeText(mContext,mCursor.getString(0),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext,display.class);
                    intent.putExtra("id",mCursor.getString(0));
                    intent.putExtra("title",mCursor.getString(1));
                    intent.putExtra("note",mCursor.getString(2));
                    intent.putExtra("min",mCursor.getString(4));
                    mContext.startActivity(intent);


                }
            });
            titleTV = itemView.findViewById(R.id.titleTV);
            noteTV = itemView.findViewById(R.id.noteTV);
            expTV = itemView.findViewById(R.id.expTV);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycletemplate,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        String title = mCursor.getString(1);
        String note = mCursor.getString(2);
        String min = mCursor.getString(4);

        holder.titleTV.setText(title);
        holder.noteTV.setText(note);
        holder.expTV.setText(String.valueOf(min));
        holder.itemView.setTag(mCursor.getString(0));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor (Cursor newcursor){
        if(mCursor!=null) mCursor.close();
        mCursor = newcursor;

    }
}
