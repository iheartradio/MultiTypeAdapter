package com.iheartradio.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.example.data.ColorData;
import com.iheartradio.example.typeadapters.GridItemBinder;
import com.iheartradio.heterogeneousadapter.MultiTypeAdapter;
import com.iheartradio.heterogeneousadapter.MultiTypeDataHelper;

import java.util.ArrayList;
import java.util.List;

public class SingleTypeGridExample extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<ColorData> twoDataList = new ArrayList<>();
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());

        MultiTypeDataHelper builder = new MultiTypeDataHelper()
                .add(twoDataList);

        mAdapter.setData(builder.getData());
    }

    @Override
    MultiTypeAdapter onCreateAdapter() {
        return new MultiTypeAdapter(new GridItemBinder());
    }

    @Override
    RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3);
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
