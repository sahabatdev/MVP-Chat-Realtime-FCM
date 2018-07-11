package com.sahabatdeveloper.realtimechatsahabatdeveloper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    List<ChatMessage> listChat;
    Context context;

    public ChatAdapter(List<ChatMessage> listChat, Context context) {
        this.listChat = listChat;
        this.context = context;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat,parent,false));
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        ChatMessage chat = listChat.get(position);
        if(chat.getDari().contains(MainActivity.DARI_USERNAME)){
            holder.linearDari.setVisibility(View.VISIBLE);
            holder.linearKe.setVisibility(View.GONE);
            
            holder.tvNamaDari.setText(chat.getDari());
            holder.tvPesanDari.setText(chat.getPesan());
        }else{
            holder.linearDari.setVisibility(View.GONE);
            holder.linearKe.setVisibility(View.VISIBLE);

            holder.tvNamaKe.setText(chat.getDari());
            holder.tvPesanKe.setText(chat.getPesan());
        }
    }

    @Override
    public int getItemCount() {
        return listChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearDari, linearKe;
        TextView tvNamaDari, tvPesanDari, tvNamaKe, tvPesanKe;

        public ViewHolder(View itemView) {
            super(itemView);
            linearDari = (LinearLayout) itemView.findViewById(R.id.linear_dari);
            linearKe = (LinearLayout) itemView.findViewById(R.id.linear_ke);

            tvNamaDari = (TextView) itemView.findViewById(R.id.nama_dari);
            tvNamaKe = (TextView) itemView.findViewById(R.id.nama_ke);

            tvPesanDari = (TextView) itemView.findViewById(R.id.pesan_dari);
            tvPesanKe = (TextView) itemView.findViewById(R.id.pesan_ke);


        }
    }
}
