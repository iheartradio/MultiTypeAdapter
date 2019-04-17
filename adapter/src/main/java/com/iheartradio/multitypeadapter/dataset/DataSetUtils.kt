package com.iheartradio.multitypeadapter.dataset

/**
 * Created by Jonathan Muller on 1/17/19.
 */
object DataSetUtils {

    fun <T> listFrom(data: DataSet<T>): List<T> {
        return (0 .. data.size()).map { data[it] }
    }
}