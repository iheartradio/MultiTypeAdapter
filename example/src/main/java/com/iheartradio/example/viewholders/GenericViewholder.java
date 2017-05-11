package com.iheartradio.example.viewholders;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iheartradio.example.data.ColorData;
import com.iheartradio.heterogeneousadapter.InflatingContext;

/**
 * Created by Jonathan Muller on 4/27/17.
 */

public class GenericViewholder extends RecyclerView.ViewHolder {

    private View mView;

    public GenericViewholder(View itemView) {
        super(itemView);
    }

    public static GenericViewholder create(final InflatingContext inflatingContext,
                                           @LayoutRes final int layoutRes) {
        return new GenericViewholder(inflatingContext.inflate(layoutRes));
    }

    public void bindColor(final ColorData colorData) {
        itemView.setBackgroundColor(colorData.getColor());
    }
}
