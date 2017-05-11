package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.heterogeneousadapter.interfaces.SpanHandler;

/**
 * Created by Jonathan Muller on 4/27/17.
 */

public class GridLayoutSpanHandler implements SpanHandler {

    private final MultiTypeAdapter mAdapter;
    private boolean mSpanLookupSet;

    GridLayoutSpanHandler(final MultiTypeAdapter adapter){
        mAdapter = adapter;
        mSpanLookupSet = false;
    }

    @Override
    public void calculateSpan(final RecyclerView.LayoutManager layoutManager) {
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
