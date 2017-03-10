package com.iheartradio.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jonathan Muller on 3/7/17.
 */

public class ListItemOneViewHolder extends RecyclerView.ViewHolder {

    public ListItemOneViewHolder(View itemView) {
        super(itemView);
    }

    public static ListItemOneViewHolder create(final ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_1, parent, false);
        return new ListItemOneViewHolder(view);
    }
}
