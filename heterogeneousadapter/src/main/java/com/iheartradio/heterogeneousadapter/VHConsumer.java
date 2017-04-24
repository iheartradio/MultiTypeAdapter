package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Jonathan Muller on 4/24/17.
 */

public interface VHConsumer<T extends RecyclerView.ViewHolder> {
    void accept(T viewHolder);
}
