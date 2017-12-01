# JsDownload
使用Retrofit+RxJava实现能获取进度的下载库

UML图
![image](https://raw.githubusercontent.com/shuaijia/JsDownload/master/uml/uml.png)

### Usage
```
final DownloadProgressListener listener = new DownloadProgressListener() {
    @Override
    public void onStartDownload() {
        Log.e(TAG, "onStartDownload: ");
    }

    @Override
    public void onProgress(int progress) {
        Log.e(TAG, "onProgress: " + progress);
    }

    @Override
    public void onFinishDownload() {
        Log.e(TAG, "onFinishDownload: ");
    }

    @Override
    public void onFail(String errorInfo) {
        Log.e(TAG, "onFail: " + errorInfo);
    }
};


DownloadUtils downloadUtils = new DownloadUtils(baseUrl, listener);

downloadUtils.download(url, Environment.getExternalStorageDirectory().getAbsolutePath()+"a.apk", new Subscriber() {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        listener.onFail(e.toString());
    }

    @Override
    public void onNext(Object o) {
        listener.onFinishDownload();
    }
});
```
