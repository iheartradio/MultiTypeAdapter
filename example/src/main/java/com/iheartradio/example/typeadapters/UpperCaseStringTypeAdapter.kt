package com.iheartradio.example.typeadapters

import android.view.ViewGroup

import com.iheartradio.example.data.UpperCaseStringData
import com.iheartradio.example.viewholders.ListItemOneViewHolder
import com.iheartradio.multitypeadapter.TypeAdapter

/**
 * Created by Jonathan Muller on 3/7/17.
 */

class UpperCaseStringTypeAdapter : TypeAdapter<UpperCaseStringData, ListItemOneViewHolder>() {

    override fun isMyData(data: Any): Boolean {
        return data is UpperCaseStringData
    }

    override fun onCreateViewHolder(parent: ViewGroup): ListItemOneViewHolder {
        return ListItemOneViewHolder.create(parent)
    }

    override fun isDataEqual(data1: UpperCaseStringData, data2: UpperCaseStringData): Boolean {
        return data1.data == data2.data
    }

    fun onBindViewHolder(viewHolder: ListItemOneViewHolder, data: UpperCaseStringData, payloads: List<Any>) {
        viewHolder.bind(data)
    }
}
