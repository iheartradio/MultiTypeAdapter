package com.iheartradio.multitypeadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * This Factory is to allow a more functional approach to creating TypeAdapters.
 * Contains methods for creating TypeAdapters composed of methods.
 */
object TypeAdapterFactory {

    @JvmOverloads
    fun <D, V : RecyclerView.ViewHolder> create(targetClass: Class<*>,
                                                onCreateViewHolder: (ViewGroup) -> V,
                                                onBindViewHolder: ((V, D, List<Any>?) -> Unit)? = null,
                                                onAttach: ((V) -> Unit)? = null,
                                                onDetach: ((V) -> Unit)? = null,
                                                getSpan: (() -> Int)? = null) : TypeAdapter<D, V> {
        return TypeAdapterImpl(targetClass = targetClass,
                               onCreateViewHolder = onCreateViewHolder,
                               onBindViewHolder = onBindViewHolder,
                               onAttach = onAttach,
                               onDetach = onDetach,
                               getSpan = getSpan)
    }

    @JvmOverloads
    fun <D, V : RecyclerView.ViewHolder> create(isMyData: (Any) -> Boolean,
                                                onCreateViewHolder: (ViewGroup) -> V,
                                                onBindViewHolder: ((V, D, List<Any>?) -> Unit)? = null,
                                                onAttach: ((V) -> Unit)? = null,
                                                onDetach: ((V) -> Unit)? = null,
                                                getSpan: (() -> Int)? = null) : TypeAdapter<D, V> {
        return TypeAdapterImpl(isMyData = isMyData,
                               onCreateViewHolder = onCreateViewHolder,
                               onBindViewHolder = onBindViewHolder,
                               onAttach = onAttach,
                               onDetach = onDetach,
                               spanSupplier = getSpan)
    }
}
