package com.iheartradio.example.typeadapters;

import com.iheartradio.example.data.SimpleClickableTextData;
import com.iheartradio.example.viewholders.SimpleItemViewHolder;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.InflatingContext;


/**
 * Created by Jonathan Muller on 4/25/17.
 */

public class SimpleClickTextBinder extends HeterogeneousBinder<SimpleClickableTextData, SimpleItemViewHolder> {

    @Override
    public boolean isMyData(Object data) {
        return data instanceof SimpleClickableTextData;
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(InflatingContext inflating) {
        return SimpleItemViewHolder.create(inflating);
    }

    @Override
    public void onBindViewHolder(SimpleItemViewHolder viewHolder, SimpleClickableTextData data) {
        viewHolder.bind(data);
    }
}
