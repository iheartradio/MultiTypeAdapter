package com.iheartradio.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iheartradio.example.data.LowerCaseStringData;
import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;

import java.util.List;

/**
 * Created by Jonathan Muller on 4/25/17.
 */
public abstract class BaseActivity extends Activity {

    protected View mAddButton;
    protected View mRemoveButton;
    protected View mMoveButton;
    protected RecyclerView mRecyclerView;
    protected HeterogeneousAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        mAddButton = findViewById(R.id.add_button);
        mRemoveButton = findViewById(R.id.remove_button);
        mMoveButton = findViewById(R.id.move_button);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mAdapter = onCreateAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onAddButtonClicked();
            }
        });
        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onRemoveButtonClicked();
            }
        });
        mMoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onMoveButtonClicked();
            }
        });
    }

    abstract HeterogeneousAdapter onCreateAdapter();
    abstract RecyclerView.LayoutManager getLayoutManager();

    public void onAddButtonClicked() {
        List<Object> listData = mAdapter.data();
        List<Object> modifiedList = ListUtils.addRandomData(listData, new LowerCaseStringData(String.valueOf(ListUtils.randomRange(0, 100))));
        mAdapter.setData(modifiedList);
    }

    public void onRemoveButtonClicked() {
        List<Object> listData = mAdapter.data();
        List<Object> modifiedList = ListUtils.removeRandomData(listData);
        mAdapter.setData(modifiedList);
    }

    public void onMoveButtonClicked() {
        List<Object> listData = mAdapter.data();
        List<Object> modifiedList = ListUtils.moveRandomData(listData);
        mAdapter.setData(modifiedList);
    }
}
