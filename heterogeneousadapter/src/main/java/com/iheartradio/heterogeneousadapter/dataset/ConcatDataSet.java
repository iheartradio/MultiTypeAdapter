package com.iheartradio.heterogeneousadapter.dataset;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ConcatDataSet<T> implements DataSet<T> {

    private final class Slave {

        private final int mIndex;
        private final DataSet<? extends T> mDataSet;

        public Slave(final int index, final DataSet<? extends T> dataSet) {
            mIndex = index;
            mDataSet = dataSet;
        }

        public int size() {
            return mDataSet.size();
        }

        public boolean is(final DataSet<?> target) {
            return mDataSet == target;
        }

        public T get(final int index) {
            return mDataSet.get(index);
        }
    }

    private final List<Slave> mSlaves;

    public static <T> ConcatDataSet<T> concat(final DataSet<? extends T>...dataSets) {
        return new ConcatDataSet<>(Arrays.asList(dataSets));
    }

    public ConcatDataSet(final List<? extends DataSet<? extends T>> slaves) {
        mSlaves = new ArrayList<>();

        for (int i = 0; i < slaves.size(); ++i) {
            mSlaves.add(new Slave(i, slaves.get(i)));
        }
    }

    @Override
    public int size() {
        return elementsIn(slaves());
    }

    public Integer indexIn(final DataSet<? extends T> targetSet, final int outerIndex) {

        int innerIndex = outerIndex;

        for (final Slave slave : mSlaves) {
            if (slave.is(targetSet)) {
                if (innerIndex >= 0
                    && innerIndex < slave.size()) {
                    return innerIndex;
                } else {
                    // out of bounds
                    return null;
                }
            } else {
                innerIndex -= slave.size();
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public T get(final int index) {

        int actualIndex = index;

        for (final Slave slave : mSlaves) {
            final int countHere = slave.size();
            if (countHere <= actualIndex) {
                actualIndex -= countHere;
            } else {
                return slave.get(actualIndex);
            }
        }

        throw new IndexOutOfBoundsException();
    }

    public void insert(int index, final DataSet<? extends T> data) {
        int actualIndex = index;

        Slave slaveToInsertInto = null;

        for (final Slave slave : mSlaves) {
            final int countHere = slave.size();
            if (countHere <= actualIndex) {
                actualIndex -= countHere;
            } else {
                slaveToInsertInto = slave;
            }
        }

        if (slaveToInsertInto == null) {
            slaveToInsertInto = mSlaves.get(mSlaves.size() - 1);
        }

        // if we want to insert it at the start of a slave, just add new slave in front with the data
        if (actualIndex == 0) {
            int insertionIndex = mSlaves.indexOf(slaveToInsertInto);
            mSlaves.add(insertionIndex, new Slave(0, data));
        // if we want to insert it at the end of a slave, just add new slave at the end with the data
        } else if (actualIndex == slaveToInsertInto.size()) {
            mSlaves.add(index, new Slave(0, data));
        // if we want to insert it inside the slave, we need to split the slave in half
        // and insert a new slave in between with our data
        } else {
            List<DataSet<?>> dataSetList = new ArrayList<>();
            for (int i = 0; i < slaveToInsertInto.size(); i++) {
                if (i == actualIndex) {
                    dataSetList.add(new ItemDataSet<>(data));
                    dataSetList.add(new ItemDataSet<>(slaveToInsertInto.get(i)));
                } else {
                    dataSetList.add(new ItemDataSet<>(slaveToInsertInto.get(i)));
                }
            }
            int indexToReplace = mSlaves.indexOf(slaveToInsertInto);
            mSlaves.remove(indexToReplace);
            mSlaves.add(indexToReplace, new Slave(0, (DataSet<? extends T>) new ConcatDataSet<>(dataSetList)));
        }

    }

    private Slave getSlaveForIndex(final int index) {
        int actualIndex = index;

        for (final Slave slave : mSlaves) {
            final int countHere = slave.size();
            if (countHere <= actualIndex) {
                actualIndex -= countHere;
            } else {
                return slave;
            }
        }

        // if we can't find the index, we pass the last slave
        return mSlaves.get(mSlaves.size() - 1);
    }

    private List<Slave> slaves() {
        return mSlaves;
    }

    private int elementsIn(final List<Slave> slaves) {
        int totalCount = 0;
        for (Slave slave : slaves) {
            totalCount += slave.size();
        }
        return totalCount;
    }

}