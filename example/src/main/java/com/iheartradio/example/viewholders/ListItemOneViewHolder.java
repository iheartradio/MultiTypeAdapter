package com.iheartradio.example.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.iheartradio.example.R;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.data.UpperCaseStringData;
import com.iheartradio.heterogeneousadapter.InflatingContext;

/**
 * Created by Jonathan Muller on 3/7/17.
 */

public class ListItemOneViewHolder extends RecyclerView.ViewHolder {

    TextView mText;

    public ListItemOneViewHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text);
    }

    public static ListItemOneViewHolder create(final InflatingContext inflatingContext) {
        return new ListItemOneViewHolder(inflatingContext.inflate(R.layout.list_item_1));
    }

    public void bind(final UpperCaseStringData data) {
        mText.setText(data.getData());
    }
}
