package com.iheartradio.example.data;

/**
 * Created by Jonathan Muller on 3/7/17.
 */

public class UpperCaseStringData {
    public String mData;

    public UpperCaseStringData(final String data) {
        mData = data;
    }

    public String getData() {
        return mData.toUpperCase();
    }
}
