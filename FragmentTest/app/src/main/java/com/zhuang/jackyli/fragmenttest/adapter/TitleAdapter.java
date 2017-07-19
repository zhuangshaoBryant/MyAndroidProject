package com.zhuang.jackyli.fragmenttest.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuang.jackyli.fragmenttest.R;
import com.zhuang.jackyli.fragmenttest.activity.ContentActivity;
import com.zhuang.jackyli.fragmenttest.dao.News;
import com.zhuang.jackyli.fragmenttest.fragment.ContentFragment;
import com.zhuang.jackyli.fragmenttest.fragment.TitleFragment;

import java.util.List;

/**
 * Created by jackyli on 2017/7/19.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder> {
    List<News> mNewsList;
    Boolean isTwo; // 是否为双页模式
    Fragment fragment;

    public TitleAdapter(List<News> mNewsList, Boolean isTwo, Fragment fragment) {
        this.mNewsList = mNewsList;
        this.isTwo = isTwo;
        this.fragment = fragment;
    }

    public TitleAdapter(List<News> mNewsList, Boolean isTwo) {
        this.mNewsList = mNewsList;
        this.isTwo = isTwo;
    }

    public TitleAdapter(List<News> mNewsList) {
        this.mNewsList = mNewsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News news = mNewsList.get(holder.getAdapterPosition());
                if(isTwo){
                    ContentFragment contentFragment = (ContentFragment) fragment.getFragmentManager().findFragmentById(R.id.content_fragment);
                    contentFragment.refresh(news.getTitle(),news.getContent());
                }else{
                    ContentActivity.actionStart(fragment.getActivity(),news.getTitle(),news.getContent());
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
News news = mNewsList.get(position);
        holder.mTextView.setText(news.getTitle());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.title_TextView);
        }
    }
}
