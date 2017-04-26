package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

import com.iheartradio.heterogeneousadapter.interfaces.BiConsumer;
import com.iheartradio.heterogeneousadapter.interfaces.Consumer;
import com.iheartradio.heterogeneousadapter.interfaces.Function1;
import com.iheartradio.heterogeneousadapter.interfaces.Supplier;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

class HeterogeneousBinderImpl<D, V extends RecyclerView.ViewHolder> extends HeterogeneousBinder<D, V> {

    private final Function1<Object, Boolean> mIsMyData;
    private final Function1<InflatingContext, ? extends V> mOnCreateViewHolder;
    private final BiConsumer<? super V, ? super D> mOnBindViewHolder;
    private final Consumer<? super V> mOnAttach;
    private final Consumer<? super V> mOnDetach;
    private final Supplier<Integer> mSpanSupplier;

    HeterogeneousBinderImpl(final Function1<Object, Boolean> isMyData,
                            final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                            final BiConsumer<? super V, ? super D> onBindViewHolder,
                            final Consumer<? super V> onAttach,
                            final Consumer<? super V> onDetach,
                            final Supplier<Integer> getSpan) {

        mIsMyData = isMyData;
        mOnCreateViewHolder = onCreateViewHolder;
        mOnBindViewHolder = onBindViewHolder;
        mOnAttach = onAttach;
        mOnDetach = onDetach;
        mSpanSupplier = getSpan;
    }

    HeterogeneousBinderImpl(final Class<?> targetClass,
                            final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                            final BiConsumer<? super V, ? super D> onBindViewHolder,
                            final Consumer<? super V> onAttach,
                            final Consumer<? super V> onDetach,
                            final Supplier<Integer> getSpan) {
        this(new Function1<Object, Boolean>() {
            @Override
            public Boolean invoke(Object input) {
                return targetClass.isAssignableFrom(input.getClass());
            }
        }, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, getSpan);
    }


    @Override
    public boolean isMyData(final Object data) {
        return mIsMyData.invoke(data);
    }

    @Override
    public V onCreateViewHolder(final InflatingContext inflating) {
        return mOnCreateViewHolder.invoke(inflating);
    }

    @Override
    public void onBindViewHolder(final V viewHolder, final D data) {
        mOnBindViewHolder.invoke(viewHolder, data);
    }

    @Override
    public void onAttach(V viewHolder) {
        mOnAttach.invoke(viewHolder);
    }

    @Override
    public void onDetach(V viewHolder) {
        mOnDetach.invoke(viewHolder);
    }

    @Override
    public int getSpan() {
        return mSpanSupplier.invoke();
    }

}
