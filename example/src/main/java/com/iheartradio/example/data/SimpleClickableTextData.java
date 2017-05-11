package com.iheartradio.example.data;

/**
 * Created by Jonathan Muller on 4/25/17.
 */

public class SimpleClickableTextData {

    private Runnable mAction;
    private String mText;

    public SimpleClickableTextData(final String text, final Runnable action) {
        mAction = action;
        mText = text;
    }

    public Runnable getAction() {
        return mAction;
    }

    public String getText() {
        return mText;
    }
}
