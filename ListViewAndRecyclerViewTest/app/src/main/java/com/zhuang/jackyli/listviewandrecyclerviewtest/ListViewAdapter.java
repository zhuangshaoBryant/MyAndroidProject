package com.zhuang.jackyli.listviewandrecyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhuang.jackyli.listviewandrecyclerviewtest.model.Msg;

import java.util.List;

/**
 * Created by jackyli on 2017/7/19.
 */

public class ListViewAdapter extends BaseAdapter {
    private List<Msg> mMsgList;

    public ListViewAdapter(List<Msg> mMsgList) {
        this.mMsgList = mMsgList;
    }

    @Override
    public int getCount() {
        return mMsgList.size();
    }

    @Override
    public Msg getItem(int i) {
        return mMsgList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item,viewGroup,false);
            holder.mReceiveTextView = (TextView) view.findViewById(R.id.receive_TextView);
            holder.mSendTextView = (TextView) view.findViewById(R.id.send_TextView);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Msg msg = mMsgList.get(i);
        if(msg.getType()==Msg.TYPE_RECEIVED){
            holder.mSendTextView.setVisibility(View.GONE);
            holder.mReceiveTextView.setVisibility(View.VISIBLE);
            holder.mReceiveTextView.setText("收到您发送的信息："+ msg.getContent());
        }else if(msg.getType()==Msg.TYPE_SEND){
            holder.mSendTextView.setVisibility(View.VISIBLE);
            holder.mReceiveTextView.setVisibility(View.GONE);
            holder.mSendTextView.setText(msg.getContent());
        }
        return view;
    }

    public final class ViewHolder{
        TextView mReceiveTextView;
        TextView mSendTextView;
    }
}
