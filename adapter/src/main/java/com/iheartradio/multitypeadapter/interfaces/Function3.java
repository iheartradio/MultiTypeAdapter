package com.iheartradio.multitypeadapter.interfaces;

/**
 * Created by Jonathan Muller on 2/6/18.
 */

public interface Function3<Input1, Input2, Input3, Output> {
    Output invoke(Input1 input1, Input2 input2, Input3 input3);
}
