package com.bizzan.base;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ActivityManage {

    private static ActivityManage sInstance = new ActivityManage();

    private WeakReference sCurrentActivityWeakRef;

    public static List<Activity> activities = new ArrayList<>();

    public static ActivityManage getInstance() {
        return sInstance;
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = (Activity) sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        if (sCurrentActivityWeakRef == null || !activity.equals(sCurrentActivityWeakRef.get())) {
            sCurrentActivityWeakRef = new WeakReference<>(activity);
        }
    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishOther(Activity exceptActivity) {
        for (Activity activity : activities) {
            if (activity.hashCode() == exceptActivity.hashCode()) {
                continue;
            }
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}

