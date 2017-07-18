package com.zhuang.jackyli.activitylifecycle1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhuang.jackyli.activitylifecycle1.util.LogUtil;


public class MainActivity extends AppCompatActivity {
    public static final String TEG = "MainActivityCycleTest";
    Button mButton1;
    Button mButton2;
    TextView mTelTextView;
    TextView mMailTextView;
    TextView mTimeTextView;
    Button mRequestButton;
    Button mTimeButton;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtil.d(TEG, TEG + "  onSaveInstanceState调用了");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.d(TEG, TEG + "  onConfigurationChanged");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String text = savedInstanceState.getString("text");
        LogUtil.d(TEG, TEG + "  onRestoreInstanceState  " + text);
        //mTimeTextView.setText(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.d(TEG, TEG+"onCreate方法调用" +"Task id = " +getTaskId());
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mTimeButton = (Button) findViewById(R.id.time_Button);
        mTelTextView = (TextView) findViewById(R.id.tel_textview);
        mTimeTextView = (TextView) findViewById(R.id.time_EditText);
        mMailTextView = (TextView) findViewById(R.id.mail_textview);
        mRequestButton = (Button) findViewById(R.id.request_button);
        //显示启动
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });
        //隐式启动
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.zhuang.jackyli.STARTSECONDACTIVITY");
                startActivity(intent);

            }
        });

        mRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.zhuang.jackyli.STARTSECONDACTIVITY");
                startActivityForResult(intent, 1);
            }
        });

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String tel = data.getStringExtra("tel");
                    String mail = data.getStringExtra("mail");
                    mTelTextView.setText(tel);
                    mMailTextView.setText(mail);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TEG, TEG + "onStart方法调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TEG, TEG + "onResume方法调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TEG, TEG + "onPause方法调用");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TEG, TEG + "onStop方法调用");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TEG, TEG + "onRestart方法调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TEG, TEG + "onDestroy方法调用");
    }
}
