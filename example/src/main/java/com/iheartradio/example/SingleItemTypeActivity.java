package com.iheartradio.example;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.example.typeadapters.LowerCaseStringTypeAdapter;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.multitypeadapter.Items;
import com.iheartradio.multitypeadapter.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class SingleItemTypeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<? super Object> twoDataList = new ArrayList<>();
        twoDataList.add(new LowerCaseStringData("1"));
        twoDataList.add(new LowerCaseStringData("2"));
        twoDataList.add(new LowerCaseStringData("3"));
        twoDataList.add(new LowerCaseStringData("4"));

        Items builder = new Items()
                .add(twoDataList)
                .add(new LowerCaseStringData("5"));

        mAdapter.setData(builder.get());
    }

    @Override
    MultiTypeAdapter onCreateAdapter() {
        return new MultiTypeAdapter(new LowerCaseStringTypeAdapter());
    }

    @Override
    RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }
}