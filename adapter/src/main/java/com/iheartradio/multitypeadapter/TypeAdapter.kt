package com.iheartradio.multitypeadapter

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class TypeAdapter<D, V : RecyclerView.ViewHolder> {

    /**
     * Used to determine the span for this data
     * @return default span of 1 unless overridden
     */
    open val span: Int
        get() = DEFAULT_SPAN

    /**
     * Used to determine if this TypeAdapter manages the incoming data
     * @param data
     * @return true if data is managed by this TypeAdapter
     */
    abstract fun isMyData(data: Any): Boolean

    /**
     * @param parent
     * @return ViewHolder of type V
     */
    abstract fun onCreateViewHolder(parent: ViewGroup): V

    /**
     *
     * @param viewHolder
     * @param data to be bound to the viewholder
     */
    fun onBindViewHolder(viewHolder: V, data: D) {}

    open fun onBindViewHolder(viewHolder: V, data: D, payloads: List<Any>?) {
        onBindViewHolder(viewHolder, data)
    }

    open fun onAttach(view: V) {}

    open fun onDetach(view: V) {}

    /**
     * Used to determine if two pieces of data are equal. E.g. If their id is the same, or content
     * @param data1
     * @param data2
     * @return
     */
    open fun isDataEqual(data1: D, data2: D): Boolean {
        return false
    }

    /**
     * Used to determine if two pieces of data have the same contents. E.g. If all fields are equal
     * @param data1
     * @param data2
     * @return
     */
    fun areContentsSame(data1: D, data2: D): Boolean {
        return false
    }

    open fun getChangePayload(oldData: D, newData: D, diffBundle: Bundle): Any? {
        return null
    }


    class Builder<D, V : RecyclerView.ViewHolder> {

        private val mIsMyData: (Any) -> Boolean
        private val mOnCreateViewHolder: (ViewGroup) -> V

        private var mOnBindViewHolder: ((V, D, List<Any>?) -> Unit)? = null

        private var mOnAttach: ((V) -> Unit)? = null
        private var mOnDetach: ((V) -> Unit)? = null

        private var mSpanSupplier: () -> Int = { DEFAULT_SPAN }
        private var mIsDataEqual: (D, D) -> Boolean = { d, d2 -> false }
        private var mGetChangePayload: (D, D, Bundle) -> Any? = { d, d2, bundle -> null }

        constructor(isMyData: (Any) -> Boolean, onCreateViewHolder: (ViewGroup) -> V) {
            mIsMyData = isMyData
            mOnCreateViewHolder = onCreateViewHolder
        }

        constructor(dataType: Class<*>, onCreateViewHolder: (ViewGroup) -> V) {
            mIsMyData = { o -> dataType.isAssignableFrom(o.javaClass) }
            mOnCreateViewHolder = onCreateViewHolder
        }

        fun setOnBindViewHolder(onBindViewHolder: (V, D, List<Any>?) -> Unit): Builder<D, V> {
            mOnBindViewHolder = onBindViewHolder
            return this
        }

        fun setOnBindViewHolder(onBindViewHolder: (V, D) -> Unit): Builder<D, V> {
            mOnBindViewHolder = { v, d, _ -> onBindViewHolder(v, d) }
            return this
        }

        fun setOnAttach(onAttach: (V) -> Unit): Builder<D, V> {
            mOnAttach = onAttach
            return this
        }

        fun setOnDetach(onDetach: (V) -> Unit): Builder<D, V> {
            mOnDetach = onDetach
            return this
        }

        fun setSpanSupplier(spanSupplier: () -> Int): Builder<D, V> {
            mSpanSupplier = spanSupplier
            return this
        }

        fun setSpan(span: Int): Builder<D, V> {
            mSpanSupplier = { span }
            return this
        }

        fun setIsDataEqual(isDataEqual: (D, D) -> Boolean): Builder<D, V> {
            mIsDataEqual = isDataEqual
            return this
        }

        fun setGetChangePayload(getChangePayload: (D, D, Bundle) -> Any?): Builder<D, V> {
            mGetChangePayload = getChangePayload
            return this
        }

        fun build(): TypeAdapter<D, V> {
            return object : TypeAdapter<D, V>() {

                override val span: Int
                    get() = mSpanSupplier.invoke()

                override fun isMyData(data: Any): Boolean {
                    return mIsMyData.invoke(data)
                }

                override fun onCreateViewHolder(parent: ViewGroup): V {
                    return mOnCreateViewHolder.invoke(parent)
                }

                override fun onBindViewHolder(viewHolder: V, data: D, payloads: List<Any>?) {
                    mOnBindViewHolder?.invoke(viewHolder, data, payloads)
                }

                override fun onAttach(view: V) {
                    mOnAttach?.invoke(view)
                }

                override fun onDetach(view: V) {
                    mOnDetach?.invoke(view)
                }

                override fun isDataEqual(data1: D, data2: D): Boolean {
                    return mIsDataEqual.invoke(data1, data2)
                }

                override fun getChangePayload(oldData: D, newData: D, diffBundle: Bundle): Any? {
                    return mGetChangePayload.invoke(oldData, newData, diffBundle)
                }
            }
        }

        companion object {
            private const val DEFAULT_SPAN = 1
        }
    }

    companion object {
        protected const val DEFAULT_SPAN = 1
    }

}
