// SurgeryDetailsActivity.java
package com.example.doctorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SurgeryDetailsActivity extends AppCompatActivity {

    private TextView surgeryNameTextView, surgeryDescriptionTextView;
    private Button bookAppointmentButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surgery_details_card);

        surgeryNameTextView = findViewById(R.id.surgeryNameTextView);
        surgeryDescriptionTextView = findViewById(R.id.surgeryDescriptionTextView);
        bookAppointmentButton = findViewById(R.id.bookAppointmentButton);

        dbHelper = new DatabaseHelper(this);

        // Fetch surgery details from intent extras or database and set them in TextViews
        String surgeryName = "Heart Surgery"; // Example surgery name
        String surgeryDescription = "This surgery involves the repair of heart defects."; // Example description

        surgeryNameTextView.setText(surgeryName);
        surgeryDescriptionTextView.setText(surgeryDescription);

        bookAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle booking appointment logic here
                bookAppointment();
            }
        });
    }

    private void bookAppointment() {
        String surgeryName = surgeryNameTextView.getText().toString().trim();
        String surgeryDescription = surgeryDescriptionTextView.getText().toString().trim();

        boolean isInserted = dbHelper.addAppointment(surgeryName, surgeryDescription);

        if (isInserted) {
            Toast.makeText(this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to book appointment. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
