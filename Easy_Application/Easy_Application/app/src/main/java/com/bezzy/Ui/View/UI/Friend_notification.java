package com.bezzy.Ui.View.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.easy_application.R;

public class Friend_notification extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_notification);
        recyclerView=findViewById(R.id.friendsnoti_list);
    }
}