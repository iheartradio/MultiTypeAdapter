package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public abstract class HeterogeneousBinder<D, V extends RecyclerView.ViewHolder> {

    private static final int DEFAULT_SPAN = 1;

    public abstract boolean isMyData(Object data);

    public abstract V onCreateViewHolder(InflatingContext inflating);

    public void onBindViewHolder(V viewHolder, D data) { }

    public void onAttach(V view) { }

    public void onDetach(V view) { }

    public int getSpan() {
        return DEFAULT_SPAN;
    }

    public boolean areItemsTheSame(final D oldData, final D newData) {
        return false;
    }

    public boolean areContentsTheSame(final D oldData, final D newData) {
        return false;
    }
}
