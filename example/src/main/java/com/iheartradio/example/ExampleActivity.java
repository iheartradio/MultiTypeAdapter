package com.iheartradio.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;
import com.iheartradio.heterogeneousadapter.HeterogeneousBinder;
import com.iheartradio.heterogeneousadapter.dataset.ConcatDataSet;
import com.iheartradio.heterogeneousadapter.dataset.DataSet;
import com.iheartradio.heterogeneousadapter.dataset.ItemDataSet;

import java.util.ArrayList;
import java.util.List;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HeterogeneousAdapter adapter = new HeterogeneousAdapter(new ListItemOneBinder());
        recyclerView.setAdapter(adapter);

        List<DataSet<?>> data = new ArrayList<>();
        data.add(new ItemDataSet<>(new ListItemOneData()));
        data.add(new ItemDataSet<>(new ListItemOneData()));
        data.add(new ItemDataSet<>(new ListItemOneData()));
        data.add(new ItemDataSet<>(new ListItemOneData()));

        adapter.setData(new ConcatDataSet<>(data));
    }
}
