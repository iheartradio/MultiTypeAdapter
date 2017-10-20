package com.iheartradio.multitypeadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.iheartradio.multitypeadapter.interfaces.Consumer;
import com.iheartradio.multitypeadapter.interfaces.Function1;
import com.iheartradio.multitypeadapter.interfaces.Supplier;
import com.iheartradio.multitypeadapter.interfaces.TriConsumer;

import java.util.List;

/**
 * Generic TypeAdapter implementation used by the TypeAdaptersFactory to create its implementations
 * @param <D>
 * @param <V>
 */
class TypeAdapterImpl<D, V extends RecyclerView.ViewHolder> extends TypeAdapter<D, V> {

    private static final int DEFAULT_SPAN = 1;

    private final Function1<Object, Boolean> mIsMyData;
    private final Function1<ViewGroup, ? extends V> mOnCreateViewHolder;
    private final TriConsumer<? super V, ? super D, List<Object>> mOnBindViewHolder;
    private final Consumer<? super V> mOnAttach;
    private final Consumer<? super V> mOnDetach;
    private final Supplier<Integer> mSpanSupplier;

    TypeAdapterImpl(@NonNull final Function1<Object, Boolean> isMyData,
                    @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                    @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                    @Nullable final Consumer<? super V> onAttach,
                    @Nullable final Consumer<? super V> onDetach,
                    @Nullable final Supplier<Integer> getSpan) {

        mIsMyData = isMyData;
        mOnCreateViewHolder = onCreateViewHolder;
        mOnBindViewHolder = onBindViewHolder;
        mOnAttach = onAttach;
        mOnDetach = onDetach;
        mSpanSupplier = getSpan;
    }

    TypeAdapterImpl(@NonNull final Class<?> targetClass,
                    @NonNull final Function1<ViewGroup, ? extends V> onCreateViewHolder,
                    @Nullable final TriConsumer<? super V, ? super D, List<Object>> onBindViewHolder,
                    @Nullable final Consumer<? super V> onAttach,
                    @Nullable final Consumer<? super V> onDetach,
                    @Nullable final Supplier<Integer> getSpan) {
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
    public V onCreateViewHolder(final ViewGroup inflating) {
        return mOnCreateViewHolder.invoke(inflating);
    }

    @Override
    public void onBindViewHolder(final V viewHolder, final D data, final List<Object> payloads) {
        if (mOnBindViewHolder != null) {
            mOnBindViewHolder.invoke(viewHolder, data, payloads);
        }
    }

    @Override
    public void onAttach(V viewHolder) {
        if (mOnAttach != null) {
            mOnAttach.invoke(viewHolder);
        }
    }

    @Override
    public void onDetach(V viewHolder) {
        if (mOnDetach != null) {
            mOnDetach.invoke(viewHolder);
        }
    }

    @Override
    public int getSpan() {
        if (mSpanSupplier != null) {
            return mSpanSupplier.invoke();
        } else {
            return DEFAULT_SPAN;
        }
    }

}
