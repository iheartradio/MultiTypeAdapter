package com.iheartradio.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.example.data.ColorData;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.typeadapters.GridItemTypeAdapter;
import com.iheartradio.example.typeadapters.LowerCaseStringTypeAdapter;
import com.iheartradio.heterogeneousadapter.MultiTypeAdapter;
import com.iheartradio.heterogeneousadapter.TypeAdapter;
import com.iheartradio.heterogeneousadapter.MultiTypeDataHelper;
import com.iheartradio.heterogeneousadapter.ItemTouchHelperFactory;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeGridExample extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MultiTypeDataHelper builder = new MultiTypeDataHelper()
                .add(new LowerCaseStringData("Section 1"))
                .add(randomColor())
                .add(randomColor())
                .add(randomColor())
                .add(new LowerCaseStringData("Section 2"))
                .add(randomColor())
                .add(randomColor())
                .add(randomColor())
                .add(randomColor())
                .add(new LowerCaseStringData("Section 3"))
                .add(randomColor())
                .add(randomColor());

        mAdapter.setData(builder.getData());
    }

    @Override
    MultiTypeAdapter onCreateAdapter() {
        final List<TypeAdapter<?, ?>> binders = new ArrayList<>();

        binders.add(new GridItemTypeAdapter());
        binders.add(new LowerCaseStringTypeAdapter());

        MultiTypeAdapter adapter = new MultiTypeAdapter(binders);

        ItemTouchHelperFactory.create(adapter, mRecyclerView, true, true, true);

        return adapter;
    }

    @Override
    RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 2);
    }

    @Override
    public void onAddButtonClicked() {
        List<Object> listData = mAdapter.data();
        List<Object> modifiedList = ListUtils.addRandomData(listData, randomColor());
        mAdapter.setData(modifiedList);
    }

    private ColorData randomColor() {
        return new ColorData(Color.argb(ListUtils.randomRange(100, 255),
                randomValue(),
                randomValue(),
                randomValue()));
    }

    private int randomValue() {
        return ListUtils.randomRange(0, 255);
    }
}
