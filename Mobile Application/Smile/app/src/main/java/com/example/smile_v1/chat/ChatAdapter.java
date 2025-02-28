package com.example.smile_v1.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smile_v1.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private List<ChatList> chatLists;
    private final Context context;
    private String userMobile;

    public ChatAdapter(List<ChatList> chatLists, Context context) {
        this.chatLists = chatLists;
        this.context = context;
        //this.userMobile = MemoryData.getData(context);

        // Retrieve the user mobile number from SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        this.userMobile = sharedPreferences.getString("phone_number", "N/A"); // Default value is "N/A" if not found
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adpter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {

        ChatList chat = chatLists.get(position);

        if (chat.getMobile().equals(userMobile)){
            holder.studentLayout.setVisibility(View.VISIBLE);
            holder.counsilorLayout.setVisibility(View.GONE);

            holder.studentMessage.setText(chat.getMessage());
            holder.studentMessageTime.setText(chat.getDate()+" "+chat.getTime());

        }else {

            holder.studentLayout.setVisibility(View.GONE);
            holder.counsilorLayout.setVisibility(View.VISIBLE);

            holder.councilorMessage.setText(chat.getMessage());
            holder.councilorMessageTime.setText(chat.getDate()+" "+chat.getTime());

        }

    }

    @Override
    public int getItemCount() {
        return chatLists.size();
    }

    public void updateChatList(List<ChatList> chatLists){

        this.chatLists = chatLists;
        notifyDataSetChanged();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout counsilorLayout, studentLayout;
        private TextView councilorMessage, studentMessage;
        private TextView councilorMessageTime, studentMessageTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            counsilorLayout = itemView.findViewById(R.id.counsilorLayout);
            studentLayout = itemView.findViewById(R.id.studentLayout);
            councilorMessage = itemView.findViewById(R.id.councilorMessage);
            studentMessage = itemView.findViewById(R.id.studentMessage);
            councilorMessageTime = itemView.findViewById(R.id.councilorMessageTime);
            studentMessageTime = itemView.findViewById(R.id.studentMessageTime);
        }
    }
}
