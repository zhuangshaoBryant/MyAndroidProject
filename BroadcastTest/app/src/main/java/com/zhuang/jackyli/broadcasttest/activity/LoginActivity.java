package com.zhuang.jackyli.broadcasttest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhuang.jackyli.broadcasttest.R;

public class LoginActivity extends BaseActivity {

    private EditText mNameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mNameEditText = (EditText) findViewById(R.id.name_EditText);
        mPasswordEditText = (EditText) findViewById(R.id.password_EditText);
        mLoginButton = (Button) findViewById(R.id.commit_Button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                if(!name.equals("admin") || !password.equals("password")){
                    Toast.makeText(LoginActivity.this,"用户名或密码不对",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

}
