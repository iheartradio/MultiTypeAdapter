package com.iheartradio.example;

import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.InflatingContext;

/**
 * Created by Jonathan Muller on 3/10/17.
 */

public class ListItemTwoBinder extends HeterogeneousBinder<ListItemTwoData, ListItemTwoViewHolder> {

    @Override
    public boolean isMyData(Object data) {
        return data instanceof ListItemTwoData;
    }

    @Override
    public ListItemTwoViewHolder onCreateViewHolder(InflatingContext inflating) {
        return ListItemTwoViewHolder.create(inflating.parent());
    }

    @Override
    public boolean isDataEqual(final ListItemTwoData data1, final ListItemTwoData data2) {
        if (data1 == null || data2 == null) {
            return false;
        }
        return data1.getData().equals(data2.getData());
    }

    @Override
    public void onBindViewHolder(final ListItemTwoViewHolder viewHolder, final ListItemTwoData data) {
        viewHolder.bind(data);
    }
}
