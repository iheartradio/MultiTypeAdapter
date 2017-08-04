package com.iheartradio.heterogeneousadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.iheartradio.heterogeneousadapter.interfaces.SpanHandler;

public class GridLayoutSpanHandler implements SpanHandler {

    private final MultiTypeAdapter mAdapter;
    private boolean mSpanLookupSet;

    GridLayoutSpanHandler(@NonNull final MultiTypeAdapter adapter){
        mAdapter = adapter;
        mSpanLookupSet = false;
    }

    @Override
    public void calculateSpan(@NonNull final RecyclerView.LayoutManager layoutManager) {
        if (!mSpanLookupSet && layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager)layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return mAdapter.getBinderForPosition(position).getSpan();
                }
            });
            mSpanLookupSet = true;
        }
    }
}
