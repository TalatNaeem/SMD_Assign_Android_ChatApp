package com.talat.i170213_i170208;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CallHistoryAdapter extends RecyclerView.Adapter<CallHistoryAdapter.CallHistoryViewHolder> {

    List<com.talat.i170213_i170208.CallRecord> list;
    Context context;

    public CallHistoryAdapter(List<com.talat.i170213_i170208.CallRecord> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CallHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.callrecord, parent, false);
        return new CallHistoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CallHistoryViewHolder holder, int position) {
        holder.name.setText(list.get(holder.getAdapterPosition()).getName());
        holder.status.setText(list.get(holder.getAdapterPosition()).getStatus());
        holder.time.setText(list.get(holder.getAdapterPosition()).getTime());
        holder.profile.setImageResource(list.get(holder.getAdapterPosition()).getImage());
        if (list.get(holder.getAdapterPosition()).getStatus() == "inbound") {
            holder.status_image.setImageResource(R.drawable.outgoing);
        }
        else {
            holder.status_image.setImageResource(R.drawable.missed_call);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Screen9.class);
                intent.putExtra("Name", list.get(holder.getAdapterPosition()).getName());
                intent.putExtra("Image", list.get(holder.getAdapterPosition()).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CallHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView name, status, time;
        ImageView profile, status_image;
        View view;

        public CallHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
            time = itemView.findViewById(R.id.time);
            profile = itemView.findViewById(R.id.profpic);
            status_image = itemView.findViewById(R.id.status_img);
            view = itemView;
        }
    }
}
