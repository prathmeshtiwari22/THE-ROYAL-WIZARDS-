package com.example.meshchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDisasterOptions(View view) {
        // Handle click on Disaster button
        // Here you can implement logic to show a list of disaster options
        // For now, let's assume you have selected a disaster and its information
        String selectedDisasterName = "Earthquake";
        String selectedDisasterInfo = "Information about the earthquake.";

        // Create an Intent to start DisasterDetailsActivity
        Intent intent = new Intent(MainActivity.this, DisasterDetailsActivity.class);
        // Pass the selected disaster name and information to DisasterDetailsActivity
        intent.putExtra("disasterName", selectedDisasterName);
        intent.putExtra("disasterInfo", selectedDisasterInfo);
        // Start the activity
        startActivity(intent);
    }

    public void showTreatmentOptions(View view) {
        // Handle click on Treatment button
        Toast.makeText(this, "Displaying treatment options", Toast.LENGTH_SHORT).show();
        // Here you can implement logic to show a list of treatment options
        // and handle clicks on those options to display their information
    }
}
