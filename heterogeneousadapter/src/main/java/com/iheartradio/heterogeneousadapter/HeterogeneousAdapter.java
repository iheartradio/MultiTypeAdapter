package com.iheartradio.heterogeneousadapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public class HeterogeneousAdapter extends RecyclerView.Adapter<ViewHolder> {

    private BinderHandler mBinderHandler;
    private List<Object> mData = new ArrayList<>();

    public HeterogeneousAdapter(final List<HeterogeneousBinder<?, ?>> heterogeneousBinders) {
        mBinderHandler = new BinderHandler(heterogeneousBinders);
    }

    public HeterogeneousAdapter(final HeterogeneousBinder<?, ?> heterogeneousBinder) {
        mBinderHandler = new BinderHandler(heterogeneousBinder);
    }

    public void setData(final List<Object> data) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new HeterogeneousBinderDiffCallback(mBinderHandler, data, mData));
        mData = data;
        diffResult.dispatchUpdatesTo(this);
    }

    public List<Object> data() {
        return mData;
    }

    public BinderHandler binderHandler() {
        return mBinderHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mBinderHandler.createViewHolder(parent, viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(final ViewHolder genericHolder, final int position) {
        mBinderHandler.bindViewHolder(genericHolder, mData.get(position));
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

    public HeterogeneousBinder<?, ?> getBinderForPosition(final int position) {
        if (position >= mData.size()) {
            throw new ArrayIndexOutOfBoundsException("Position " + position + " is out of range for list of size " + mData.size());
        }
        return mBinderHandler.getBinderForData(mData.get(position));
    }
}
