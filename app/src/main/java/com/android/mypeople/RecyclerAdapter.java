package com.android.mypeople;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    //------------------Click Event------------------
//    public interface OnItemClickListener{
//        void onItemClick(View v, int position);
//    }
//
//    private OnItemClickListener mListener = null;
//
//    public void setOnItemClickListener(OnItemClickListener listener){
//        this.mListener = listener;
//    }
    //------------------Click Event------------------

    private ArrayList<Bean_friendslist> mDataset = null;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_1;
        public TextView tv_2;
        public TextView tv_3;

        MyViewHolder(View v) {
            super(v);
            tv_1 = v.findViewById(R.id.textView1);
            tv_2 = v.findViewById(R.id.textView2);
            tv_3 = v.findViewById(R.id.textView3);

            //--------------------Click Event--------------------
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    int position=getAdapterPosition();//어뎁터 포지션값
//                    // 뷰홀더에서 사라지면 NO_POSITION 을 리턴
//                    if(position!=RecyclerView.NO_POSITION){
//                        if(mListener !=null){
//                            mListener.onItemClick(view,position);
//                        }
//                    }
//                }
//            });
            //---------------------Click Event---------------------

        }
    }

    // 메인 액티비티에서 받은 myDataset을 가져오
    public RecyclerAdapter(MainActivity mainActivity, int member, ArrayList<Bean_friendslist> myDataset) {
        mDataset = myDataset;
//
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_items, parent, false);
        //     반복할 xml 파일
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // 표시하는 메소드
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //데이터를 받은걸 올리기
        // Integer.toString(mDataset.get(position).getSno())
        // mDataset.get(position).getUsername()
        holder.tv_1.setText("test1");
        holder.tv_2.setText("testtest");
        holder.tv_3.setText("good");




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}