package com.iheartradio.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iheartradio.heterogeneousadapter.InflatingContext;

/**
 * Created by Jonathan Muller on 3/10/17.
 */

public class ListItemTwoViewHolder extends RecyclerView.ViewHolder {

    TextView mText;

    public ListItemTwoViewHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text);
    }

    public static ListItemTwoViewHolder create(final InflatingContext inflatingContext) {
        return new ListItemTwoViewHolder(inflatingContext.inflate(R.layout.list_item_2));
    }

    public void bind(final ListItemTwoData data) {
        mText.setText(data.getData());
    }
}
