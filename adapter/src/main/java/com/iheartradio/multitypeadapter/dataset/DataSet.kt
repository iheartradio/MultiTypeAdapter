package com.iheartradio.multitypeadapter.dataset

/**
 * Created by Jonathan Muller on 1/16/19.
 */


interface DataSet<T> {

    fun size(): Int

    operator fun get(index: Int): T

    interface ChangeEvent {

        fun alterIndex(action: (Int) -> Int): ChangeEvent

        operator fun invoke(unspecificChange: (() -> Unit)? = null,
                            added: ((Int) -> Unit)? = null,
                            removed: ((Int) -> Unit)? = null,
                            moved: ((Int, Int) -> Unit)? = null,
                            itemChanged: ((Int) -> Unit)? = null,
                            rangeAdded: ((Range) -> Unit)? = null)
    }

}

data class Range(val first: Int, val last: Int) {

    val count: Int = last - first + 1

    fun alterIndex(function: (Int) -> Int) = Range(function(first), function(last))
}

class RangeAddedEvent(private val range: Range) : DataSet.ChangeEvent {

    override fun alterIndex(action: (Int) -> Int): DataSet.ChangeEvent {
        return RangeAddedEvent(range.alterIndex(action))
    }

    override fun invoke(unspecificChange: (() -> Unit)?,
                        added: ((Int) -> Unit)?,
                        removed: ((Int) -> Unit)?,
                        moved: ((Int, Int) -> Unit)?,
                        itemChanged: ((Int) -> Unit)?,
                        rangeAdded: ((Range) -> Unit)?) {
        rangeAdded?.invoke(range)
    }
}

class DataSetChangeEvent : DataSet.ChangeEvent {

    override fun alterIndex(action: (Int) -> Int): DataSet.ChangeEvent {
        return this
    }

    override fun invoke(unspecificChange: (() -> Unit)?,
                        added: ((Int) -> Unit)?,
                        removed: ((Int) -> Unit)?,
                        moved: ((Int, Int) -> Unit)?,
                        itemChanged: ((Int) -> Unit)?,
                        rangeAdded: ((Range) -> Unit)?) {
        unspecificChange?.invoke()
    }
}

class MovedEvent(private val from: Int,
                        private val to: Int) : DataSet.ChangeEvent {

    override fun alterIndex(action: (Int) -> Int): DataSet.ChangeEvent {
        return MovedEvent(action(from), action(to))
    }

    override fun invoke(unspecificChange: (() -> Unit)?,
                        added: ((Int) -> Unit)?,
                        removed: ((Int) -> Unit)?,
                        moved: ((Int, Int) -> Unit)?,
                        itemChanged: ((Int) -> Unit)?,
                        rangeAdded: ((Range) -> Unit)?) {
        moved?.invoke(from, to)
    }
}

class AddedEvent(private val index: Int) : DataSet.ChangeEvent {

    override fun alterIndex(action: (Int) -> Int): DataSet.ChangeEvent {
        return AddedEvent(action(index))
    }

    override fun invoke(unspecificChange: (() -> Unit)?,
                        added: ((Int) -> Unit)?,
                        removed: ((Int) -> Unit)?,
                        moved: ((Int, Int) -> Unit)?,
                        itemChanged: ((Int) -> Unit)?,
                        rangeAdded: ((Range) -> Unit)?) {
        added?.invoke(index)
    }

}

class RemovedEvent(private val index: Int) : DataSet.ChangeEvent {

    override fun alterIndex(action: (Int) -> Int): DataSet.ChangeEvent {
        return RemovedEvent(action(index))
    }

    override fun invoke(unspecificChange: (() -> Unit)?,
                        added: ((Int) -> Unit)?,
                        removed: ((Int) -> Unit)?,
                        moved: ((Int, Int) -> Unit)?,
                        itemChanged: ((Int) -> Unit)?,
                        rangeAdded: ((Range) -> Unit)?) {
        removed?.invoke(index)
    }
}

class ItemChangedEvent(private val index: Int) : DataSet.ChangeEvent {

    override fun alterIndex(action: (Int) -> Int): DataSet.ChangeEvent {
        return ItemChangedEvent(index)
    }

    override fun invoke(unspecificChange: (() -> Unit)?,
                        added: ((Int) -> Unit)?,
                        removed: ((Int) -> Unit)?,
                        moved: ((Int, Int) -> Unit)?,
                        itemChanged: ((Int) -> Unit)?,
                        rangeAdded: ((Range) -> Unit)?) {
        itemChanged?.invoke(index)
    }
}