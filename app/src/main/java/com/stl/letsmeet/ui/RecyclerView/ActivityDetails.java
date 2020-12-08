package com.stl.letsmeet.ui.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stl.letsmeet.R;

public class ActivityDetails extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description, category, date;

    String data1, data2, data3, data4;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        category = findViewById(R.id.categoryView);
        date = findViewById(R.id.dateView);

        getData();
        setData();
    }

    private void getData() {
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")
                && getIntent().hasExtra("data3") && getIntent().hasExtra("data4")) {

            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            data4 = getIntent().getStringExtra("data4");
            myImage = getIntent().getIntExtra("myImage", 1);


        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {

        title.setText(data1);
        description.setText(data2);
        category.setText(data3);
        date.setText(data4);
        mainImageView.setImageResource(myImage);

    }
}