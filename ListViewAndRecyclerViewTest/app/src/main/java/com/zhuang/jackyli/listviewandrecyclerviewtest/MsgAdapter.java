package com.zhuang.jackyli.listviewandrecyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuang.jackyli.listviewandrecyclerviewtest.model.Msg;

import java.util.List;

/**
 * Created by jackyli on 2017/7/19.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;

    public MsgAdapter(List<Msg> mMsgList) {
        this.mMsgList = mMsgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mReceiveTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                //Toast.makeText(parent.getContext(),"点击位置为"+position,Toast.LENGTH_SHORT).show();
                mMsgList.remove(position);
               notifyItemRemoved(position);
                return true;
            }
        });
        holder.mSendTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                //Toast.makeText(parent.getContext(),"点击位置为"+position,Toast.LENGTH_SHORT).show();
                mMsgList.remove(position-1);
                notifyItemRemoved(position);
                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if(msg.getType()==Msg.TYPE_RECEIVED){
            holder.mSendTextView.setVisibility(View.GONE);
            holder.mReceiveTextView.setVisibility(View.VISIBLE);
            holder.mReceiveTextView.setText("收到您发送的信息："+ msg.getContent());
        }else if(msg.getType()==Msg.TYPE_SEND){
            holder.mSendTextView.setVisibility(View.VISIBLE);
            holder.mReceiveTextView.setVisibility(View.GONE);
            holder.mSendTextView.setText(msg.getContent());
        }


    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mReceiveTextView;
        TextView mSendTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mReceiveTextView = itemView.findViewById(R.id.receive_TextView);
            mSendTextView = itemView.findViewById(R.id.send_TextView);
        }
    }
}
