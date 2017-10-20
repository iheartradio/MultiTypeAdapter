package com.iheartradio.example.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iheartradio.example.R;
import com.iheartradio.example.data.LowerCaseStringData;

/**
 * Created by Jonathan Muller on 3/10/17.
 */

public class ListItemTwoViewHolder extends RecyclerView.ViewHolder {

    TextView mText;

    public ListItemTwoViewHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text);
    }

    public static ListItemTwoViewHolder create(final ViewGroup parent) {
        return new ListItemTwoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_2, parent, false));
    }

    public void bind(final LowerCaseStringData data) {
        mText.setText(data.getData());
    }
}
