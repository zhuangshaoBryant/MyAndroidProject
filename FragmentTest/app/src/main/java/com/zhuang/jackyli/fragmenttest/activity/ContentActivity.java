package com.zhuang.jackyli.fragmenttest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zhuang.jackyli.fragmenttest.R;
import com.zhuang.jackyli.fragmenttest.fragment.ContentFragment;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        ContentFragment contentFragment = (ContentFragment)getSupportFragmentManager().findFragmentById(R.id.content_fragment);
        contentFragment.refresh(title,content);

    }

    public static void actionStart(Context context, String title, String content){
        Intent intent = new Intent(context,ContentActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }


}
