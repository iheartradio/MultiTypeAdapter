package com.iheartradio.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.iheartradio.example.data.ColorData;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.typeadapters.GridItemTypeAdapter;
import com.iheartradio.example.viewholders.ListItemTwoViewHolder;
import com.iheartradio.multitypeadapter.Items;
import com.iheartradio.multitypeadapter.MultiTypeAdapter;
import com.iheartradio.multitypeadapter.TypeAdapter;
import com.iheartradio.multitypeadapter.interfaces.BiConsumer;
import com.iheartradio.multitypeadapter.interfaces.Function1;
import com.iheartradio.multitypeadapter.interfaces.Supplier;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeGridExample extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Items builder = new Items()
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

        mAdapter.setData(builder.get());
    }

    @Override
    MultiTypeAdapter onCreateAdapter() {
        final List<TypeAdapter<?, ?>> binders = new ArrayList<>();

        binders.add(new GridItemTypeAdapter());

        TypeAdapter<?, ?> typeAdapter =
                new TypeAdapter.Builder<LowerCaseStringData, ListItemTwoViewHolder>(new Function1<Object, Boolean>() {
                    @Override
                    public Boolean invoke(final Object data) {
                        return data instanceof LowerCaseStringData;
                    }
                }, new Function1<ViewGroup, ListItemTwoViewHolder>() {
                    @Override
                    public ListItemTwoViewHolder invoke(final ViewGroup viewGroup) {
                        return ListItemTwoViewHolder.create(viewGroup);
                    }
                })
                        .setSpanSupplier(new Supplier<Integer>() {
                            @Override
                            public Integer invoke() {
                                return 2;
                            }
                        })
                        .setOnBindViewHolder(new BiConsumer<ListItemTwoViewHolder, LowerCaseStringData>() {
                            @Override
                            public void invoke(final ListItemTwoViewHolder viewHolder, final LowerCaseStringData data) {
                                viewHolder.bind(data);
                            }
                        })
                        .build();

        binders.add(typeAdapter);

        return new MultiTypeAdapter(binders);
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
