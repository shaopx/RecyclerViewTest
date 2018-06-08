package com.spx.recyclerviewtest.app;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spx.recyclerviewtest.LinearLayoutManager;
import com.spx.recyclerviewtest.R;
import com.spx.recyclerviewtest.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private static final String TAG = "ScrollingActivity";
    private List<Fragment> mFragments = new ArrayList<>();
    private RecyclerView recyclerView;
    private PreCachingMoreLayoutManager linearLayoutManager;
    private List<String> data = new ArrayList<>();
//    protected ViewPager mViewPager;

    private TextView infoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (int i = 0; i < 30; i++) {
            data.add("afsdfbasfd[" + i + "]");
        }

        infoTv = findViewById(R.id.info_tv);

//        mViewPager = findViewById(R.id.view_pager);
//        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));


        recyclerView = findViewById(R.id.recycler_view);
        linearLayoutManager = new PreCachingMoreLayoutManager(this);

        //为了调试方便, 先关闭prefetch功能
        linearLayoutManager.setItemPrefetchEnabled(false);
//        linearLayoutManager.setExtraLayoutSpace(getResources().getDisplayMetrics().heightPixels*8);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MyAdapter());
//        recyclerView.setItemViewCacheSize(20);



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState != 1) {
                    Log.d(TAG, "onScrollStateChanged: newState:" + newState);
                } else {
                    Log.d(TAG, "onScrollStateChanged: newState:" + newState);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
//                Log.d(TAG, "onScrolled: dy:" + dy + ", last:" + lastCompletelyVisibleItemPosition);
//                if(dy>0 && lastCompletelyVisibleItemPosition==(data.size()-1)){
//                    Log.d(TAG, "onScrolled: !!!! load more");
//                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    debugRecyclerViewInfo();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    private void debugRecyclerViewInfo() {
//        Log.d(TAG, "debugRecyclerViewInfo: ");
        RecyclerView.Recycler recycler = recyclerView.mRecycler;
        StringBuilder info = new StringBuilder();
        ArrayList<RecyclerView.ViewHolder> mAttachedScrap = recycler.mAttachedScrap;

        info.append("mAttachedScrap:");
        debugScrap(mAttachedScrap, info);
        info.append("\r\n");
        info.append("mCachedViews:");
        debugScrap(recycler.mCachedViews, info);
        info.append("\r\n");


        RecyclerView.RecycledViewPool recycledViewPool = recyclerView.getRecycledViewPool();
//        recycledViewPool.clear();
        debugRecycledViewPool(recycledViewPool, info);


        infoTv.setText(info.toString());
    }

    private void debugRecycledViewPool(RecyclerView.RecycledViewPool recycledViewPool, StringBuilder info) {
        SparseArray<RecyclerView.RecycledViewPool.ScrapData> mScrap = recycledViewPool.mScrap;
        String str = "";
        for (int i = 0; i < mScrap.size(); i++) {
            RecyclerView.RecycledViewPool.ScrapData data = mScrap.valueAt(i);
            ArrayList<RecyclerView.ViewHolder> mScrapHeap = data.mScrapHeap;
            info.append(i+"=");
            debugScrap(mScrapHeap, info);
            info.append(",");
        }
        info.append("\r\n");
    }

    private void debugScrap(ArrayList<RecyclerView.ViewHolder> scrap, StringBuilder sb){
        String str = "";
        for (int i = 0; i < scrap.size(); i++) {
            RecyclerView.ViewHolder viewHolder = scrap.get(i);

            if (i > 0) {
                str += ",";
            }
            str += "" + viewHolder.mPosition;
        }
        sb.append("["+str+"]");
    }

    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "推荐";
                case 1:
                    return "懂友圈";
                case 2:
                    return "视频";
            }
            return "";
        }

    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_tv);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ScrollingActivity.this).inflate(R.layout.item_layout, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            Log.w(TAG, "onBindViewHolder: ...." + position);
            holder.textView.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
