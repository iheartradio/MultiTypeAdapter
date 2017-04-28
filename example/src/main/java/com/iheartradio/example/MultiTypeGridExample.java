package com.iheartradio.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.example.data.ColorData;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.typeadapters.GridItemBinder;
import com.iheartradio.example.typeadapters.LowerCaseStringBinder;
import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.HeterogeneousDataCreator;
import com.iheartradio.heterogeneousadapter.ItemTouchHelperFactory;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeGridExample extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HeterogeneousDataCreator builder = new HeterogeneousDataCreator()
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
    HeterogeneousAdapter onCreateAdapter() {
        final List<HeterogeneousBinder<?, ?>> binders = new ArrayList<>();

        binders.add(new GridItemBinder());
        binders.add(new LowerCaseStringBinder());

        HeterogeneousAdapter adapter = new HeterogeneousAdapter(binders);

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
