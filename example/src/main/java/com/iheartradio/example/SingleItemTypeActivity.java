package com.iheartradio.example;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.iheartradio.example.typeadapters.LowerCaseStringBinder;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.heterogeneousadapter.HeterogeneousDataCreator;
import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;
import com.iheartradio.heterogeneousadapter.SimpleItemTouchHelperCallback;
import com.iheartradio.heterogeneousadapter.interfaces.ItemTouchHelperFactory;

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
        HeterogeneousAdapter adapter = new HeterogeneousAdapter(new LowerCaseStringBinder());

        ItemTouchHelperFactory.create(adapter, mRecyclerView, true, true, false);

        return adapter;
    }

    @Override
    RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }
}
