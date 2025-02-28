package com.example.smile_v1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class HotlineActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PERMISSION = 1;
    Button dial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the status bar invisible
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hotline);

        // Initialize the button
        dial = findViewById(R.id.dial);

        // Call the button
        dialBtn();
    }

    public void dialBtn() {
        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if CALL_PHONE permission is granted
                if (ContextCompat.checkSelfPermission(HotlineActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // Request permission
                    ActivityCompat.requestPermissions(HotlineActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
                } else {
                    // Permission already granted, make the call
                    makePhoneCall();
                }
            }
        });
    }

    private void makePhoneCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:1333"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                makePhoneCall();
            } else {
                // Permission denied
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}