package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

import com.iheartradio.heterogeneousadapter.interfaces.TriConsumer;
import com.iheartradio.heterogeneousadapter.interfaces.Consumer;
import com.iheartradio.heterogeneousadapter.interfaces.Function1;
import com.iheartradio.heterogeneousadapter.interfaces.Supplier;

import java.util.List;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public class TypeAdapterFactory {

    private static final int DEFAULT_SPAN = 1;

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(final Class<D> targetClass,
                                                                                  final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  final Consumer<? super V> onAttach,
                                                                                  final Consumer<? super V> onDetach) {
        return new TypeAdapterImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, new Supplier<Integer>() {
            @Override
            public Integer invoke() {
                return DEFAULT_SPAN;
            }
        });
    }

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(final Class<D> targetClass,
                                                                                  final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  final Consumer<? super V> onAttach,
                                                                                  final Consumer<? super V> onDetach,
                                                                                  final Supplier<Integer> spanSupplier) {
        return new TypeAdapterImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, spanSupplier);
    }

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(final Class<D> targetClass,
                                                                                  final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder) {
        return new TypeAdapterImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, new Consumer<V>() {
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

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(final Class<D> targetClass,
                                                                                  final Function1<InflatingContext, ? extends V> onCreateViewHolder) {
        return new TypeAdapterImpl<>(targetClass, onCreateViewHolder,
            new TriConsumer<V, D, List<Object>>() {
                @Override
                public void invoke(V v, D d, List<Object> payloads) {

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

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(final Class<D> targetClass,
                                                                                  final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  final Supplier<Integer> spanSupplier) {
        return new TypeAdapterImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, new Consumer<V>() {
            @Override
            public void invoke(final V viewHolder) {

            }
        }, new Consumer<V>() {
            @Override
            public void invoke(final V viewHolder) {

            }
        }, spanSupplier);
    }

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(final Function1<Object, Boolean> isMyData,
                                                                                  final Function1<InflatingContext, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  final Consumer<? super V> onAttach,
                                                                                  final Consumer<? super V> onDetach) {
        return new TypeAdapterImpl<>(isMyData, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, new Supplier<Integer>() {
            @Override
            public Integer invoke() {
                return DEFAULT_SPAN;
            }
        });
    }
}
