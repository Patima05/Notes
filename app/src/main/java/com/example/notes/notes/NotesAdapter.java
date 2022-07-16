package com.example.notes.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Note;
import com.example.notes.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note>list;

    public void setListener(NotesClickListener listener) {
        this.listener = listener;
    }

    private NotesClickListener listener;

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.getItemView().<TextView>findViewById(R.id.text_view_item).setText((list.get(position).getTitle()));
        holder.getItemView().<ImageView>findViewById(R.id.image_view_item).setImageResource(list.get(position).getImageIndex());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<Note> getList() {
        return list;
    }

    public void setList(List<Note> list) {
        this.list = list;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            itemView.findViewById(R.id.text_view_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTextViewClick(getAdapterPosition());
                }
            });

            itemView.findViewById(R.id.image_view_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onImageClick(list.get(getAdapterPosition()));
                }
            });
        }

        public View getItemView() {
            return itemView;
        }
    }
}
