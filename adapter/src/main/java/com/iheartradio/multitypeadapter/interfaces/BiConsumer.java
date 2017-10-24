package com.iheartradio.multitypeadapter.interfaces;

/**
 * Created by Jonathan Muller on 10/24/17.
 */

public interface BiConsumer<Input1, Input2> {
    void invoke(final Input1 input1, final Input2 input2);
}
