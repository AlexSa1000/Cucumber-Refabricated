package com.alex.cucumber.util;

import net.minecraft.util.RandomSource;

import java.text.NumberFormat;

public final class Utils {
    public static final RandomSource RANDOM = RandomSource.create();

    @Deprecated(forRemoval = true)
    public static String format(Object obj) {
        return NumberFormat.getInstance().format(obj);
    }

    public static int randInt(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
}
