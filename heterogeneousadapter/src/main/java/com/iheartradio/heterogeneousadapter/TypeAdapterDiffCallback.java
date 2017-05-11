package com.iheartradio.heterogeneousadapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Jonathan Muller on 3/11/17.
 */

public class TypeAdapterDiffCallback extends DiffUtil.Callback {

    private final List<Object> mOldData;
    private final List<Object> mNewData;
    private final TypeAdapterHandler mTypeAdapterHandler;

    public TypeAdapterDiffCallback(final TypeAdapterHandler typeAdapterHandler,
                                   final List<Object> newData,
                                   final List<Object> oldData) {
        mOldData = oldData;
        mNewData = newData;
        mTypeAdapterHandler = typeAdapterHandler;
    }

    @Override
    public int getOldListSize() {
        return mOldData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mTypeAdapterHandler.isDataTheSame(mOldData.get(oldItemPosition), mNewData.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldData.get(oldItemPosition).equals(mNewData.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return mTypeAdapterHandler.getChangePayload(mOldData.get(oldItemPosition), mNewData.get(newItemPosition));
    }
}