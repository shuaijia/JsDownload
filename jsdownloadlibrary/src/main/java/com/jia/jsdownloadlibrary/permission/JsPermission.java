package com.jia.jsdownloadlibrary.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Describtion:Permission封装类
 * Created by jia on 2017/6/29.
 * 人之所以能，是相信能
 */
public class JsPermission {

    private Activity activity;

    private int requestCode;

    private JsPermissionListener listener;

    private String[] permissions;

    private static JsPermission instance = new JsPermission();

    private static List<Integer> codes = new ArrayList<>();

    private JsPermission() {
    }

    /**
     * 关联上下文
     *
     * @param activity
     * @return
     */
    @NonNull
    public static JsPermission with(@NonNull Activity activity) {
        instance.setActivity(activity);
        return instance;
    }

    /**
     * 关联上下文
     *
     * @param fragment
     * @return
     */
    @NonNull
    public static JsPermission with(@NonNull android.app.Fragment fragment) {
        instance.setActivity(fragment.getActivity());
        return instance;
    }

    /**
     * 关联上下文
     *
     * @param fragment
     * @return
     */
    @NonNull
    public static JsPermission with(@NonNull android.support.v4.app.Fragment fragment) {
        instance.setActivity(fragment.getActivity());
        return instance;
    }

    /**
     * 设置权限请求码
     *
     * @param requestCode
     * @return
     */
    @NonNull
    public JsPermission requestCode(@NonNull int requestCode) {
        codes.add(requestCode);
        instance.setRequestCode(requestCode);
        return instance;
    }

    /**
     * 设置请求回调
     *
     * @param listener
     * @return
     */
    @NonNull
    public JsPermission callBack(@NonNull JsPermissionListener listener) {
        instance.setListener(listener);
        return instance;
    }

    /**
     * 请求项目
     *
     * @param permissions
     * @return
     */
    @NonNull
    public JsPermission permission(@NonNull String... permissions) {
        instance.setPermissions(permissions);
        return instance;
    }

    /**
     * 开始请求
     */
    @NonNull
    public void send() {
        if (instance == null || instance.getActivity() == null || instance.getListener() == null
                || instance.getPermissions() == null) {
            return;
        }

        // 判断是否授权
        if (JsPermissionUtils.getInstance().checkPermission(instance.getActivity(), instance.getPermissions())) {
            // 已经授权，执行授权回调
            instance.getListener().onPermit(instance.getRequestCode(), instance.getPermissions());
        } else {
            JsPermissionUtils.getInstance().requestPermission(instance.getActivity(), instance.getRequestCode(), instance.getPermissions());
        }

    }

    /**
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (instance == null) {
            return;
        }
        for (int j = 0; j < codes.size(); j++) {
            if (requestCode == codes.get(j)) {
                // 遍历请求时的所有权限
                for (int i = 0; i < grantResults.length; i++) {
                    // 授权
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        instance.getListener().onPermit(codes.get(j), permissions);
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        instance.getListener().onCancel(codes.get(j), permissions);
                    }

                }
                codes.remove(codes.get(j));
            }
        }
    }


    //==================================以下为get、set方法================================================
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public JsPermissionListener getListener() {
        return listener;
    }

    public void setListener(JsPermissionListener listener) {
        this.listener = listener;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
