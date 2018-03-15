package com.iheartradio.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.iheartradio.example.data.ColorData;
import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.example.viewholders.GenericViewholder;
import com.iheartradio.example.viewholders.ListItemTwoViewHolder;
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

        List<Object> items = new ArrayList();
        items.add(new LowerCaseStringData("Section 1"));
        items.add(randomColor());
        items.add(randomColor());
        items.add(randomColor());
        items.add(new LowerCaseStringData("Section 2"));
        items.add(randomColor());
        items.add(randomColor());
        items.add(randomColor());
        items.add(randomColor());
        items.add(new LowerCaseStringData("Section 3"));
        items.add(randomColor());
        items.add(randomColor());

        List<Float> floats = new ArrayList<>();
        items.addAll(floats);

        mAdapter.setData(items);
    }

    @Override
    MultiTypeAdapter onCreateAdapter() {
        final List<TypeAdapter<?, ?>> binders = new ArrayList<>();

        //binders.add(new GridItemTypeAdapter());

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

        TypeAdapter<?, ?> otherTypeAdapter =
                new TypeAdapter.Builder<ColorData, GenericViewholder>(new Function1<Object, Boolean>() {
                    @Override
                    public Boolean invoke(final Object data) {
                        return data instanceof ColorData;
                    }
                }, new Function1<ViewGroup, GenericViewholder>() {
                    @Override
                    public GenericViewholder invoke(final ViewGroup viewGroup) {
                        return GenericViewholder.create(viewGroup, R.layout.grid_item);
                    }
                })
                        .setOnBindViewHolder(new BiConsumer<GenericViewholder, ColorData>() {
                            @Override
                            public void invoke(final GenericViewholder viewHolder, final ColorData data) {
                                viewHolder.bindColor(data);
                            }
                        })
                        .build();

        binders.add(typeAdapter);
        binders.add(otherTypeAdapter);

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
        mAdapter.setData(modifiedList, false);
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
