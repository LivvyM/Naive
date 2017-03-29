package cc.livvy.common.base;

import android.app.Application;

/**
 * 获取context
 *
 * Created by livvy on 17-3-29.
 */

public class BaseApplication extends Application{

    public static BaseApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        if (context == null) {
            context = this;
        }
    }

    public static BaseApplication getInstance() {
        return context;
    }
}
