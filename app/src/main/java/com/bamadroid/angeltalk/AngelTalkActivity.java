package com.bamadroid.angeltalk;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * Angel Talk is an Application that will allow a special needs
 * child to communicate using pictures and recorded voices.
 *
 */
public class AngelTalkActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private SQLiteDatabase mAngleTalkDatabase;
    private DatabaseHelper mDatabaseHelper;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angel_talk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getFragmentManager();

        if (openDatabase())
        {
            Toast.makeText(this.getApplicationContext(),"Database is Open!", Toast.LENGTH_LONG);
        }
        else
        {
            Toast.makeText(this.getApplicationContext(),"Database Failed!", Toast.LENGTH_LONG);
        }

        SuperUserFragment superUserFragment = new SuperUserFragment();
        SwitchUser(superUserFragment, false);

//        Button pictureButton = (Button) findViewById(R.id.picture_button);
//        pictureButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dispatchTakePictureIntent();
//            }
//        });
//
//        //TODO: Not used at this time
//        Button recordButton = (Button) findViewById(R.id.record_button);
//        recordButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //dispatchRecordIntent();
//                Cursor cursor = mDatabaseHelper.getAllItems(mAngleTalkDatabase);
//                if (cursor != null) {
//                    byte[] bytes = cursor.getBlob(1/*cursor.getColumnIndex(DatabaseConstants.KEY_IMAGE)*/);
//                    Bitmap bitmap = null;
//                    bitmap = mDatabaseHelper.dbBitmapUtility.getImage(bytes);
//
//                    ImageButton imageButton = (ImageButton) findViewById(R.id.picture_button2);
//                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
//                    imageButton.setBackgroundDrawable(bitmapDrawable);
//                }
//
//            }
//        });
//
//        //TODO: Not implemented yet
//        GridView gridview = (GridView) findViewById(R.id.gridView);
//        //gridview.setAdapter(new ImageAdapter(this, 1));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(AngelTalkActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void SwitchUser(Fragment fragment, boolean password_protected)
    {
        if (password_protected)
        {
            Toast.makeText(getApplicationContext(),"Password Required!", Toast.LENGTH_LONG);
        }

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.angel_talk_container, fragment, "USER_FRAGMENT");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_angel_talk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_superuser) {
            SuperUserFragment superUserFragment = new SuperUserFragment();
            SwitchUser(superUserFragment, true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // Build Smart Image to Store
            SmartImage smartImage = buildSmartImage(imageBitmap);
            mDatabaseHelper.addEntry(mAngleTalkDatabase, smartImage.getName(), smartImage.getImage(), smartImage.getSoundPath());
        }
    }

    private SmartImage buildSmartImage(Bitmap bitmap)
    {
        return new SmartImage.SmartImageBuilder(bitmap)
                .setName(getName())
                .setSoundPath(getSoundPath())
                .build();
    }

    /**
     * Get the Name of the SmartImage
     * @return The string name of the SmartImage
     */
    private String getName()
    {
       return null;
    }

    /**
     * Record the  sound and store the path for the SmartImage
     * @return The string sound path for the SmartImage
     */
    private String getSoundPath()
    {
        return null;
    }



    /**
     * Send Intent that allows user to take a picture.
     * This Intent expects results (onActivityResult())
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private boolean openDatabase()
    {
        mDatabaseHelper = new DatabaseHelper(this.getApplicationContext());
        mAngleTalkDatabase = mDatabaseHelper.getWritableDatabase();

        return mAngleTalkDatabase.isOpen();
    }


}
