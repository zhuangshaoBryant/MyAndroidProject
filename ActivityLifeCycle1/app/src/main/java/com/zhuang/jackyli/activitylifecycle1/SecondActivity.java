package com.zhuang.jackyli.activitylifecycle1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhuang.jackyli.activitylifecycle1.util.LogUtil;

public class SecondActivity extends AppCompatActivity {


    public static final String TEG = "SecondActivityCycleTest";
    Button mOkButton;
    Button mButton2;
    EditText mTelEditText;
    EditText mMailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        LogUtil.d(TEG, TEG+"onCreate方法调用" +"Task id = " +getTaskId());
        mOkButton = (Button) findViewById(R.id.button_ok);
        mTelEditText = (EditText) findViewById(R.id.tel_editText);
        mMailEditText = (EditText) findViewById(R.id.mail_editText);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(i);
            }
        });
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel = mTelEditText.getText().toString();
                String mail = mMailEditText.getText().toString();
                if(!TextUtils.isEmpty(tel)&&!TextUtils.isEmpty(mail)){
                    Intent intent = new Intent();
                    intent.putExtra("tel",tel);
                    intent.putExtra("mail",mail);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(SecondActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TEG, TEG+"onStart方法调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TEG, TEG+"onResume方法调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TEG, TEG+"onPause方法调用");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TEG, TEG+"onStop方法调用");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TEG, TEG+"onRestart方法调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TEG, TEG+"onDestroy方法调用");
    }

}
