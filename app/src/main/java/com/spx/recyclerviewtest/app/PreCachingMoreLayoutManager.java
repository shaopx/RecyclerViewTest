package com.spx.recyclerviewtest.app;


import android.content.Context;

import com.spx.recyclerviewtest.LinearLayoutManager;
import com.spx.recyclerviewtest.RecyclerView;

/**
 * shaopx
 * 尝试提前加载更多的项目
 */
public class PreCachingMoreLayoutManager extends LinearLayoutManager {
    private static final int DEFAULT_EXTRA_LAYOUT_SPACE = 600;
    private int extraLayoutSpace = -1;
    private Context context;

    public PreCachingMoreLayoutManager(Context context) {
        super(context);
        this.context = context;
    }

    public PreCachingMoreLayoutManager(Context context, int extraLayoutSpace) {
        super(context);
        this.context = context;
        this.extraLayoutSpace = extraLayoutSpace;
    }

    public PreCachingMoreLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.context = context;
    }

    public void setExtraLayoutSpace(int extraLayoutSpace) {
        this.extraLayoutSpace = extraLayoutSpace;
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        if (extraLayoutSpace > 0) {
            return extraLayoutSpace;
        }
        return DEFAULT_EXTRA_LAYOUT_SPACE;
    }
}
