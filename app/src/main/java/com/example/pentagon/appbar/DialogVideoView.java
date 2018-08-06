package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class DialogVideoView {
Activity mContext;
    public DialogVideoView(Activity mContext1,String url){
        MediaPlayer mediaPlayer;
        SurfaceHolder vidHolder;
        SurfaceView vidSurface;
        final VideoView vidView;
        String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        final ProgressBar progressBar;
        this.mContext=mContext1;
        Dialog dd=new Dialog(mContext);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.videoview);
        dd.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dd.setCancelable(true);

        //   Glide.with(mContext).load(image).into((ImageView) dd.findViewById(R.id.image));
        vidView = (VideoView)dd.findViewById(R.id.myVideo);
        progressBar=(ProgressBar)dd.findViewById(R.id.progressBar);
        // Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            vidAddress= extras.getString("url");
//            //The key argument here must match that used in the other activity
//        }
        //  vidAddress = getIntent().getStringExtra("url");
        vidView.setVideoURI(Uri.parse(url));
        vidView.start();
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading.....");
//        progressDialog.setCancelable(true);
//        progressDialog.show();

        vidView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                MediaController vidControl = new MediaController(mContext);
                vidControl.setAnchorView(vidView);

                vidView.setMediaController(vidControl);
                progressBar.setVisibility(View.GONE);
                //progressDialog.dismiss();
                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START)
                            progressBar.setVisibility(View.VISIBLE);
                        if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END)
                            progressBar.setVisibility(View.GONE);
                        return false;
                    }
                });

            }

        });
        dd.show();
        Window window = dd.getWindow();
        window.setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT, 650);
    }
}


