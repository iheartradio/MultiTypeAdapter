package com.iheartradio.example.viewholders;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iheartradio.example.data.ColorData;

/**
 * Created by Jonathan Muller on 4/27/17.
 */

public class GenericViewholder extends RecyclerView.ViewHolder {

    public GenericViewholder(View itemView) {
        super(itemView);
    }

    public static GenericViewholder create(final ViewGroup parent, @LayoutRes final int layoutRes) {
        return new GenericViewholder(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    public void bindColor(final ColorData colorData) {
        itemView.setBackgroundColor(colorData.getColor());
    }
}
