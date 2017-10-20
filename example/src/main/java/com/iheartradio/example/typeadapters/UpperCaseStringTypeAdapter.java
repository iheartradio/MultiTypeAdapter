package com.iheartradio.example.typeadapters;

import android.view.ViewGroup;

import com.iheartradio.example.data.UpperCaseStringData;
import com.iheartradio.example.viewholders.ListItemOneViewHolder;
import com.iheartradio.multitypeadapter.TypeAdapter;

import java.util.List;

/**
 * Created by Jonathan Muller on 3/7/17.
 */

public class UpperCaseStringTypeAdapter extends TypeAdapter<UpperCaseStringData, ListItemOneViewHolder> {

    @Override
    public boolean isMyData(Object data) {
        return data instanceof UpperCaseStringData;
    }

    @Override
    public ListItemOneViewHolder onCreateViewHolder(final ViewGroup parent) {
        return ListItemOneViewHolder.create(parent);
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
