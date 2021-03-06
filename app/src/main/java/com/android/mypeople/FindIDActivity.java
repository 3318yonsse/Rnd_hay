package com.android.mypeople;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FindIDActivity extends AppCompatActivity {

    final static String TAG = "FindIDActivity";
    Button btn_continue;
    EditText et_tel;
    Intent intent;
    String macIP;
    String urlAddrfindid = null;
    String telVaildation ="^\\d{3}-\\d{3,4}-\\d{4}$";
    Bean_user bean = new Bean_user();
    TextView tv_telcheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        intent = getIntent();
        macIP = intent.getStringExtra("macIP");
        Log.v(TAG, "macIP : " + macIP);

        et_tel = findViewById(R.id.findid_et_tel);
//        tv_telcheck = findViewById(R.id.findid_tv_telcheck);

        /////////////////////////////////////////////////////////
        // 자동하이픈                                            //
        /////////////////////////////////////////////////////////
        et_tel.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        /////////////////////////////////////////////////////////


        btn_continue = findViewById(R.id.findid_btn_continue);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String requestTel = et_tel.getText().toString();
                Log.v(TAG, "requestTel : " + requestTel);


                // 전화번호 입력 유효성 검사
                if(!requestTel.matches(telVaildation) || requestTel.equals("")){

                    new AlertDialog.Builder(FindIDActivity.this)
                            .setTitle("전화번호를 확인하세요")
                            .setPositiveButton("확인", null)
                            .show();
                }else {
                    urlAddrfindid = "http://"+macIP+":8080/mypeople/findUserId.jsp?utel="+requestTel;
                    Log.v(TAG,"urlAddrfindid : " + urlAddrfindid );
                    String result = findconnectGetData();

                    Log.v(TAG,"result : " + result);
                    if(result.length() >0 ){
                        new AlertDialog.Builder(FindIDActivity.this)
                                .setTitle("계정 찾기 결과입니다.")
                                .setMessage(result)
                                .setPositiveButton("확인", null)
                                .show();
                    }

                }



            }
        });


    }


    // 데이터 값 가져오기
    private String findconnectGetData(){
            String result = null;
        try {
            NetworkTask_youngjae networkTask_youngjae = new NetworkTask_youngjae(FindIDActivity.this, urlAddrfindid, "useridFind");
            Object obj = networkTask_youngjae.execute().get();
            result = (String) obj;
            Log.v(TAG, "-----------" + result);


        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}