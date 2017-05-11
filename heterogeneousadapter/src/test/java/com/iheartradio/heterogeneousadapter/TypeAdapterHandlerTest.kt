package com.iheartradio.heterogeneousadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import org.jetbrains.spek.api.Spek
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Created by Jonathan Muller on 5/10/17.
 */
class TypeAdapterHandlerTest : Spek({

    describe("TypeAdapterHandler") {
        context("created using a single binder") {

            val viewHolder = mock(ViewHolder::class.java)

            val referenceBinder = object : TypeAdapter<String, ViewHolder>() {

                override fun isMyData(data: Any?): Boolean {
                    return data is String
                }

                override fun onCreateViewHolder(inflating: InflatingContext?): ViewHolder {
                    return viewHolder
                }

            }

            val binderHandler = TypeAdapterHandler(listOf(referenceBinder))

            on("returning a list of binders") {

                val myBinders = binderHandler.binders

                it("should have a length of 1") {
                    assertEquals(1, myBinders.size)
                }

                it("should be equal to the binder we set in the context") {
                    assertEquals(referenceBinder.hashCode(), myBinders[0].hashCode())
                }
            }

            on("calling getBinderForData on a String") {
                val returnedBinder = binderHandler.getBinderForData("TEST")

                it ("should be equal to our reference binder") {
                    assertEquals(referenceBinder.hashCode(), returnedBinder.hashCode())
                }
            }

        }
    }
})
