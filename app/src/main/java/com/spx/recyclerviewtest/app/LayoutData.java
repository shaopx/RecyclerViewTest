package com.spx.recyclerviewtest.app;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spx.recyclerviewtest.LayoutDataViewHolder;

import java.util.HashMap;

/**
 * Created by shaopengxiang on 2017/11/13.
 * 这时一个既包含数据  也包含布局信息的类   所有的首页, 话题列表, 发现列表的卡片对象都要继承这个类
 */
public abstract class LayoutData {
    private static final String TAG = "LayoutData";
    private static HashMap<Integer, LayoutData> layoutDataHashMap = new HashMap<>(24);

    public String getId(){
        return hashCode()+"";
    }


    public abstract int getItemLayoutId();

    public abstract LayoutDataViewHolder createViewHolder(Context context, View itemView);

    public void onBindViewHolder(Context context, LayoutDataViewHolder holder, int position){
        holder.bindData(context, this, position);
    }

    public static void handleItem(int itemType, LayoutData layoutData){
        if(layoutDataHashMap.containsKey(itemType)) return ;
        layoutDataHashMap.put(itemType, layoutData);
    }

    public static LayoutDataViewHolder onCreateViewHolder(Context context, ViewGroup parent, int viewType) {
        LayoutData layoutData = layoutDataHashMap.get(viewType);
        Log.d(TAG, "onCreateViewHolder: viewtype:"+viewType +", layoutData:"+layoutData);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return layoutData.createViewHolder(context, inflater.inflate(layoutData.getItemLayoutId(), parent, false));
    }

//    protected abstract RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType);
}

