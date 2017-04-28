package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.iheartradio.heterogeneousadapter.interfaces.ItemTouchHelperAdapter;

/**
 * Created by Jonathan Muller on 4/28/17.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter mAdapter;
    private final boolean mIsItemSwipeEnabled;
    private final boolean mIsVerticalDragEnabled;
    private final boolean mIsHorizontalDragEnabled;

    public SimpleItemTouchHelperCallback(final ItemTouchHelperAdapter adapter,
                                         final boolean isItemSwipeEnabled,
                                         final boolean isVerticalDragEnabled,
                                         final boolean isHorizontalDragEnabled) {
        mAdapter = adapter;
        mIsItemSwipeEnabled = isItemSwipeEnabled;
        mIsVerticalDragEnabled = isVerticalDragEnabled;
        mIsHorizontalDragEnabled = isHorizontalDragEnabled;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = 0;
        if (mIsHorizontalDragEnabled && mIsVerticalDragEnabled) {
            dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else if (mIsHorizontalDragEnabled) {
            dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else if (mIsVerticalDragEnabled) {
            dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }

        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlag, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return mIsVerticalDragEnabled;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return mIsItemSwipeEnabled;
    }
}
