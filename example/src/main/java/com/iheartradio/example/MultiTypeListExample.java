package com.iheartradio.example;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.example.data.UpperCaseStringData;
import com.iheartradio.example.typeadapters.LowerCaseStringTypeAdapter;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.typeadapters.UpperCaseStringTypeAdapter;
import com.iheartradio.multitypeadapter.MultiTypeAdapter;
import com.iheartradio.multitypeadapter.TypeAdapter;
import com.iheartradio.multitypeadapter.Items;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeListExample extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<LowerCaseStringData> twoDataList = new ArrayList<>();
        twoDataList.add(new LowerCaseStringData("1"));
        twoDataList.add(new LowerCaseStringData("2"));
        twoDataList.add(new LowerCaseStringData("3"));
        twoDataList.add(new LowerCaseStringData("4"));

        final Items builder = new Items()
                .add(twoDataList)
                .add(new UpperCaseStringData("I'm some UpperCaseString Data"));

        mAdapter.setData(builder.get());
    }

    @Override
    MultiTypeAdapter onCreateAdapter() {
        final List<TypeAdapter<?, ?>> binders = new ArrayList<>();
        binders.add(new LowerCaseStringTypeAdapter());
        binders.add(new UpperCaseStringTypeAdapter());
        return new MultiTypeAdapter(binders);
    }

    @Override
    RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    public void onAddButtonClicked() {
        final List<Object> listData = mAdapter.data();
        final List<Object> modifiedList = ListUtils.addRandomData(listData, new UpperCaseStringData("UpperCaseString Data"));
        mAdapter.setData(modifiedList, true);
    }
}
