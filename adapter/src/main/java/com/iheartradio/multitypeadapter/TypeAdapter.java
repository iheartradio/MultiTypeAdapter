package com.iheartradio.multitypeadapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.iheartradio.multitypeadapter.interfaces.BiConsumer;
import com.iheartradio.multitypeadapter.interfaces.Consumer;
import com.iheartradio.multitypeadapter.interfaces.Function1;
import com.iheartradio.multitypeadapter.interfaces.Function2;
import com.iheartradio.multitypeadapter.interfaces.Function3;
import com.iheartradio.multitypeadapter.interfaces.Supplier;
import com.iheartradio.multitypeadapter.interfaces.TriConsumer;

import java.util.List;

public abstract class TypeAdapter<D, V extends RecyclerView.ViewHolder> {

    protected static final int DEFAULT_SPAN = 1;

    /**
     * Used to determine if this TypeAdapter manages the incoming data
     * @param data
     * @return true if data is managed by this TypeAdapter
     */
    public abstract boolean isMyData(final Object data);

    /**
     * @param parent
     * @return ViewHolder of type V
     */
    public abstract V onCreateViewHolder(final ViewGroup parent);

    /**
     *
     * @param viewHolder
     * @param data to be bound to the viewholder
     */
    public void onBindViewHolder(final V viewHolder, final D data) { }

    public void onBindViewHolder(final V viewHolder, final D data, final List<Object> payloads) {
        onBindViewHolder(viewHolder, data);
    }

    public void onAttach(final V view) { }

    public void onDetach(final V view) { }

    /**
     * Used to determine the span for this data
     * @return default span of 1 unless overridden
     */
    public int getSpan() {
        return DEFAULT_SPAN;
    }

    /**
     * Used to determine if two pieces of data are equal. E.g. If their id is the same, or content
     * @param data1
     * @param data2
     * @return
     */
    public boolean isDataEqual(final D data1, final D data2) {
        return false;
    }

    public Object getChangePayload(final D oldData, final D newData, final Bundle diffBundle) { return null; }








    public static class Builder<D, V extends RecyclerView.ViewHolder> {

        private static final int DEFAULT_SPAN = 1;

        private final Function1<Object, Boolean> mIsMyData;
        private final Function1<ViewGroup, ? extends V> mOnCreateViewHolder;

        private TriConsumer<? super V, ? super D, List<Object>> mOnBindViewHolder = new TriConsumer<V, D, List<Object>>() {
            @Override
            public void invoke(final V v, final D d, final List<Object> objects) {
                // NO OP
            }
        };
        private Consumer<? super V> mOnAttach = new Consumer<V>() {
            @Override
            public void invoke(final V v) {
                // NO OP
            }
        };
        private Consumer<? super V> mOnDetach = new Consumer<V>() {
            @Override
            public void invoke(final V v) {
                // NO OP
            }
        };
        private Supplier<Integer> mSpanSupplier = new Supplier<Integer>() {
            @Override
            public Integer invoke() {
                return DEFAULT_SPAN;
            }
        };
        private Function2<D, D, Boolean> mIsDataEqual = new Function2<D, D, Boolean>() {
            @Override
            public Boolean invoke(final D d, final D d2) {
                return false;
            }
        };
        private Function3<D, D, Bundle, Object> mGetChangePayload = new Function3<D, D, Bundle, Object>() {
            @Override
            public Object invoke(final D d, final D d2, final Bundle bundle) {
                return null;
            }
        };

        public Builder(@NonNull final Function1<Object, Boolean> isMyData,
                       @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder) {
            mIsMyData = isMyData;
            mOnCreateViewHolder = onCreateViewHolder;
        }

        public Builder(@NonNull final Class<?> dataType,
                       @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder) {
            mIsMyData = new Function1<Object, Boolean>() {
                @Override
                public Boolean invoke(final Object o) {
                    return dataType.isAssignableFrom(o.getClass());
                }
            };
            mOnCreateViewHolder = onCreateViewHolder;
        }

        public Builder<D, V> setOnBindViewHolder(@NonNull final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder) {
            mOnBindViewHolder = onBindViewHolder;
            return this;
        }

        public Builder<D, V> setOnBindViewHolder(@NonNull final BiConsumer<? super V, ? super D> onBindViewHolder) {
            mOnBindViewHolder = TypeAdapterFactory.toTriConsumer(onBindViewHolder);
            return this;
        }

        public Builder<D, V> setOnAttach(@NonNull final Consumer<? super V> onAttach) {
            mOnAttach = onAttach;
            return this;
        }

        public Builder<D, V> setOnDetach(@NonNull final Consumer<? super V> onDetach) {
            mOnDetach= onDetach;
            return this;
        }

        public Builder<D, V> setSpanSupplier(@NonNull final Supplier<Integer> spanSupplier) {
            mSpanSupplier = spanSupplier;
            return this;
        }

        public Builder<D, V> setSpan(@NonNull final int span) {
            mSpanSupplier = new Supplier<Integer>() {
                @Override
                public Integer invoke() {
                    return span;
                }
            };
            return this;
        }

        public Builder<D, V> setIsDataEqual(@NonNull final Function2<D, D, Boolean> isDataEqual) {
            mIsDataEqual = isDataEqual;
            return this;
        }

        public Builder<D, V> setGetChangePayload(@NonNull final Function3<D, D, Bundle, Object> getChangePayload) {
            mGetChangePayload = getChangePayload;
            return this;
        }

        public TypeAdapter<D, V> build() {
            return new TypeAdapter<D, V>() {
                @Override
                public boolean isMyData(final Object data) {
                    return mIsMyData.invoke(data);
                }

                @Override
                public V onCreateViewHolder(final ViewGroup parent) {
                    return mOnCreateViewHolder.invoke(parent);
                }

                @Override
                public void onBindViewHolder(final V viewHolder, final D data, final List<Object> payloads) {
                    mOnBindViewHolder.invoke(viewHolder, data, payloads);
                }

                @Override
                public void onAttach(final V view) {
                    mOnAttach.invoke(view);
                }

                @Override
                public void onDetach(final V view) {
                    mOnDetach.invoke(view);
                }

                @Override
                public int getSpan() {
                    return mSpanSupplier.invoke();
                }

                @Override
                public boolean isDataEqual(final D data1, final D data2) {
                    return mIsDataEqual.invoke(data1, data2);
                }

                @Override
                public Object getChangePayload(final D oldData, final D newData, final Bundle diffBundle) {
                    return mGetChangePayload.invoke(oldData, newData, diffBundle);
                }
            };
        }
    }

}
