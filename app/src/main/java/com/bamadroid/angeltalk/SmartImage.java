package com.bamadroid.angeltalk;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Data that will be behind the gridView
 *
 */
public class SmartImage {
    private String mName;
    private Bitmap mImage;
    private String mSoundPath;
    private Integer mDatabaseId;


    public static class SmartImageBuilder
    {
        private String bName;
        private Bitmap bImage;
        private String bSoundPath;
        private Integer bDatabaseId;

        public SmartImageBuilder(Bitmap bitmap)
        {
            this.bImage = bitmap;
        }

        public SmartImageBuilder setSoundPath(String soundPath)
        {
            this.bSoundPath = soundPath;
            return this;
        }

        public SmartImageBuilder setName(String  name)
        {
            this.bName = name;
            return this;
        }

        public SmartImageBuilder setDatabaseId(Integer  id)
        {
            this.bDatabaseId = id;
            return this;
        }

        public SmartImage build()
        {
            return new SmartImage(this);
        }
    }

    public SmartImage(SmartImageBuilder builder)
    {
        this.mName = builder.bName;
        this.mImage = builder.bImage;
        this.mSoundPath = builder.bSoundPath;
        this.mDatabaseId = builder.bDatabaseId;
    }

    public String getName()
    {
        return mName;
    }

    public String getSoundPath()
    {
        return mSoundPath;
    }

    public Integer getDatabaseId()
    {
        return mDatabaseId;
    }

    public Bitmap getImage()
    {
        return mImage;
    }

    public void playSound(Context context)
    {
        // play sound
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.create(context, Uri.parse(mSoundPath));
        mediaPlayer.start();
    }

}
