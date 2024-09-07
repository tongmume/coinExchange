package com.bizzan.utils.okhttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by Administrator on 2017/10/17.
 */

public abstract class FileCallback extends Callback<File> {
    /**
     * 目标文件存储的文件夹路径
     */
    private String path;

    public abstract void inProgress(float progress);

    public FileCallback(String path) {
        this.path = path;
    }

    @Override
    public File parseNetworkResponse(Response response) throws IOException {
        return saveFile(response);
    }

    public File saveFile(Response response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;
            File file = new File(path);
            if (!file.getParentFile().exists()) file.mkdirs();
            if (file.exists()) file.delete();
            file.createNewFile();
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                WonderfulOkhttpUtils.getInstance().getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        inProgress(finalSum * 1.0f / total);
                    }
                });
            }
            fos.flush();
            return file;

        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }

        }
    }
}
