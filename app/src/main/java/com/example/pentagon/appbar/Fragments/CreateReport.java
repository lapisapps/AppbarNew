package com.example.pentagon.appbar.Fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.CustomPagerAdapter;
import com.example.pentagon.appbar.AdapterClass.VerticalViewPager;
import com.example.pentagon.appbar.AudioRecordDialog;
import com.example.pentagon.appbar.CameraUtils;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingTag;
import com.example.pentagon.appbar.HomeActivity;
import com.example.pentagon.appbar.PageReportDataView;
import com.example.pentagon.appbar.PreviewDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.pentagon.appbar.Utility.MEDIA_TYPE_AUDIO;
import static com.example.pentagon.appbar.Utility.MEDIA_TYPE_IMAGE;
import static com.example.pentagon.appbar.Utility.MEDIA_TYPE_VIDEO;
import static com.example.pentagon.appbar.Utility.fab1;
import static com.example.pentagon.appbar.Utility.fab2;
import static com.example.pentagon.appbar.Utility.fab3;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateReport.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateReport extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static String ARG_PARAM1 = "param1";
    private static String ARG_PARAM2 = "param2";
    public static ArrayList<DataTag> dataDisciplines;
    public static ArrayList<DataTag> dataSystems;


    private  int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private  int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private  int CAMERA_CAPTURE_AUDIO_REQUEST_CODE = 300;
    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;
    private boolean FAB_Status = false;
    private static String imageStoragePath;
   public static VerticalViewPager viewPager;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   public static DataReport loaddata;
    public static ArrayList<DataPreview> dataPreviews;
  public static   CustomPagerAdapter customPagerAdapter;
View view;
    private OnFragmentInteractionListener mListener;
    private FloatingActionButton next,previous;

    public CreateReport() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateReport.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateReport newInstance(String param1, String param2) {
        CreateReport fragment = new CreateReport();
        Bundle args = new Bundle();
        args.putSerializable("details", (Serializable) param1);
       // args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try{
        //    Utility.camera.setVisibility(View.VISIBLE);
            Utility.newreport.setVisibility(View.GONE);
            view=  inflater.inflate(R.layout.fragment_create_report, container, false);
        viewPager = (VerticalViewPager) view.findViewById(R.id.viewpager);
            viewPager.canScrollVertically(1);

        setViewPager();

//setEnable(false);
       loaddata = (DataReport)getArguments().getSerializable("report");
         dataPreviews=new ArrayList<>();
       if(loaddata!=null) {

        Log.e("dfd","fddfd");
           CreateReport.dataPreviews = new SqliteDb(getActivity()).getReportData(CreateReport.loaddata.getId());
          // CreateReport.dataDisciplines = new SqliteDb(getActivity()).getReportDiscipline(CreateReport.loaddata.getId());
          // CreateReport.dataSystems = new SqliteDb(getActivity()).getReportSystem(CreateReport.loaddata.getId());
       }
       else{
           CreateReport.dataPreviews=new ArrayList<>();
           CreateReport.dataDisciplines=new ArrayList<>();
           CreateReport.dataSystems=new ArrayList<>();
       }


        }catch (Exception e){

            e.printStackTrace();
        }
        if(CreateReport.dataPreviews!=null)
        Log.e("dfd","fddfd"+CreateReport.dataPreviews.size());

        Utility.newreport.setVisibility(View.GONE);

setFab();
        next=view.findViewById(R.id.floatingActionButton);
        previous=view.findViewById(R.id.floatingActionButton2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()!=viewPager.getChildCount())
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()!=0)
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1,true);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             position=viewPager.getCurrentItem();
                next.setVisibility(View.VISIBLE);
                previous.setVisibility(View.VISIBLE);

                if(position>=viewPager.getChildCount()-1) {
                    next.setVisibility(View.GONE);

                }

                if(position==0){
                    previous.setVisibility(View.GONE);

                }
                Log.e("position",viewPager.getCurrentItem()+"  "+viewPager.getChildCount());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;

    }

    private void setViewPager() {
        customPagerAdapter=new CustomPagerAdapter(getFragmentManager());
        customPagerAdapter.addFrag(new PageReport1(), "PageReport1");
        customPagerAdapter.addFrag(new PageReport2(), "FragmentSettingTag");
        customPagerAdapter.addFrag(new PageReportTag(), "PageReportTag");
        customPagerAdapter.addFrag(new PageReportArea(), "PageReportarea");
        customPagerAdapter.addFrag(new PageReportSystem(), "PageReportsystem");
        customPagerAdapter.addFrag(new PageReportDiscipline(), "PageReportdispl");

//        FragmentDataViewImage dd= new FragmentDataViewImage();
//        Bundle bb=new Bundle();
//        bb.putInt("type",Utility.MEDIA_TYPE_IMAGE);
        //customPagerAdapter.addFrag(new FragmentDataViewImage(), "PageReportimage");
//      dd= new FragmentDataViewImage();
//     bb=new Bundle();
//        bb.putInt("type",Utility.MEDIA_TYPE_VIDEO);
       // customPagerAdapter.addFrag(new FragmentDataViewVideo(), "PageReportvidoe");
//        dd= new FragmentDataViewImage();
//        bb=new Bundle();
//        bb.putInt("type",Utility.MEDIA_TYPE_AUDIO);
        customPagerAdapter.addFrag(new PageReportDataView(), "PageReportaudio");
        customPagerAdapter.addFrag(new PageReport5(), "PageReport5");
        viewPager.setOffscreenPageLimit(7);
        viewPager.setAdapter(customPagerAdapter);

        viewPager.setPageMargin(12);


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


        //Animations
//        show_fab_1 = AnimationUtils.loadAnimation(getContext(), R.anim.fab1_show);
//        hide_fab_1 = AnimationUtils.loadAnimation(getContext(), R.anim.fab1_hide);
//        show_fab_2 = AnimationUtils.loadAnimation(getContext(), R.anim.fab2_show);
//        hide_fab_2 = AnimationUtils.loadAnimation(getContext(), R.anim.fab2_hide);
//        show_fab_3 = AnimationUtils.loadAnimation(getContext(), R.anim.fab3_show);
//        hide_fab_3 = AnimationUtils.loadAnimation(getContext(), R.anim.fab3_hide);
//
//
//        Utility.camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (FAB_Status == false) {
//                    //Display FAB menu
//                    expandFAB();
//                    FAB_Status = true;
//                } else {
//                    //Close FAB menu
//                    hideFAB();
//                    FAB_Status = false;
//                }
//            }
//        });
//fab1.setVisibility(View.VISIBLE);
//        fab2.setVisibility(View.VISIBLE);
//        fab3.setVisibility(View.VISIBLE);
//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             if (CameraUtils.checkPermissions(getActivity())) {
//                    if(Utility.savemenu.getTitle().equals("edit")){
//
//                    Utility.optionItemSave(getActivity(),0);
//                    }
//                 captureImage();
//                } else {
//                    requestCameraPermission(MEDIA_TYPE_IMAGE);
//                }
//            }
//        });
//
//        fab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (CameraUtils.checkPermissions(getActivity())) {
//
//                    if(Utility.savemenu.getTitle().equals("edit")){
//
//                        Utility.optionItemSave(getActivity(),0);
//                    }
//                    captureVideo();
//                } else {
//                    requestCameraPermission(MEDIA_TYPE_VIDEO);
//                }
//            }
//        });
//
//        fab3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (CameraUtils.checkPermissions(getActivity())) {
//                    if(Utility.savemenu.getTitle().equals("edit")){
//
//                        Utility.optionItemSave(getActivity(),0);
//                    }
//                    captureAudio();
//                } else {
//                    requestCameraPermission(MEDIA_TYPE_VIDEO);
//                }
//            }
//        });

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }



//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
    @Override
    public void onDetach() {
        Utility.optionItemSave(getActivity(),0);
        super.onDetach();

        //Toast.makeText(getActivity(), "onDetach", Toast.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
//    private void previewVideo() {
//        try {
//            // hide video preview
//            //  txtDescription.setVisibility(View.GONE);
//            //  videoPreview.setVisibility(View.GONE);
//
//            //  imageView.setVisibility(View.VISIBLE);
//
//            //   Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
//
//            // imageView.setImageBitmap(bitmap);
////            if(dataPreview==null)
////                dataPreview=new ArrayList<>();
//            DataPreview dd=new DataPreview();
//            dd.setPath(imageStoragePath);
//            dd.setType(MEDIA_TYPE_VIDEO+"");
//         //   CreateReport.dataPreviews.add(dd);
//            new PreviewDialog(getContext(),dd,true);
//
//          //  PageReportVideo.setVideos(getActivity());
//
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Alert dialog to navigate to app settings
//     * to enable necessary permissions
//     */
//    private void showPermissionsAlert() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Permissions required!")
//                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
//                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        CameraUtils.openSettings(getContext());
//                    }
//                })
//                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).show();
//    }
//    private void requestCameraPermission(final int type) {
//        Dexter.withActivity(getActivity())
//                .withPermissions(Manifest.permission.CAMERA,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.RECORD_AUDIO)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {
//
//                            if (type == MEDIA_TYPE_IMAGE) {
//                                // capture picture
//                               captureImage();
//                            } else if (type == MEDIA_TYPE_VIDEO) {
//                                captureVideo();
//                            }
//                            else
//                                captureAudio();
//
//                        } else if (report.isAnyPermissionPermanentlyDenied()) {
//                            showPermissionsAlert();
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).check();
//    }
//
//
//    private void expandFAB() {
//
//        //Floating Action Button 1
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
//        layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
//        layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
//        fab1.setLayoutParams(layoutParams);
//        fab1.startAnimation(show_fab_1);
//        fab1.setClickable(true);
//
//        //Floating Action Button 2
//        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
//        layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.5);
//        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.5);
//        fab2.setLayoutParams(layoutParams2);
//        fab2.startAnimation(show_fab_2);
//        fab2.setClickable(true);
//
//        //Floating Action Button 3
//        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
//        layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.25);
//        layoutParams3.bottomMargin += (int) (fab3.getHeight() * 1.7);
//        fab3.setLayoutParams(layoutParams3);
//        fab3.startAnimation(show_fab_3);
//        fab3.setClickable(true);
//    }
//
//
//    private void hideFAB() {
//
//        //Floating Action Button 1
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
//        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
//        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
//        fab1.setLayoutParams(layoutParams);
//        fab1.startAnimation(hide_fab_1);
//        fab1.setClickable(false);
//
//        //Floating Action Button 2
//        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
//        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.5);
//        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
//        fab2.setLayoutParams(layoutParams2);
//        fab2.startAnimation(hide_fab_2);
//        fab2.setClickable(false);
//
//        //Floating Action Button 3
//        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
//        layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.25);
//        layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
//        fab3.setLayoutParams(layoutParams3);
//        fab3.startAnimation(hide_fab_3);
//        fab3.setClickable(false);
//    }
//
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
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        InputStream stream = null;
//
//
//        // if the result is capturing Image
//        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // Refreshing the gallery
//                CameraUtils.refreshGallery(getActivity(), imageStoragePath);
//
//                // successfully captured the image
//                // display it in image view
//                previewCapturedImage();
//            } else if (resultCode == RESULT_CANCELED) {
//                // user cancelled Image capture
//                Toast.makeText(getActivity(),
//                        "User cancelled image capture", Toast.LENGTH_SHORT)
//                        .show();
//            } else {
//                // failed to capture image
//                Toast.makeText(getActivity(),
//                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // Refreshing the gallery
//                CameraUtils.refreshGallery(getActivity(), imageStoragePath);
//
//                // video successfully recorded
//                // preview the recorded video
//                previewVideo();
//            } else if (resultCode == RESULT_CANCELED) {
//                // user cancelled recording
//                Toast.makeText(getActivity(),
//                        "User cancelled video recording", Toast.LENGTH_SHORT)
//                        .show();
//            } else {
//                // failed to record video
//                Toast.makeText(getActivity(),
//                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        } else if (requestCode == CAMERA_CAPTURE_AUDIO_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // Refreshing the gallery
//                CameraUtils.refreshGallery(getActivity(), imageStoragePath);
//
//                // video successfully recorded
//                // preview the recorded video
//                previewAudio();
//            } else if (resultCode == RESULT_CANCELED) {
//                // user cancelled recording
//                Toast.makeText(getActivity(),
//                        "User cancelled video recording", Toast.LENGTH_SHORT)
//                        .show();
//            } else {
//                // failed to record video
//                Toast.makeText(getActivity(),
//                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//
//    }
//
//    public  void previewAudio() {
//        try {
//            // hide video preview
//            //  txtDescription.setVisibility(View.GONE);
//            //  videoPreview.setVisibility(View.GONE);
//
//            //  imageView.setVisibility(View.VISIBLE);
//
//            //   Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
//
//            // imageView.setImageBitmap(bitmap);
////            if(dataPreview==null)
////                dataPreview=new ArrayList<>();
//            DataPreview dd=new DataPreview();
//            dd.setPath(imageStoragePath);
//            dd.setType(MEDIA_TYPE_AUDIO+"");
//           new PreviewDialog(getContext(),dd,true);
//           // CreateReport.dataPreviews.add(dd);
//           // PageReportAudio.setAudios(getActivity());
//
//
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void captureAudio() {
////        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
////
////        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_AUDIO);
////        if (file != null) {
////            imageStoragePath = file.getAbsolutePath();
////        }
////
////        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);
////
////        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
////
////        // start the image capture Intent
////        startActivityForResult(intent, CAMERA_CAPTURE_AUDIO_REQUEST_CODE);
//        viewPager.setCurrentItem(8, true);
//        audioDialog();
//    }
//
//    private void audioDialog() {
//        AudioRecordDialog.main2Activity=CreateReport.this;
//        new AudioRecordDialog(getContext(),false);
//    }
//
//    private void captureImage() {
//        viewPager.setCurrentItem(6, true);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
//        if (file != null) {
//            imageStoragePath = file.getAbsolutePath();
//        }
//
//        Uri fileUri = CameraUtils.getOutputMediaFileUri(getActivity(), file);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//
//        // start the image capture Intent
//        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
//    }
//    private void captureVideo() {
//        viewPager.setCurrentItem(7, true);
//        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//
//        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_VIDEO);
//        if (file != null) {
//            imageStoragePath = file.getAbsolutePath();
//        }
//
//        Uri fileUri = CameraUtils.getOutputMediaFileUri(getActivity(), file);
//
//        // set video quality
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
//
//        // start the video capture Intent
//        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
//    }
//    public void previewCapturedImage() {
//        try {
//            // hide video preview
//            //  txtDescription.setVisibility(View.GONE);
//            //  videoPreview.setVisibility(View.GONE);
//
//            //  imageView.setVisibility(View.VISIBLE);
//
//            //   Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
//
//            // imageView.setImageBitmap(bitmap);
//
//
//            DataPreview dd=new DataPreview();
//            dd.setPath(imageStoragePath);
//            dd.setType(MEDIA_TYPE_IMAGE+"");
//            //CreateReport.dataPreviews.add(dd);
//            new PreviewDialog(getContext(),dd,true);
//
//           // PageReportImage.setImages(getActivity());
//
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void onResume() {

        super.onResume();
        ActionBar actionBar = ((HomeActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Editor");

        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setIcon(null);

    }
}
