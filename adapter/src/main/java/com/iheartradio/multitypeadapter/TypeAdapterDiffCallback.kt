package com.iheartradio.multitypeadapter

import android.support.v7.util.DiffUtil

class TypeAdapterDiffCallback internal constructor(private val multiTypeAdapter: MultiTypeAdapter,
                                                   private val newData: List<Any>,
                                                   private val oldData: List<Any>) :
        DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return multiTypeAdapter.isDataTheSame(oldData[oldItemPosition], newData[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return multiTypeAdapter.areContentsTheSame(oldData[oldItemPosition], newData[newItemPosition])
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return multiTypeAdapter.getChangePayload(oldData[oldItemPosition], newData[newItemPosition])
    }
}