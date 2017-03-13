package com.iheartradio.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jonathan Muller on 3/10/17.
 */

public class ListItemTwoViewHolder extends RecyclerView.ViewHolder {

    public ListItemTwoViewHolder(View itemView) {
        super(itemView);
    }

    public static ListItemTwoViewHolder create(final ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_2, parent, false);
        return new ListItemTwoViewHolder(view);
    }
}
