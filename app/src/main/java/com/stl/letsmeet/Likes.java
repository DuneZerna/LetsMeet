package com.stl.letsmeet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.stl.letsmeet.ui.login.LoginActivity;

import java.util.ArrayList;
import android.os.Bundle;

public class Likes extends AppCompatActivity {
    ArrayList<String> selection = new ArrayList<String>();
    TextView final_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);
        final_text = (TextView)findViewById(R.id.ViewData);

        final Button NextButton = findViewById(R.id.NextButton);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Likes.this, Profile.class);
                startActivity(intent);
            }
        });

        final Button back_button = findViewById(R.id.likes_back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dune: Backtrace activity to previous
                finish();
            }
        });

    }

    public void selectItem(View view) {

        boolean checked = ((CheckBox) view) .isChecked();
        switch (view.getId())
        {
            case R.id.Chocolate:
                if(checked)
                {
                    selection.add("Chocolate");
                }
                else
                {
                    selection.remove("Chocolate");
                }
                break;

            case R.id.MoviescheckBox:
                if(checked)
                {
                    selection.add("Movies");
                }
                else
                {
                    selection.remove("Movies");
                }
                break;

            case R.id.MusiccheckBox:
                if(checked)
                {
                    selection.add("Music");
                }
                else
                {
                    selection.remove("Music");
                }
                break;

            case R.id.TravelcheckBox:
                if(checked)
                {
                    selection.add("Travel");
                }
                else
                {
                    selection.remove("Travel");
                }
                break;

            case R.id.YogacheckBox:
                if(checked)
                {
                    //Sharedpref
                    selection.add("Yoga");
                }
                else
                {
                    selection.remove("Yoga");
                }
                break;
        }
    }

    public void finalSelection(View view) {

        String Showtheresult = " ";
        for (String selections : selection)
        {
            Showtheresult = Showtheresult + selections + "\n";
        }
        final_text.setText(Showtheresult);
        final_text.setEnabled(true);
    }
}