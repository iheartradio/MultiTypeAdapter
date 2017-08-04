package com.iheartradio.heterogeneousadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.iheartradio.heterogeneousadapter.interfaces.Consumer;
import com.iheartradio.heterogeneousadapter.interfaces.Function1;
import com.iheartradio.heterogeneousadapter.interfaces.Supplier;
import com.iheartradio.heterogeneousadapter.interfaces.TriConsumer;
import java.util.List;

public class TypeAdapterFactory {

    private static final int DEFAULT_SPAN = 1;

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder) {
        return new TypeAdapterImpl<>(targetClass,
                onCreateViewHolder,
                null,
                null,
                null,
                null);
    }

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder) {
        return new TypeAdapterImpl<>(targetClass,
                onCreateViewHolder,
                onBindViewHolder,
                null,
                null,
                null);
    }

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  final Consumer<? super V> onAttach,
                                                                                  final Consumer<? super V> onDetach) {
        return new TypeAdapterImpl<>(targetClass,
                onCreateViewHolder,
                onBindViewHolder,
                onAttach,
                onDetach,
                null);
    }

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  final Supplier<Integer> spanSupplier) {
        return new TypeAdapterImpl<>(targetClass,
                onCreateViewHolder,
                onBindViewHolder,
                null,
                null,
                spanSupplier);
    }

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Function1<Object, Boolean> isMyData,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  final Consumer<? super V> onAttach,
                                                                                  final Consumer<? super V> onDetach) {
        return new TypeAdapterImpl<>(isMyData,
                onCreateViewHolder,
                onBindViewHolder,
                onAttach,
                onDetach,
                null);
    }

    public static <D, V extends RecyclerView.ViewHolder> TypeAdapter<D, V> create(@NonNull final Class<D> targetClass,
                                                                                  @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                                                                                  final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                                                                                  final Consumer<? super V> onAttach,
                                                                                  final Consumer<? super V> onDetach,
                                                                                  final Supplier<Integer> spanSupplier) {
        return new TypeAdapterImpl<>(targetClass,
                onCreateViewHolder,
                onBindViewHolder,
                onAttach,
                onDetach,
                spanSupplier);
    }
}
