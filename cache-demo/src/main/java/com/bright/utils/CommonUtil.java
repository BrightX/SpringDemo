package com.bright.utils;

import org.springframework.lang.NonNull;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Bright Xu
 * @since 2023/12/31
 */
public final class CommonUtil {
    private CommonUtil() {
    }

    @NonNull
    public static int diffDate(@NonNull Date date1, @NonNull Date date2, int field) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int v1 = cal1.get(field);
        int v2 = cal2.get(field);
        return v1 - v2;
    }
}
