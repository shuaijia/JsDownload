package com.jia.jsdownloadlibrary.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Description: 运行时权限工具类
 * Created by jia on 2017/6/29.
 * 人之所以能，是相信能
 */
public class JsPermissionUtils {

    private static JsPermissionUtils instance;

    private JsPermissionUtils() {
    }

    public static JsPermissionUtils getInstance() {
        if (instance == null) {
            instance = new JsPermissionUtils();
        }
        return instance;
    }

    /**
     * 检查权限检查
     *
     * @param context
     * @param permissions
     * @return
     */
    public boolean checkPermission(Context context, String... permissions) {

        for (String permission : permissions) {
            // 判断当前该权限是否允许
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    /**
     * 权限请求方法
     * @param activity
     * @param code
     * @param permissions
     */
    public void requestPermission(Activity activity, int code, String... permissions) {
        ActivityCompat.requestPermissions(activity, permissions, code);
    }

    /**
     * 判断是否需要动态申请权限
     * @return
     */
    public static boolean needRequestPermission(){
        return Build.VERSION.SDK_INT >= 23;
    }
}
