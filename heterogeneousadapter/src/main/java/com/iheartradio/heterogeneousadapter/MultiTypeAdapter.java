package com.iheartradio.heterogeneousadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.iheartradio.heterogeneousadapter.interfaces.SpanHandler;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.ViewHolder;

public final class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final TypeAdapterHandler mTypeAdapterHandler;

    private SpanHandler mSpanHandler;
    private List<Object> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;

    public MultiTypeAdapter(@NonNull final List<TypeAdapter<?, ?>> typeAdapters) {
        mTypeAdapterHandler = new TypeAdapterHandler(typeAdapters);
        mSpanHandler = new GridLayoutSpanHandler(this);
    }

    public MultiTypeAdapter(@NonNull final TypeAdapter<?, ?> typeAdapter) {
        mTypeAdapterHandler = new TypeAdapterHandler(typeAdapter);
        mSpanHandler = new GridLayoutSpanHandler(this);
    }

    public void setData(@NonNull final List<Object> data, final boolean animDiff) {
//        DiffUtil.DiffResult diffResult = null;
//        if (animDiff) {
//            diffResult = DiffUtil.calculateDiff(new TypeAdapterDiffCallback(mTypeAdapterHandler, data, mData));
//        }

        mData = data;
        notifyDataSetChanged();
        setupSpanHandling();

//        if (diffResult != null) {
//            diffResult.dispatchUpdatesTo(this);
//        }
    }

    public void setData(@NonNull final List<Object> data) {
        setData(data, true);
    }

    @NonNull
    public List<Object> data() {
        return new ArrayList<>(mData);
    }

    public void setSpanHandler(@Nullable final SpanHandler spanHandler) {
        mSpanHandler = spanHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return mTypeAdapterHandler.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) { /* not used */ }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @Nullable final List<Object> payloads) {
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
    public void onViewAttachedToWindow(@NonNull final ViewHolder genericHolder) {
        mTypeAdapterHandler.getBinderForType(genericHolder.getItemViewType()).onAttach(genericHolder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull final ViewHolder genericHolder) {
        mTypeAdapterHandler.getBinderForType(genericHolder.getItemViewType()).onDetach(genericHolder);
    }

    public TypeAdapter<?, ?> getBinderForPosition(final int position) {
        if (position >= mData.size()) {
            throw new ArrayIndexOutOfBoundsException("Position " + position + " is out of range for list of size " + mData.size());
        }
        return mTypeAdapterHandler.getBinderForData(mData.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull final RecyclerView recyclerView) {
        mRecyclerView = null;
    }

    private void setupSpanHandling() {
        if (mSpanHandler != null && mRecyclerView != null) {
            mSpanHandler.calculateSpan(mRecyclerView.getLayoutManager());
        }
    }
}
