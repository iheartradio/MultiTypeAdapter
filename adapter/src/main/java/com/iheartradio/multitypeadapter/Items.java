package com.iheartradio.multitypeadapter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to create a list of items to add to the adapter
 */
public class Items {

    private final List<Object> mData = new ArrayList<>();

    public Items() {

    }

    public Items(@NonNull final List<?> data) {
        mData.addAll(data);
    }

    public Items(@NonNull final Object data) {
        mData.add(data);
    }

    public Items add(final Object data) {
        mData.add(data);
        return this;
    }

    public Items add(final List<?> data) {
        mData.addAll(data);
        return this;
    }

    public Items add(final int index, final Object data) {
        mData.add(index, data);
        return this;
    }

    public Items add(final int index, final List<?> data) {
        mData.addAll(index, data);
        return this;
    }

    @Deprecated
    public List<Object> get() {
        return mData;
    }

    public List<Object> data() {
        return mData;
    }
}
