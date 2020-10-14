package com.stl.letsmeet.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.stl.letsmeet.Profile;
import com.stl.letsmeet.R;


public class RegisterActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button createButton = findViewById(R.id.registerLayout_register);
        final Button cancelButton = findViewById(R.id.registerLayout_cancel);

        Toast toast = Toast.makeText(getApplicationContext(), "RegisterActivity", Toast.LENGTH_LONG);
        toast.show();

        // Start Register activity
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
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


        final EditText firstNameInput = findViewById(R.id.firstName);
        final EditText lastNameInput = findViewById(R.id.lastName);
        final EditText emailInput = findViewById(R.id.promptEmail);
        final EditText postalCodeInput = findViewById(R.id.postalCode);
        final EditText passwordInput = findViewById(R.id.promptPassword);
        final EditText passwordRepeatInput = findViewById(R.id.promptPasswordAgain);
        final Button registerButton = findViewById(R.id.register_button);

    }
}
