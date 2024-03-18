package com.example.lose.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull; // Updated import
import androidx.appcompat.app.AlertDialog; // Updated import
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Updated import
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tann.vattana.lostandfoundapplication.Class.InternetConnectivityHandler;
import com.example.lose.R;

import java.util.PriorityQueue;

public class EditPasswordPage extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText passwordEditText;
    private EditText confirmPwdEditText;
    private TextView wrongPasswordTextView;
    private FirebaseDatabase database;
    private FirebaseUser firebaseUser;
    private DatabaseReference myRef;
    private AuthCredential credential;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password_page);

        initializedUI();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle passwordData = getIntent().getExtras();
        String password = passwordData.getString("Password");
        email = passwordData.getString("Email");
        passwordEditText.setText(password);
        confirmPwdEditText.setText(password);
    }

    private void changeUserPassword() {
        final String password = passwordEditText.getText().toString();
        String confirmPwd = confirmPwdEditText.getText().toString();

        if (password.isEmpty()) {
            passwordEditText.setError("Password is required.");
            passwordEditText.requestFocus();
            return;
        }

        if (confirmPwd.isEmpty()) {
            confirmPwdEditText.setError("Please confirm the new password.");
            confirmPwdEditText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordEditText.setError("The minimum length of password is 6 characters.");
            passwordEditText.requestFocus();
            return;
        }

        if (!confirmPwd.equals(password)) {
            wrongPasswordTextView.setText("Incorrect Password. Please try again.");
            passwordEditText.setText("");
            confirmPwdEditText.setText("");
            passwordEditText.requestFocus();
            return;
        }

        String id = firebaseUser.getUid();
        Log.d("ID", id);

        //Update password data in firebase database
        myRef.child(id).child("password").setValue(password);

        //Update password in Firebase Authentication
        credential = EmailAuthProvider.getCredential(email, password);
        firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("Re-authenticate", "User re-authenticate");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                        }
                    }
                });
            }
        });

        startActivity(new Intent(getApplicationContext(), ProfilePage.class));
        finish();
    }

    private void initializedUI() {
        toolbar = findViewById(R.id.actionBar); // Updated findViewById import
        passwordEditText = findViewById(R.id.editPasswordEditText); // Updated findViewById import
        confirmPwdEditText = findViewById(R.id.editConfirmPwdEditText); // Updated findViewById import
        wrongPasswordTextView = findViewById(R.id.wrongPasswordErrorTextView); // Updated findViewById import
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.saveBtn) {
            if (InternetConnectivityHandler.haveNetworkConnection(getApplicationContext())) {
                changeUserPassword();
            } else {
                showNoInternetDialog();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //Show no internet alert dialog
    private void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Internet Connection")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setCancelable(false)
                .setMessage("No Internet connection. Make sure that Wi-Fi or mobile data is turned on, then try again.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
