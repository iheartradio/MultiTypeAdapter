package com.iheartradio.example;

import android.os.Bundle;

import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.data.UpperCaseStringData;
import com.iheartradio.example.typeadapters.LowerCaseStringBinder;
import com.iheartradio.example.typeadapters.UpperCaseStringBinder;
import com.iheartradio.example.viewholders.ListItemOneViewHolder;
import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.HeterogeneousDataCreator;
import com.iheartradio.heterogeneousadapter.InflatingContext;
import com.iheartradio.heterogeneousadapter.interfaces.Function1;

import java.util.ArrayList;
import java.util.List;

public class BuilderPatternActivity extends BaseActivity {

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
                .add(new UpperCaseStringData("I'm some UpperCaseString Data"));

        mAdapter.setData(builder.getData());
    }

    @Override
    HeterogeneousAdapter onCreateAdapter() {
        List<HeterogeneousBinder<?, ?>> binders = new ArrayList<>();
        binders.add(new LowerCaseStringBinder());
        binders.add(new UpperCaseStringBinder());

        binders.add(new HeterogeneousBinder.Builder<>(LowerCaseStringData.class,
                new Function1<InflatingContext, ListItemOneViewHolder>() {
                    @Override
                    public ListItemOneViewHolder invoke(InflatingContext input) {
                        return ListItemOneViewHolder.create(input);
                    }
                }).build());

        return new HeterogeneousAdapter(binders);
    }

    public void onAddButtonClicked() {
        List<Object> listData = mAdapter.data();
        List<Object> modifiedList = ListUtils.addRandomData(listData, new UpperCaseStringData("UpperCaseString Data"));
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
