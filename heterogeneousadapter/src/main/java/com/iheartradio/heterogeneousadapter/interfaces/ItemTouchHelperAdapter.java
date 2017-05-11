package com.iheartradio.heterogeneousadapter.interfaces;

/**
 * Created by Jonathan Muller on 4/28/17.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(final int fromPosition, final int toPosition);
    void onItemDismiss(final int position);
}
