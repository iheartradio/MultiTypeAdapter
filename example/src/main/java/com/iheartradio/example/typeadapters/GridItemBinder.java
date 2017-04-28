package com.iheartradio.example.typeadapters;

import com.iheartradio.example.R;
import com.iheartradio.example.data.ColorData;
import com.iheartradio.example.viewholders.GenericViewholder;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.InflatingContext;

/**
 * Created by Jonathan Muller on 4/27/17.
 */

public class GridItemBinder extends HeterogeneousBinder<ColorData, GenericViewholder> {

    @Override
    public boolean isMyData(Object data) {
        return data instanceof ColorData;
    }

    @Override
    public GenericViewholder onCreateViewHolder(InflatingContext inflating) {
        return GenericViewholder.create(inflating, R.layout.grid_item);
    }

    @Override
    public boolean isDataEqual(ColorData data1, ColorData data2) {
        return data1.getColor() == data2.getColor();
    }

    @Override
    public void onBindViewHolder(GenericViewholder viewHolder, ColorData data) {
        viewHolder.bindColor(data);
    }
}
