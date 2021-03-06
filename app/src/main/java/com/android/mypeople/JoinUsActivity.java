package com.android.mypeople;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class JoinUsActivity extends AppCompatActivity {

    final static String TAG = "JoinUsActivty";
    EditText et_id;
    Button btn_continue;
    TextView tv_idcheck;
    Intent intent;
    String macIP;
    InputMethodManager inputMethodManager ;
    LinearLayout ll_hide;
    String urlAddrloginduplicationCheck = null;

    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinus);

        intent = getIntent();
        macIP = intent.getStringExtra("macIP");
        Log.v(TAG, "macIP : " + macIP);

        et_id = findViewById(R.id.join_et_id);


        btn_continue = findViewById(R.id.join_btn_continue);

        tv_idcheck = findViewById(R.id.join_tv_idcheck);




        // 이메일 입력 및 계속하기 버튼. DB저장 X
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // 입력받은 이메일(ID)를 추가정보 입력페이지로 넘김.
                String et_idsend = et_id.getText().toString();
                Log.v(TAG, "et_idsend : " + et_idsend);




                Log.v(TAG, "urlAddrloginduplicationCheck : " + urlAddrloginduplicationCheck);



                String edyo = et_id.getText().toString();

                if(edyo.equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(edyo).matches()){
                    tv_idcheck.setText("입력정보를 확인해주세요");


                } else {

                    urlAddrloginduplicationCheck = "http://"+macIP+":8080/mypeople/loginduplicationCheck.jsp?userid="+edyo;
                    count = loginduplicationCheck();

                    if(count == 0){
                        intent = new Intent(JoinUsActivity.this, JoinUsAddActivity.class);
                        // 입력받은 이메일 넘김.
                        intent.putExtra("et_idsend", et_idsend);
                        intent.putExtra("macIP", macIP);  // IP주소를 보내줌.
                        Log.v(TAG, "macIP123 : " + macIP);
                        startActivity(intent);
                    }else{
                        tv_idcheck.setText("중복된 아이디 입니다.");
                    }




                }
            }
        });




        //키보드 화면 터치시 숨기기위해 선언.
        ll_hide = findViewById(R.id.join_ll_hide);
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);  //OS에서 지원해주는 메소드이다.

        //키보드 화면 터치시 숨김.
        ll_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMethodManager.hideSoftInputFromWindow(ll_hide.getWindowToken(),0);
            }
        });
    }

    private int loginduplicationCheck(){
        try {
            NetworkTask_youngjae networkTask2 = new NetworkTask_youngjae(JoinUsActivity.this, urlAddrloginduplicationCheck, "useridCheck");
            Object obj = networkTask2.execute().get();

            count = (int) obj;
            Log.v("여기","loginCount : " + count);


        }catch (Exception e){
            e.printStackTrace();
        }

        return count;
    }
}