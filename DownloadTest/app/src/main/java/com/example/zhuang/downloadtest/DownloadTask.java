package com.example.zhuang.downloadtest;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhuang on 17-7-23.
 */


public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;

    public static final int TYPE_CANCELED = 3;

    private boolean isCancel = false;
    private boolean isPause = false;
    private int lastProgress;
    private DownloadListener downloadListener;

    public DownloadTask(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream = null;
        RandomAccessFile savedFile = null;
        File file = null;
        long downloadLength = 0;
        String url = params[0];
        String fileName = url.substring(url.lastIndexOf("/"));
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        file = new File(directory + fileName);
        if (file.exists()) {
            downloadLength = file.length();
        }
        try {
            long contentLength = getContentLength(url);
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadLength) {
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().addHeader("RANGE", "bytes=" + downloadLength + "-").url(url).build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                inputStream = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);
                byte[] bytes = new byte[1024];
                int len;
                int total = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    if (isCancel) {
                        return TYPE_CANCELED;
                    } else if (isPause) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(bytes, 0, len);
                        //计算百分比
                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);
                    }

                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCancel && file != null) {
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];
        if (progress > lastProgress) {
            lastProgress = progress;
            downloadListener.onProgress(progress);
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        switch (integer){
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                downloadListener.onFailed();
                break;
            case TYPE_PAUSED:
                downloadListener.onPause();
                break;
            case TYPE_CANCELED:
                downloadListener.onCancle();
            default:
                break;
        }
    }

    public void pauseDownload(){
        isPause = true;
    }

    public void cancleDownload() {
        isCancel = true;
    }
    private long getContentLength(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contengLength = response.body().contentLength();
            response.close();
            return contengLength;
        }
        return 0;
    }
}
