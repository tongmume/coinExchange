package com.bizzan.utils;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by Administrator on 2018/3/9.
 */

public class WonderfulUriUtils {
    /**
     * 获取Uri对象
     *
     * @param context
     * @param file
     * @return
     */
    public static Uri getUriForFile(Context context, File file) {
        return Build.VERSION.SDK_INT >= 24 ? getUriForFile24(context, file) : Uri.fromFile(file);
    }

    public static Uri getUriForFile24(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
    }

    public static File getUriBeforeKitKat(Context context, Uri imageUri) {
        String imagePath = getImagePath(context, imageUri, null);
        if (imagePath != null)
            return new File(imagePath);
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static File getUriFromKitKat(Context context, Uri imageUri) {
        String imagePath = null;
        if (DocumentsContract.isDocumentUri(context, imageUri)) {
            //如果是document类型的Uri,则通过document id 处理
            String docId = DocumentsContract.getDocumentId(imageUri);
            if ("com.android.providers.media.documents".equals(imageUri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(imageUri.getAuthority())) {
                imageUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(context, imageUri, null);
            }
        } else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // 如果是content 类型的Uri，则使用普通方式处理
            imagePath = getImagePath(context, imageUri, null);
        } else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            // 如果是file 类型的Uri直接 获取路径
            imagePath = imageUri.getPath();
        }
        if (imagePath != null)
            return new File(imagePath);
        return null;
    }

    public static String getImagePath(Context context, Uri uri, String selection) {
        String imagePath = null;
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null)
            if (cursor.moveToFirst())
                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return imagePath;
    }
}
