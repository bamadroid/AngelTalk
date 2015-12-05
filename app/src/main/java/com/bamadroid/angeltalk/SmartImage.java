package com.bamadroid.angeltalk;

import android.graphics.Bitmap;

/**
 * Created by ray on 12/4/2015.
 */
public class SmartImage {
    private int mId;
    private String mName;
    private Bitmap mImage;
    private String mSoundPath;


    public SmartImage(int aId, String aName, Bitmap aImage, String aSoundPath)
    {
        this.mId = aId;
        this.mName = aName;
        this.mImage = aImage;
        this.mSoundPath = aSoundPath;
    }

    public SmartImage(){}

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getSoundPath() {
        return mSoundPath;
    }

    public void setSoundPath(String mSoundPath) {
        this.mSoundPath = mSoundPath;
    }
}
