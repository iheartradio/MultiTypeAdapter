package com.iheartradio.example.typeadapters;

import android.view.ViewGroup;

import com.iheartradio.example.R;
import com.iheartradio.example.data.ColorData;
import com.iheartradio.example.viewholders.GenericViewholder;
import com.iheartradio.multitypeadapter.TypeAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Jonathan Muller on 4/27/17.
 */

public class GridItemTypeAdapter extends TypeAdapter<ColorData, GenericViewholder> {

    @Override
    public boolean isMyData(Object data) {
        return data instanceof ColorData;
    }

    @Override
    public GenericViewholder onCreateViewHolder(final ViewGroup parent) {
        return GenericViewholder.create(parent, R.layout.grid_item);
    }

    @Override
    public boolean isDataEqual(ColorData data1, ColorData data2) {
        return data1.getColor() == data2.getColor();
    }

    @Override
    public void onBindViewHolder(@NotNull final GenericViewholder viewHolder,
                                 final ColorData data,
                                 @Nullable final List<?> payloads) {
        viewHolder.bindColor(data);
    }
}
