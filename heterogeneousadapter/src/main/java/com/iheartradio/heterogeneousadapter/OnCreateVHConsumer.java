package com.iheartradio.heterogeneousadapter;

/**
 * Created by Jonathan Muller on 4/24/17.
 */

public interface OnCreateVHConsumer <V> {
    V createViewHolder(final InflatingContext inflatingContext);
}
