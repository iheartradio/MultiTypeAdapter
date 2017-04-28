package com.iheartradio.heterogeneousadapter.interfaces;

/**
 * Created by Jonathan Muller on 4/26/17.
 */

public interface TriConsumer<Input1, Input2, Input3> {
    void invoke(final Input1 input1, final Input2 input2, final Input3 input3);
}
