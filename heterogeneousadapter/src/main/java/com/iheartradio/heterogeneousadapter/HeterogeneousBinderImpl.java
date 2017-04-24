package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public class HeterogeneousBinderImpl<D, V extends RecyclerView.ViewHolder> extends HeterogeneousBinder<D, V> {

    private final Class<D> mTargetClass;
    private final OnCreateVHConsumer<? extends V> mOnCreateViewHolder;
    private final OnBindConsumer<? super V, ? super D> mOnBindViewHolder;
    private final VHConsumer<? super V> mOnAttach;
    private final VHConsumer<? super V> mOnDetach;
    private final SpanSupplier mSpanSupplier;

    HeterogeneousBinderImpl(final Class<D> targetClass,
                            final OnCreateVHConsumer<? extends V> onCreateViewHolder,
                            final OnBindConsumer<? super V, ? super D> onBindViewHolder,
                            final VHConsumer<? super V> onAttach,
                            final VHConsumer<? super V> onDetach,
                            final SpanSupplier getSpan) {

        mTargetClass = targetClass;
        mOnCreateViewHolder = onCreateViewHolder;
        mOnBindViewHolder = onBindViewHolder;
        mOnAttach = onAttach;
        mOnDetach = onDetach;
        mSpanSupplier = getSpan;
    }


    @Override
    public boolean isMyData(final Object data) {

        return mTargetClass.isAssignableFrom(data.getClass());
    }

    @Override
    public V onCreateViewHolder(final InflatingContext inflating) {
        return mOnCreateViewHolder.createViewHolder(inflating);
    }

    @Override
    public void onBindViewHolder(final V viewHolder,
                                 final D data) {
        mOnBindViewHolder.bindViewHolder(viewHolder,
                                         data);
    }

    @Override
    public void onAttach(V viewHolder) {
        mOnAttach.accept(viewHolder);
    }

    @Override
    public void onDetach(V viewHolder) {
        mOnDetach.accept(viewHolder);
    }

    @Override
    public int getSpan() {
        return mSpanSupplier.getSpan();
    }

}
