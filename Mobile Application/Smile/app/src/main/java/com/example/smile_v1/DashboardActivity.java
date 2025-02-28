package com.example.smile_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class DashboardActivity extends AppCompatActivity {

    // References
    TextView txtName;

    ConstraintLayout anxityTest, chatWithCounsillor, activities, profile, hotline, testDepression;

    ImageView img_home, img_test, img_profile;

    // Database reference
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chatapplication-7455c-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        // Initialization
        txtName = findViewById(R.id.txtName);
        anxityTest = findViewById(R.id.anxityTest);
        chatWithCounsillor = findViewById(R.id.chatWithCounsillor);
        activities = findViewById(R.id.activities);
        profile = findViewById(R.id.profile);
        img_home = findViewById(R.id.img_home);
        img_test = findViewById(R.id.img_test);
        img_profile = findViewById(R.id.img_profile);
        hotline = findViewById(R.id.hotline);
        testDepression =  findViewById(R.id.testDepression);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "N/A");

        // Set profile values
        txtName.setText("Hi, " + name);

        // Calling the methods
        gotoTest();
        setChatWithCounsillor();
        goActivities();
        goProfile();
        setHome();
        setTest();
        setProfile();
        gotoHotline();
        gotoDepressionTest();
    }

    private void setProfile() {
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setTest() {
        img_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AnxitytestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setHome() {
        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    public void gotoTest() {
        anxityTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AnxitytestActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setChatWithCounsillor() {
        chatWithCounsillor.setOnClickListener(v -> {
            // Show loading dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
            builder.setCancelable(false)
                    .setMessage("Loading...");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            //check the user is already logged
            if (!MemoryData.getData(this).isEmpty()) {

                Intent intent = new Intent(DashboardActivity.this, StudentChatActivity.class);
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
                        if (snapshot.child("users").hasChild(phoneNumber)) {
                            Toast.makeText(DashboardActivity.this, "Mobile Already Exists", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            databaseReference.child("users").child(phoneNumber).child("email").setValue(email);
                            databaseReference.child("users").child(phoneNumber).child("name").setValue(name);

                            // save mobile to the memory
                            MemoryData.saveData(phoneNumber, DashboardActivity.this);

                            //save name to memory
                            MemoryData.saveName(name, DashboardActivity.this);

                            Toast.makeText(DashboardActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();

                            Intent intent = new Intent(DashboardActivity.this, StudentChatActivity.class);
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

    public void goActivities() {
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ActivitiesActivity.class);
                startActivity(intent);

            }
        });
    }

    public void goProfile() {
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void gotoHotline(){
        hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, HotlineActivity.class);
                startActivity(intent);
            }
        });
    }

    public void gotoDepressionTest(){
        testDepression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, DepressiontestActivity.class);
                startActivity(intent);
            }
        });
    }


}
