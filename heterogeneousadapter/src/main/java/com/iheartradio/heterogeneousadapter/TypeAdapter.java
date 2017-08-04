package com.iheartradio.heterogeneousadapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class TypeAdapter<D, V extends RecyclerView.ViewHolder> {

    private static final int DEFAULT_SPAN = 1;

    public abstract boolean isMyData(final Object data);

    public abstract V onCreateViewHolder(final ViewGroup parent);

    public void onBindViewHolder(final V viewHolder, final D data, final List<Object> payloads) { }

    public void onAttach(final V view) { }

    public void onDetach(final V view) { }

    public int getSpan() {
        return DEFAULT_SPAN;
    }

    public boolean isDataEqual(final D data1, final D data2) {
        return false;
    }

    public Object getChangePayload(final D oldData, final D newData, final Bundle diffBundle) { return null; }
}
