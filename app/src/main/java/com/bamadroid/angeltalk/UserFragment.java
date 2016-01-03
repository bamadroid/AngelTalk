package com.bamadroid.angeltalk;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ray on 1/3/2016.
 */
public class UserFragment extends Fragment{

    private Context mContext;
    private GridView mGridView;
    private ImageAdapter mImageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.angel_talk_container, container,false);

        if (view != null)
        {
            mContext = mGridView.getContext();
            mGridView = (GridView) mGridView.findViewById(R.id.gridView);
            // provides a menu for the grid
            registerForContextMenu(mGridView);


            if (savedInstanceState == null)
            {
                //TODO: create smart image layout, Nothing fancy but needs to be here for Super user layout
                mImageAdapter = new ImageAdapter(getActivity(),R.layout.content_angel_talk, new ArrayList<SmartImage>());
                mGridView.setAdapter(mImageAdapter);
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SmartImage smartImage = (SmartImage) parent.getItemAtPosition(position);
                        if (smartImage != null && smartImage.getDatabaseId() != null)
                        {
                            // play sound
                        }
                        else
                        {
                            Toast.makeText(view.getContext(), "No recording found for Image", Toast.LENGTH_LONG);
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
}
