package com.iheartradio.heterogeneousadapter;

/**
 * Created by Jonathan Muller on 4/24/17.
 */

public interface OnBindConsumer<V, T> {
    void bindViewHolder(V holder, T data);
}
