package com.jia.jsdownloadlibrary.permission;

/**
 * Describtion:权限请求回调
 * Created by jia on 2017/6/29.
 * 人之所以能，是相信能
 */
public interface JsPermissionListener {

    /**
     * 授权
     */
    void onPermit(int requestCode, String... permission);

    /**
     * 未授权
     */
    void onCancel(int requestCode, String... permission);

}
