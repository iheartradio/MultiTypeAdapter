package com.iheartradio.example.typeadapters;

import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.viewholders.ListItemTwoViewHolder;
import com.iheartradio.heterogeneousadapter.TypeAdapter;
import com.iheartradio.heterogeneousadapter.InflatingContext;

import java.util.List;

/**
 * Created by Jonathan Muller on 3/10/17.
 */

public class LowerCaseStringTypeAdapter extends TypeAdapter<LowerCaseStringData, ListItemTwoViewHolder> {

    @Override
    public boolean isMyData(Object data) {
        return data instanceof LowerCaseStringData;
    }

    @Override
    public ListItemTwoViewHolder onCreateViewHolder(InflatingContext inflating) {
        return ListItemTwoViewHolder.create(inflating);
    }

    @Override
    public boolean isDataEqual(final LowerCaseStringData data1, final LowerCaseStringData data2) {
        if (data1 == null || data2 == null) {
            return false;
        }
        return data1.getData().equals(data2.getData());
    }

    @Override
    public void onBindViewHolder(final ListItemTwoViewHolder viewHolder,
                                 final LowerCaseStringData data,
                                 final List<Object> payloads) {
        viewHolder.bind(data);
    }

    @Override
    public int getSpan() {
        return 2;
    }
}
