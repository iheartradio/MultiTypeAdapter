package com.iheartradio.example.viewholders;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iheartradio.example.R;
import com.iheartradio.example.data.SimpleClickableTextData;

import java.util.List;

/**
 * Created by Jonathan Muller on 4/25/17.
 */

public class SimpleItemViewHolder extends RecyclerView.ViewHolder {

    public static final String TEXT_KEY = "TEXT_KEY";

    private TextView mTextView;

    public SimpleItemViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.text);
    }

    public static SimpleItemViewHolder create(final ViewGroup parent) {
        return new SimpleItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_2, parent, false));
    }

    public void bind(final SimpleClickableTextData data, final List<Object> payloads) {

        if (payloads.isEmpty()) {
            mTextView.setText(data.getText());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.getAction().run();
                }
            });
        } else {
            Bundle payload = (Bundle) payloads.get(0);
            mTextView.setText(payload.getString(TEXT_KEY));
        }
    }
}