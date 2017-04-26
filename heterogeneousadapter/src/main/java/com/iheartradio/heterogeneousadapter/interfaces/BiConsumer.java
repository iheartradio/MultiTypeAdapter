package com.iheartradio.heterogeneousadapter.interfaces;

/**
 * Created by Jonathan Muller on 4/26/17.
 */

public interface BiConsumer<Input1, Input2> {
    void invoke(Input1 input1, Input2 input2);
}
