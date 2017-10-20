package com.iheartradio.multitypeadapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class TypeAdapter<D, V extends RecyclerView.ViewHolder> {

    protected static final int DEFAULT_SPAN = 1;

    /**
     * Used to determine if this TypeAdapter manages the incoming data
     * @param data
     * @return true if data is managed by this TypeAdapter
     */
    public abstract boolean isMyData(final Object data);

    /**
     * @param parent
     * @return ViewHolder of type V
     */
    public abstract V onCreateViewHolder(final ViewGroup parent);

    /**
     *
     * @param viewHolder
     * @param data to be bound to the viewholder
     */
    public void onBindViewHolder(final V viewHolder, final D data) { }

    public void onBindViewHolder(final V viewHolder, final D data, final List<Object> payloads) {
        onBindViewHolder(viewHolder, data);
    }

    public void onAttach(final V view) { }

    public void onDetach(final V view) { }

    /**
     * Used to determine the span for this data
     * @return default span of 1 unless overridden
     */
    public int getSpan() {
        return DEFAULT_SPAN;
    }

    /**
     * Used to determine if two pieces of data are equal. E.g. If their id is the same, or content
     * @param data1
     * @param data2
     * @return
     */
    public boolean isDataEqual(final D data1, final D data2) {
        return false;
    }

    public Object getChangePayload(final D oldData, final D newData, final Bundle diffBundle) { return null; }
}
