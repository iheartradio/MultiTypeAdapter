package com.iheartradio.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iheartradio.heterogeneousadapter.InflatingContext;

/**
 * Created by Jonathan Muller on 3/7/17.
 */

public class ListItemOneViewHolder extends RecyclerView.ViewHolder {

    public ListItemOneViewHolder(View itemView) {
        super(itemView);
    }

    public static ListItemOneViewHolder create(final InflatingContext inflatingContext) {
        return new ListItemOneViewHolder(inflatingContext.inflate(R.layout.list_item_1));
    }
}
