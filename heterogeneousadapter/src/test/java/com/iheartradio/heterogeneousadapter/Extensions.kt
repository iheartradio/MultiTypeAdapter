package com.iheartradio.heterogeneousadapter

import org.mockito.Mockito

/**
 * Created by Jonathan Muller on 4/28/17.
 */
class Extensions {
    inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)
}