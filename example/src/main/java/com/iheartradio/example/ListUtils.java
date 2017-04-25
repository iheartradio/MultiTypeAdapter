package com.iheartradio.example;

import java.util.List;
import java.util.Random;

/**
 * Created by Jonathan Muller on 4/25/17.
 */

public class ListUtils {

    private static Random mRandom = new Random();

    public static int randomRange(final int min, final int max) {
        return mRandom.nextInt(max - min) + min;
    }

    public static int randomListPosition(final List<?> list) {
        return randomRange(0, list.size());
    }

    public static List moveRandomData(final List<Object> list) {
        int randomPosition = randomListPosition(list);
        Object object = list.get(randomPosition);
        list.remove(randomPosition);
        list.add(randomListPosition(list), object);
        return list;
    }

    public static List removeRandomData(final List<Object> list) {
        int randomPosition = randomListPosition(list);
        list.remove(randomPosition);
        return list;
    }

    public static List addRandomData(final List<Object> list, final Object data) {
        list.add(randomListPosition(list), data);
        return list;
    }

}
