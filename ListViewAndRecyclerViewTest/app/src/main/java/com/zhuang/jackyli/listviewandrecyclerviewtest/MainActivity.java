package com.zhuang.jackyli.listviewandrecyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.zhuang.jackyli.listviewandrecyclerviewtest.model.Msg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mMsgRecyclerView;
    EditText mInputEditText;
    Button mSendButton;
    List<Msg> mMsgList;
    ListView mMsgListView;
    ListViewAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.listview_layout); //使用ListView布局
        mMsgList = new ArrayList<>();
        initMsgs(); // 初始化消息数据
        // mMsgRecyclerView = (RecyclerView) findViewById(R.id.msg_recyclerView);
        mMsgListView = (ListView) findViewById(R.id.msg_listView);
        mInputEditText = (EditText) findViewById(R.id.input_EditText);
        mSendButton = (Button) findViewById(R.id.send_Button);
        msgAdapter = new ListViewAdapter(mMsgList);
        mMsgListView.setAdapter(msgAdapter);
        mMsgListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mMsgList.remove(i);
                msgAdapter.notifyDataSetChanged();
                return true;
            }
        });
       /* LinearLayoutManager manager = new LinearLayoutManager(this);
        final MsgAdapter msgAdapter = new MsgAdapter(mMsgList);
        mMsgRecyclerView.setLayoutManager(manager);
        mMsgRecyclerView.setAdapter(msgAdapter);*/

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mInputEditText.getText().toString())) {
                    Msg msgSend = new Msg(mInputEditText.getText().toString(), Msg.TYPE_SEND);
                    mMsgList.add(msgSend);
                    msgAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("你好", Msg.TYPE_RECEIVED);
        mMsgList.add(msg1);
        Msg msg2 = new Msg("你是？", Msg.TYPE_RECEIVED);
        mMsgList.add(msg2);
        Msg msg3 = new Msg("哈哈哈 ", Msg.TYPE_RECEIVED);
        mMsgList.add(msg3);
    }
}
