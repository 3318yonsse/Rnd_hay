package com.android.mypeople;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listView = null;
    private RecyclerView.LayoutManager layoutManager = null;
    private ArrayList<Bean_friendslist> data = null;
    private RecyclerAdapter adapter = null;

    BottomAppBar bab;
    boolean isCenter=true;
    Spinner spinner_field = null;
    Class_LMW class_lmw = new Class_LMW();
    LinearLayout linearLayout = null;
    EditText searchText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<Bean_friendslist>();
        data.add(new Bean_friendslist(1, 10, "1"));
        data.add(new Bean_friendslist(2, 20, "2"));
        // 리사이클러뷰 규격 만들기
        listView = findViewById(R.id.listView_Friends);
        listView.setHasFixedSize(true);

        // Context는 Activity
        adapter = new RecyclerAdapter(MainActivity.this, R.layout.activity_main, data);
        listView.setAdapter(adapter);

        // 레이아웃 매니저 만들기
        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);

        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar); // 상단 툴바

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        searchText = findViewById(R.id.main_Edit_SearchText);

        bab=findViewById(R.id.bab);
        linearLayout = findViewById(R.id.main_Linear_outer);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) { // 배경 터치했을 경우 키보드 내리기
                if(searchText.getText().toString() != null){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });



//        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // 검색버튼으로 바꾸기
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                switch (actionId)
//                {
//                    case EditorInfo.IME_ACTION_SEARCH :
//
//                        Toast.makeText(MainActivity.this, "editText ACTION_SEARCH 이벤트 호출", Toast.LENGTH_SHORT).show();
//                        Log.e("MainActivity", "입력 내용 : " + searchText.getText().toString());
//
//                        break;
//                }
//
//                return true;
//            }
//        });

        searchText.setOnKeyListener(new View.OnKeyListener() { // 엔터 키 눌렀을 경우!(onClick 메소드 사용해서 검색버튼 누르기)
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    finish();
                    return true;
                }
                return false;
            }
        });

    }

    ////////////////////////////////////////////////////////////
    //                                                        //
    //                                                        //
    //                       구분용 주석                       //
    //                                                        //
    //                                                        //
    ////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 툴바 햄버거 메뉴

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public void clickFab(View view) { // 하단 앱바 홈버튼 클릭시
        isCenter= !isCenter;

        if(isCenter) bab.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        else bab.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }


}