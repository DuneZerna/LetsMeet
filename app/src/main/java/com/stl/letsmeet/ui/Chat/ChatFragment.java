package com.stl.letsmeet.ui.Chat;

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

import com.stl.letsmeet.Likes;
import com.stl.letsmeet.R;
import com.stl.letsmeet.ui.RecyclerView.MyAdapter;
import com.stl.letsmeet.ui.login.LoginActivity;

public class ChatFragment extends Fragment {

    private ChatViewModel chatViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chat, container, false);

        chatViewModel =
                ViewModelProviders.of(this).get(ChatViewModel.class);

        /*final TextView textView = root.findViewById(R.id.text_chat);
        chatViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        final Button chatButtonRe = root.findViewById(R.id.chatButtonRe);

        chatButtonRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ChatActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}