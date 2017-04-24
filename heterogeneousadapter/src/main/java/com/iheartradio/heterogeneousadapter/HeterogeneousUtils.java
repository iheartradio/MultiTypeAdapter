package com.iheartradio.heterogeneousadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by Jonathan Muller on 4/24/17.
 */

public class HeterogeneousUtils {

    public static GridLayoutManager createGridLayoutManager(final Context context, final int span, final HeterogeneousAdapter adapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, span);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getBinderForPosition(position).getSpan();
            }
        });
        return gridLayoutManager;
    }
}
