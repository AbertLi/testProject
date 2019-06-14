package com.albert.interfalib;

/**
 * 插件下载
 */
public interface IDownload {
    void downLoad(String url, String savePath, String name, IDownLoadCallBack callBack);
}
