package com.iheartradio.example;

import android.os.Bundle;

import com.iheartradio.example.typeadapters.LowerCaseStringBinder;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.heterogeneousadapter.HeterogeneousDataCreator;
import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;

import java.util.ArrayList;
import java.util.List;

public class SingleItemTypeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<LowerCaseStringData> twoDataList = new ArrayList<>();
        twoDataList.add(new LowerCaseStringData("1"));
        twoDataList.add(new LowerCaseStringData("2"));
        twoDataList.add(new LowerCaseStringData("3"));
        twoDataList.add(new LowerCaseStringData("4"));

        HeterogeneousDataCreator builder = new HeterogeneousDataCreator()
                .add(twoDataList)
                .add(new LowerCaseStringData("5"));

        mAdapter.setData(builder.getData());
    }

    @Override
    HeterogeneousAdapter onCreateAdapter() {
        return new HeterogeneousAdapter(new LowerCaseStringBinder());
    }

    public void onAddButtonClicked() {
        List<Object> listData = mAdapter.data();
        List<Object> modifiedList = ListUtils.addRandomData(listData, new LowerCaseStringData(String.valueOf(ListUtils.randomRange(0, 100))));
        mAdapter.setData(modifiedList);
    }

    public void onRemoveButtonClicked() {
        List<Object> listData = mAdapter.data();
        List<Object> modifiedList = ListUtils.removeRandomData(listData);
        mAdapter.setData(modifiedList);
    }

    public void onMoveButtonClicked() {
        List<Object> listData = mAdapter.data();
        List<Object> modifiedList = ListUtils.moveRandomData(listData);
        mAdapter.setData(modifiedList);
    }
}
