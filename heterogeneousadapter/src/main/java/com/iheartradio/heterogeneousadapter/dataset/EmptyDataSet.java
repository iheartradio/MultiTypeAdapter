package com.iheartradio.heterogeneousadapter.dataset;

public final class EmptyDataSet<T> implements DataSet<T> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public
    T get(final int index) {
        throw new IndexOutOfBoundsException();
    }


    public void insert(int position, T data) {
        throw new UnsupportedOperationException();
    }
}