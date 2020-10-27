package com.bezzy.Ui.View.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bezzy.Ui.View.Model.PostAdapter;
import com.bezzy.Ui.View.Model.PostItem;
import com.example.easy_application.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    Button button;
    RecyclerView postRecyclerView;
    List<PostItem> postItems;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        button=view.findViewById(R.id.edit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Editprofile.class);
                startActivity(intent);
            }
        });
        postRecyclerView=view.findViewById(R.id.postRecyclerView);

        postRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        List<PostItem> postItems=new ArrayList<>();
        postItems.add(new PostItem(R.drawable.bnm));
        postItems.add(new PostItem(R.drawable.dfg));
        postItems.add(new PostItem(R.drawable.dsz));
        postItems.add(new PostItem(R.drawable.gfd));
        postItems.add(new PostItem(R.drawable.sde));
        postItems.add(new PostItem(R.drawable.xde));
        postItems.add(new PostItem(R.drawable.qwe));
        postItems.add(new PostItem(R.drawable.tyy));
        postItems.add(new PostItem(R.drawable.mju));
        postItems.add(new PostItem(R.drawable.jkl));
        postItems.add(new PostItem(R.drawable.ffew));
        postItems.add(new PostItem(R.drawable.fgh));
        postItems.add(new PostItem(R.drawable.frr));
        postItems.add(new PostItem(R.drawable.vfr));
        postRecyclerView.setAdapter((new PostAdapter(postItems)));





        return view;

    }
}