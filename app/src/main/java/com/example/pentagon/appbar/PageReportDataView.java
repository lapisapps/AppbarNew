package com.example.pentagon.appbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.CustomPagerAdapter;
import com.example.pentagon.appbar.AdapterClass.HorizontalViewPager;
import com.example.pentagon.appbar.AdapterClass.MyFragmentStatePagerAdapter;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterCameraPreviews;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.FragmentDataViewAudio;
import com.example.pentagon.appbar.Fragments.FragmentDataViewImage;
import com.example.pentagon.appbar.Fragments.FragmentDataViewVideo;
import com.example.pentagon.appbar.Fragments.PageReportAudio;
import com.example.pentagon.appbar.Fragments.PageReportImage;
import com.example.pentagon.appbar.Fragments.PageReportVideo;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingArea;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingDiscipline;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingSystem;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingTag;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.pentagon.appbar.Fragments.CreateReport.dataPreviews;
import static com.example.pentagon.appbar.Utility.MEDIA_TYPE_AUDIO;
import static com.example.pentagon.appbar.Utility.MEDIA_TYPE_IMAGE;
import static com.example.pentagon.appbar.Utility.MEDIA_TYPE_VIDEO;
import static com.example.pentagon.appbar.Utility.fab1;
import static com.example.pentagon.appbar.Utility.fab2;
import static com.example.pentagon.appbar.Utility.fab3;

/**
 * A fragment representing a list of Items.
 * <p/>

 * interface.
 */
public class PageReportDataView extends Fragment  {
    private  int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private  int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private  int CAMERA_CAPTURE_AUDIO_REQUEST_CODE = 300;
    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;
   static TextView status;
    static RadioButton radioimage,radiovideo,radioaudio;
    private boolean FAB_Status = false;
    private static String imageStoragePath;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
View view;
    public static   MyFragmentStatePagerAdapter customPagerAdapter;
    public static   ArrayList<DataPreview>   ddimage,ddaudio,ddvideo;
    public  static FragmentManager fragmentManager;
 static ViewPager viewPagerimage,viewPagervideo,viewPageraudio;
    private static TabLayout tabLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */



            public PageReportDataView() {
                // Required empty public constructor
            }

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                view= inflater.inflate(R.layout.fragment_reportdataview, container, false);

             //   toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//               setSupportActionBar(toolbar);
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       tabLayout = (TabLayout) view.findViewById(R.id.tabindicatorr);
                viewPagerimage = (HorizontalViewPager) view.findViewById(R.id.viewpagerimage);
                viewPagervideo = (HorizontalViewPager) view.findViewById(R.id.viewpagervideo);
                viewPageraudio = (HorizontalViewPager) view.findViewById(R.id.viewpageraudio);
               setViewPager();
                setViewPageraudio();
            setViewPagervideo();

//             tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//             tabLayout.setupWithViewPager(viewPager);

                Utility.fab1 = (FloatingActionButton) view.findViewById(R.id.fab_1);
                Utility.fab2 = (FloatingActionButton) view.findViewById(R.id.fab_2);
                Utility.fab3 = (FloatingActionButton) view.findViewById(R.id.fab_3);
                status=(TextView)view.findViewById(R.id.textView14) ;
                status.setVisibility(View.GONE);
initilize();
radioimage.setChecked(true);
//viewPager.setOnTouchListener(new View.OnTouchListener() {
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//      return gestureDetector.onTouchEvent(event);
//    }
//});
                return view;
            }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//        return gestureDetector.onTouchEvent(event);
//    }
    private void initilize() {

        fab1.setVisibility(View.VISIBLE);
        fab2.setVisibility(View.VISIBLE);
        fab3.setVisibility(View.VISIBLE);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CameraUtils.checkPermissions(getActivity())) {
                    if(Utility.savemenu.getTitle().equals("edit")){

                        Utility.optionItemSave(getActivity(),0);
                    }
                    captureImage();
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE);
                }
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CameraUtils.checkPermissions(getActivity())) {

                    if(Utility.savemenu.getTitle().equals("edit")){

                        Utility.optionItemSave(getActivity(),0);
                    }
                    captureVideo();
                } else {
                    requestCameraPermission(MEDIA_TYPE_VIDEO);
                }
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CameraUtils.checkPermissions(getActivity())) {
                    if(Utility.savemenu.getTitle().equals("edit")){

                        Utility.optionItemSave(getActivity(),0);
                    }
                    captureAudio();
                } else {
                    requestCameraPermission(MEDIA_TYPE_VIDEO);
                }
            }
        });

        radioimage=(RadioButton) view.findViewById(R.id.radioimage);
        radiovideo=(RadioButton) view.findViewById(R.id.radiovideo);
        radioaudio=(RadioButton) view.findViewById(R.id.radioaudio);
        radioimage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.e("ddd",viewPagerimage.getChildCount()+"");
                    status.setVisibility(View.GONE);
           viewPagerimage.setVisibility(View.VISIBLE);
                    viewPagervideo.setVisibility(View.GONE);
                    viewPageraudio.setVisibility(View.GONE);
                    if(ddimage.size()==0){
                        status.setVisibility(View.VISIBLE);
                        status.setText("No Images");

                    }
                    if(ddimage.size()<=1){
                        tabLayout.setupWithViewPager(null);

                    }
                    else {
                    tabLayout.setupWithViewPager(viewPagerimage, true);
                }

                }

            }
        });
        radiovideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPagerimage.setVisibility(View.GONE);
                    status.setVisibility(View.GONE);
                    viewPagervideo.setVisibility(View.VISIBLE);
                    viewPageraudio.setVisibility(View.GONE);
                    if(ddvideo.size()==0){
                        status.setVisibility(View.VISIBLE);
                        status.setText("No Videos");

                    }
                    if(ddvideo.size()<=1){
                        tabLayout.setupWithViewPager(null);

                    }
                    else {

                        tabLayout.setupWithViewPager(viewPagervideo, true);
                    }
                }

            }
        });
        radioaudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPagerimage.setVisibility(View.GONE);
                    status.setVisibility(View.GONE);
                    viewPagervideo.setVisibility(View.GONE);
                    viewPageraudio.setVisibility(View.VISIBLE);
                    if(ddaudio.size()==0){
                        status.setVisibility(View.VISIBLE);
                        status.setText("No Audios");

                    }
                    if(ddaudio.size()<=1){
                        tabLayout.setupWithViewPager(null);
                    }
                    else {

                        tabLayout.setupWithViewPager(viewPageraudio, true);
                    }
                }

            }
        });
    }

//    private void setRecyclerviewImage() {
//
//        ddimage=new ArrayList<>();
//        for(int j=0;j<dataPreviews.size();j++){
//
//            if(Integer.parseInt(dataPreviews.get(j).getType())==(Utility.MEDIA_TYPE_IMAGE)){
//                ddimage.add(dataPreviews.get(j));
//
//            }
//        }
//        if(ddimage.size()>0)
//            noimage.setVisibility(View.GONE);
//        images = new RecyclerViewAdapterCameraPreviews(context,ddimage);
//        recyimages.setVisibility(View.VISIBLE);
//
//        recyimages.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
//        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyimages.setNestedScrollingEnabled(true);
//
//        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
//        recyimages.setLayoutManager(mLayoutManager);
//        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyimages.setItemAnimator(new DefaultItemAnimator());
//        recyimages.setAdapter(images);
//
//
//
//
//        recyimages.addOnItemTouchListener(new RecyclerItemClickListener(context, recyimages, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                new PreviewDialog(context,ddimage.get(position),false);
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));
//        images.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                if(ddimage.size()>0){
//                    noimage.setVisibility(View.GONE);
//                }
//                else {
//
//                    noimage.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setupViewPagerImage(ViewPager viewPager) {
        CustomPagerAdapter adapter = new CustomPagerAdapter(getChildFragmentManager());
       // adapter.addFrag(new FragmentSettingsProjects(), "Project");
        adapter.addFrag(new FragmentDataViewImage(), "Image");
//        adapter.addFrag(new FragmentDataViewVideo(), "Video");
//      adapter.addFrag(new FragmentDataViewAudio(), "Audio");

viewPager.setOffscreenPageLimit(3);

        viewPager.setAdapter(adapter);

    }
    private void setupViewPagerAudio(ViewPager viewPager) {
        CustomPagerAdapter adapter = new CustomPagerAdapter(getChildFragmentManager());
        // adapter.addFrag(new FragmentSettingsProjects(), "Project");
        adapter.addFrag(new FragmentDataViewAudio(), "Audio");
//        adapter.addFrag(new FragmentDataViewVideo(), "Video");
//      adapter.addFrag(new FragmentDataViewAudio(), "Audio");

        viewPager.setOffscreenPageLimit(3);

        viewPager.setAdapter(adapter);

    }
    private void setupViewPagerVideo(ViewPager viewPager) {
        CustomPagerAdapter adapter = new CustomPagerAdapter(getChildFragmentManager());
        // adapter.addFrag(new FragmentSettingsProjects(), "Project");
        adapter.addFrag(new FragmentDataViewVideo(), "Video");
//        adapter.addFrag(new FragmentDataViewVideo(), "Video");
//      adapter.addFrag(new FragmentDataViewAudio(), "Audio");

        viewPager.setOffscreenPageLimit(3);

        viewPager.setAdapter(adapter);

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




    private void previewVideo() {
        try {
            // hide video preview
            //  txtDescription.setVisibility(View.GONE);
            //  videoPreview.setVisibility(View.GONE);

            //  imageView.setVisibility(View.VISIBLE);

            //   Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);

            // imageView.setImageBitmap(bitmap);
//            if(dataPreview==null)
//                dataPreview=new ArrayList<>();
            DataPreview dd=new DataPreview();
            dd.setPath(imageStoragePath);
            dd.setType(MEDIA_TYPE_VIDEO+"");
//            dd.setSelected(true);
//
//               CreateReport.dataPreviews.add(dd);
//            PageReportDataView.addVideo(dd,-1);
         new PreviewDialog(getContext(),dd,true);

            //  PageReportVideo.setVideos(getActivity());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Alert dialog to navigate to app settings
     * to enable necessary permissions
     */
    private void showPermissionsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(getContext());
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    private void requestCameraPermission(final int type) {
        Dexter.withActivity(getActivity())
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

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        if(FAB_Status){
//
//            hideFAB();
//            FAB_Status = false;
//        }
//
//        return false;
//    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;


        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getActivity(), imageStoragePath);

                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getActivity(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getActivity(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getActivity(), imageStoragePath);

                // video successfully recorded
                // preview the recorded video
                previewVideo();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getActivity(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getActivity(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_AUDIO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getActivity(), imageStoragePath);

                // video successfully recorded
                // preview the recorded video
                previewAudio();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getActivity(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getActivity(),
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
//            if(dataPreview==null)
//                dataPreview=new ArrayList<>();
            DataPreview dd=new DataPreview();
            dd.setPath(imageStoragePath);
            dd.setType(MEDIA_TYPE_AUDIO+"");
            new PreviewDialog(getContext(),dd,true);
            // CreateReport.dataPreviews.add(dd);
            // PageReportAudio.setAudios(getActivity());


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
        //viewPager.setCurrentItem(8, true);
        audioDialog();
    }

    private void audioDialog() {
        AudioRecordDialog.main2Activity=PageReportDataView.this;
        new AudioRecordDialog(getContext(),false);
    }

    private void captureImage() {
      //  viewPager.setCurrentItem(6, true);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getActivity(), file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }
    private void captureVideo() {
    //    viewPager.setCurrentItem(7, true);
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_VIDEO);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getActivity(), file);

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


            DataPreview dd=new DataPreview();
            dd.setPath(imageStoragePath);
            dd.setType(MEDIA_TYPE_IMAGE+"");
//dd.setSelected(true);
//            CreateReport.dataPreviews.add(dd);
//            PageReportDataView.addImage(dd,-1);
            //CreateReport.dataPreviews.add(dd);
         new PreviewDialog(getContext(),dd,true);

            // PageReportImage.setImages(getActivity());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    private void setViewPagervideo() {
        ddvideo=new ArrayList<>();

        for(int j=0;j<dataPreviews.size();j++){

            if(Integer.parseInt(dataPreviews.get(j).getType())==(Utility.MEDIA_TYPE_VIDEO)){
                ddvideo.add(dataPreviews.get(j));
//                FragmentDataViewChild ff=new FragmentDataViewChild();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data",(Serializable) dataPreviews.get(j));
//                ff.setArguments(bundle);
//                customPagerAdapter.addFrag(ff, "child"+type+j);
            }
        }

        fragmentManager=getChildFragmentManager();
        customPagerAdapter=new MyFragmentStatePagerAdapter(fragmentManager,ddvideo);
//        customPagerAdapter.addFrag(new PageReport1(), "PageReport1");
//        customPagerAdapter.addFrag(new FragmentSettingTag(), "FragmentSettingTag");
//        customPagerAdapter.addFrag(new PageReportTag(), "PageReportTag");
//        customPagerAdapter.addFrag(new PageReportArea(), "PageReportarea");
//        customPagerAdapter.addFrag(new PageReportSystem(), "PageReportsystem");
//        customPagerAdapter.addFrag(new PageReportDiscipline(), "PageReportdispl");
//        customPagerAdapter.addFrag(new PageReportImage(), "PageReportimage");
//        customPagerAdapter.addFrag(new PageReportVideo(), "PageReportvidoe");
//        customPagerAdapter.addFrag(new PageReportAudio(), "PageReportaudio");
//        customPagerAdapter.addFrag(new PageReport5(), "PageReport5");
        viewPagervideo.setOffscreenPageLimit(10);
        viewPagervideo.setAdapter(customPagerAdapter);

        viewPagervideo.setPageMargin(12);


    }
    private void setViewPageraudio() {
        ddaudio=new ArrayList<>();

        for(int j=0;j<dataPreviews.size();j++){

            if(Integer.parseInt(dataPreviews.get(j).getType())==(Utility.MEDIA_TYPE_AUDIO)){
                ddaudio.add(dataPreviews.get(j));
//                FragmentDataViewChild ff=new FragmentDataViewChild();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data",(Serializable) dataPreviews.get(j));
//                ff.setArguments(bundle);
//                customPagerAdapter.addFrag(ff, "child"+type+j);
            }
        }

        fragmentManager=getChildFragmentManager();
        customPagerAdapter=new MyFragmentStatePagerAdapter(fragmentManager,ddaudio);
//        customPagerAdapter.addFrag(new PageReport1(), "PageReport1");
//        customPagerAdapter.addFrag(new FragmentSettingTag(), "FragmentSettingTag");
//        customPagerAdapter.addFrag(new PageReportTag(), "PageReportTag");
//        customPagerAdapter.addFrag(new PageReportArea(), "PageReportarea");
//        customPagerAdapter.addFrag(new PageReportSystem(), "PageReportsystem");
//        customPagerAdapter.addFrag(new PageReportDiscipline(), "PageReportdispl");
//        customPagerAdapter.addFrag(new PageReportImage(), "PageReportimage");
//        customPagerAdapter.addFrag(new PageReportVideo(), "PageReportvidoe");
//        customPagerAdapter.addFrag(new PageReportAudio(), "PageReportaudio");
//        customPagerAdapter.addFrag(new PageReport5(), "PageReport5");
        viewPageraudio.setOffscreenPageLimit(10);
        viewPageraudio.setAdapter(customPagerAdapter);

        viewPageraudio.setPageMargin(12);

    }
    private void setViewPager() {


        ddimage = new ArrayList<>();

        for (int j = 0; j < dataPreviews.size(); j++) {

            if (Integer.parseInt(dataPreviews.get(j).getType()) == (Utility.MEDIA_TYPE_IMAGE)) {
                ddimage.add(dataPreviews.get(j));
//                FragmentDataViewChild ff=new FragmentDataViewChild();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data",(Serializable) dataPreviews.get(j));
//                ff.setArguments(bundle);
//                customPagerAdapter.addFrag(ff, "child"+type+j);
            }
        }

        fragmentManager = getChildFragmentManager();
        customPagerAdapter = new MyFragmentStatePagerAdapter(fragmentManager, ddimage);
//        customPagerAdapter.addFrag(new PageReport1(), "PageReport1");
//        customPagerAdapter.addFrag(new FragmentSettingTag(), "FragmentSettingTag");
//        customPagerAdapter.addFrag(new PageReportTag(), "PageReportTag");
//        customPagerAdapter.addFrag(new PageReportArea(), "PageReportarea");
//        customPagerAdapter.addFrag(new PageReportSystem(), "PageReportsystem");
//        customPagerAdapter.addFrag(new PageReportDiscipline(), "PageReportdispl");
//        customPagerAdapter.addFrag(new PageReportImage(), "PageReportimage");
//        customPagerAdapter.addFrag(new PageReportVideo(), "PageReportvidoe");
//        customPagerAdapter.addFrag(new PageReportAudio(), "PageReportaudio");
//        customPagerAdapter.addFrag(new PageReport5(), "PageReport5");
        viewPagerimage.setOffscreenPageLimit(10);
        viewPagerimage.setAdapter(customPagerAdapter);

        viewPagerimage.setPageMargin(12);

//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent motionEvent) {
//
//
//                return true;
//
//            }
//        });
        //  viewPager.canScrollVertically(1);
    }


    public static void addImage(DataPreview dd,int n) {

//        FragmentDataViewChild ff=new FragmentDataViewChild();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("data",(Serializable) dd);
//        ff.setArguments(bundle);

        ddimage = new ArrayList<>();
        status.setVisibility(View.GONE);
        for (int j = 0; j < dataPreviews.size(); j++) {

            if (Integer.parseInt(dataPreviews.get(j).getType()) == (Utility.MEDIA_TYPE_IMAGE)) {
                ddimage.add(dataPreviews.get(j));
//                FragmentDataViewChild ff=new FragmentDataViewChild();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data",(Serializable) dataPreviews.get(j));
//                ff.setArguments(bundle);
//                customPagerAdapter.addFrag(ff, "child"+type+j);
            }
        }
        if(n>=0) {
            Log.e("description", dd.getDescr() + "");
//            ddimage.remove(n);
//            ddimage.add(n, dd);
        }
        else {
          //  ddimage.add(dd);
            n=ddimage.size()-1;
        }
            customPagerAdapter = new MyFragmentStatePagerAdapter(fragmentManager, ddimage);
//        customPagerAdapter.addFrag(new PageReport1(), "PageReport1");
//        customPagerAdapter.addFrag(new FragmentSettingTag(), "FragmentSettingTag");
//        customPagerAdapter.addFrag(new PageReportTag(), "PageReportTag");
//        customPagerAdapter.addFrag(new PageReportArea(), "PageReportarea");
//        customPagerAdapter.addFrag(new PageReportSystem(), "PageReportsystem");
//        customPagerAdapter.addFrag(new PageReportDiscipline(), "PageReportdispl");
//        customPagerAdapter.addFrag(new PageReportImage(), "PageReportimage");
//        customPagerAdapter.addFrag(new PageReportVideo(), "PageReportvidoe");
//        customPagerAdapter.addFrag(new PageReportAudio(), "PageReportaudio");
//        customPagerAdapter.addFrag(new PageReport5(), "PageReport5");
            viewPagerimage.setOffscreenPageLimit(10);
            viewPagerimage.setAdapter(customPagerAdapter);
            viewPagerimage.setCurrentItem(0,true);
            viewPagerimage.setPageMargin(12);
            if(radioimage.isChecked()&&ddimage.size()>1){
                tabLayout.setupWithViewPager(viewPagerimage, true);
            }
        radioimage.setChecked(true);

       // viewPagerimage.setCurrentItem(ddimage.size()-1,true);
    }
    public static void addAudio(DataPreview dd,int n) {

//        FragmentDataViewChild ff=new FragmentDataViewChild();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("data",(Serializable) dd);
//        ff.setArguments(bundle);
        ddaudio = new ArrayList<>();
        status.setVisibility(View.GONE);
        for (int j = 0; j < dataPreviews.size(); j++) {

            if (Integer.parseInt(dataPreviews.get(j).getType()) == (Utility.MEDIA_TYPE_AUDIO)) {
                ddaudio.add(dataPreviews.get(j));
//                FragmentDataViewChild ff=new FragmentDataViewChild();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data",(Serializable) dataPreviews.get(j));
//                ff.setArguments(bundle);
//                customPagerAdapter.addFrag(ff, "child"+type+j);
            }
        }
        if(n>=0) {
            Log.e("description", dd.getDescr() + "");
          //  ddaudio.remove(n);
           // ddaudio.add(n, dd);
        }
        else {
            //ddaudio.add(dd);
            n=ddaudio.size()-1;
        }
        customPagerAdapter = new MyFragmentStatePagerAdapter(fragmentManager, ddaudio);
//        customPagerAdapter.addFrag(new PageReport1(), "PageReport1");
//        customPagerAdapter.addFrag(new FragmentSettingTag(), "FragmentSettingTag");
//        customPagerAdapter.addFrag(new PageReportTag(), "PageReportTag");
//        customPagerAdapter.addFrag(new PageReportArea(), "PageReportarea");
//        customPagerAdapter.addFrag(new PageReportSystem(), "PageReportsystem");
//        customPagerAdapter.addFrag(new PageReportDiscipline(), "PageReportdispl");
//        customPagerAdapter.addFrag(new PageReportImage(), "PageReportimage");
//        customPagerAdapter.addFrag(new PageReportVideo(), "PageReportvidoe");
//        customPagerAdapter.addFrag(new PageReportAudio(), "PageReportaudio");
//        customPagerAdapter.addFrag(new PageReport5(), "PageReport5");
        viewPageraudio.setOffscreenPageLimit(10);
        viewPageraudio.setAdapter(customPagerAdapter);
        viewPageraudio.setCurrentItem(0,true);
        viewPageraudio.setPageMargin(12);
        if(radioaudio.isChecked()&&ddaudio.size()>1){
            tabLayout.setupWithViewPager(viewPageraudio, true);
        }
        radioaudio.setChecked(true);

    }
    public static void addVideo(DataPreview dd,int n) {

//        FragmentDataViewChild ff=new FragmentDataViewChild();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("data",(Serializable) dd);
//        ff.setArguments(bundle);
        ddvideo = new ArrayList<>();
status.setVisibility(View.GONE);
        for (int j = 0; j < dataPreviews.size(); j++) {

            if (Integer.parseInt(dataPreviews.get(j).getType()) == (Utility.MEDIA_TYPE_VIDEO)) {
                ddvideo.add(dataPreviews.get(j));
//                FragmentDataViewChild ff=new FragmentDataViewChild();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data",(Serializable) dataPreviews.get(j));
//                ff.setArguments(bundle);
//                customPagerAdapter.addFrag(ff, "child"+type+j);
            }
        }
        if(n>=0) {
           // Log.e("description", dd.getDescr() + "");
           // ddvideo.remove(n);
          //  ddvideo.add(n, dd);
        }
        else {
            //ddvideo.add(dd);
            n=ddvideo.size()-1;
        }
        customPagerAdapter = new MyFragmentStatePagerAdapter(fragmentManager, ddvideo);
//        customPagerAdapter.addFrag(new PageReport1(), "PageReport1");
//        customPagerAdapter.addFrag(new FragmentSettingTag(), "FragmentSettingTag");
//        customPagerAdapter.addFrag(new PageReportTag(), "PageReportTag");
//        customPagerAdapter.addFrag(new PageReportArea(), "PageReportarea");
//        customPagerAdapter.addFrag(new PageReportSystem(), "PageReportsystem");
//        customPagerAdapter.addFrag(new PageReportDiscipline(), "PageReportdispl");
//        customPagerAdapter.addFrag(new PageReportImage(), "PageReportimage");
//        customPagerAdapter.addFrag(new PageReportVideo(), "PageReportvidoe");
//        customPagerAdapter.addFrag(new PageReportAudio(), "PageReportaudio");
//        customPagerAdapter.addFrag(new PageReport5(), "PageReport5");
        viewPagervideo.setOffscreenPageLimit(10);
        viewPagervideo.setAdapter(customPagerAdapter);
        viewPagervideo.setCurrentItem(0,true);
        viewPagervideo.setPageMargin(12);
        if(radiovideo.isChecked()){
            if(ddvideo.size()>1)
            tabLayout.setupWithViewPager(viewPagervideo, true);
        }
        radiovideo.setChecked(true);

    }
    @Override
    public void onResume() {

        super.onResume();
        ActionBar actionBar = ((HomeActivity)getActivity()).getSupportActionBar();
    //    actionBar.setTitle("Settings");
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);

    }

}


