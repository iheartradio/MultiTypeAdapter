package com.iheartradio.multitypeadapter

import android.support.v7.widget.RecyclerView

interface SpanHandler {
    fun calculateSpan(layoutManager: RecyclerView.LayoutManager)
}
