package com.iheartradio.heterogeneousadapter;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeDataHelper {

    private final List<Object> mData = new ArrayList<>();

    public MultiTypeDataHelper add(final Object data) {
        mData.add(data);
        return this;
    }

    public MultiTypeDataHelper add(final List<?> data) {
        mData.addAll(data);
        return this;
    }

    public MultiTypeDataHelper add(final int index, final Object data) {
        mData.add(index, data);
        return this;
    }

    public MultiTypeDataHelper add(final int index, final List<?> data) {
        mData.addAll(index, data);
        return this;
    }

    public List<Object> getData() {
        return mData;
    }
}
