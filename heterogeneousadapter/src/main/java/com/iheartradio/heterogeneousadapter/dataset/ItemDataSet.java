package com.iheartradio.heterogeneousadapter.dataset;

public final class ItemDataSet<T> implements DataSet<T> {

    private final T mT;

    public static <T> ItemDataSet<T> of(final T t) {
        return new ItemDataSet(t);
    }

    public ItemDataSet(final T t) {
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