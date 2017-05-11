package com.iheartradio.heterogeneousadapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Jonathan Muller on 3/11/17.
 */

public class HeterogeneousBinderDiffCallback extends DiffUtil.Callback {

    private final List<Object> mOldData;
    private final List<Object> mNewData;
    private final BinderHandler mBinderHandler;

    public HeterogeneousBinderDiffCallback(final BinderHandler binderHandler,
                                           final List<Object> newData,
                                           final List<Object> oldData) {
        mOldData = oldData;
        mNewData = newData;
        mBinderHandler = binderHandler;
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
        return mBinderHandler.isDataTheSame(mOldData.get(oldItemPosition), mNewData.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return mOldData.get(oldItemPosition).equals(mNewData.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return mBinderHandler.getChangePayload(mOldData.get(oldItemPosition), mNewData.get(newItemPosition));
    }
}