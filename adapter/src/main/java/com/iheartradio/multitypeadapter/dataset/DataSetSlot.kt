package com.iheartradio.multitypeadapter.dataset

import java.util.*


class DataSetSlot<T>(private var slave: DataSet<out T>?) : DataSet<T> {

    private val mOwnChangeEvent = NotifyIfHaveSubscribers(ReceiverSubscription())

    private var mSubscribed: Boolean = false

    val isFilled: Boolean
        get() = slave!!.isPresent()

    init {

        mOwnChangeEvent.onFirstSubscryibed().subscribe(???({ this.beSubscribed() }))

        mOwnChangeEvent.onLastUnsubscribed().subscribe(???({ this.beUnsubscribed() }))
    }

    fun set(dataSet: Optional<out DataSet<out T>>) {
        if (slave!!.isPresent() === dataSet.isPresent() && (!slave!!.isPresent() || slave!!.get() === dataSet.get())) {

            // no reset - nothing to do
            return
        }

        if (mSubscribed) {
            slaveEvent(
                    Receiver2<Subscription<Receiver<DataSet.ChangeEvent>>, Receiver<DataSet.ChangeEvent>> { Subscription.unsubscribe() })
        }

        slave = dataSet

        if (mSubscribed) {
            slaveEvent(
                    Receiver2<Subscription<Receiver<DataSet.ChangeEvent>>, Receiver<DataSet.ChangeEvent>> { Subscription.subscribe() })
        }

        mOwnChangeEvent.slave().receive(DatasetChanged())

    }

    fun set(dataset: DataSet<out T>) {
        set(Optional.of(dataset))
    }

    fun clear() {
        set(Optional.empty())
    }

    fun count(): Int {
        return slave!!.map(???({ DataSet.count() })).orElse(0)
    }

    fun get(index: Int): T {
        Validate.assertIsTrue(slave!!.isPresent(), "slave.isPresent()")

        return slave!!.get().get(index)
    }

    fun changeEvent(): Subscription<Receiver<ChangeEvent>> {
        return mOwnChangeEvent
    }

    private fun slaveEvent(action: Receiver2<Subscription<Receiver<DataSet.ChangeEvent>>, Receiver<DataSet.ChangeEvent>>) {
        slave!!.ifPresent({ ds -> action.receive(ds.changeEvent(), mOwnChangeEvent.slave()) })
    }

    private fun beSubscribed() {
        mSubscribed = true

        slaveEvent(
                Receiver2<Subscription<Receiver<DataSet.ChangeEvent>>, Receiver<DataSet.ChangeEvent>> { Subscription.subscribe() })
    }

    private fun beUnsubscribed() {
        mSubscribed = false

        slaveEvent(
                Receiver2<Subscription<Receiver<DataSet.ChangeEvent>>, Receiver<DataSet.ChangeEvent>> { Subscription.unsubscribe() })
    }

    companion object {

        fun <T> emptySlot(): DataSetSlot<T> {
            return DataSetSlot<Any>(Optional.empty())
        }
    }


}
