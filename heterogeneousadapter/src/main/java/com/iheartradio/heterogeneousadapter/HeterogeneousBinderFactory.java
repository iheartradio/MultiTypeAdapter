package com.iheartradio.heterogeneousadapter;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public class HeterogeneousBinderFactory {

    private static final int DEFAULT_SPAN = 1;

//    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
//                                                                                          final Function<InflatingContext, ? extends V> onCreateViewHolder,
//                                                                                          final ViewBinder<? super V, ? super D> onBindViewHolder,
//                                                                                          final Receiver<? super V> onAttach,
//                                                                                          final Receiver<? super V> onDetach) {
//        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, () -> DEFAULT_SPAN);
//    }
//
//    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
//                                                                                          final Function<InflatingContext, ? extends V> onCreateViewHolder,
//                                                                                          final ViewBinder<? super V, ? super D> onBindViewHolder,
//                                                                                          final Receiver<? super V> onAttach,
//                                                                                          final Receiver<? super V> onDetach,
//                                                                                          final Supplier<Integer> getSpan) {
//        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, onAttach, onDetach, getSpan);
//    }
//
//    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
//                                                                                          final Function<InflatingContext, ? extends V> onCreateViewHolder,
//                                                                                          final ViewBinder<? super V, ? super D> onBindViewHolder) {
//        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, v -> {}, v -> {}, () -> DEFAULT_SPAN);
//    }
//
//    public static <D, V extends RecyclerView.ViewHolder> HeterogeneousBinder<D, V> create(final Class<D> targetClass,
//                                                                                          final Function<InflatingContext, ? extends V> onCreateViewHolder,
//                                                                                          final ViewBinder<? super V, ? super D> onBindViewHolder,
//                                                                                          final Supplier<Integer> getSpan) {
//        return new HeterogeneousBinderImpl<>(targetClass, onCreateViewHolder, onBindViewHolder, v -> {}, v -> {}, getSpan);
//    }

}
