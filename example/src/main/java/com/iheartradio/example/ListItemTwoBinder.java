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
        return data1.data.equals(data2.data);
    }
}
