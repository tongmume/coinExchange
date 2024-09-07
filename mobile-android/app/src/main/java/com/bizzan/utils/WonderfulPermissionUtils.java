package com.bizzan.utils;

import android.content.Context;
import android.hardware.Camera;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/5.
 */

public class WonderfulPermissionUtils {

    public static boolean isCanUseCamera(Context context) {
        Camera camera = WonderfulCameraUtils.getCameraInstance(context, Camera.CameraInfo.CAMERA_FACING_FRONT);
        if (camera == null) return false;
        else {
            camera.release();
            return true;
        }
    }

    public static boolean isCanUseStorage(Context context) {
        byte[] buf = "hello world".getBytes();
        File file = WonderfulFileUtils.getCommonPathFile(context, "/a.test");
        if (file == null) return false;
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream(file);
            fis = new FileInputStream(file);
            fos.write(buf);
            fis.read();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) fos.close();
                if (fis != null) fis.close();
                if (file != null) file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
