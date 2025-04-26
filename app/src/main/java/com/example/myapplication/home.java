package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class home extends AppCompatActivity {

    private static final String TAG = "homeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize VideoView and play video
        final VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid));
        videoView.start();
        videoView.setOnCompletionListener(mp -> videoView.start()); // Set video to loop
        // Mute the video
        videoView.setOnPreparedListener(mp -> mp.setVolume(0f, 0f)); // Set volume to 0 for both left and right channels

        // Initialize cart ImageView and set OnClickListener
        ImageView cart = findViewById(R.id.appLogo);
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, activity_cart.class);
            startActivity(intent);
        });

        // Initialize logout ImageView and set OnClickListener
        ImageView logout = findViewById(R.id.navIcon);
        logout.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, activity_login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
        // Initialize CardViews
        CardView box1 = findViewById(R.id.box1);
        CardView box2 = findViewById(R.id.box2);
        CardView box3 = findViewById(R.id.box3);
        CardView box4 = findViewById(R.id.box4);

        // Set onClickListeners to navigate to respective activities
        box1.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, Cricket.class);
            startActivity(intent);
        });

        box2.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, activity_football.class);
            startActivity(intent);
        });

        box3.setOnClickListener(v -> {
            Intent intent = new Intent(home.this,activity_badminton.class);
            startActivity(intent);
        });

        box4.setOnClickListener(v -> {
            Intent intent = new Intent(home.this, activity_jersey.class);
            startActivity(intent);
        });
    }
}
