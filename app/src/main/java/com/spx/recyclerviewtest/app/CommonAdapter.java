package com.spx.recyclerviewtest.app;


import android.content.Context;
import com.spx.recyclerviewtest.RecyclerView;
import android.view.ViewGroup;


import com.spx.recyclerviewtest.LayoutDataViewHolder;
import com.spx.recyclerviewtest.app.LayoutData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaopengxiang on 2017/11/13.
 */

public class CommonAdapter extends RecyclerView.Adapter<LayoutDataViewHolder> {

    private Context mContext;
    private List<LayoutData> mData = new ArrayList<>();

    public CommonAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<LayoutData> data) {
        for (int i = data.size() - 1; i >= 0; i--) {
            LayoutData topicDataItem = data.get(i);
            if (!isContained(mData, topicDataItem)) {
                mData.add(0, topicDataItem);
            }
        }

        notifyDataSetChanged();
    }

    public void addData(List<LayoutData> data) {
        for (int i = 0; i <data.size(); i++) {
            LayoutData topicDataItem = data.get(i);
            if (!isContained(mData, topicDataItem)) {
                mData.add(topicDataItem);
            }
        }
        notifyDataSetChanged();
//        notifyItemRangeInserted(mData.size() - data.size(), data.size());
    }

    @Override
    public int getItemViewType(int position) {
        LayoutData layoutData = mData.get(position);
        int itemViewType = layoutData.getItemLayoutId();
        LayoutData.handleItem(itemViewType, layoutData);
        return itemViewType;
    }

    @Override
    public LayoutDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return LayoutData.onCreateViewHolder(mContext, parent, viewType);
    }

    @Override
    public void onBindViewHolder(LayoutDataViewHolder holder, int position) {
        mData.get(position).onBindViewHolder(mContext, holder, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

//    private void removeItemOfType(List<LayoutData> list, String recommend_topic) {
//        for (int i = list.size()-1; i >=0; i--) {
//            LayoutData tdi = list.get(i);
//            if (tdi.getType().equals(recommend_topic)) {
//                list.remove(tdi);
//            }
//        }
//    }

    private boolean isContained(List<LayoutData> list, LayoutData item) {
        for (int i = 0; i < list.size(); i++) {
            LayoutData tdi = list.get(i);
            if (tdi.getId() != null && tdi.getId().equals(item.getId())) {
                return true;
            }
        }
        return false;
    }
}
