package com.bamadroid.angeltalk;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ray on 1/3/2016.
 */
public class SuperUserFragment  extends Fragment {

    private Context mContext;
    private GridView mGridView;
    private ImageAdapter mImageAdapter;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_angel_talk, container,false);

        if (view != null)
        {
            mContext = view.getContext();
            mGridView = (GridView) view.findViewById(R.id.gridView);

            // provides a menu for the grid
            registerForContextMenu(mGridView);


            if (savedInstanceState == null)
            {
                //TODO: create smart image layout, Nothing fancy but needs to be here for Super user layout
                //mImageAdapter = new ImageAdapter(getActivity(),R.layout.smart_image, new ArrayList<SmartImage>());
                SmartImage.SmartImageBuilder smartImageBuilder = new SmartImage.SmartImageBuilder(BitmapFactory.decodeResource(getResources(), R.drawable.default_image));
                SmartImage smartImage = new SmartImage(smartImageBuilder);
                ArrayList<SmartImage> arrayList = new ArrayList<SmartImage>();
                arrayList.add(smartImage);
                mImageAdapter = new ImageAdapter(getActivity(),R.layout.smart_image,arrayList);
                mGridView.setAdapter(mImageAdapter);
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SmartImage smartImage = (SmartImage) parent.getItemAtPosition(position);
                        if (smartImage != null && smartImage.getDatabaseId() != null) {
                            // play sound

                        }
                    }
                });


            }
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Send Intent that allows user to take a picture.
     * This Intent expects results (onActivityResult())
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(mContext.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}