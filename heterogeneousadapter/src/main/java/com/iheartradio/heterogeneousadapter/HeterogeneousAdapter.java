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

    private final List<HeterogeneousBinder<?, ?>> mHeterogeneousBinders;
    private DataSet<?> mData = new EmptyDataSet<>();
    private boolean mCalcDiff = false;

    public HeterogeneousAdapter(final List<HeterogeneousBinder<?, ?>> heterogeneousBinders) {
        mHeterogeneousBinders = new ArrayList<>(heterogeneousBinders);
    }

    public HeterogeneousAdapter(final HeterogeneousBinder<?, ?> heterogeneousBinder) {
        mHeterogeneousBinders = new ArrayList<>();
        mHeterogeneousBinders.add(heterogeneousBinder);
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
        return mHeterogeneousBinders.get(viewType).onCreateViewHolder(new InflatingContext(LayoutInflater.from(parent.getContext()), parent));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(final ViewHolder genericHolder, final int position) {
        HeterogeneousBinder<Object, ViewHolder> binder = getBinderForPosition(position);
        if (binder != null) {
            binder.onBindViewHolder(genericHolder, mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return heterogeneousBinderIndexFor(mData.get(position));
    }

    @Override
    public void onViewAttachedToWindow(final ViewHolder genericHolder) {
        heterogeneousBinderForType(genericHolder.getItemViewType())
                .onAttach(genericHolder);
    }

    @Override
    public void onViewDetachedFromWindow(final ViewHolder genericHolder) {
        heterogeneousBinderForType(genericHolder.getItemViewType())
                .onDetach(genericHolder);
    }

    @SuppressWarnings("unchecked")
    private HeterogeneousBinder<Object, ViewHolder> heterogeneousBinderForType(final int type) {
        return (HeterogeneousBinder<Object, ViewHolder>) mHeterogeneousBinders.get(type);
    }

    @SuppressWarnings("unchecked")
    private HeterogeneousBinder<Object, ViewHolder> heterogeneousBinderForData(final Object data) {
        for (int i = 0; i < mHeterogeneousBinders.size(); i++) {
            if (mHeterogeneousBinders.get(i).isMyData(data)) {
                return (HeterogeneousBinder<Object, ViewHolder>)mHeterogeneousBinders.get(i);
            }
        }

        throw new RuntimeException("Could not find binder for data type: " + data.getClass().getSimpleName());
    }

    private Integer heterogeneousBinderIndexFor(final Object data) {
        for (int i = 0; i < mHeterogeneousBinders.size(); i++) {
            if (mHeterogeneousBinders.get(i).isMyData(data)) {
                return i;
            }
        }

        throw new RuntimeException("Could not find binder index for data type: " + data.getClass().getSimpleName());
    }

    public int getSpanForPosition(final int position) {
        return heterogeneousBinderForData(mData.get(position)).getSpan();
    }

    public HeterogeneousBinder<Object, ViewHolder> getBinderForPosition(final int position) {
        return heterogeneousBinderForData(mData.get(position));
    }
}
