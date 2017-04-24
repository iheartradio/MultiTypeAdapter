package com.iheartradio.example;

import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.InflatingContext;

/**
 * Created by Jonathan Muller on 3/7/17.
 */

public class ListItemOneBinder extends HeterogeneousBinder<ListItemOneData, ListItemOneViewHolder> {

    @Override
    public boolean isMyData(Object data) {
        return data instanceof ListItemOneData;
    }

    @Override
    public ListItemOneViewHolder onCreateViewHolder(InflatingContext inflating) {
        return ListItemOneViewHolder.create(inflating.parent());
    }

    @Override
    public boolean isDataEqual(final ListItemOneData data1, final ListItemOneData data2) {
        if (data1 == null || data2 == null) {
            return false;
        }
        return data1.data.equals(data2.data);
    }
}
