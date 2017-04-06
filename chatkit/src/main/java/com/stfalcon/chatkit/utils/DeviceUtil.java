package com.stfalcon.chatkit.utils;

import android.content.Context;

/**
 * Created by lvm on 2016/12/17.
 */

public class DeviceUtil {

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
