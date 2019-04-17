/*
 * Copyright (C) 2017 iHeartRadio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iheartradio.multitypeadapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import java.util.*

/**
 *
 */
class MultiTypeAdapter : RecyclerView.Adapter<ViewHolder> {

    private val typeAdapters = ArrayList<TypeAdapter<*, *>>()

    private var spanHandler1: SpanHandler? = GridLayoutSpanHandler(this)
    private var data: List<Any> = ArrayList()
    private var recyclerView: RecyclerView? = null

    constructor(typeAdapters: List<TypeAdapter<*, *>>) {
        this.typeAdapters.addAll(typeAdapters)
    }

    constructor(typeAdapter: TypeAdapter<*, *>) {
        this.typeAdapters.add(typeAdapter)
    }

    /**
     * Dynamically adds a TypeAdapte
     * @param typeAdapter to be added
     */
    fun addTypeAdapter(typeAdapter: TypeAdapter<*, *>) {
        this.typeAdapters.add(typeAdapter)
    }

    /**
     * Sets a list of data to the adapter. Overwrites any old data.
     * @param data to be set to the adapter
     * @param animateChanges true by default, false will avoid using DiffUtils to animate items
     */
    @JvmOverloads
    fun setData(data: List<Any>, animateChanges: Boolean = false) {
        var diffResult: DiffUtil.DiffResult? = null
        if (animateChanges) {
            diffResult = DiffUtil.calculateDiff(TypeAdapterDiffCallback(this, data, this.data))
        }

        this.data = data

        setupSpanHandling()

        if (diffResult != null) {
            diffResult.dispatchUpdatesTo(this)
        } else {
            notifyDataSetChanged()
        }
    }

    /**
     * Convenience method for using items.
     * Sets a list of data to the adapter. Overwrites any old data.
     * @param items
     */
    fun setData(items: Items) {
        setData(items.get(), false)
    }

    /**
     * Convenience method for using items.
     * Sets a list of data to the adapter. Overwrites any old data.
     * @param items to be set to the adapter
     * @param animateChanges true by default, false will avoid using DiffUtils to animate items
     */
    fun setData(items: Items, animateChanges: Boolean) {
        setData(items.get(), animateChanges)
    }

    /**
     * To access data with type info, use the TypeAdapter abstract class
     * @return List of Objects representing the data
     */
    fun data(): List<Any> {
        return ArrayList(data)
    }

    /**
     * Sets custom implementation of the SpanHandler. This will overwrite the default GridLayoutSpanHandler
     * @param spanHandler
     */
    fun setSpanHandler(spanHandler: SpanHandler?) {
        spanHandler1 = spanHandler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return this.typeAdapters[viewType].onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getTypeAdapterForData(data[position]).onBindViewHolder(holder, data[position])
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>?) {
        getTypeAdapterForData(data[position]).onBindViewHolder(holder, data[position], payloads)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return getItemTypeForData(data[position])
    }

    override fun onViewAttachedToWindow(genericHolder: ViewHolder) {
        getTypeAdapterForType(genericHolder.itemViewType).onAttach(genericHolder)
    }

    override fun onViewDetachedFromWindow(genericHolder: ViewHolder) {
        getTypeAdapterForType(genericHolder.itemViewType).onDetach(genericHolder)
    }

    /**
     * Type adapter positions are set in the order that they are added. This allows quick access to
     * specific TypeAdapters
     * @param position
     * @return the TypeAdapter at the given position
     */
    fun getTypeAdapterForPosition(position: Int): TypeAdapter<*, *> {
        if (position >= data.size) {
            throw ArrayIndexOutOfBoundsException(
                    "Position " + position + " is out of range for list of size " + data.size)
        }
        return getTypeAdapterForData(data[position])
    }

    internal fun getTypeAdapterForType(type: Int): TypeAdapter<Any, RecyclerView.ViewHolder> {
        return typeAdapters[type] as TypeAdapter<Any, RecyclerView.ViewHolder>
    }

    internal fun getTypeAdapterForData(data: Any): TypeAdapter<Any, RecyclerView.ViewHolder> {
        for (i in typeAdapters.indices) {
            if (typeAdapters[i].isMyData(data)) {
                return typeAdapters[i] as TypeAdapter<Any, RecyclerView.ViewHolder>
            }
        }

        throw RuntimeException("Could not find TypeAdapter for data type: " + data.javaClass.simpleName)
    }

    internal fun getItemTypeForData(data: Any): Int {
        for (i in typeAdapters.indices) {
            if (typeAdapters[i].isMyData(data)) {
                return i
            }
        }

        throw RuntimeException("Could not find TypeAdapter index for data type: " + data.javaClass.simpleName)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = null
    }

    private fun setupSpanHandling() {
        if (spanHandler1 != null && recyclerView != null) {
            spanHandler1!!.calculateSpan(recyclerView!!.layoutManager)
        }
    }

    internal fun isDataTheSame(data1: Any, data2: Any): Boolean {
        // Are the item types the same
        return if (getItemTypeForData(data1) == getItemTypeForData(data2)) {
            // Is the data equal
            getTypeAdapterForData(data1).isDataEqual(data1, data2)
        } else {
            false
        }
    }

    internal fun areContentsTheSame(data1: Any, data2: Any): Boolean {
        return getTypeAdapterForData(data1).areContentsSame(data1, data2)
    }

    internal fun getChangePayload(oldData: Any, newData: Any): Any? {
        return getTypeAdapterForData(newData).getChangePayload(oldData, newData, Bundle())
    }
}