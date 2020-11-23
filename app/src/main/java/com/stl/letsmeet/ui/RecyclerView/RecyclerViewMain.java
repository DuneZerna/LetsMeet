package com.stl.letsmeet.ui.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.stl.letsmeet.R;

public class RecyclerViewMain extends AppCompatActivity {

    RecyclerView recyclerView;
    String s1[], s2[], s3[], s4[];

    int images[] = {R.drawable.chess,R.drawable.dumbbell,R.drawable.football,R.drawable.football,R.drawable.running,
            R.drawable.running,R.drawable.pokemon_go,R.drawable.card_game,R.drawable.binoculars,R.drawable.bowling};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.programming_languages);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.category);
        s4 = getResources().getStringArray(R.array.date);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, s4, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}