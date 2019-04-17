package com.iheartradio.multitypeadapter.dataset

import java.util.*

class ListDataSet<T> @JvmOverloads constructor(data: List<T> = emptyList()) : DataSet<T> {

    private val changeEvents = mutableListOf<(DataSet.ChangeEvent) -> Unit>()
    private lateinit var data: MutableList<T>

    init {
        setData(data)
    }

    private fun fireEvent(event: DataSet.ChangeEvent) {
        changeEvents.forEach { it(event) }
    }

    fun setData(newData: List<T>) {
        data = ArrayList(newData)

        fireEvent(DataSetChangeEvent())
    }

    fun data(): List<T> = data

    fun add(t: T) {
        addAt(size(), t)
    }

    fun addAt(index: Int, t: T) {
        add(index, t)
    }

    fun addAll(elements: List<T>) {
        val cntWas = data.size

        data.addAll(elements)

        val cntNow = data.size

        fireEvent(RangeAddedEvent(Range(cntWas, cntNow - 1)))
    }

    private fun add(index: Int, t: T) {
        data.add(index, t)

        fireEvent(AddedEvent(index))
    }

    fun remove(t: T) {
        val index = data.indexOf(t)

        if (index < 0) return

        removeAt(index)
    }

    fun removeAt(index: Int) {
        data.removeAt(index)

        fireEvent(RemovedEvent(index))
    }

    fun replaceAt(index: Int, t: T) {
        data[index] = t

        fireEvent(ItemChangedEvent(index))
    }

    fun move(from: Int, to: Int) {
        val itemToMove = data.removeAt(from)
        data.add(to, itemToMove)

        fireEvent(MovedEvent(from, to))
    }

    fun clear() {
        setData(emptyList())
    }

    override fun size(): Int = data.size

    override operator fun get(index: Int): T {
        return data[index]
    }

    fun addChangeEventListener(listener: (DataSet.ChangeEvent) -> Unit) {
        changeEvents.add(listener)
    }

    fun removeChangeEventListener(listener: (DataSet.ChangeEvent) -> Unit) {
        changeEvents.remove(listener)
    }

    companion object {

        fun <T> copyOf(other: DataSet<T>): ListDataSet<T> {
            return ListDataSet(DataSetUtils.listFrom(other))
        }
    }

}