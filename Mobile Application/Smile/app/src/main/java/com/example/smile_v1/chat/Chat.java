package com.example.smile_v1.chat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smile_v1.MemoryData;
import com.example.smile_v1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Chat extends AppCompatActivity {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chatapplication-7455c-default-rtdb.firebaseio.com/");

    private final List<ChatList> chatLists = new ArrayList<>();

    private String chatKey;

    String getUserMobile = "";

    //String getOtherStudentMobile = "";

    private RecyclerView chattingRecyclerView;

    private ChatAdapter chatAdapter;

    private boolean loadingFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final ImageView backArrow = findViewById(R.id.backArrow);
        final TextView name = findViewById(R.id.name);
        final EditText messageText = findViewById(R.id.messageText);
        final ImageView sendBtn = findViewById(R.id.sendBtn);
        chattingRecyclerView = findViewById(R.id.chattingRecyclerView);

        // get data from message adapter class
        final String getName = getIntent().getStringExtra("name");
        chatKey = getIntent().getStringExtra("chatKey");
        final String getOtherStudentMobile = getIntent().getStringExtra("mobile");

        // get data from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
       // String getName = sharedPreferences.getString("name", "N/A");
        final String getMobile = sharedPreferences.getString("phone_number", "N/A");
//        chatKey = getIntent().getStringExtra("chatKey");


        // get user mobile from memory
        //getUserMobile = MemoryData.getData(Chat.this);
        //getUserMobile = getMobile;

        getUserMobile = getMobile;
        //getOtherStudentMobile = getOtherMobile;


        name.setText(getName);

        chattingRecyclerView.setHasFixedSize(true);
        chattingRecyclerView.setLayoutManager(new LinearLayoutManager(Chat.this));

        chatAdapter = new ChatAdapter(chatLists, Chat.this);

        chattingRecyclerView.setAdapter(chatAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (chatKey.isEmpty()) {
                    // generate chat key, default chat key is 1
                    chatKey = "1";

                }

                    if (snapshot.hasChild("chat")) {

                        if (snapshot.child("chat").child(chatKey).hasChild("messages")) {
                            chatLists.clear();

                            for (DataSnapshot messageSnapshot : snapshot.child("chat").child(chatKey).child("messages").getChildren()) {

                                if (messageSnapshot.hasChild("msg") && messageSnapshot.hasChild("mobile")) {
                                    String messageTimestamps = messageSnapshot.getKey();
                                    String getMobile = messageSnapshot.child("mobile").getValue(String.class);
                                    String getMsg = messageSnapshot.child("msg").getValue(String.class);


//                                    Timestamp timestamp = new Timestamp(Long.parseLong(messageTimestamps));
//                                    Date date = new Date(timestamp.toDate().getTime());
                                    long timeMillis = Long.parseLong(messageTimestamps) * 1000; // Convert to milliseconds
                                    Date date = new Date(timeMillis);
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
                                    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());


                                    ChatList chatList = new ChatList(getMobile, getName, getMsg, simpleDateFormat.format(date), simpleTimeFormat.format(date));
                                    chatLists.add(chatList);

                                    if (loadingFirstTime || Long.parseLong(messageTimestamps) > Long.parseLong(MemoryData.getLastMsgTs(Chat.this, chatKey))) {

                                        loadingFirstTime = false;

                                        MemoryData.saveLastMsgTs(messageTimestamps, chatKey, Chat.this);
                                        chatAdapter.updateChatList(chatLists);
                                        chatAdapter.notifyDataSetChanged();

                                        chattingRecyclerView.scrollToPosition(chatLists.size() - 1);
                                    }


                                }

                            }
                        }
                    }

//
//                if (snapshot.hasChild("chat")) {
//                    if (snapshot.child("chat").child(chatKey).hasChild("messages")) {
//                        snapshot.child("chat").child(chatKey).child("messages");
//                    }
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getTxtMessage = messageText.getText().toString();

                if (!getTxtMessage.isEmpty()){
                    // get current time
                    String currentTimeStamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);

                    databaseReference.child("chat").child(chatKey).child("user_1").setValue(getUserMobile);
                    databaseReference.child("chat").child(chatKey).child("user_2").setValue(getOtherStudentMobile);
                    databaseReference.child("chat").child(chatKey).child("messages").child(currentTimeStamp).child("msg").setValue(getTxtMessage);
                    databaseReference.child("chat").child(chatKey).child("messages").child(currentTimeStamp).child("mobile").setValue(getUserMobile);

                    // clear the message text after send the message
                    messageText.setText("");

                }
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}