package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public class HeterogeneousBinderImpl<D, V extends RecyclerView.ViewHolder> extends HeterogeneousBinder<D, V> {
    @Override
    public boolean isMyData(Object data) {
        return false;
    }

    @Override
    public V onCreateViewHolder(InflatingContext inflating) {
        return null;
    }

//    private final Class<D> mTargetClass;
//    private final Function<InflatingContext, ? extends V> mOnCreateViewHolder;
//    private final ViewBinder<? super V, ? super D> mOnBindViewHolder;
//    private final Receiver<? super V> mOnAttach;
//    private final Receiver<? super V> mOnDetach;
//    private final Supplier<Integer> mGetSpan;
//
//    HeterogeneousBinderImpl(final Class<D> targetClass,
//                            final Function<InflatingContext, ? extends V> onCreateViewHolder,
//                            final ViewBinder<? super V, ? super D> onBindViewHolder,
//                            final Receiver<? super V> onAttach,
//                            final Receiver<? super V> onDetach,
//                            final Supplier<Integer> getSpan) {
//
//        mTargetClass = targetClass;
//        mOnCreateViewHolder = onCreateViewHolder;
//        mOnBindViewHolder = onBindViewHolder;
//        mOnAttach = onAttach;
//        mOnDetach = onDetach;
//        mGetSpan = getSpan;
//    }
//
//
//    @Override
//    public boolean isMyData(final Object data) {
//
//        return mTargetClass.isAssignableFrom(data.getClass());
//    }
//
//    @Override
//    public V onCreateViewHolder(final InflatingContext inflating) {
//        return mOnCreateViewHolder.apply(inflating);
//    }
//
//    @Override
//    public void onBindViewHolder(final V viewHolder,
//                                 final D data) {
//        mOnBindViewHolder.bindViewHolder(viewHolder,
//                                         data);
//    }
//
//    @Override
//    public void onAttach(V viewHolder) {
//        mOnAttach.receive(viewHolder);
//    }
//
//    @Override
//    public void onDetach(V viewHolder) {
//        mOnDetach.receive(viewHolder);
//    }
//
//    @Override
//    public int getSpan() {
//        return mGetSpan.get();
//    }

}
