package com.android.mypeople;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    final static String TAG = "LoginActivity";
    EditText edtIP, et_id,et_pw;
    TextView tv_join, tv_findid,tv_findpw;
    Button btn_login;
    CheckBox cb_autologin;
    Intent intent;
    InputMethodManager inputMethodManager;
    LinearLayout ll_hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtIP = findViewById(R.id.edt_ip);
        btn_login = findViewById(R.id.login_btn_login);

        et_id = findViewById(R.id.login_et_id);
        et_pw = findViewById(R.id.login_et_pw);

        tv_join = findViewById(R.id.login_tv_join);
        tv_findid = findViewById(R.id.login_tv_findid);
        tv_findpw = findViewById(R.id.login_tv_findpw);

        cb_autologin = findViewById(R.id.login_cb_AutoLogin);

        btn_login.setOnClickListener(onClickListener);
        tv_join.setOnClickListener(onClickListener);
        tv_findid.setOnClickListener(onClickListener);
        tv_findpw.setOnClickListener(onClickListener);

        //키보드 화면 터치시 숨기기위해 선언.
        ll_hide = findViewById(R.id.login_ll_hide);
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);  //OS에서 지원해주는 메소드이다.

        //키보드 화면 터치시 숨김.
        ll_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMethodManager.hideSoftInputFromWindow(ll_hide.getWindowToken(),0);
            }
        });

    }



    // 로그인 화면 onClickListener
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tempIP = edtIP.getText().toString();

            switch (v.getId()){

                // 로그인 버튼 //
                case R.id.login_btn_login :
                    Toast.makeText(LoginActivity.this, "준비중", Toast.LENGTH_SHORT).show();
                    break;

                // 아이디 찾기로 이동 //
                case R.id.login_tv_findid :
                    intent = new Intent(LoginActivity.this, FindIDActivity.class);
                    startActivity(intent);
                    break;

                // 비번 찾기로 이동 //
                case R.id.login_tv_findpw :
                    intent = new Intent(LoginActivity.this, FindPWActivity.class);
                    startActivity(intent);
                    break;




                 // 회원가입으로 이동 //
                case R.id.login_tv_join :
                    intent = new Intent(LoginActivity.this, JoinUsActivity.class);
                   intent.putExtra("macIP", tempIP);  // IP주소를 보내줌.
                    Log.v(TAG, "macIP : " + tempIP);
                    startActivity(intent);
                    break;

            }

        }
    };







} //---------------------