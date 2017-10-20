package com.iheartradio.multitypeadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.iheartradio.multitypeadapter.interfaces.SpanHandler;

/**
 * Default implementation of SpanHandler that uses the TypeAdapters
 * getSpan() method to calculate spans for each item.
 *
 * This is only utilized for GridLayoutManagers.
 */
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
                    return mAdapter.getTypeAdapterForPosition(position).getSpan();
                }
            });
            mSpanLookupSet = true;
        }
    }
}
