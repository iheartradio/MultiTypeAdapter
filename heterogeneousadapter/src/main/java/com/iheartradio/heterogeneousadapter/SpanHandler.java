package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Jonathan Muller on 4/27/17.
 */

public interface SpanHandler {
    void calculateSpan(final RecyclerView.LayoutManager layoutManager);
}
