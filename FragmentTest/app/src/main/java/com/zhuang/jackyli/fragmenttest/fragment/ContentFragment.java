package com.zhuang.jackyli.fragmenttest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuang.jackyli.fragmenttest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {
    private TextView mTitleTextView;
    private TextView mContentTextView;

    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        mContentTextView = (TextView) view.findViewById(R.id.content_content_textview);
        mTitleTextView = (TextView) view.findViewById(R.id.content_title_textview);
        return view;
    }

    public void refresh(String title, String content) {
        mContentTextView.setText(content);
        mTitleTextView.setText(title);
    }

}
