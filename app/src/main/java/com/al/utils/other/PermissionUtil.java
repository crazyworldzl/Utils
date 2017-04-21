package com.al.utils.other;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ZhangLong on 2017/4/13.
 */

public class PermissionUtil {
    /**
     *
     * @param context
     * @param permision
     * @return
     */
    public static String[] havePermission(Context context, String[] permision){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < permision.length; i++) {
            if (!(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, permision[i]))) {
                strings.add(permision[i]);
            }
        }
       if(strings.size()==0){
           return null;
       }else{
           String[] strings1 = strings.toArray(new String[strings.size()]);
           return strings1;
       }
    }

    /**
     *
     * @param context
     * @param permision
     * @param requestCode
     * @return
     */
    public static boolean requestPermission(Activity context, String[] permision, int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] strings = havePermission(context, permision);
            if (strings == null) {
                return true;
            } else {
                ActivityCompat.requestPermissions(context, permision, requestCode);
                return false;
            }
        }else{
            return false;
        }
    }
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID },
                MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
}
