package com.example.minorproject;

import android.os.Bundle;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.utteranceHandling.UtteranceAdapter;
import com.example.minorproject.utteranceHandling.UtteranceViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResultFragment extends Fragment {

    private UtteranceViewModel viewModel;
    private RecyclerView recyclerView;
    private TextView textView;

    private String TAG = Fragment.class.getSimpleName();

    private UtteranceAdapter adapter;

    public ResultFragment(){
        super(R.layout.fragment_result);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        //initializing views
        initViews(view);

//        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//
//        // Initialize the adapter and attach it to the RecyclerView
//        adapter = new UtteranceAdapter(requireActivity());
//        recyclerView.setAdapter(adapter);
//
//        viewModel = new ViewModelProvider(requireActivity()).get(UtteranceViewModel.class);
//        viewModel.getUtterancesList().observe(requireActivity(), new Observer<List<String>>() {
//            @Override
//            public void onChanged(List<String> strings) {
//                adapter.setDataset(strings);
//                textView.setVisibility(View.GONE);
//            }
//        });
    }

    public void initViews(View view){
        textView = (TextView) view.findViewById(R.id.textView3);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }
}