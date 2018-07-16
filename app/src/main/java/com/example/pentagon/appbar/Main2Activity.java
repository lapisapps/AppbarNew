package com.example.pentagon.appbar;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.FilterWithSpaceAdapter;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterCameraPreviews;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTags;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataSystems;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity
        implements View.OnTouchListener {
    //navigation elements
    Spinner prjcts;
   public static ArrayList<DataTag> prjcttags;
    LinearLayout arealayout;
    AutoCompleteTextView suf,functions,system,sequence,parttag,part,elem,details;
    RadioButton rdequip,rdpipe,rdpipesup;
TextInputLayout sufixinp,systeminp;
   public  RecyclerView recyclerView,recyclerViewtag;

    DrawerLayout drawer;
    //fab
    FloatingActionButton fab,fab1,fab2,fab3;
    private boolean FAB_Status = false;
public DataTag selectedtag;
public  ArrayList<DataTag> dataTags;
    //Animations
    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;

    private static final int REQUEST_CODE = 1;
    NavigationView navigationView;
    static ArrayList<DataPreview> dataPreview;
    private static final int OPENCAMERA =100 ;
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";
    public static final String AUDIO_EXTENSION = "mp3";
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private static final int CAMERA_CAPTURE_AUDIO_REQUEST_CODE = 300;
    private static String imageStoragePath;
    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final int MEDIA_TYPE_AUDIO =3 ;
    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 8;
    private Bitmap bitmap;

    public static final String GALLERY_DIRECTORY_NAME = "Files";
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        prjcttags=new ArrayList<>();
new SqliteDb(this).insertTemp();
      recyclerView=(RecyclerView) findViewById(R.id.images);
        if (!CameraUtils.isDeviceSupportCamera(getApplicationContext())) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device doesn't have camera
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
try {
    //imageView = (ImageView) findViewById(R.id.imageView);


   drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
   drawer.setOnTouchListener(this);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

 navigationView = (NavigationView) findViewById(R.id.nav_view);

  //  navigationView.setNavigationItemSelectedListener(this);
    setNavigation(navigationView);
    setFab();
    setView();
//    if (null == savedInstanceState) {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, Camera2BasicFragment.newInstance())
//                .commit();
//    }
}catch (Exception e){
    e.printStackTrace();
}
        Log.e("prjc",prjcttags.size()+"");
        if (CameraUtils.checkPermissions(getApplicationContext())) {
            captureVideo();
        } else {
            requestCameraPermission(MEDIA_TYPE_VIDEO);
        }
    }

    private void setFab() {
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (CameraUtils.checkPermissions(getApplicationContext())) {
//                    captureImage();
//                } else {
//                    requestCameraPermission(MEDIA_TYPE_IMAGE);
//                }
//            }
//        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);

        //Animations
        show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FAB_Status == false) {
                    //Display FAB menu
                    expandFAB();
                    FAB_Status = true;
                } else {
                    //Close FAB menu
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                if (CameraUtils.checkPermissions(getApplicationContext())) {
                    captureImage();
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE);
                }
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CameraUtils.checkPermissions(getApplicationContext())) {
                    captureVideo();
                } else {
                    requestCameraPermission(MEDIA_TYPE_VIDEO);
                }
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CameraUtils.checkPermissions(getApplicationContext())) {
                    captureAudio();
                } else {
                    requestCameraPermission(MEDIA_TYPE_VIDEO);
                }
            }
        });

    }
public void setNavigation(NavigationView navigationView){


    View headerView = navigationView.getHeaderView(0);
    prjcts=headerView.findViewById(R.id.prjct);
ArrayList<PrjctData> prjctss=new SqliteDb(this).getPrjcts();
    ArrayAdapter aa = new ArrayAdapter<PrjctData>(this,android.R.layout.simple_spinner_item,prjctss);
    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //Setting the ArrayAdapter data on the Spinner

   system=navigationView.findViewById(R.id.system);
        part=navigationView.findViewById(R.id.part);
        elem=navigationView.findViewById(R.id.elem);
        details=navigationView.findViewById(R.id.details);
 functions=navigationView.findViewById(R.id.functions);
        sufixinp=navigationView.findViewById(R.id.sufixinp);
        systeminp=navigationView.findViewById(R.id.systemin);
 suf=navigationView.findViewById(R.id.sufix);
 sequence=navigationView.findViewById(R.id.sequence);
     parttag=navigationView.findViewById(R.id.parttag);
arealayout=navigationView.findViewById(R.id.layoutarea);
arealayout.setVisibility(View.GONE);

        rdequip=(RadioButton)navigationView.findViewById(R.id.rdequip);
        rdpipe=(RadioButton)navigationView.findViewById(R.id.rdpip);
        rdpipesup=(RadioButton)navigationView.findViewById(R.id.rdpipsup);
    TextView add=(TextView)navigationView.findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTagDialog.main2Activity=Main2Activity.this;
new AddTagDialog(Main2Activity.this,0);
            }
        });

    prjcts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            // your code here
            Object item1 = parentView.getItemAtPosition(position);
            if (item1 instanceof PrjctData) {

                PrjctData tag = (PrjctData) item1;
                Utility.CURRENTPRJCT=tag;
             //   prjcttags=new SqliteDb(Main2Activity.this).getPrjctsTags(tag.getId());

                //setProjectTags(0);
//                if(prjcttags.size()>0){
//                    prjcttags.get(0).setSelected(true);
//                    loadTag(prjcttags.get(0));}
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }

    });


            //
    prjcts.setAdapter(aa);
    if(prjctss.size()>0) {
        Utility.CURRENTPRJCT = prjctss.get(0);
     //   prjcttags = new SqliteDb(Main2Activity.this).getPrjctsTags(prjctss.get(0).getId());
    }
   // setProjectTags(0);

}


//    public void setProjectTags(int n) {
//
//        Log.e("tagsize",prjcttags.size()+"");
//        for(int k=0;k<prjcttags.size();k++)
//
//  recyclerViewtag=(RecyclerView) navigationView.findViewById(R.id.tags);
//        RecyclerViewAdapterTags place = new RecyclerViewAdapterTags(Main2Activity.this,prjcttags);
//        recyclerViewtag.setVisibility(View.VISIBLE);
//
//        recyclerViewtag.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Main2Activity.this,2,GridLayoutManager.VERTICAL,false);
//        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerViewtag.setNestedScrollingEnabled(true);
//
//        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
//        recyclerViewtag.setLayoutManager(mLayoutManager);
//        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerViewtag.setItemAnimator(new DefaultItemAnimator());
//        recyclerViewtag.setAdapter(place);
//
//        recyclerViewtag.addOnItemTouchListener(new RecyclerItemClickListener(Main2Activity.this, recyclerViewtag, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, final int position) {
//     loadTag(prjcttags.get(position));
//for(int j=0;j<recyclerViewtag.getChildCount();j++)
//    recyclerViewtag.getChildAt(j).findViewById(R.id.tag).setBackgroundColor(getResources().getColor(R.color.white));
//                (view.findViewById(R.id.tag)).setBackgroundColor(getResources().getColor(R.color.rowsel));
//
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//                CheckBox ch=(CheckBox)view.findViewById(R.id.checkBox);
//
//                if(ch.isChecked())
//                    ch.setChecked(false);
//                else
//                    ch.setChecked(true);
//
//            }
//        }));
//        if(prjcttags.size()>0){
//            prjcttags.get(n).setSelected(true);
//            loadTag(prjcttags.get(n));}
//            else {
//
//           loadTag(null);
//        }
//    }

//    private void loadTag(DataTag dataTag) {
//        if(dataTag==null){
//            rdpipesup.setChecked(false);
//            rdequip.setChecked(false);
//            rdpipe.setChecked(false);
//
//            arealayout.setVisibility(View.GONE);
//            systeminp.setVisibility(View.VISIBLE);
//            systeminp.setHint("System");
//            system.setText("");
//            functions.setText("");
//            sufixinp.setHint("Partitm");
//            suf.setText("");
//            sequence.setText("");
//            parttag.setText("");
//            return;
//        }
//
//
//        if(dataTag.getType().equals(Utility.TAGEQUIP)){
//
//            rdequip.setChecked(true);
//            rdequip.setVisibility(View.VISIBLE);
//            rdpipesup.setVisibility(View.GONE);
//            rdpipe.setVisibility(View.GONE);
//            arealayout.setVisibility(View.GONE);
//            systeminp.setVisibility(View.VISIBLE);
//            systeminp.setHint("System");
//            system.setText(dataTag.getSystemname()+"-"+dataTag.getSystemcode());
//            functions.setText(dataTag.getFnname()+"-"+dataTag.getFncode());
//            sufixinp.setHint("Partitm");
//            suf.setText(dataTag.getPartitemsuf());
//            sequence.setText(dataTag.getSequence());
//            parttag.setText(dataTag.getParttag());
//        }
//        else if(dataTag.getType().equals(Utility.TAGPIPE)){
//
//          rdpipe.setChecked(true);
//
//            rdequip.setVisibility(View.GONE);
//            rdpipesup.setVisibility(View.GONE);
//            rdpipe.setVisibility(View.VISIBLE);
//            arealayout.setVisibility(View.GONE);
//            systeminp.setVisibility(View.VISIBLE);
//            system.setText(dataTag.getSystemname()+"-"+dataTag.getSystemcode());
//            functions.setText(dataTag.getFnname()+"-"+dataTag.getFncode());
//            sufixinp.setHint("Sufix");
//            suf.setText(dataTag.getPartitemsuf());
//            sequence.setText(dataTag.getSequence());
//            parttag.setText(dataTag.getParttag());
//        }
//        else {
//
//            rdpipesup.setChecked(true);
//            rdequip.setVisibility(View.GONE);
//            rdpipesup.setVisibility(View.VISIBLE);
//            rdpipe.setVisibility(View.GONE);
//            arealayout.setVisibility(View.VISIBLE);
//            systeminp.setVisibility(View.GONE);
//          //  system.setText(dataTag.getSystemname());
//            part.setText(dataTag.getAreapartname()+"-"+dataTag.getAreapartcode());
//            elem.setText(dataTag.getAreaelem());
//            details.setText(dataTag.getAreapartdetails());
//            functions.setText(dataTag.getFnname()+"-"+dataTag.getFncode());
//            sufixinp.setHint("Sufix");
//            suf.setText(dataTag.getPartitemsuf());
//            sequence.setText(dataTag.getSequence());
//            parttag.setText(dataTag.getParttag());
//        }
//    }
//    private void setNavigation(NavigationView navigationView) {
//
//
//
//
//   system=navigationView.findViewById(R.id.system);
//        part=navigationView.findViewById(R.id.part);
//        elem=navigationView.findViewById(R.id.elem);
//        details=navigationView.findViewById(R.id.details);
// functions=navigationView.findViewById(R.id.functions);
//        sufixinp=navigationView.findViewById(R.id.sufixinp);
//        systeminp=navigationView.findViewById(R.id.systemin);
// suf=navigationView.findViewById(R.id.sufix);
// sequence=navigationView.findViewById(R.id.sequence);
//     parttag=navigationView.findViewById(R.id.parttag);
//arealayout=navigationView.findViewById(R.id.layoutarea);
//arealayout.setVisibility(View.GONE);
//
//        rdequip=(RadioButton)navigationView.findViewById(R.id.rdequip);
//        rdpipe=(RadioButton)navigationView.findViewById(R.id.rdpip);
//        rdpipesup=(RadioButton)navigationView.findViewById(R.id.rdpipsup);
//        TextView add=(TextView)navigationView.findViewById(R.id.addtag);
//         recyclerViewtag=(RecyclerView) navigationView.findViewById(R.id.tags);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//selectedtag.setSufix(suf.getText().toString());
//selectedtag.setSequence(sequence.getText().toString());
//selectedtag.setParttag(parttag.getText().toString());
//if(dataTags==null)
//    dataTags=new ArrayList<>();
//DataTag dd=new DataTag();
//dd.setParttag(parttag.getText().toString());
//                dd.setSufix(suf.getText().toString());
//                dd.setSequence(sequence.getText().toString());
//                dd.setFnname(selectedtag.getFnname());
//                dd.setFncode(selectedtag.getFncode());
//                dd.setSystemname(selectedtag.getSystemname());
//                dd.setSystemcode(selectedtag.getSystemcode());
//int k=0;
//if(dataTags.size()>0)
//    k=dataTags.size()-1;
//dataTags.add(k,dd);
//setTagsRecyclerview();
//            }
//        });
//        rdequip.setChecked(true);
//initilizenavi();
////initilizing
//
////        EditText description=navigationView.findViewById(R.id.description);
////ImageView addimage=navigationView.findViewById(R.id.addimage);
////addimage.setOnClickListener(new View.OnClickListener() {
////    @Override
////    public void onClick(View v) {
////
////    }
////});
//
//
//
//
//    }







    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent dbmanager = new Intent(Main2Activity.this,AndroidDatabaseManager.class);
            startActivity(dbmanager);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void opencamer() {



        if (ContextCompat.checkSelfPermission(Main2Activity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(Main2Activity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        OPENCAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_CODE);
            // Permission has already been granted
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try {
                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);

                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                // video successfully recorded
                // preview the recorded video
                previewVideo();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_AUDIO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                // video successfully recorded
                // preview the recorded video
                previewAudio();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }

public  void previewAudio() {
        try {
            // hide video preview
            //  txtDescription.setVisibility(View.GONE);
            //  videoPreview.setVisibility(View.GONE);

            //  imageView.setVisibility(View.VISIBLE);

            //   Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);

            // imageView.setImageBitmap(bitmap);
            if(dataPreview==null)
                dataPreview=new ArrayList<>();
            DataPreview dd=new DataPreview();
            dd.setPath(imageStoragePath);
            dd.setType(MEDIA_TYPE_AUDIO+"");
            dataPreview.add(dd);
            setView();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void captureAudio() {
//        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//
//        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_AUDIO);
//        if (file != null) {
//            imageStoragePath = file.getAbsolutePath();
//        }
//
//        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//
//        // start the image capture Intent
//        startActivityForResult(intent, CAMERA_CAPTURE_AUDIO_REQUEST_CODE);

        audioDialog();
    }

    private void audioDialog() {
//        AudioRecordDialog.main2Activity=Main2Activity.this;
//new AudioRecordDialog(Main2Activity.this);
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }
    private void captureVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_VIDEO);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);

        // set video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file

        // start the video capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }
    public void previewCapturedImage() {
        try {
            // hide video preview
          //  txtDescription.setVisibility(View.GONE);
          //  videoPreview.setVisibility(View.GONE);

          //  imageView.setVisibility(View.VISIBLE);

         //   Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);

           // imageView.setImageBitmap(bitmap);

if(dataPreview==null)
    dataPreview=new ArrayList<>();
DataPreview dd=new DataPreview();
dd.setPath(imageStoragePath);
dd.setType(MEDIA_TYPE_IMAGE+"");
            new PreviewDialog(Main2Activity.this,dd,true);
dataPreview.add(dd);
setView();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public  void setView() {
if(dataPreview==null)
    dataPreview=new ArrayList<>();
        RecyclerViewAdapterCameraPreviews place = new RecyclerViewAdapterCameraPreviews(Main2Activity.this,dataPreview);
        recyclerView.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Main2Activity.this,2,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

recyclerView.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recyclerView.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(place);




        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(Main2Activity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                new PreviewDialog(Main2Activity.this,dataPreview.get(position),true);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    /**
     * Displaying video in VideoView
     */
    private void previewVideo() {
        try {
            // hide video preview
            //  txtDescription.setVisibility(View.GONE);
            //  videoPreview.setVisibility(View.GONE);

            //  imageView.setVisibility(View.VISIBLE);

            //   Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);

            // imageView.setImageBitmap(bitmap);
            if(dataPreview==null)
                dataPreview=new ArrayList<>();
            DataPreview dd=new DataPreview();
            dd.setPath(imageStoragePath);
            dd.setType(MEDIA_TYPE_VIDEO+"");
            Log.e("prjct",prjcttags.size()+"");
            new PreviewDialog(Main2Activity.this,dd,true);
            dataPreview.add(dd);
            setView();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Alert dialog to navigate to app settings
     * to enable necessary permissions
     */
    private void showPermissionsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(Main2Activity.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    private void requestCameraPermission(final int type) {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            if (type == MEDIA_TYPE_IMAGE) {
                                // capture picture
                                captureImage();
                            } else if (type == MEDIA_TYPE_VIDEO) {
                                captureVideo();
                            }
                            else
                                captureAudio();

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionsAlert();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    private void expandFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(show_fab_1);
        fab1.setClickable(true);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin += (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(show_fab_3);
        fab3.setClickable(true);
    }


    private void hideFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(hide_fab_1);
        fab1.setClickable(false);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(hide_fab_3);
        fab3.setClickable(false);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       if(FAB_Status){

           hideFAB();
           FAB_Status = false;
       }

        return false;
    }
}
