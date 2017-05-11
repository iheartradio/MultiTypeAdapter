package com.iheartradio.heterogeneousadapter.interfaces;

/**
 * Created by Jonathan Muller on 4/26/17.
 */

public interface Consumer<Input> {
    void invoke(Input input);
}
