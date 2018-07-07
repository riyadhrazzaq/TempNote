package com.example.strange.tempnote;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by riyadh on 08-Jul-18.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context mContext;
    private Cursor mCursor;


    public NoteAdapter(Context context, Cursor cursor){
            mCursor = cursor;
            mContext = context;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV, noteTV, expTV;

        public NoteViewHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            noteTV = itemView.findViewById(R.id.noteTV);
            expTV = itemView.findViewById(R.id.expTV);
        }
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
        String exp = mCursor.getString(3);

        holder.titleTV.setText(title);
        holder.noteTV.setText(note);
        holder.expTV.setText(exp);
    }

    @Override
    public int getItemCount() {
        return mCursor.getColumnCount();
    }
    public void swapCursor (Cursor newcursor){
        if(mCursor!=null) mCursor.close();
        mCursor = newcursor;
        if(newcursor!=null) notifyDataSetChanged();
    }
}
