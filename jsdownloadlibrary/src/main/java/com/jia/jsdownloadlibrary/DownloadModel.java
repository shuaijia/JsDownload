package com.jia.jsdownloadlibrary;

/**
 * Description: 下载实体类
 * Created by jia on 2017/11/30.
 * 人之所以能，是相信能
 */
public class DownloadModel {

    private int progress;
    private long currentFileSize;
    private long totalFileSize;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(long currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public long getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(long totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    @Override
    public String toString() {
        return "DownloadModel{" +
                "progress=" + progress +
                ", currentFileSize=" + currentFileSize +
                ", totalFileSize=" + totalFileSize +
                '}';
    }
}
