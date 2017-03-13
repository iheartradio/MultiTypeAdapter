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
    public boolean areItemsTheSame(ListItemTwoData oldData, ListItemTwoData newData) {
        return oldData.data.equals(newData.data);
    }

    @Override
    public boolean areContentsTheSame(ListItemTwoData oldData, ListItemTwoData newData) {
        return oldData.data.equals(newData.data);
    }
}
