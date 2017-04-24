package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public class HeterogeneousBinderFactory {

    private static final int DEFAULT_SPAN = 1;

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
                                                                                          final OnCreateVHConsumer<? extends V> onCreateViewHolder,
                                                                                          final OnBindConsumer<? super V, ? super D> onBindViewHolder,
                                                                                          final VHConsumer<? super V> onAttach,
                                                                                          final VHConsumer<? super V> onDetach) {
        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, new SpanSupplier() {
            @Override
            public int getSpan() {
                return DEFAULT_SPAN;
            }
        });
    }

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
                                                                                          final OnCreateVHConsumer<? extends V> onCreateViewHolder,
                                                                                          final OnBindConsumer<? super V, ? super D> onBindViewHolder,
                                                                                          final VHConsumer<? super V> onAttach,
                                                                                          final VHConsumer<? super V> onDetach,
                                                                                          final SpanSupplier spanSupplier) {
        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, spanSupplier);
    }

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
                                                                                          final OnCreateVHConsumer<? extends V> onCreateViewHolder,
                                                                                          final OnBindConsumer<? super V, ? super D> onBindViewHolder) {
        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, new VHConsumer<V>() {
            @Override
            public void accept(final V viewHolder) {

            }
        }, new VHConsumer<V>() {
            @Override
            public void accept(final V viewHolder) {

            }
        }, new SpanSupplier() {
            @Override
            public int getSpan() {
                return DEFAULT_SPAN;
            }
        });
    }

    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
                                                                                          final OnCreateVHConsumer<? extends V> onCreateViewHolder,
                                                                                          final OnBindConsumer<? super V, ? super D> onBindViewHolder,
                                                                                          final SpanSupplier spanSupplier) {
        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, new VHConsumer<V>() {
            @Override
            public void accept(final V viewHolder) {

            }
        }, new VHConsumer<V>() {
            @Override
            public void accept(final V viewHolder) {

            }
        }, spanSupplier);
    }
}
