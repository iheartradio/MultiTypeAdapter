package com.iheartradio.multitypeadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class TypeAdapterDiffCallback extends DiffUtil.Callback {

    private final List<Object> mOldData;
    private final List<Object> mNewData;
    private final MultiTypeAdapter mMultiTypeAdapter;

    TypeAdapterDiffCallback(@NonNull final MultiTypeAdapter multiTypeAdapter,
                            @NonNull final List<Object> newData,
                            @NonNull final List<Object> oldData) {
        mOldData = oldData;
        mNewData = newData;
        mMultiTypeAdapter = multiTypeAdapter;
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
        return mMultiTypeAdapter.isDataTheSame(mOldData.get(oldItemPosition), mNewData.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldData.get(oldItemPosition).equals(mNewData.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return mMultiTypeAdapter.getChangePayload(mOldData.get(oldItemPosition), mNewData.get(newItemPosition));
    }
}