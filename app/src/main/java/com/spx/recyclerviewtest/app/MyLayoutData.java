package com.spx.recyclerviewtest.app;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.spx.recyclerviewtest.LayoutDataViewHolder;
import com.spx.recyclerviewtest.R;

/**
 * Created by shaopengxiang on 2017/11/22.
 */

public class MyLayoutData extends LayoutData {
    public String title;
    @Override
    public int getItemLayoutId() {
        return R.layout.item_layout;
    }

    @Override
    public LayoutDataViewHolder createViewHolder(Context context, View itemView) {
        return new MyViewHolder(itemView);
    }

    class MyViewHolder extends LayoutDataViewHolder{
        TextView titleTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_tv);
        }

        @Override
        public void bindData(Context context, LayoutData layoutData, int position) {
            MyLayoutData myLayoutData = (MyLayoutData) layoutData;
            titleTv.setText(myLayoutData.title);
        }

        @Override
        public void release() {

        }
    }

}
