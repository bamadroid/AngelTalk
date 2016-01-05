package com.bamadroid.angeltalk;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * This fragment allows user to add and remove SmartImages
 */
public class ImageAdapter extends ArrayAdapter<SmartImage>
{
    private Context mContext;
    private int mLayoutResourceId;
    private ArrayList<SmartImage> mData;

    private static class ViewHolder
    {
        ImageView viewImage;
        ImageButton imageAddButton;
        ImageButton imageRemoveButton;
    }

    public ImageAdapter(Context context, int layoutResourceId, ArrayList<SmartImage> data)
    {
        super(context, layoutResourceId, data);
        mContext = context;
        mLayoutResourceId = layoutResourceId;
        mData = data;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater  inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);

            viewHolder.viewImage = (ImageView) convertView.findViewById(R.id.smartImageView);
            viewHolder.imageAddButton = (ImageButton) convertView.findViewById(R.id.addButton);
            viewHolder.imageRemoveButton = (ImageButton) convertView.findViewById(R.id.removeButton);

            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SmartImage smartImage = mData.get(position);
        if (smartImage != null)
        {
            viewHolder.viewImage.setImageDrawable(new BitmapDrawable(mContext.getResources(), smartImage.getImage()));
        }
        else
        {
            viewHolder.viewImage.setImageResource(R.drawable.default_image);
        }
       return convertView;
    }

    public ArrayList getAllItems()
    {
        return mData;
    }

    // create a new ImageView for each item referenced by the Adapter
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if (convertView == null) {  // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
//        return imageView;
//    }

    // references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.cat,
//            R.drawable.girl,
//            R.drawable.star,
//            R.drawable.wolf
//    };
}