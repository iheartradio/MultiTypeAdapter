package com.iheartradio.heterogeneousadapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Muller on 4/18/17.
 */

public final class TypeAdapterHandler {

    private final List<TypeAdapter<?, ?>> mBinders;

    TypeAdapterHandler(final List<TypeAdapter<?, ?>> binders) {
        mBinders = binders;
    }

    TypeAdapterHandler(final TypeAdapter<?, ?> binder) {
        mBinders = new ArrayList<>();
        mBinders.add(binder);
    }

    RecyclerView.ViewHolder createViewHolder(final ViewGroup parent, final int viewType) {
        return mBinders.get(viewType).onCreateViewHolder(InflatingContext.fromParent(parent));
    }

    void bindViewHolder(final RecyclerView.ViewHolder genericHolder, final Object data, final List<Object> payloads) {
        TypeAdapter<Object, RecyclerView.ViewHolder> binder = getBinderForData(data);
        if (binder != null) {
            binder.onBindViewHolder(genericHolder, data, payloads);
        }
    }

    public List<TypeAdapter<?, ?>> getBinders() {
        return mBinders;
    }

    @SuppressWarnings("unchecked")
    TypeAdapter<Object, RecyclerView.ViewHolder> getBinderForType(final int type) {
        return (TypeAdapter<Object, RecyclerView.ViewHolder>) mBinders.get(type);
    }

    @SuppressWarnings("unchecked")
    TypeAdapter<Object, RecyclerView.ViewHolder> getBinderForData(final Object data) {
        for (int i = 0; i < mBinders.size(); i++) {
            if (mBinders.get(i).isMyData(data)) {
                return (TypeAdapter<Object, RecyclerView.ViewHolder>) mBinders.get(i);
            }
        }

        throw new RuntimeException("Could not find binder for data type: " + data.getClass().getSimpleName());
    }

    int getItemTypeForData(final Object data) {
        for (int i = 0; i < mBinders.size(); i++) {
            if (mBinders.get(i).isMyData(data)) {
                return i;
            }
        }

        throw new RuntimeException("Could not find binder index for data type: " + data.getClass().getSimpleName());
    }

    boolean isDataTheSame(final Object data1, final Object data2) {
        if (getItemTypeForData(data1) == getItemTypeForData(data2)) {
            return getBinderForData(data1).isDataEqual(data1, data2);
        } else {
            return false;
        }
    }

    Object getChangePayload(final Object oldData, final Object newData) {
        return getBinderForData(newData).getChangePayload(oldData, newData, new Bundle());
    }
}