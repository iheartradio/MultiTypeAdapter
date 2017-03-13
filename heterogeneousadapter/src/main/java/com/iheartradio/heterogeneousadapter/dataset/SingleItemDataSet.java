package com.iheartradio.heterogeneousadapter.dataset;

public final class SingleItemDataSet<T> implements DataSet<T> {

    private final T mT;

    public static <T> SingleItemDataSet<T> of(final T t) {
        return new SingleItemDataSet(t);
    }

    public SingleItemDataSet(final T t) {
        mT = t;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public T get(final int index) {
        return mT;
    }
}