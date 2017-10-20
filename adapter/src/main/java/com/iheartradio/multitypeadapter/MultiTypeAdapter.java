/*
 * Copyright (C) 2017 iHeartRadio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iheartradio.multitypeadapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.iheartradio.multitypeadapter.interfaces.SpanHandler;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 *
 */
public final class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<TypeAdapter<?, ?>> mTypeAdapters = new ArrayList<>();

    private SpanHandler mSpanHandler = new GridLayoutSpanHandler(this);
    private List<Object> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;

    public MultiTypeAdapter(@NonNull final List<TypeAdapter<?, ?>> typeAdapters) {
        mTypeAdapters.addAll(typeAdapters);
    }

    public MultiTypeAdapter(@NonNull final TypeAdapter<?, ?> typeAdapter) {
        mTypeAdapters.add(typeAdapter);
    }

    public MultiTypeAdapter() {  }

    /**
     * Dynamically adds a TypeAdapte
     * @param typeAdapter to be added
     */
    public void addTypeAdapter(@NonNull final TypeAdapter<?, ?> typeAdapter) {
        mTypeAdapters.add(typeAdapter);
    }

    /**
     * Sets a list of data to the adapter. Overwrites any old data.
     * @param data to be set to the adapter
     * @param animateChanges true by default, false will avoid using DiffUtils to animate items
     */
    public void setData(@NonNull final List<Object> data, final boolean animateChanges) {
        DiffUtil.DiffResult diffResult = null;
        if (animateChanges) {
            diffResult = DiffUtil.calculateDiff(new TypeAdapterDiffCallback(this, data, mData));
        }

        mData = data;

        setupSpanHandling();

        if (diffResult != null) {
            diffResult.dispatchUpdatesTo(this);
        } else {
            notifyDataSetChanged();
        }
    }

    /**
     * Sets a list of data to the adapter. Overwrites any old data.
     * @param data
     */
    public void setData(@NonNull final List<Object> data) {
        setData(data, true);
    }

    /**
     * Convenience method for using items.
     * Sets a list of data to the adapter. Overwrites any old data.
     * @param items
     */
    public void setData(@NonNull final Items items) {
        setData(items.get(), true);
    }

    /**
     * Convenience method for using items.
     * Sets a list of data to the adapter. Overwrites any old data.
     * @param items to be set to the adapter
     * @param animateChanges true by default, false will avoid using DiffUtils to animate items
     */
    public void setData(@NonNull final Items items, final boolean animateChanges) {
        setData(items.get(), animateChanges);
    }

    /**
     * To access data with type info, use the TypeAdapter abstract class
     * @return List of Objects representing the data
     */
    @NonNull
    public List<Object> data() {
        return new ArrayList<>(mData);
    }

    /**
     * Sets custom implementation of the SpanHandler. This will overwrite the default GridLayoutSpanHandler
     * @param spanHandler
     */
    public void setSpanHandler(@Nullable final SpanHandler spanHandler) {
        mSpanHandler = spanHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return mTypeAdapters.get(viewType).onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        getTypeAdapterForData(mData.get(position)).onBindViewHolder(holder, mData.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @Nullable final List<Object> payloads) {
        getTypeAdapterForData(mData.get(position)).onBindViewHolder(holder, mData.get(position), payloads);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return getItemTypeForData(mData.get(position));
    }

    @Override
    public void onViewAttachedToWindow(@NonNull final ViewHolder genericHolder) {
        getTypeAdapterForType(genericHolder.getItemViewType()).onAttach(genericHolder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull final ViewHolder genericHolder) {
        getTypeAdapterForType(genericHolder.getItemViewType()).onDetach(genericHolder);
    }

    /**
     * Type adapter positions are set in the order that they are added. This allows quick access to
     * specific TypeAdapters
     * @param position
     * @return the TypeAdapter at the given position
     */
    public TypeAdapter<?, ?> getTypeAdapterForPosition(final int position) {
        if (position >= mData.size()) {
            throw new ArrayIndexOutOfBoundsException("Position " + position + " is out of range for list of size " + mData.size());
        }
        return getTypeAdapterForData(mData.get(position));
    }

    @SuppressWarnings("unchecked")
    TypeAdapter<Object, RecyclerView.ViewHolder> getTypeAdapterForType(final int type) {
        return (TypeAdapter<Object, RecyclerView.ViewHolder>) mTypeAdapters.get(type);
    }

    @SuppressWarnings("unchecked")
    TypeAdapter<Object, RecyclerView.ViewHolder> getTypeAdapterForData(@NonNull final Object data) {
        for (int i = 0; i < mTypeAdapters.size(); i++) {
            if (mTypeAdapters.get(i).isMyData(data)) {
                return (TypeAdapter<Object, RecyclerView.ViewHolder>) mTypeAdapters.get(i);
            }
        }

        throw new RuntimeException("Could not find TypeAdapter for data type: " + data.getClass().getSimpleName());
    }

    int getItemTypeForData(final Object data) {
        for (int i = 0; i < mTypeAdapters.size(); i++) {
            if (mTypeAdapters.get(i).isMyData(data)) {
                return i;
            }
        }

        throw new RuntimeException("Could not find TypeAdapter index for data type: " + data.getClass().getSimpleName());
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

    boolean isDataTheSame(final Object data1, final Object data2) {
        // Are the item types the same
        if (getItemTypeForData(data1) == getItemTypeForData(data2)) {
            // Is the data equal
            return getTypeAdapterForData(data1).isDataEqual(data1, data2);
        } else {
            return false;
        }
    }

    Object getChangePayload(final Object oldData, final Object newData) {
        return getTypeAdapterForData(newData).getChangePayload(oldData, newData, new Bundle());
    }
}
