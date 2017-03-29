package cc.livvy.common.stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * Created by livvy on 17-3-29.
 */

public class ActivityStack {

    public List<Activity> activityList = new ArrayList<>();

    private static ActivityStack tack = new ActivityStack();

    public static ActivityStack getInstanse() {
        return tack;
    }

    private static Class LoginClass = null;

    private ActivityStack() {
    }

    public Activity getTopActivity(){
        if(activityList.size() > 0){
            return activityList.get(activityList.size() - 1);
        }
        return null;
    }

    public Activity getCurrentActivity(){
        if(activityList.size() > 0){
            return activityList.get(activityList.size() - 1);
        }
        return null;
    }

    public void setLoginClass(Class loginClass){
        LoginClass = loginClass;
    }

    public Class getLoginClass(){
        return LoginClass;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 完全退出
     *
     * @param context
     */
    public void exit(Context context) {
        while (activityList.size() > 0) {
            activityList.get(activityList.size() - 1).finish();
        }
        System.exit(0);
    }

    /**
     * 根据class name获取activity
     *
     * @param name
     * @return
     */
    public Activity getActivityByClassName(String name) {
        for (Activity ac : activityList) {
            if (ac.getClass().getName().indexOf(name) >= 0) {
                return ac;
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public Activity getActivityByClass(Class cs) {
        for (Activity ac : activityList) {
            if (ac.getClass().equals(cs)) {
                return ac;
            }
        }
        return null;
    }

    /**
     * 弹出activity
     *
     * @param activity
     */
    public void popActivity(Activity activity) {
        removeActivity(activity);
        activity.finish();
    }

    /**
     * 弹出activity到
     *
     * @param cs
     */
    @SuppressWarnings("rawtypes")
    public void popUntilActivity(Class... cs) {
        List<Activity> list = new ArrayList<Activity>();
        for (int i = activityList.size() - 1; i >= 0; i--) {
            Activity ac = activityList.get(i);
            boolean isTop = false;
            for (int j = 0; j < cs.length; j++) {
                if (ac.getClass().equals(cs[j])) {
                    isTop = true;
                    break;
                }
            }
            if (!isTop) {
                list.add(ac);
            } else
                break;
        }
        for (Iterator<Activity> iterator = list.iterator(); iterator.hasNext();) {
            Activity activity = iterator.next();
            popActivity(activity);
        }
    }

    /**
     *
     * @Description: 判断是否存在activity
     * @param activity
     * @return boolean
     * @throws
     */
    public boolean isExistActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClassName(activity.getPackageName(), activity.getClass().getName());
        if (intent.resolveActivity(activity.getPackageManager()) == null) {
            return false;
        }
        return true;
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className
     *            某个界面名称
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
