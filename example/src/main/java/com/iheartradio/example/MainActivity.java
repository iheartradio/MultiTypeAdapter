package com.iheartradio.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iheartradio.example.data.SimpleClickableTextData;
import com.iheartradio.example.typeadapters.SimpleClickTextBinder;
import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;
import com.iheartradio.heterogeneousadapter.HeterogeneousDataCreator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HeterogeneousAdapter adapter = new HeterogeneousAdapter(new SimpleClickTextBinder());
        recyclerView.setAdapter(adapter);

        HeterogeneousDataCreator creator = new HeterogeneousDataCreator()
                .add(new SimpleClickableTextData("Single Item Type List", new Runnable() {
                    @Override
                    public void run() {
                        goToActivity(SingleItemTypeActivity.class);
                    }
                }))
                .add(new SimpleClickableTextData("Multi Item Type List", new Runnable() {
                    @Override
                    public void run() {
                        goToActivity(MultiTypeListExample.class);
                    }
                }))
                .add(new SimpleClickableTextData("Single Item Type Grid", new Runnable() {
                    @Override
                    public void run() {
                        goToActivity(SingleTypeGridExample.class);
                    }
                }))
                .add(new SimpleClickableTextData("Multi Item Type Grid", new Runnable() {
                    @Override
                    public void run() {
                        goToActivity(MultiTypeGridExample.class);
                    }
                }));

        adapter.setData(creator.getData());
    }

    private void goToActivity(final Class<?> activityClass) {
        final Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
