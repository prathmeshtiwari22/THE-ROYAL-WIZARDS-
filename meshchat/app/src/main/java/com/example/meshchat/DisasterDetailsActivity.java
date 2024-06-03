package com.example.meshchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class DisasterDetailsActivity extends AppCompatActivity {

    LinearLayout iv1,iv2,iv3,iv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disaster_details);

        // Find the LinearLayout view by its id
        iv1 = findViewById(R.id.layoutLandscape);

        // Set an onClickListener for the LinearLayout
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(DisasterDetailsActivity.this, LandscapeActivity.class);
                // Start the LandscapeActivity
                startActivity(intent);
            }
        });
        iv2 = findViewById(R.id.layoutEarthquake);

        // Set an onClickListener for the LinearLayout
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(DisasterDetailsActivity.this, EarthquakeActivity.class);
                // Start the LandscapeActivity
                startActivity(intent);
            }
        });
        iv4 = findViewById(R.id.layoutCyclone);

        // Set an onClickListener for the LinearLayout
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(DisasterDetailsActivity.this, CycloneActivity.class);
                // Start the LandscapeActivity
                startActivity(intent);
            }
        });
        iv3 = findViewById(R.id.layoutFlood);

        // Set an onClickListener for the LinearLayout
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(DisasterDetailsActivity.this, FloodActivity.class);
                // Start the LandscapeActivity
                startActivity(intent);
            }
        });
    }
}
