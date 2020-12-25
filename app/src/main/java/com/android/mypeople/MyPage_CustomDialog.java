package com.android.mypeople;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyPage_CustomDialog {

    private Context context;


    public MyPage_CustomDialog(Context context) {
        this.context = context;
    }

    public void callFunction(final String userDeleteMessage) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.costomdialog_mypage);

        dialog.show();

        final EditText userDeleteMessage_dialog = dialog.findViewById(R.id.userDeleteMessage);
        final Button mpd_okbtn = dialog.findViewById(R.id.mpd_okbtn);
        final Button mpd_cancelbtn = dialog.findViewById(R.id.mpd_cancelbtn);
        final TextView userDeleteText = dialog.findViewById(R.id.userDeleteText);

        mpd_okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String m = userDeleteMessage_dialog.getText().toString();

                Log.v("Tagg","d"+m);
                if(m.length()==0){
                    userDeleteText.setText("입력하지 않았습니다");
                }else if (m.equals("탈퇴합니다")){
                    String userDeleteMessage = m;
                    dialog.dismiss();
                } else {
                    userDeleteText.setText("틀린문장을 입력했습니다.");
                }
            }
        });

        mpd_cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


    }
}
