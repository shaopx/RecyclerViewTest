package com.spx.recyclerviewtest;

import android.content.Context;
import com.spx.recyclerviewtest.RecyclerView;
import android.view.View;

import com.spx.recyclerviewtest.app.LayoutData;

/**
 * Created by shaopengxiang on 2017/11/22.
 */

public abstract class LayoutDataViewHolder extends RecyclerView.ViewHolder {
    public LayoutDataViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(Context context, LayoutData layoutData, int position);

    public abstract void release();
}
