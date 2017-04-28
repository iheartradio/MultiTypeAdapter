package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

import com.iheartradio.heterogeneousadapter.interfaces.BiConsumer;
import com.iheartradio.heterogeneousadapter.interfaces.Consumer;
import com.iheartradio.heterogeneousadapter.interfaces.Function1;
import com.iheartradio.heterogeneousadapter.interfaces.Supplier;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public class HeterogeneousBinderFactory {

    private static final int DEFAULT_SPAN = 1;

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
                                                                                          final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                          final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                          final Consumer<? super V> onAttach,
                                                                                          final Consumer<? super V> onDetach) {
        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, new Supplier<Integer>() {
            @Override
            public Integer invoke() {
                return DEFAULT_SPAN;
            }
        });
    }

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
                                                                                          final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                          final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                          final Consumer<? super V> onAttach,
                                                                                          final Consumer<? super V> onDetach,
                                                                                          final Supplier<Integer> spanSupplier) {
        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, spanSupplier);
    }

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
                                                                                          final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                          final BiConsumer<? super V, ? super D> onBindViewHolder) {
        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, new Consumer<V>() {
            @Override
            public void invoke(final V viewHolder) {

            }
        }, new Consumer<V>() {
            @Override
            public void invoke(final V viewHolder) {

            }
        }, new Supplier<Integer>() {
            @Override
            public Integer invoke() {
                return DEFAULT_SPAN;
            }
        });
    }

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
                                                                                          final Function1<InflatingContext, ? extends V> onCreateViewHolder) {
        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder,
            new BiConsumer<V, D>() {
                @Override
                public void invoke(V v, D d) {

                }
        }, new Consumer<V>() {
            @Override
            public void invoke(final V viewHolder) {

            }
        }, new Consumer<V>() {
            @Override
            public void invoke(final V viewHolder) {

            }
        }, new Supplier<Integer>() {
            @Override
            public Integer invoke() {
                return DEFAULT_SPAN;
            }
        });
    }

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
                                                                                          final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                          final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                          final Supplier<Integer> spanSupplier) {
        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, new Consumer<V>() {
            @Override
            public void invoke(final V viewHolder) {

            }
        }, new Consumer<V>() {
            @Override
            public void invoke(final V viewHolder) {

            }
        }, spanSupplier);
    }

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Function1<Object, Boolean> isMyData,
                                                                                          final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                          final BiConsumer<? super V, ? super D> onBindViewHolder,
                                                                                          final Consumer<? super V> onAttach,
                                                                                          final Consumer<? super V> onDetach) {
        return new HeterogeneousBinderImpl<>(isMyData, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, new Supplier<Integer>() {
            @Override
            public Integer invoke() {
                return DEFAULT_SPAN;
            }
        });
    }
}
