package com.zhuang.jackyli.fragmenttest.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuang.jackyli.fragmenttest.R;
import com.zhuang.jackyli.fragmenttest.adapter.TitleAdapter;
import com.zhuang.jackyli.fragmenttest.dao.News;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TitleFragment extends Fragment {

    Boolean isTwo;
    RecyclerView mRecyclerView;

    public TitleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_title, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.title_RecyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.content_frameLayout) != null) {
            isTwo = true;
        } else {
            isTwo = false;
        }
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        TitleAdapter adapter = new TitleAdapter(getNews(), isTwo, TitleFragment.this);
        mRecyclerView.setAdapter(adapter);
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.setTitle("标题 " + i);
            news.setContent("标题 " + i + "的内容");
            newsList.add(news);
        }
        return newsList;
    }

}
