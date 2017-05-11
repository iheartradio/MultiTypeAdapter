package com.iheartradio.example.typeadapters;

import com.iheartradio.example.data.UpperCaseStringData;
import com.iheartradio.example.viewholders.ListItemOneViewHolder;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.InflatingContext;

import java.util.List;

/**
 * Created by Jonathan Muller on 3/7/17.
 */

public class UpperCaseStringBinder extends HeterogeneousBinder<UpperCaseStringData, ListItemOneViewHolder> {

    @Override
    public boolean isMyData(Object data) {
        return data instanceof UpperCaseStringData;
    }

    @Override
    public ListItemOneViewHolder onCreateViewHolder(InflatingContext inflating) {
        return ListItemOneViewHolder.create(inflating);
    }

    @Override
    public boolean isDataEqual(final UpperCaseStringData data1, final UpperCaseStringData data2) {
        if (data1 == null || data2 == null) {
            return false;
        }
        return data1.getData().equals(data2.getData());
    }

    @Override
    public void onBindViewHolder(ListItemOneViewHolder viewHolder, UpperCaseStringData data, final List<Object> payloads) {
        viewHolder.bind(data);
    }
}
