package com.bizzan.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;

;

/**
 * Created by wonderful on 2017/7/17.
 */

public class WonderfulFileUtils {
    public static boolean hasSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable();
    }

    public static File getLongSaveFile(Context context, String dirname, String filename) {
        File file;
        if (hasSDCard()) {
            file = new File(context.getExternalFilesDir(dirname), filename);
        } else {
            file = new File(context.getFilesDir(), filename);
        }
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File getLongSaveDir(Context context, String dirname) throws IOException {
        File file;
        if (hasSDCard()) {
            file = context.getExternalFilesDir(dirname);
        } else {
            file = context.getFilesDir();
        }
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        if (!file.exists()) file.createNewFile();
        return file;
    }

    public static File getCacheSaveFile(Context context, String filename) {
        File file;
        if (hasSDCard()) {
            file = new File(context.getExternalCacheDir(), filename);
        } else {
            file = new File(context.getCacheDir(), filename);
        }
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public static File getCacheSaveDir(Context context) throws IOException {
        File file;
        if (hasSDCard()) {
            file = context.getExternalCacheDir();
        } else {
            file = context.getCacheDir();
        }
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        if (!file.exists()) file.createNewFile();
        return file;
    }

    public static File getCommonPathFile(Context context, String filename) {
        File file = new File(Environment.getExternalStorageDirectory() + filename);
        if (file.isDirectory())
            return null;
        if (file.exists())
            file.delete();
        if (!file.getParentFile().exists())
            file.mkdirs();
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Uri getUriForFile(Context context, File file) {
        return Build.VERSION.SDK_INT >= 24 ? getUriForFile24(context, file) : Uri.fromFile(file);
    }

    public static Uri getUriForFile24(Context context, File file) {
        return FileProvider.getUriForFile(context, "com.bizzan.app.fileprovider", file);
    }

}
