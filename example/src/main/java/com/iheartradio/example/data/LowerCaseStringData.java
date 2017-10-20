package com.iheartradio.example.data;

/**
 * Created by Jonathan Muller on 3/10/17.
 */

public class LowerCaseStringData {
    public String mData;

    public LowerCaseStringData(final String data) {
        mData = data;
    }

    public String getData() {
        return mData.toLowerCase();
    }
}
