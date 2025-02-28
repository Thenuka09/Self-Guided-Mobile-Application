package com.example.smile_v1.messages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smile_v1.R;
import com.example.smile_v1.chat.Chat;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {

    private  List<MessageList> messageLists;
    private final Context context;

    public MessagesAdapter(List<MessageList> messageLists, Context context) {
        this.messageLists = messageLists;
        this.context = context;
    }

    @NonNull
    @Override
    public MessagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MyViewHolder holder, int position) {

        MessageList list2 = messageLists.get(position);

        holder.name.setText(list2.getName());
        holder.lastMessage.setText((list2.getLastMessage()));

        if(list2.getUnSeenMessage() == 0){
            holder.unSeenMesssage.setVisibility(View.GONE);
            holder.lastMessage.setTextColor(Color.parseColor("#959595"));
        }else {
            holder.unSeenMesssage.setVisibility(View.VISIBLE);
            holder.unSeenMesssage.setText(list2.getUnSeenMessage()+"");
            holder.lastMessage.setTextColor(context.getResources().getColor(R.color.splash_screen_blue));
        }

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chat.class);
                intent.putExtra("mobile", list2.getMobile());
                intent.putExtra("name", list2.getName());
                intent.putExtra("chatKey", list2.getChatKey());
                context.startActivity(intent);
            }
        });

    }

    public void updateData(List<MessageList> messageLists){
        this.messageLists = messageLists;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return messageLists.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView lastMessage;
        private TextView unSeenMesssage;
        private LinearLayout rootLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views
            name = itemView.findViewById(R.id.name);
            lastMessage = itemView.findViewById(R.id.lastMessage);
            unSeenMesssage = itemView.findViewById(R.id.unSeenMesssage);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }
    }
}
