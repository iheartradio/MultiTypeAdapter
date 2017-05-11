package com.iheartradio.example;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.example.data.UpperCaseStringData;
import com.iheartradio.example.typeadapters.LowerCaseStringTypeAdapter;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.typeadapters.UpperCaseStringTypeAdapter;
import com.iheartradio.heterogeneousadapter.MultiTypeAdapter;
import com.iheartradio.heterogeneousadapter.TypeAdapter;
import com.iheartradio.heterogeneousadapter.MultiTypeDataHelper;

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

        MultiTypeDataHelper builder = new MultiTypeDataHelper()
                .add(twoDataList)
                .add(new UpperCaseStringData("I'm some UpperCaseString Data"));

        mAdapter.setData(builder.getData());
    }

    @Override
    MultiTypeAdapter onCreateAdapter() {
        List<TypeAdapter<?, ?>> binders = new ArrayList<>();
        binders.add(new LowerCaseStringTypeAdapter());
        binders.add(new UpperCaseStringTypeAdapter());
        return new MultiTypeAdapter(binders);
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
