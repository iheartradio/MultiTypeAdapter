package com.iheartradio.example.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iheartradio.example.R;
import com.iheartradio.example.data.UpperCaseStringData;

/**
 * Created by Jonathan Muller on 3/7/17.
 */

public class ListItemOneViewHolder extends RecyclerView.ViewHolder {

    TextView mText;

    public ListItemOneViewHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text);
    }

    public static ListItemOneViewHolder create(final ViewGroup parent) {
        return new ListItemOneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_1, parent, false));
    }

    public void bind(final UpperCaseStringData data) {
        mText.setText(data.getData());
    }
}
