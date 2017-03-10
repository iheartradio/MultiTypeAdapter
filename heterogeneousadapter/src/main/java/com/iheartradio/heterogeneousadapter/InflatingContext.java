package com.iheartradio.heterogeneousadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jonathan Muller on 2/27/17.
 */

public class InflatingContext {

    private final LayoutInflater mInflater;
    private final ViewGroup mParent;

    public static InflatingContext fromParent(final ViewGroup parent) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        return new InflatingContext(inflater, parent);
    }

    public InflatingContext(final LayoutInflater inflater, final ViewGroup parent) {
        mInflater = inflater;
        mParent = parent;
    }

    public View inflate(final int layout) {
        return mInflater.inflate(layout, mParent, false);
    }

    public LayoutInflater inflater() {
        return mInflater;
    }

    public ViewGroup parent() {
        return mParent;
    }

}
