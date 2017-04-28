package com.iheartradio.example;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.example.data.UpperCaseStringData;
import com.iheartradio.example.typeadapters.LowerCaseStringBinder;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.typeadapters.UpperCaseStringBinder;
import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.HeterogeneousDataCreator;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeListExample extends BaseActivity {

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
        return new HeterogeneousAdapter(binders);
    }

    @Override
    RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    public void onAddButtonClicked() {
        List<Object> listData = mAdapter.data();
        List<Object> modifiedList = ListUtils.addRandomData(listData, new UpperCaseStringData("UpperCaseString Data"));
        mAdapter.setData(modifiedList);
    }
}
