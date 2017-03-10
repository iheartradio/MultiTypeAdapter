package com.iheartradio.heterogeneousadapter.dataset;

public interface DataSet<T> {
    int size();
    T get(int index);
}