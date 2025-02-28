package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class CounsilorWelcomeActivity extends AppCompatActivity {

    // Initialization
    Button btnLetsChat;
    TextView txtName;

    ImageView logout;

    // Database reference
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chatapplication-7455c-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //invisible the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_counsilor_welcome);

        // Reference
        btnLetsChat = findViewById(R.id.btnLetsChat);
        txtName = findViewById(R.id.txtName);
        logout = findViewById(R.id.logout);

        // display the name using shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession",MODE_PRIVATE);
        String name =  sharedPreferences.getString("name", "N/A");

        // set the name
        txtName.setText("Hi, "+name);

        // call the method
        setChatWithCounselor();

        // call the setLogout method
        setLogout();


    }

    public void setChatWithCounselor() {
        btnLetsChat.setOnClickListener(v -> {
            // Show loading dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(CounsilorWelcomeActivity.this);
            builder.setCancelable(false)
                    .setMessage("Loading...");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            //check the user is already logged
            if(!MemoryData.getData(this).isEmpty()){

                Intent intent = new Intent(CounsilorWelcomeActivity.this, StudentChatActivity.class);
                intent.putExtra("mobile", MemoryData.getData(this));
                intent.putExtra("name", MemoryData.getName(this));
                intent.putExtra("email", "");
                startActivity(intent);
                //finish();

                alertDialog.dismiss();
            }

            // Get user data from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
            String name = sharedPreferences.getString("name", "N/A");
            String email = sharedPreferences.getString("email", "N/A");
            String phoneNumber = sharedPreferences.getString("phone_number", "N/A");

            Log.d("StudentName", name);
            Log.d("StudentEmail", email);
            Log.d("StudentPhoneNumber", phoneNumber);

            // Check if required data is present
            if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
                Toasty.error(getApplicationContext(), "All fields are required", Toasty.LENGTH_SHORT).show();
                alertDialog.dismiss();
            } else {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                    //alertDialog.dismiss();

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child("users").hasChild(phoneNumber)){
                            Toast.makeText(CounsilorWelcomeActivity.this, "Mobile Already Exists", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }

                        else {
                            databaseReference.child("users").child(phoneNumber).child("email").setValue(email);
                            databaseReference.child("users").child(phoneNumber).child("name").setValue(name);

                            // save mobile to the memory
                            MemoryData.saveData(phoneNumber, CounsilorWelcomeActivity.this);

                            //save name to memory
                            MemoryData.saveName(name,CounsilorWelcomeActivity.this);

                            Toast.makeText(CounsilorWelcomeActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();

                            Intent intent = new Intent(CounsilorWelcomeActivity.this, StudentChatActivity.class);
//                            intent.putExtra("mobile", phoneNumber);
//                            intent.putExtra("name", name);
//                            intent.putExtra("email", email);
                            startActivity(intent);
                            //finish();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        alertDialog.dismiss();
                    }
                });

            }
        });
    }

    public void setLogout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // clear the shared preferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // redirect to the login page
                Intent intent = new Intent(CounsilorWelcomeActivity.this, Login.class);
                startActivity(intent);
                Toasty.success(CounsilorWelcomeActivity.this, "Logout Success",Toasty.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}