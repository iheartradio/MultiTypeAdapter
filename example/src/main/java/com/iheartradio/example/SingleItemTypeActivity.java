package com.iheartradio.example;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.example.typeadapters.LowerCaseStringTypeAdapter;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.heterogeneousadapter.MultiTypeDataHelper;
import com.iheartradio.heterogeneousadapter.MultiTypeAdapter;
import com.iheartradio.heterogeneousadapter.ItemTouchHelperFactory;

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

        MultiTypeDataHelper builder = new MultiTypeDataHelper()
                .add(twoDataList)
                .add(new LowerCaseStringData("5"));

        mAdapter.setData(builder.getData());
    }

    @Override
    MultiTypeAdapter onCreateAdapter() {
        MultiTypeAdapter adapter = new MultiTypeAdapter(new LowerCaseStringTypeAdapter());

        ItemTouchHelperFactory.create(adapter, mRecyclerView, true, true, false);

        return adapter;
    }

    @Override
    RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }
}
