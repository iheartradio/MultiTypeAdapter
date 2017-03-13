package com.iheartradio.heterogeneousadapter;

import com.iheartradio.heterogeneousadapter.dataset.ConcatDataSet;
import com.iheartradio.heterogeneousadapter.dataset.DataSet;
import com.iheartradio.heterogeneousadapter.dataset.ListDataSet;
import com.iheartradio.heterogeneousadapter.dataset.SingleItemDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Muller on 3/11/17.
 */

public class HeterogeneousDataCreator {

    private final ArrayList<DataSet<?>> mDataSets = new ArrayList<>();

    public HeterogeneousDataCreator add(final Object data) {
        mDataSets.add(new SingleItemDataSet<>(data));
        return this;
    }

    public HeterogeneousDataCreator add(final List<?> data) {
        mDataSets.addAll(createListOfDataSets(data));
        return this;
    }

    public HeterogeneousDataCreator add(final int index, final Object data) {
        mDataSets.add(index, new SingleItemDataSet<>(data));
        return this;
    }

    public HeterogeneousDataCreator add(final int index, final List<?> data) {
        mDataSets.addAll(index, createListOfDataSets(data));
        return this;
    }

    private List<DataSet<?>> createListOfDataSets(final List<?> data) {
        List<DataSet<?>> dataSetList = new ArrayList<>();
        for (Object listData : data) {
            dataSetList.add(new SingleItemDataSet<>(listData));
        }
        return dataSetList;
    }

    public DataSet<?> getData() {
        return new ConcatDataSet<>(mDataSets);
    }
}
