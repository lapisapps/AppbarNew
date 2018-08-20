package com.example.pentagon.appbar;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.Fragments.CreateReport;

import com.example.pentagon.appbar.Fragments.FragmentDataViewAudio;
import com.example.pentagon.appbar.Fragments.PageReportAudio;

import java.io.File;
import java.io.IOException;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.pentagon.appbar.Utility.MEDIA_TYPE_AUDIO;
import static com.example.pentagon.appbar.Utility.MEDIA_TYPE_IMAGE;

public class AudioRecordDialog extends Dialog implements View.OnClickListener {
    private final Button close;
    private int RECORD_AUDIO_REQUEST_CODE =123 ;
    public static PageReportDataView main2Activity;
    private Toolbar toolbar;
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
    Context context;
    EditText desc;
    Button btnsave;
    android.app.AlertDialog alertDialog;
    public static DataPreview dataPreview;
    public AudioRecordDialog(@NonNull final Context mContext,Boolean forview) {
        super(mContext);
        context=mContext;
        android.app.AlertDialog.Builder builder;



        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_audiorec,
                null);
        linearlayoutdesc=(LinearLayout)layout.findViewById(R.id.linearLayout) ;
        linearlayoutdesc.setVisibility(View.GONE);
        btnsave=(Button)layout.findViewById(R.id.done) ;
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPreview dd=new DataPreview();
                dd.setPath(fileName);
                dd.setType(MEDIA_TYPE_AUDIO+"");
                dd.setDescr(desc.getText().toString());
                dd.setSelected(true);
                CreateReport.dataPreviews.add(dd);
                PageReportDataView.addAudio(dd,-1);

                Toast.makeText(mContext, "Audio Record saved", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        desc=(EditText) layout.findViewById(R.id.description);
        linearLayoutRecorder = (LinearLayout) layout.findViewById(R.id.linearLayoutRecorder);
        chronometer = (Chronometer) layout.findViewById(R.id.chronometerTimer);
       chronometer.setBase(SystemClock.elapsedRealtime());
        imageViewRecord = (ImageView) layout.findViewById(R.id.imageViewRecord);
        imageViewStop = (ImageView) layout.findViewById(R.id.imageViewStop);
        imageViewPlay = (ImageView) layout.findViewById(R.id.imageViewPlay);
        linearLayoutPlay = (LinearLayout) layout.findViewById(R.id.linearLayoutPlay);
        seekBar = (SeekBar) layout.findViewById(R.id.seekBar);
        imageViewRecord.setOnClickListener(this);
        imageViewStop.setOnClickListener(this);
        imageViewPlay.setOnClickListener(this);
        builder = new android.app.AlertDialog.Builder(mContext);
        builder.setView(layout);
        alertDialog = builder.create();
        if(forview){


            setForView();
        }

        close=layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        //  alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();
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
    linearlayoutdesc.setVisibility(View.VISIBLE);
    desc.setText(dataPreview.getDescr());
    desc.setFocusable(false);
    btnsave.setVisibility(View.GONE);
    linearLayoutPlay.setVisibility(View.VISIBLE);
    imageViewStop.setVisibility(View.GONE);
    linearLayoutPlay.setVisibility(View.VISIBLE);
    imageViewPlay.setImageResource(R.drawable.ic_play);
    imageViewRecord.setVisibility(View.GONE);
    isPlaying=false;
      //  linearLayoutRecorder.setVisibility(View.GONE);
}


    @Override
    public void onClick(View view) {
        if( view == imageViewRecord ){
            prepareforRecording();
            startRecording();
        }else if( view == imageViewStop ){
            prepareforStop();
            stopRecording();
        }else if( view == imageViewPlay ){
            if( !isPlaying && fileName != null ){
                isPlaying = true;
                startPlaying();
            }else{
                isPlaying = false;
                stopPlaying();
            }
        }

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

}
