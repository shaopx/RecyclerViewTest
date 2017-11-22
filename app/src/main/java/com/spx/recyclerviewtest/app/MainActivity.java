package com.spx.recyclerviewtest.app;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.spx.recyclerviewtest.LinearLayoutManager;
import com.spx.recyclerviewtest.RecyclerView;

import com.spx.recyclerviewtest.LayoutDataViewHolder;
import com.spx.recyclerviewtest.R;
import com.spx.recyclerviewtest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static List<String> data = new ArrayList<>();
    static {
        for (int l = 0; l < 1000; l++) {
            data.add("data_"+l);
        }
    }

    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter commonAdapter = new CommonAdapter(this);
        activityMainBinding.recyclerView.setAdapter(commonAdapter);

        List<LayoutData> dataList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            MyLayoutData layoutData = new MyLayoutData();
            layoutData.title = data.get(i);
            dataList.add(layoutData);
        }

        commonAdapter.setData(dataList);

        RecyclerView recyclerView = activityMainBinding.recyclerView;
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool(){
            @Override
            public void putRecycledView(RecyclerView.ViewHolder scrap) {
                if(scrap instanceof LayoutDataViewHolder){
                    LayoutDataViewHolder layoutDataViewHolder = (LayoutDataViewHolder) scrap;
                    layoutDataViewHolder.release();
                }
                super.putRecycledView(scrap);
            }
        };

//        activityMainBinding.recyclerView.setItemViewCacheSize(2);
//        activityMainBinding.recyclerView.getRecycledViewPool().setMaxRecycledViews(1, 10);

    }

//    class LayoutDataViewHolder extends RecyclerView.ViewHolder{
//        TextView titleTv;
//        public LayoutDataViewHolder(View itemView) {
//            super(itemView);
//            titleTv = itemView.findViewById(R.id.title_tv);
//        }
//
//        public void bind(int position){
//            titleTv.setText("str from :"+data.get(position));
//        }
//    }
//
//    class MyAdataper extends RecyclerView.Adapter<LayoutDataViewHolder> {
//
//        public MyAdataper(MainActivity mainActivity) {
//
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            return 1;
//        }
//
//        @Override
//        public LayoutDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            Log.d(TAG, "onCreateViewHolder: ... viewtype:"+viewType+" count:"+(count++));
//            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout, parent, false);
//            return new LayoutDataViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(LayoutDataViewHolder holder, int position) {
//            holder.bind(position);
//        }
//
//        @Override
//        public int getItemCount() {
//            return data.size();
//        }
//    }
}
