package com.bizzan.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

/**
 * Created by Administrator on 2018/1/26.
 */

public class WonderfulCameraUtils {
    /**
     * 检测是否有相机设备
     */
    public static boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA) && Camera.getNumberOfCameras() > 0;
    }

    /**
     * 获取相机的实例
     *
     * @param cameraId 0 后置 Camera.CameraInfo.CAMERA_FACING_BACK   1 前置  Camera.CameraInfo.CAMERA_FACING_FRONT
     */
    public static Camera getCameraInstance(Context context, int cameraId) {
        Camera camera = null;
        try {
            if (checkCameraHardware(context)) camera = Camera.open(cameraId);
        } catch (Exception e) {
            return null;
        }
        return camera;
    }


}
