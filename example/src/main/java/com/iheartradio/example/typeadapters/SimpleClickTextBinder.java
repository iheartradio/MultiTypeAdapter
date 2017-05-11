package com.iheartradio.example.typeadapters;

import android.os.Bundle;

import com.iheartradio.example.data.SimpleClickableTextData;
import com.iheartradio.example.viewholders.SimpleItemViewHolder;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.InflatingContext;

import java.util.List;


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
    public void onBindViewHolder(SimpleItemViewHolder viewHolder, SimpleClickableTextData data, final List<Object> payloads) {
        viewHolder.bind(data, payloads);
    }

    @Override
    public boolean isDataEqual(SimpleClickableTextData data1, SimpleClickableTextData data2) {
        return data1.getText().equals(data2.getText());
    }

    @Override
    public Object getChangePayload(SimpleClickableTextData oldData, SimpleClickableTextData newData, final Bundle diffBundle) {
        if (!oldData.getText().equals(newData.getText())) {
            diffBundle.putString(SimpleItemViewHolder.TEXT_KEY, newData.getText());
        }
        return diffBundle;
    }
}
