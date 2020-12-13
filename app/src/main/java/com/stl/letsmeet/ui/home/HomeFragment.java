package com.stl.letsmeet.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stl.letsmeet.Likes;
import com.stl.letsmeet.R;
import com.stl.letsmeet.ui.RecyclerView.MyAdapter;
import com.stl.letsmeet.ui.login.LoginActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RecyclerView recyclerView;
    String s1[], s2[], s3[], s4[];
    String d1[], d2[], d3[], d4[];
    String l1[], l2[], l3[], l4[];

    int images[] = {R.drawable.chess,R.drawable.dumbbell,R.drawable.football,R.drawable.football,R.drawable.running,
            R.drawable.running,R.drawable.pokemon_go,R.drawable.card_game,R.drawable.binoculars};

    int imagesDate[] = {R.drawable.running,R.drawable.binoculars,R.drawable.pokemon_go,R.drawable.running,R.drawable.football,
            R.drawable.chess,R.drawable.dumbbell,R.drawable.card_game,R.drawable.football};

    int imagesLocation[] = {R.drawable.pokemon_go,R.drawable.dumbbell,R.drawable.card_game,R.drawable.running,R.drawable.binoculars,
            R.drawable.football,R.drawable.football,R.drawable.running,R.drawable.chess};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView2);

        s1 = getResources().getStringArray(R.array.titles);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.category);
        s4 = getResources().getStringArray(R.array.date);

        d1 = getResources().getStringArray(R.array.titles2);
        d2 = getResources().getStringArray(R.array.description2);
        d3 = getResources().getStringArray(R.array.category2);
        d4 = getResources().getStringArray(R.array.date2);

        l1 = getResources().getStringArray(R.array.titles3);
        l2 = getResources().getStringArray(R.array.description3);
        l3 = getResources().getStringArray(R.array.category3);
        l4 = getResources().getStringArray(R.array.date3);

        final Button locationButton = root.findViewById(R.id.button12);
        final Button dateButton = root.findViewById(R.id.button22);
        final Button categoryButton = root.findViewById(R.id.button32);

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter myAdapter = new MyAdapter(getActivity().getApplicationContext(), l1, l2, l3, l4, imagesLocation);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter myAdapter = new MyAdapter(getActivity().getApplicationContext(), d1, d2, d3, d4, imagesDate);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            }
        });

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter myAdapter = new MyAdapter(getActivity().getApplicationContext(), s1, s2, s3, s4, images);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            }
        });

        MyAdapter myAdapter = new MyAdapter(getActivity().getApplicationContext(), s1, s2, s3, s4, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        return root;
    }



}