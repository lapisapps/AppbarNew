package com.example.pentagon.appbar.Fragments;


import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.pentagon.appbar.AudioRecordDialog;
import com.example.pentagon.appbar.CameraUtils;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.DescriptionDialog;
import com.example.pentagon.appbar.Main2Activity;
import com.example.pentagon.appbar.MainActivity;
import com.example.pentagon.appbar.PreviewDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.Utility;
import com.example.pentagon.appbar.VideoPlayDialog;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.pentagon.appbar.Main2Activity.BITMAP_SAMPLE_SIZE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDataViewChild#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDataViewChild extends Fragment implements SurfaceHolder.Callback{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //audio play
    private Chronometer chronometer;
    private ImageView imageViewRecord, imageViewPlay, imageViewStop;
    private SeekBar seekBar;
    private LinearLayout linearLayoutRecorder, linearLayoutPlay,linearlayoutdesc;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private String fileName = null;
    private int lastProgress = 0;
    private Handler mHandler = new Handler();
    private boolean isPlaying = false;

    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    VideoView videoView;
  int viewpagerpos;
    private String mParam1;
    private String mParam2;
    View view;
public DataPreview dataPreview;

    public FragmentDataViewChild() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDataViewChild.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDataViewChild newInstance(String param1, String param2) {
        FragmentDataViewChild fragment = new FragmentDataViewChild();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
public DataPreview getDataPreview(){

        return dataPreview;
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

        dataPreview = (DataPreview) getArguments().getSerializable("data");
        viewpagerpos=getArguments().getInt("position");
        view=inflater.inflate(R.layout.fragment_fragment_data_view, container, false);
        initial();
        return view;
    }
    public void initial(){

        ImageView imageView=view.findViewById(R.id.imageView);
        final TextView desc=view.findViewById(R.id.description);
        desc.setText(dataPreview.getDescr());

desc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DescriptionDialog.viewpos=viewpagerpos;
        DescriptionDialog.dataPreview=dataPreview;
        new DescriptionDialog(getActivity(),FragmentDataViewChild.this,desc.getText().toString(),true);
    }
});


        ImageView vidpreview=view.findViewById(R.id.videopreview);
        linearLayoutRecorder=view.findViewById(R.id.linearLayoutRecorder);
        videoView=view.findViewById(R.id.videoView);



        imageView.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);
        vidpreview.setVisibility(View.GONE);
        linearLayoutRecorder.setVisibility(View.GONE);
        if(Integer.parseInt(dataPreview.getType())== Main2Activity.MEDIA_TYPE_IMAGE){

            imageView.setVisibility(View.VISIBLE);

            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, dataPreview.getPath());

            imageView.setImageBitmap(bitmap);
        }else if(Integer.parseInt(dataPreview.getType())==Main2Activity.MEDIA_TYPE_VIDEO) {
           try{

               setVideoView();

            vidpreview.setImageResource(R.drawable.ic_videopreview_24dp);
            vidpreview.setVisibility(View.VISIBLE);
 videoView.setVisibility(View.VISIBLE);
////               //Uri uri = Uri.parse(dataPreview.getPath());
////            videoView.setVideoURI(Uri.parse(dataPreview.getPath()));
////
////           // videoView.seekTo(10);
            vidpreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Uri uri=Uri.parse(dataPreview.getPath());
//
//                    //Setting MediaController and URI, then starting the videoView
//                    //  videoView.setMediaController(mediaController);
//                    videoView=view.findViewById(R.id.videoView);
//
//                    videoView.setVideoURI(uri);
//                   videoView.start();
                    new VideoPlayDialog(getActivity(),dataPreview,false);
                }
            });
           }catch (Exception e){
               e.printStackTrace();
           }
        }else {
//            vidpreview.setVisibility(View.VISIBLE);
//
//            vidpreview.setImageResource(R.drawable.audiorecorder);
//vidpreview.setOnTouchListener(new View.OnTouchListener() {
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        AudioRecordDialog.dataPreview=dataPreview;
//        new AudioRecordDialog(getActivity(),true);
//        return false;
//    }
//});

            linearLayoutRecorder.setVisibility(View.VISIBLE);
            setAudioPlayView();

        }

    }

    private void setVideoView() {

     //   MediaController mediaController= new MediaController(getContext());
       // mediaController.setAnchorView(videoView);

        //specify the location of media file
//        Uri uri=Uri.parse(dataPreview.getPath());
//
//        //Setting MediaController and URI, then starting the videoView
//      //  videoView.setMediaController(mediaController);
//
//        videoView.setVideoURI(uri);
//
//        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                if(mp.isPlaying())
//                    mp.stop();
//                return false;
//            }
//        });
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.start();
//
//            }
//        });
     //   videoView.start();


//        mSurfaceHolder = videoView.getHolder();
//        mSurfaceHolder.addCallback(this);
    }

    private void setAudioPlayView() {

        chronometer = (Chronometer) view.findViewById(R.id.chronometerTimer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        imageViewRecord = (ImageView) view.findViewById(R.id.imageViewRecord);
        imageViewStop = (ImageView) view.findViewById(R.id.imageViewStop);
        imageViewPlay = (ImageView) view.findViewById(R.id.imageViewPlay);
        linearLayoutPlay = (LinearLayout) view.findViewById(R.id.linearLayoutPlay);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        imageViewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareforRecording();
                startRecording();
            }
        });
        imageViewStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareforStop();
                stopRecording();
            }
        });
        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !isPlaying && fileName != null ){
                    isPlaying = true;
                    startPlaying();
                }else{
                    isPlaying = false;
                    stopPlaying();
                }
            }
        });
        setForView();
    }
    private void prepareforRecording() {
        TransitionManager.beginDelayedTransition(linearLayoutRecorder);
        imageViewRecord.setVisibility(View.GONE);
        imageViewStop.setVisibility(View.VISIBLE);
        linearLayoutPlay.setVisibility(View.GONE);
    }


    private void startRecording() {
        //we use the MediaRecorder class to record
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        /**In the lines below, we create a directory named VoiceRecorderSimplifiedCoding/Audios in the phone storage
         * and the audios are being stored in the Audios folder **/

        ;
        fileName = CameraUtils.getOutputMediaFile(Main2Activity.MEDIA_TYPE_AUDIO).getPath();
        Log.d("filename",fileName);
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastProgress = 0;
        seekBar.setProgress(0);
        stopPlaying();
        //starting the chronometer
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

    }


    private void stopPlaying() {
        try{
            mPlayer.release();
        }catch (Exception e){
            e.printStackTrace();
        }

        mPlayer = null;
        //showing the play button
        imageViewPlay.setImageResource(R.drawable.ic_play);
        chronometer.stop();

    }


    private void prepareforStop() {
        TransitionManager.beginDelayedTransition(linearLayoutRecorder);
        //   imageViewRecord.setVisibility(View.VISIBLE);
        imageViewStop.setVisibility(View.GONE);
        linearLayoutPlay.setVisibility(View.VISIBLE);
    }

    private void stopRecording() {

        try{
            mRecorder.stop();
            mRecorder.release();
        }catch (Exception e){
            e.printStackTrace();
        }
        mRecorder = null;
        //starting the chronometer
        chronometer.stop();
        //  chronometer.setBase(SystemClock.elapsedRealtime());
        //showing the play button
        linearlayoutdesc.setVisibility(View.VISIBLE);





        // main2Activity.previewAudio();
        //Toast.makeText(context, "Recording saved successfully.", Toast.LENGTH_SHORT).show();
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        chronometer.setBase(SystemClock.elapsedRealtime());
        Log.d("instartPlaying",fileName);
        try {
            mPlayer.setDataSource(fileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }
        //making the imageview pause button
        imageViewPlay.setImageResource(R.drawable.ic_pause);

        seekBar.setProgress(lastProgress);
        mPlayer.seekTo(lastProgress);
        seekBar.setMax(mPlayer.getDuration());
        seekUpdation();
        chronometer.start();

        /** once the audio is complete, timer is stopped here**/
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imageViewPlay.setImageResource(R.drawable.ic_play);
                isPlaying = false;
                lastProgress = 0;
                seekBar.setProgress(0);
                seekBar.setMax(mPlayer.getDuration());
                //  seekUpdation();
//    stopPlaying();
                //starting the chronometer
                // chronometer.setBase(SystemClock.elapsedRealtime());
                mPlayer.seekTo(lastProgress);
                chronometer.stop();
            }
        });

        /** moving the track as per the seekBar's position**/
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if( mPlayer!=null && fromUser ){
                    //here the track's progress is being changed as per the progress bar
                    mPlayer.seekTo(progress);
                    //timer is being updated as per the progress of the seekbar
                    chronometer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
                    lastProgress = progress;

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };

    private void seekUpdation() {
        if(mPlayer != null){
            int mCurrentPosition = mPlayer.getCurrentPosition() ;
            seekBar.setProgress(mCurrentPosition);
            lastProgress = mCurrentPosition;
        }
        mHandler.postDelayed(runnable, 100);
    }
    public void setForView(){

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        /**In the lines below, we create a directory named VoiceRecorderSimplifiedCoding/Audios in the phone storage
         * and the audios are being stored in the Audios folder **/

        ;
        fileName=dataPreview.getPath();
        Log.d("filename",fileName);
//    mRecorder.setOutputFile(dataPreview.getPath());
//    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//    try {
//        mRecorder.prepare();
////        mRecorder.start();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
        lastProgress = 0;
        seekBar.setProgress(0);
//    stopPlaying();
        //starting the chronometer
        chronometer.setBase(SystemClock.elapsedRealtime());
        //chronometer.start();
//        linearlayoutdesc.setVisibility(View.VISIBLE);

        linearLayoutPlay.setVisibility(View.VISIBLE);
        imageViewStop.setVisibility(View.GONE);
        linearLayoutPlay.setVisibility(View.VISIBLE);
        imageViewPlay.setImageResource(R.drawable.ic_play);
        imageViewRecord.setVisibility(View.GONE);
        isPlaying=false;
        //  linearLayoutRecorder.setVisibility(View.GONE);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDisplay(mSurfaceHolder);
        try {
            mMediaPlayer.setDataSource(dataPreview.getPath());
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
mMediaPlayer.start();
                }
            });


            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

}
