package com.iheartradio.multitypeadapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Default implementation of SpanHandler that uses the TypeAdapters
 * getSpan() method to calculate spans for each item.
 *
 * This is only utilized for GridLayoutManagers.
 */
class GridLayoutSpanHandler internal constructor(private val adapter: MultiTypeAdapter) : SpanHandler {

    private var spanLookupSet: Boolean = false

    override fun calculateSpan(layoutManager: RecyclerView.LayoutManager) {
        if (!spanLookupSet && layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return adapter.getTypeAdapterForPosition(position).span
                }
            }
            spanLookupSet = true
        }
    }
}
