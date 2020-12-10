package com.stl.letsmeet.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stl.letsmeet.Preferences;
import com.stl.letsmeet.R;


public class RegisterActivity extends AppCompatActivity {


    private FireMissilesDialogFragment dialogFragment = new FireMissilesDialogFragment();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Button createButton = findViewById(R.id.registerLayout_register);
        final Button cancelButton = findViewById(R.id.registerLayout_cancel);


        final EditText firstNameInput = findViewById(R.id.firstName);
        final EditText lastNameInput = findViewById(R.id.lastName);
        final EditText emailInput = findViewById(R.id.promptEmail);
        final EditText postalCodeInput = findViewById(R.id.postalCode);
        final EditText passwordInput = findViewById(R.id.promptPassword);
        final EditText passwordRepeatInput = findViewById(R.id.promptPasswordAgain);
        final Button registerButton = findViewById(R.id.register_button);

        final String userEmail = null;

        Toast toast = Toast.makeText(getApplicationContext(), "RegisterActivity", Toast.LENGTH_LONG);
        toast.show();


        // Start Register activity
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firstNameInput.getText().toString().isEmpty() || lastNameInput.getText().toString().isEmpty() || emailInput.getText().toString().isEmpty() || postalCodeInput.getText().toString().isEmpty() ||
                        passwordInput.getText().toString().isEmpty() || passwordRepeatInput.getText().toString().isEmpty()) {

                    Toast toast = Toast.makeText(getApplicationContext(), "All fields were not filled", Toast.LENGTH_LONG);
                    toast.show();

                } else {

                    // Storing data into SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                    // Creating an Editor object
                    // to edit(write to the file)
                    SharedPreferences.Editor saveProfile = sharedPreferences.edit();

                    // Storing the key and its value
                    // as the data fetched from edittext
                    saveProfile.putString("firstName", firstNameInput.getText().toString());
                    saveProfile.putString("lastName", lastNameInput.getText().toString());
                    saveProfile.putString("email", emailInput.getText().toString());
                    saveProfile.putString("postalCode", postalCodeInput.getText().toString());
                    saveProfile.putString("password", passwordInput.getText().toString());


                    // Once the changes have been made,
                    // we need to commit (apply() will do the execution in the background) to apply those changes made,
                    // otherwise, it will throw an error @DuneZerna
                    saveProfile.apply();

                    Toast toast = Toast.makeText(getApplicationContext(), "Your profile was saved successfully", Toast.LENGTH_LONG);
                    toast.show();

                }

                Intent intent = new Intent(RegisterActivity.this, Preferences.class);
                startActivity(intent);

            }
        });



        // Cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}
