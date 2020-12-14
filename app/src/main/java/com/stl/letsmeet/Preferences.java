package com.stl.letsmeet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Preferences extends AppCompatActivity{

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;

    /**
     * UI Variables
     */
    CheckBox mCheckBox1, mCheckBox2, mCheckBox3;

    int questionsAnswered = 0;

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            /*
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
             */
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (AUTO_HIDE) {
                        delayedHide(AUTO_HIDE_DELAY_MILLIS);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;
                default:
                    break;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preferences);


        // Read from SharedPref(mySharedPref)
        final TextView welcomeText;
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String firstName = sharedPreferences.getString("firstName","");
        welcomeText = findViewById(R.id.welcome_text);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set 1
        mCheckBox1 = findViewById(R.id.checkBox1);
        mCheckBox2 = findViewById(R.id.checkBox2);
        mCheckBox3 = findViewById(R.id.checkBox3);

        mCheckBox1.setVisibility(View.INVISIBLE);
        mCheckBox2.setVisibility(View.INVISIBLE);
        mCheckBox3.setVisibility(View.INVISIBLE);

        welcomeText.setText("Welcome " + firstName.toUpperCase());

        /**
         * Text delay
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                welcomeText.setText("On friday night, what would like to do?");
                mCheckBox1.setText("Go for a run");
                mCheckBox2.setText("Try to eat as much pizza as possible");
                mCheckBox3.setText("Party like rebel amish");
            }
        }, 5000);

        /**
         * Animate checkboxes
         */
        Handler animate = new Handler();
        animate.postDelayed(new Runnable(){
            @Override
            public void run() {
                mCheckBox1.setVisibility(View.VISIBLE);
                mCheckBox2.setVisibility(View.VISIBLE);
                mCheckBox3.setVisibility(View.VISIBLE);
            }
        }, 8000);

        /**
         *
         */


        mCheckBox1.setOnClickListener(new View.OnClickListener() {
            Handler animate = new Handler();
            @Override
            public void onClick(View v) {

                if ( ((CheckBox)v).isChecked() ) {
                    welcomeText.setText(((CheckBox)v).getText().toString() +"\nNice choice!");
                    questionsAnswered++;
                    mCheckBox1.setChecked(false);
                    mCheckBox2.setChecked(false);
                    mCheckBox3.setChecked(false);
                    mCheckBox1.setVisibility(View.INVISIBLE);
                    mCheckBox2.setVisibility(View.INVISIBLE);
                    mCheckBox3.setVisibility(View.INVISIBLE);

                    animate.postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            if(questionsAnswered == 1){
                                mCheckBox1.setChecked(false);
                                mCheckBox2.setChecked(false);
                                mCheckBox3.setChecked(false);
                                welcomeText.setText("On saturday morning, what would like to do?");
                                mCheckBox1.setText("Put my clothes back on");
                                mCheckBox2.setText("Construct a DIY robot");
                                mCheckBox3.setText("MMA");

                                mCheckBox1.setVisibility(View.VISIBLE);
                                mCheckBox2.setVisibility(View.VISIBLE);
                                mCheckBox3.setVisibility(View.VISIBLE);
                            } else if (questionsAnswered == 2){
                                mCheckBox1.setChecked(false);
                                mCheckBox2.setChecked(false);
                                mCheckBox3.setChecked(false);
                                welcomeText.setText("On sunday morning, what would like to do?");
                                mCheckBox1.setText("Firat");
                                mCheckBox2.setText("Dune");
                                mCheckBox3.setText("Daniel");

                                mCheckBox1.setVisibility(View.VISIBLE);
                                mCheckBox2.setVisibility(View.VISIBLE);
                                mCheckBox3.setVisibility(View.VISIBLE);
                            } else if (questionsAnswered == 3){
                                Intent intent = new Intent(Preferences.this, Profile.class);
                                startActivity(intent);
                            }
                        }
                    }, 3000);
                }
            }
        });


        mCheckBox2.setOnClickListener(new View.OnClickListener() {
            Handler animate = new Handler();


            @Override
            public void onClick(View v) {
                if ( ((CheckBox)v).isChecked() && questionsAnswered == 0) {
                    welcomeText.setText(((CheckBox)v).getText().toString() +"\nNice choice!");
                    questionsAnswered++;
                    mCheckBox1.setChecked(false);
                    mCheckBox2.setChecked(false);
                    mCheckBox3.setChecked(false);
                    mCheckBox1.setVisibility(View.INVISIBLE);
                    mCheckBox2.setVisibility(View.INVISIBLE);
                    mCheckBox3.setVisibility(View.INVISIBLE);

                    animate.postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            if(questionsAnswered == 1){
                                mCheckBox1.setChecked(false);
                                mCheckBox2.setChecked(false);
                                mCheckBox3.setChecked(false);
                                welcomeText.setText("On saturday morning, what would like to do?");
                                mCheckBox1.setText("Put my clothes back on");
                                mCheckBox2.setText("Construct a DIY robot");
                                mCheckBox3.setText("MMA");

                                mCheckBox1.setVisibility(View.VISIBLE);
                                mCheckBox2.setVisibility(View.VISIBLE);
                                mCheckBox3.setVisibility(View.VISIBLE);
                            } else if (questionsAnswered == 2){
                                mCheckBox1.setChecked(false);
                                mCheckBox2.setChecked(false);
                                mCheckBox3.setChecked(false);
                                welcomeText.setText("On sunday morning, what would like to do?");
                                mCheckBox1.setText("Firat");
                                mCheckBox2.setText("Dune");
                                mCheckBox3.setText("Daniel");

                                mCheckBox1.setVisibility(View.VISIBLE);
                                mCheckBox2.setVisibility(View.VISIBLE);
                                mCheckBox3.setVisibility(View.VISIBLE);
                            } else if (questionsAnswered == 3){
                                Intent intent = new Intent(Preferences.this, Profile.class);
                                startActivity(intent);
                            }
                        }
                    }, 3000);
                }
            }
        });

        mCheckBox3.setOnClickListener(new View.OnClickListener() {
            Handler animate = new Handler();
            @Override
            public void onClick(View v) {

                if ( ((CheckBox)v).isChecked() ) {
                    welcomeText.setText(((CheckBox)v).getText().toString() +"\nNice choice!");
                    questionsAnswered++;
                    mCheckBox1.setChecked(false);
                    mCheckBox2.setChecked(false);
                    mCheckBox3.setChecked(false);
                    mCheckBox1.setVisibility(View.INVISIBLE);
                    mCheckBox2.setVisibility(View.INVISIBLE);
                    mCheckBox3.setVisibility(View.INVISIBLE);

                    animate.postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            if(questionsAnswered == 1){
                                mCheckBox1.setChecked(false);
                                mCheckBox2.setChecked(false);
                                mCheckBox3.setChecked(false);
                                welcomeText.setText("On saturday morning, what would like to do?");
                                mCheckBox1.setText("Put my clothes back on");
                                mCheckBox2.setText("Construct a DIY robot");
                                mCheckBox3.setText("MMA");

                                mCheckBox1.setVisibility(View.VISIBLE);
                                mCheckBox2.setVisibility(View.VISIBLE);
                                mCheckBox3.setVisibility(View.VISIBLE);
                            } else if (questionsAnswered == 2){
                                mCheckBox1.setChecked(false);
                                mCheckBox2.setChecked(false);
                                mCheckBox3.setChecked(false);
                                welcomeText.setText("On sunday morning, what would like to do?");
                                mCheckBox1.setText("Firat");
                                mCheckBox2.setText("Dune");
                                mCheckBox3.setText("Daniel");

                                mCheckBox1.setVisibility(View.VISIBLE);
                                mCheckBox2.setVisibility(View.VISIBLE);
                                mCheckBox3.setVisibility(View.VISIBLE);
                            } else if (questionsAnswered == 3){
                                Intent intent = new Intent(Preferences.this, Profile.class);
                                startActivity(intent);
                            }
                        }
                    }, 3000);
                }
            }
        });


        /*
        if ((checkBoxState1 == true || checkBoxState2 == true || checkBoxState3 == true)){

            welcomeText.setText("Nice choice!");
            mCheckBox1.setVisibility(View.INVISIBLE);
            mCheckBox2.setVisibility(View.INVISIBLE);
            mCheckBox3.setVisibility(View.INVISIBLE);

            animate.postDelayed(new Runnable(){
                @Override
                public void run() {
                    welcomeText.setText("On saturday morning, what would like to do?");
                    mCheckBox1.setText("Put my clothes back on");
                    mCheckBox2.setText("Construct a DIY robot");
                    mCheckBox3.setText("MMA");

                    mCheckBox1.setVisibility(View.VISIBLE);
                    mCheckBox2.setVisibility(View.VISIBLE);
                    mCheckBox3.setVisibility(View.VISIBLE);
                }
            }, 8000);
        }
        */

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), " " + view.getId(), Toast.LENGTH_LONG).show();

                switch (view.getId()) {
                    case R.id.checkBox1:
                        if (mCheckBox1.isChecked())
                            Toast.makeText(getApplicationContext(), "Android", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.checkBox2:
                        if (mCheckBox2.isChecked())
                            Toast.makeText(getApplicationContext(), "Java", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.checkBox3:
                        if (mCheckBox3.isChecked())
                            Toast.makeText(getApplicationContext(), "PHP", Toast.LENGTH_LONG).show();
                        break;

                }
                //toggle();

            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }



    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

}