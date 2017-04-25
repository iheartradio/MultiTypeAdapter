package com.iheartradio.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iheartradio.heterogeneousadapter.HeterogeneousAdapter;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    public void onAddButtonClicked() { }
    public void onRemoveButtonClicked() { }
    public void onMoveButtonClicked() { }
}
