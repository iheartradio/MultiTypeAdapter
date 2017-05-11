package com.iheartradio.heterogeneousadapter.interfaces;

/**
 * Created by Jonathan Muller on 4/26/17.
 */

public interface Function2<Input1, Input2, Output> {
    Output invoke(Input1 input1, Input2 input2);
}
