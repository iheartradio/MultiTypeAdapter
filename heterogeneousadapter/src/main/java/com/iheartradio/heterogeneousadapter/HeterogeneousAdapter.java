package com.iheartradio.heterogeneousadapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iheartradio.heterogeneousadapter.dataset.DataSet;
import com.iheartradio.heterogeneousadapter.dataset.EmptyDataSet;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public class HeterogeneousAdapter extends RecyclerView.Adapter<ViewHolder> {

    private BinderHandler mBinderHandler;
    private DataSet<?> mData = new EmptyDataSet<>();
    private boolean mCalcDiff = false;

    public HeterogeneousAdapter(final List<HeterogeneousBinder<?, ?>> heterogeneousBinders) {
        mBinderHandler = new BinderHandler(heterogeneousBinders);
    }

    public HeterogeneousAdapter(final HeterogeneousBinder<?, ?> heterogeneousBinder) {
        mBinderHandler = new BinderHandler(heterogeneousBinder);
    }

    public void setData(final DataSet<?> data) {
        if (mCalcDiff) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new HeterogeneousBinderDiffCallback(this, data));
            mData = data;
            diffResult.dispatchUpdatesTo(this);
        } else {
            // do nothing if data is not actually changed
            if (mData == data) {
                return;
            }

            mData = data;

            notifyDataSetChanged();
        }
    }

    public DataSet<?> dataSet() {
        return mData;
    }

    public void calcDiff(final boolean calcDiff) {
        mCalcDiff = calcDiff;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mBinderHandler.createViewHolder(parent, viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(final ViewHolder genericHolder, final int position) {
        mBinderHandler.bindViewHolder(genericHolder, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return mBinderHandler.getItemTypeForData(mData.get(position));
    }

    @Override
    public void onViewAttachedToWindow(final ViewHolder genericHolder) {
        mBinderHandler.getBinderForType(genericHolder.getItemViewType()).onAttach(genericHolder);
    }

    @Override
    public void onViewDetachedFromWindow(final ViewHolder genericHolder) {
        mBinderHandler.getBinderForType(genericHolder.getItemViewType()).onDetach(genericHolder);
    }
}
