package com.bamadroid.angeltalk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by ray on 12/4/2015.
 */
public class DbBitmapUtility {

    private int mTargetWidth, mTargetHeight;

    public DbBitmapUtility(int aTargetWidth, int aTargetHeight) {
        mTargetHeight = aTargetHeight;
        mTargetWidth = aTargetWidth;
    }

    // convert from bitmap to byte array
    public byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


    public Bitmap getImage(byte[] image) {
        return convertToBitmap(image);
    }

    // convert from byte array to bitmap
    private Bitmap convertToBitmap(byte[] image) {
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(image, 0, image.length);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/mTargetWidth, photoH/mTargetHeight);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
