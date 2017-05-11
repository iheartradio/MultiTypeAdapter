package com.iheartradio.heterogeneousadapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.iheartradio.heterogeneousadapter.interfaces.ItemTouchHelperAdapter;
import com.iheartradio.heterogeneousadapter.interfaces.SpanHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public final class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> implements ItemTouchHelperAdapter {

    private final TypeAdapterHandler mTypeAdapterHandler;

    private SpanHandler mSpanHandler;
    private List<Object> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;

    public MultiTypeAdapter(final List<TypeAdapter<?, ?>> typeAdapters) {
        mTypeAdapterHandler = new TypeAdapterHandler(typeAdapters);
        mSpanHandler = new GridLayoutSpanHandler(this);
    }

    public MultiTypeAdapter(final TypeAdapter<?, ?> typeAdapter) {
        mTypeAdapterHandler = new TypeAdapterHandler(typeAdapter);
        mSpanHandler = new GridLayoutSpanHandler(this);
    }

    public void setData(final List<Object> data, final boolean useDiff) {
        DiffUtil.DiffResult diffResult = null;
        if (useDiff) {
            diffResult = DiffUtil.calculateDiff(new TypeAdapterDiffCallback(mTypeAdapterHandler, data, mData));
        }

        mData = data;
        setupSpanHandling();

        if (diffResult != null) {
            diffResult.dispatchUpdatesTo(this);
        }
    }

    public void setData(final List<Object> data) {
        setData(data, true);
    }

    public List<Object> data() {
        return new ArrayList<>(mData);
    }

    public TypeAdapterHandler binderHandler() {
        return mTypeAdapterHandler;
    }

    public void setSpanHandler(@Nullable final SpanHandler spanHandler) {
        mSpanHandler = spanHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mTypeAdapterHandler.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) { /* not used */ }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        mTypeAdapterHandler.bindViewHolder(holder, mData.get(position), payloads);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return mTypeAdapterHandler.getItemTypeForData(mData.get(position));
    }

    @Override
    public void onViewAttachedToWindow(final ViewHolder genericHolder) {
        mTypeAdapterHandler.getBinderForType(genericHolder.getItemViewType()).onAttach(genericHolder);
    }

    @Override
    public void onViewDetachedFromWindow(final ViewHolder genericHolder) {
        mTypeAdapterHandler.getBinderForType(genericHolder.getItemViewType()).onDetach(genericHolder);
    }

    public TypeAdapter<?, ?> getBinderForPosition(final int position) {
        if (position >= mData.size()) {
            throw new ArrayIndexOutOfBoundsException("Position " + position + " is out of range for list of size " + mData.size());
        }
        return mTypeAdapterHandler.getBinderForData(mData.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        mRecyclerView = null;
    }

    private void setupSpanHandling() {
        if (mSpanHandler != null && mRecyclerView != null) {
            mSpanHandler.calculateSpan(mRecyclerView.getLayoutManager());
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mData, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mData, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
