package com.iheartradio.example.typeadapters

import android.os.Bundle
import android.view.ViewGroup

import com.iheartradio.example.data.SimpleClickableTextData
import com.iheartradio.example.viewholders.SimpleItemViewHolder
import com.iheartradio.multitypeadapter.TypeAdapter


/**
 * Created by Jonathan Muller on 4/25/17.
 */

class SimpleClickTextTypeAdapter : TypeAdapter<SimpleClickableTextData, SimpleItemViewHolder>() {

    override fun isMyData(data: Any): Boolean {
        return data is SimpleClickableTextData
    }

    override fun onCreateViewHolder(parent: ViewGroup): SimpleItemViewHolder {
        return SimpleItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(viewHolder: SimpleItemViewHolder, data: SimpleClickableTextData, payloads: List<Any>?) {
        viewHolder.bind(data, payloads)
    }

    override fun isDataEqual(data1: SimpleClickableTextData, data2: SimpleClickableTextData): Boolean {
        return data1.text == data2.text
    }

    override fun getChangePayload(oldData: SimpleClickableTextData, newData: SimpleClickableTextData, diffBundle: Bundle): Any? {
        if (oldData.text != newData.text) {
            diffBundle.putString(SimpleItemViewHolder.TEXT_KEY, newData.text)
        }
        return diffBundle
    }
}
