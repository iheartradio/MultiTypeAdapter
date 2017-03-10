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
}
