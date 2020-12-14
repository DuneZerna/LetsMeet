package com.stl.letsmeet.ui.login;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stl.letsmeet.Preferences;
import com.stl.letsmeet.Profile;
import com.stl.letsmeet.R;
import com.stl.letsmeet.ui.RecyclerView.MyAdapter;

public class LoginActivity extends AppCompatActivity {



    public boolean model;
    RecyclerView recyclerView;
    String s1[], s2[], s3[], s4[];

    int images[] = {R.drawable.chess,R.drawable.dumbbell,R.drawable.football,R.drawable.football,R.drawable.running,
            R.drawable.running,R.drawable.pokemon_go,R.drawable.card_game,R.drawable.binoculars,R.drawable.bowling};

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register_button);
        //final Button recyclerViewButton = findViewById(R.id.recyclerView_button);
        //final Button chatButton = findViewById(R.id.dontMindMe);
        //final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        recyclerView = findViewById(R.id.recyclerView2);

        s1 = getResources().getStringArray(R.array.titles);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.category);
        s4 = getResources().getStringArray(R.array.date);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, s4, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
           Using AlertBox to manage user sessions.
         */
        // Used for session checking
        final SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        if (!sharedPreferences.getString("email", "").isEmpty() &&
                sharedPreferences.getBoolean("newUser",false) == false){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            alertDialogBuilder.setTitle("In session");
            alertDialogBuilder.setIcon(R.drawable.letsmeet_background);
            alertDialogBuilder.setMessage("We have detected you already have a login, do you want to log in user that profile?");
            alertDialogBuilder.setCancelable(false);

            // No button
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().clear().apply();
                    Toast toast = Toast.makeText(getApplicationContext(), "Cache has been cleared", Toast.LENGTH_LONG);
                    toast.show();
                }
            });

            // Yes button
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor saveProfile = sharedPreferences.edit();
                    saveProfile.putBoolean("newUser",false);
                    saveProfile.apply();

                    Intent intent;
                    intent = new Intent(LoginActivity.this, Profile.class);
                    startActivity(intent);
                }
            });


            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        /*
           The method for validating user login and managing route for new users.
         */
        // Login activity
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String parsed = usernameEditText.getText().toString().toLowerCase();
                String stored = sharedPreferences.getString("email","").toLowerCase();


                // Dune: Only user is validated for now
                if (parsed.equals(stored)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG);
                    toast.show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertDialogBuilder.setTitle("New user?");
                    alertDialogBuilder.setIcon(R.drawable.letsmeet_background);
                    alertDialogBuilder.setMessage("Are you new to LetsMeet?");
                    alertDialogBuilder.setCancelable(false);

                    // No button
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent;
                            intent = new Intent(LoginActivity.this, Profile.class);
                            startActivity(intent);
                        }
                    });

                    // Yes button
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent;
                            intent = new Intent(LoginActivity.this, Preferences.class);
                            startActivity(intent);
                        }
                    });


                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Login failed : " + sharedPreferences.getString("email",""), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


        // Start Register activity
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model = true;
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }

        });


        // Opens up the recyclerView Activity
        /*
        recyclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RecyclerViewMain.class);
                startActivity(intent);
            }
        });
        */
        /*
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        */
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });


        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                //loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });
        /*
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
        */

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}