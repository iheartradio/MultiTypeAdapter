package com.iheartradio.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.heterogeneousadapter.HeterogeneousDataCreator;
import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;

import java.util.ArrayList;
import java.util.List;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<HeterogeneousBinder<?, ?>> binders = new ArrayList<>();
        binders.add(new ListItemOneBinder());
        binders.add(new ListItemTwoBinder());

        HeterogeneousAdapter adapter = new HeterogeneousAdapter(binders);
        recyclerView.setAdapter(adapter);

        List<ListItemTwoData> twoDataList = new ArrayList<>();
        twoDataList.add(new ListItemTwoData());
        twoDataList.add(new ListItemTwoData());
        twoDataList.add(new ListItemTwoData());
        twoDataList.add(new ListItemTwoData());

        HeterogeneousDataCreator builder = new HeterogeneousDataCreator()
                .add(twoDataList)
                .add(new ListItemOneData());

        builder.add(1, new ListItemOneData());
        builder.add(3, new ListItemOneData());

        adapter.calcDiff(true);



        adapter.setData(builder.getData());
    }
}
