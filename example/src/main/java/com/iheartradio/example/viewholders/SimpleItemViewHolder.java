package com.iheartradio.example.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.iheartradio.example.R;
import com.iheartradio.example.data.SimpleClickableTextData;
import com.iheartradio.heterogeneousadapter.InflatingContext;

/**
 * Created by Jonathan Muller on 4/25/17.
 */

public class SimpleItemViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    public SimpleItemViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.text);
    }

    public static SimpleItemViewHolder create(final InflatingContext inflatingContext) {
        return new SimpleItemViewHolder(inflatingContext.inflate(R.layout.list_item_2));
    }

    public void bind(final SimpleClickableTextData data) {
        mTextView.setText(data.getText());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.getAction().run();
            }
        });
    }
}
