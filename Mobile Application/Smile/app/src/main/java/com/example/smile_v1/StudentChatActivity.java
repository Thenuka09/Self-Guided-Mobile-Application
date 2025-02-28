package com.example.smile_v1;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smile_v1.messages.MessageList;
import com.example.smile_v1.messages.MessagesAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentChatActivity extends AppCompatActivity {

    private final List<MessageList> messageLists = new ArrayList<>();

    private RecyclerView messageRecyclerView;


    private int unSeenMessages = 0;
    private String lastMessage = "";

    private String chatKey = "";

    private boolean dataset = false;


    private MessagesAdapter messagesAdapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chatapplication-7455c-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student_chat);

        messageRecyclerView =findViewById(R.id.messageRecyclerView);

        // get intent values
//        String name = getIntent().getStringExtra("name");
//        String email = getIntent().getStringExtra("email");
//        String mobile = getIntent().getStringExtra("mobile");

        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "N/A");
        String email = sharedPreferences.getString("email", "N/A");
        String mobile = sharedPreferences.getString("phone_number", "N/A");


        messageRecyclerView.setHasFixedSize(true);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set Adapter to Recycler view
        messagesAdapter = new MessagesAdapter(messageLists, StudentChatActivity.this);
        messageRecyclerView.setAdapter(messagesAdapter);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        //progressDialog.show();

        // get profile picture from the firebase
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                //final String profilePicUrl = snapshot.child("users").child(mobile).child("Profile_Pic").getValue(String.class);
////
////                if (profilePicUrl.isEmpty()){
////
////                    // set profile pic
////                    //Picasso.get().load(profilePicUrl).into(userProfilePic);
////
////                }
////
//                progressDialog.dismiss();
//           }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                progressDialog.dismiss();
//
//            }
//        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                messageLists.clear();

                unSeenMessages = 0;
                lastMessage = "";
                chatKey = "";


                for (DataSnapshot dataSnapshot : snapshot.child("users").getChildren()){

                    final String getMobile = dataSnapshot.getKey();
                    final String getName = dataSnapshot.child("name").getValue(String.class);

                    dataset = false;

                    if (!getMobile.equals(mobile)){

                        //final String getName = dataSnapshot.child("name").getValue(String.class);

                        databaseReference.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                int getChatCounts = (int)snapshot.getChildrenCount();

                                if (getChatCounts > 0){
                                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){

                                        final String getKey = dataSnapshot1.getKey();
                                        chatKey = getKey;

                                        if (dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("messages")){


                                            final String getuserOne = dataSnapshot1.child("user_1").getValue(String.class);
                                            final String getUserTwo = dataSnapshot1.child("user_2").getValue(String.class);

                                            if ((getuserOne.equals(getMobile) && getUserTwo.equals(mobile)) || (getuserOne.equals(mobile) && getUserTwo.equals(getMobile))){

                                                for (DataSnapshot chatDataSnapshot : dataSnapshot1.child("messages").getChildren()){

                                                    final long getMessageKey = Long.parseLong(chatDataSnapshot.getKey());
                                                    final long getLastSeenMessage = Long.parseLong(MemoryData.getLastMsgTs(StudentChatActivity.this,getKey));

                                                    lastMessage = chatDataSnapshot.child("msg").getValue(String.class);

                                                    if(getMessageKey > getLastSeenMessage){
                                                        unSeenMessages++;
                                                    }

                                                }
                                            }

                                        }

                                    }
                                }

                                if(!dataset){
                                    dataset = true;

                                    MessageList messageList = new MessageList(getName,getMobile,lastMessage,unSeenMessages, chatKey);
                                    messageLists.add(messageList);
                                    //messageRecyclerView.setAdapter(new MessagesAdapter(messageLists, StudentChatActivity.this));
                                    messagesAdapter.updateData(messageLists);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


//                        MessageList messageList = new MessageList(getName,getMobile,lastMessage,unSeenMessages);
//                        messageLists.add(messageList);

                    }

                    //messageRecyclerView.setAdapter(new MessagesAdapter(messageLists, StudentChatActivity.this));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}