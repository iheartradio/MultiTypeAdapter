package com.iheartradio.heterogeneousadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.iheartradio.multitypeadapter.MultiTypeAdapter;
import com.iheartradio.multitypeadapter.TypeAdapter;

import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Created by Jonathan Muller on 10/4/17.
 */
public class MultiTypeAdapterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    RecyclerView mRecyclerView;

    @Mock
    RecyclerView.ViewHolder mViewHolder;

    private MultiTypeAdapter mAdapter = new MultiTypeAdapter();

    private final TypeAdapter<String, RecyclerView.ViewHolder> mTypeAdapter = new TypeAdapter<String, RecyclerView.ViewHolder>() {
        @Override
        public boolean isMyData(final Object data) {
            return data instanceof String;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent) {
            return mViewHolder;
        }
    };

    private static final String TEST_STRING_ONE = "testString1";
    private static final String TEST_STRING_TWO = "testString2";



//    @Before
//    public void setUp() throws Exception {
//        mAdapter = new MultiTypeAdapter(mTypeAdapter);
//
//        TypeAdapter typeAdapter = mAdapter.getTypeAdapterForData("");
//        assertNotNull(typeAdapter);
//
//        mRecyclerView.setAdapter(mAdapter);
//    }
//
//    @Test
//    public void addTypeAdapter() throws Exception {
//        mAdapter.addTypeAdapter(mTypeAdapter);
//        TypeAdapter typeAdapter = mAdapter.getTypeAdapterForData("");
//        assertNotNull(typeAdapter);
//    }
//
//    @Test
//    public void setData() throws Exception {
//        mAdapter.addTypeAdapter(mTypeAdapter);
//        Items items = new Items();
//        items.add(TEST_STRING_ONE);
//        mAdapter.setData(items);
//        assertEquals(mAdapter.data().get(0), TEST_STRING_ONE);
//    }
//
//    @Test
//    public void setData1() throws Exception {
//        Items items = new Items();
//        items.add(TEST_STRING_ONE);
//        mAdapter.setData(items, false);
//        assertEquals(mAdapter.data().get(0), TEST_STRING_ONE);
//    }
//
//    @Test
//    public void setData2() throws Exception {
//        Items items = new Items();
//        items.add(TEST_STRING_ONE);
//        mAdapter.setData(items.get());
//        assertEquals(mAdapter.data().get(0), TEST_STRING_ONE);
//    }
//
//    @Test
//    public void setData3() throws Exception {
//        Items items = new Items();
//        items.add(TEST_STRING_ONE);
//        mAdapter.setData(items.get(), false);
//        assertEquals(mAdapter.data().get(0), TEST_STRING_ONE);
//    }
//
//    @Test
//    public void data() throws Exception {
//        Items items = new Items();
//        items.add(TEST_STRING_ONE);
//        mAdapter.setData(items);
//        assertNotNull(mAdapter.data());
//        assertEquals(mAdapter.data().get(0), TEST_STRING_ONE);
//    }
//
//    @Test
//    public void setSpanHandler() throws Exception {
//    }
//
//    @Test
//    public void getItemCount() throws Exception {
//        Items items = new Items();
//        items.add(TEST_STRING_ONE);
//        items.add(TEST_STRING_TWO);
//        mAdapter.setData(items);
//        assertEquals(mAdapter.getItemCount(), 2);
//    }
//
//    @Test
//    public void getTypeAdapterForPosition() throws Exception {
//    }
}