package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

import com.iheartradio.heterogeneousadapter.interfaces.BiConsumer;
import com.iheartradio.heterogeneousadapter.interfaces.Consumer;
import com.iheartradio.heterogeneousadapter.interfaces.Function1;
import com.iheartradio.heterogeneousadapter.interfaces.Supplier;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public abstract class HeterogeneousBinder<D, V extends RecyclerView.ViewHolder> {

    private static final int DEFAULT_SPAN = 1;

    public abstract boolean isMyData(Object data);

    public abstract V onCreateViewHolder(InflatingContext inflating);

    public void onBindViewHolder(V viewHolder, D data) { }

    public void onAttach(V view) { }

    public void onDetach(V view) { }

    public int getSpan() {
        return DEFAULT_SPAN;
    }

    public boolean isDataEqual(final D data1, final D data2) {
        return false;
    }




    public static class Builder<D, V extends RecyclerView.ViewHolder> {

        private final Function1<Object, Boolean> mIsMyData;

        private final Function1<InflatingContext, ? extends V> mOnCreateViewHolder;

        private BiConsumer<? super V, ? super D> mOnBindViewHolder = new BiConsumer<V, D>() {
            @Override
            public void invoke(V v, D d) {
                // Default no-op
            }
        };

        private Consumer<? super V> mOnAttach = new Consumer<V>() {
            @Override
            public void invoke(V v) {
                // Default no-op
            }
        };

        private Consumer<? super V> mOnDetach = new Consumer<V>() {
            @Override
            public void invoke(V v) {
                // Default no-op
            }
        };

        private Supplier<Integer> mOnGetSpan = new Supplier<Integer>() {
            @Override
            public Integer invoke() {
                return DEFAULT_SPAN;
            }
        };

        public Builder(final Function1<Object, Boolean> onIsMyData,
                       final Function1<InflatingContext, ? extends V> onCreateViewHolder) {
            mIsMyData = onIsMyData;
            mOnCreateViewHolder = onCreateViewHolder;
        }

        public Builder(final Class<D> targetClass,
                       final Function1<InflatingContext, ? extends V> onCreateViewHolder) {
            this(new Function1<Object, Boolean>() {
                @Override
                public Boolean invoke(Object input) {
                    return targetClass.isAssignableFrom(input.getClass());
                }
            },
            onCreateViewHolder);
        }

        public Builder onBindViewHolder(final BiConsumer<? super V, ? super D> onBindViewHolder) {
            mOnBindViewHolder = onBindViewHolder;
            return this;
        }

        public Builder onAttach(final Consumer<? super V> onAttach) {
            mOnAttach = onAttach;
            return this;
        }

        public Builder onDetach(final Consumer<? super V> onDetach) {
            mOnDetach = onDetach;
            return this;
        }

        public Builder onGetSpan(final Supplier<Integer> onGetSpan) {
            mOnGetSpan = onGetSpan;
            return this;
        }

        public HeterogeneousBinder<D, V> build() {
            return new HeterogeneousBinderImpl<>(
                    mIsMyData,
                    mOnCreateViewHolder,
                    mOnBindViewHolder,
                    mOnAttach,
                    mOnDetach,
                    mOnGetSpan);
        }

    }
}
