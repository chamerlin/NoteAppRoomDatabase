package com.example.noteappdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends ListAdapter<Notes, NoteAdapter.NoteHolder>  {

    public NoteAdapter(){
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Notes> DIFF_CALLBACK = new DiffUtil.ItemCallback<Notes>() {
        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())&&
                    oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    private OnItemClickListener listener;


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_bg, parent, false);
        return new NoteHolder(itemView);
    }

    public void onBindViewHolder(@NonNull NoteHolder holder, int position){
        Notes currentNote = getItem(position);
        holder.tvTitle.setText(currentNote.getTitle());
    }

    public Notes getNoteAt(int position){
        return getItem(position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }



    public interface OnItemClickListener{
        void onItemClick(Notes note);
    }


    public class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

}
