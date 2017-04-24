package com.iheartradio.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iheartradio.heterogeneousadapter.HeterogeneousDataCreator;
import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.dataset.SingleItemDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExampleActivity extends AppCompatActivity {

    private View mAddButton;
    private HeterogeneousAdapter mAdapter;
    private Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        mAddButton = findViewById(R.id.add_button);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter(recyclerView);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int insertionIndex = randomRange(0, mAdapter.getItemCount());
                HeterogeneousDataCreator creator = getAdapterData();
                creator.add(insertionIndex, new ListItemOneData());
                mAdapter.setData(creator.getData());
            }
        });
    }

    private void initAdapter(final RecyclerView recyclerView) {

        List<HeterogeneousBinder<?, ?>> binders = new ArrayList<>();
        binders.add(new ListItemOneBinder());
        binders.add(new ListItemTwoBinder());

        mAdapter = new HeterogeneousAdapter(binders);
        recyclerView.setAdapter(mAdapter);
        mAdapter.calcDiff(true);


        mAdapter.setData(getAdapterData().getData());
    }

    private HeterogeneousDataCreator getAdapterData() {
        List<ListItemTwoData> twoDataList = new ArrayList<>();
        twoDataList.add(new ListItemTwoData("1"));
        twoDataList.add(new ListItemTwoData("2"));
        twoDataList.add(new ListItemTwoData("3"));
        twoDataList.add(new ListItemTwoData("4"));

        HeterogeneousDataCreator builder = new HeterogeneousDataCreator()
                .add(twoDataList)
                .add(new ListItemOneData());

        builder.add(1, new ListItemOneData());
        builder.add(3, new ListItemOneData());

        return builder;
    }

    private int randomRange(final int min, final int max) {
        return mRandom.nextInt(max - min) + min;
    }
}
