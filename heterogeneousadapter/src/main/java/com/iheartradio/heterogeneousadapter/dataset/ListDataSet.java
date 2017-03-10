package com.iheartradio.heterogeneousadapter.dataset;


import java.util.ArrayList;
import java.util.List;

public final class ListDataSet<T> implements DataSet<T> {

    private List<T> mData;


    public static <T> ListDataSet<T> of(final List<T> other) {
        return new ListDataSet<>(other);
    }

    public ListDataSet() {
        this(new ArrayList<T>());
    }

    public ListDataSet(final List<? extends T> data) {
        setData(data);
    }

    public void setData(final List<? extends T> newData) {
        mData = new ArrayList<>(newData);
    }

    public List<T> data() {

        return new ArrayList<>(mData);
    }

    public void add(final T data) {
        addAt(size(), data);
    }

    public void addAt(final int index,
                      final T data) {
        add(index, data);
    }

    public void addAll(final List<? extends T> elements) {
        mData.addAll(elements);
    }

    private void add(final int index, final T t) {
        mData.add(index, t);
    }

    public void deleteElement(final T data) {
        final int index = mData.indexOf(data);

        if (index < 0) {
            return;
        }

        deleteAt(index);
    }

    public void deleteAt(final int index) {
        mData.remove(index);
    }

    public void replaceAt(final int index, final T data) {
        mData.set(index, data);
    }

    public void move(final int from, final int to) {
        final T elt = mData.remove(from);
        mData.add(to, elt);
    }

    public void clear() {
        mData.clear();
    }

    @Override
    public int size() {
        return mData.size();
    }

    @Override
    public T get(final int index) {
        return mData.get(index);
    }

    public void insert(int index, T data) {
        mData.add(index, data);
    }
}