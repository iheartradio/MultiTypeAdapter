package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.iheartradio.heterogeneousadapter.SimpleItemTouchHelperCallback;
import com.iheartradio.heterogeneousadapter.interfaces.ItemTouchHelperAdapter;

/**
 * Created by Jonathan Muller on 4/28/17.
 */

public class ItemTouchHelperFactory {

    public static ItemTouchHelper create(final ItemTouchHelperAdapter adapter,
                                         final RecyclerView recyclerView,
                                         final boolean isSwipeEnabled,
                                         final boolean isVerticalDragEnabled,
                                         final boolean isHorizontalDragEnabled) {
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter,
                                                                              isSwipeEnabled,
                                                                              isVerticalDragEnabled,
                                                                              isHorizontalDragEnabled);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return itemTouchHelper;
    }
}
