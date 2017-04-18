package com.iheartradio.heterogeneousadapter;

import android.support.v7.util.DiffUtil;

import com.iheartradio.heterogeneousadapter.dataset.DataSet;

/**
 * Created by Jonathan Muller on 3/11/17.
 */

public class HeterogeneousBinderDiffCallback extends DiffUtil.Callback {

    private final DataSet<?> mOldData;
    private final DataSet<?> mNewData;
    private final BinderHandler mBinderHandler;

    public HeterogeneousBinderDiffCallback(final BinderHandler binderHandler,
                                           final DataSet<?> newData,
                                           final DataSet<?> oldData) {
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
}