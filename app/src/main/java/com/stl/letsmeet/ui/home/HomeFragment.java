package com.stl.letsmeet.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stl.letsmeet.R;
import com.stl.letsmeet.ui.RecyclerView.MyAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RecyclerView recyclerView;
    String s1[], s2[], s3[], s4[];

    int images[] = {R.drawable.chess,R.drawable.dumbbell,R.drawable.football,R.drawable.football,R.drawable.running,
            R.drawable.running,R.drawable.pokemon_go,R.drawable.card_game,R.drawable.binoculars,R.drawable.bowling};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = root.findViewById(R.id.recyclerView2);

        s1 = getResources().getStringArray(R.array.programming_languages);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.category);
        s4 = getResources().getStringArray(R.array.date);

        MyAdapter myAdapter = new MyAdapter(getActivity().getApplicationContext(), s1, s2, s3, s4, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        return root;
    }



}