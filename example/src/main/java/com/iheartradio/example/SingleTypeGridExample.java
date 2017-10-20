package com.iheartradio.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.iheartradio.example.data.ColorData;
import com.iheartradio.example.typeadapters.GridItemTypeAdapter;
import com.iheartradio.multitypeadapter.MultiTypeAdapter;
import java.util.ArrayList;
import java.util.List;

public class SingleTypeGridExample extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<? super Object> twoDataList = new ArrayList<>();
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());
        twoDataList.add(randomColor());

        mAdapter.setData(twoDataList);
    }

    @Override
    MultiTypeAdapter onCreateAdapter() {
        return new MultiTypeAdapter(new GridItemTypeAdapter());
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
