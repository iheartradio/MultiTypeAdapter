package com.iheartradio.multitypeadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Generic TypeAdapter implementation used by the TypeAdaptersFactory to create its implementations
 * @param <D>
 * @param <V>
</V></D> */
internal class TypeAdapterImpl<D, V : RecyclerView.ViewHolder>(private val isMyData: (Any) -> Boolean,
                                                               private val onCreateViewHolder: (ViewGroup) -> V,
                                                               private val onBindViewHolder: ((V, D, List<Any>?) -> Unit)? = null,
                                                               private val onAttach: ((V) -> Unit)? = null,
                                                               private val onDetach: ((V) -> Unit)? = null,
                                                               private val spanSupplier: (() -> Int)? = null) : TypeAdapter<D, V>() {


    constructor(targetClass: Class<*>,
                onCreateViewHolder: (ViewGroup) -> V,
                onBindViewHolder: ((V, D, List<Any>?) -> Unit)?,
                onAttach: ((V) -> Unit)?,
                onDetach: ((V) -> Unit)?,
                getSpan: (() -> Int)?) : this({ input -> targetClass.isAssignableFrom(input.javaClass) },
                                              onCreateViewHolder,
                                              onBindViewHolder,
                                              onAttach,
                                              onDetach,
                                              getSpan)


    override fun isMyData(data: Any): Boolean {
        return isMyData.invoke(data)
    }

    override fun onCreateViewHolder(inflating: ViewGroup): V {
        return onCreateViewHolder.invoke(inflating)
    }

    override fun onBindViewHolder(viewHolder: V, data: D, payloads: List<Any>?) {
        onBindViewHolder?.invoke(viewHolder, data, payloads)
    }

    override fun onAttach(viewHolder: V) {
        onAttach?.invoke(viewHolder)
    }

    override fun onDetach(viewHolder: V) {
        onDetach?.invoke(viewHolder)
    }

    override val span: Int
        get() = if (spanSupplier != null) {
            spanSupplier.invoke()
        } else {
            DEFAULT_SPAN
        }

    companion object {
        private const val DEFAULT_SPAN = 1
    }

}
