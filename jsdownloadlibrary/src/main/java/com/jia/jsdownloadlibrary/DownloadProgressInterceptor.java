package com.jia.jsdownloadlibrary;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Description: 带进度 下载  拦截器
 * Created by jia on 2017/11/30.
 * 人之所以能，是相信能
 *
 * 观察，修改以及可能短路的请求输出和响应请求的回来。
 * 通常情况下拦截器用来添加，移除或者转换请求或者回应的头部信息
 */
public class DownloadProgressInterceptor implements Interceptor {

    private DownloadProgressListener downloadListener;

    public DownloadProgressInterceptor(DownloadProgressListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new DownloadProgressResponseBody(response.body(), downloadListener)).build();
    }
}
