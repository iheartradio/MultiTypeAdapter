package com.iheartradio.heterogeneousadapter;

import android.support.v7.util.DiffUtil;

import com.iheartradio.heterogeneousadapter.dataset.DataSet;

/**
 * Created by Jonathan Muller on 3/11/17.
 */

public class HeterogeneousBinderDiffCallback extends DiffUtil.Callback {

    private final DataSet<?> mNewData;
    private final HeterogeneousAdapter mAdapter;

    public HeterogeneousBinderDiffCallback(final HeterogeneousAdapter adapter, final DataSet<?> newData) {
        mNewData = newData;
        mAdapter = adapter;
    }

    @Override
    public int getOldListSize() {
        return mAdapter.dataSet().size();
    }

    @Override
    public int getNewListSize() {
        return mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (mAdapter.getBinderForPosition(oldItemPosition) == mAdapter.getBinderForPosition(newItemPosition)) {
            return mAdapter.getBinderForPosition(oldItemPosition).areItemsTheSame(mAdapter.dataSet().get(oldItemPosition),
                                                                              mAdapter.dataSet().get(newItemPosition));
        } else {
            return false;
        }
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mAdapter.getBinderForPosition(oldItemPosition)
                .areContentsTheSame(mAdapter.dataSet().get(oldItemPosition), mAdapter.dataSet().get(newItemPosition));
    }
}
