package com.iheartradio.heterogeneousadapter

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TypeAdapterHandlerTest : Spek({

    describe("TypeAdapterHandler") {

        context("created using a single binder") {

            val viewHolder = mock<ViewHolder>()

            val referenceBinder = object : TypeAdapter<String, ViewHolder>() {

                override fun isMyData(data: Any?): Boolean {
                    return data is String
                }

                override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
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

            on ("given an item type for a string") {

                val DATA = "MyString"
                val itemType = binderHandler.getItemTypeForData(DATA)

                it ("getBinderForType should return a typeAdapter that can handle that data") {
                    val typeAdapter = binderHandler.getBinderForType(itemType)
                    assertTrue { typeAdapter.isMyData(DATA) }
                }
            }

        }
    }
})
