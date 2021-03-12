package com.example.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class customWorkoutFragment extends Fragment {

//    public customWorkoutFragment() {
//
//    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    private Button mButton;
    private ArrayList<String> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customworkouts, container, false);
        arrayList = new ArrayList();
        String[] oi = {"lol"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                arrayList);

        ListView listview = view.findViewById(R.id.list);


        listview.setAdapter(adapter);
        mButton = view.findViewById(R.id.button);


        mButton.setOnClickListener(v -> {

                    int count = arrayList.size() + 1;
                    arrayList.add("item" + count);
                    adapter.notifyDataSetChanged();
                }

        );
        return view;
    }

}
