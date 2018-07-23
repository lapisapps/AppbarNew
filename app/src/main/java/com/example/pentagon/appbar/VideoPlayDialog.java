package com.example.pentagon.appbar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.FragmentDataViewAudio;
import com.example.pentagon.appbar.Fragments.FragmentDataViewImage;
import com.example.pentagon.appbar.Fragments.FragmentDataViewVideo;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.pentagon.appbar.Main2Activity.BITMAP_SAMPLE_SIZE;

public class VideoPlayDialog extends Dialog {
    Spinner prjcts;
    ArrayList<DataTag> prjcttags;
    LinearLayout arealayout;
    AutoCompleteTextView suf,functions,system,sequence,parttag,part,elem,details;
    RadioButton rdequip,rdpipe,rdpipesup;
    TextInputLayout sufixinp,systeminp;
    Context context;
    DataPreview dataPreview;
    TextView expand,add;
    LinearLayout tagdetails;
    RecyclerView recyclerViewtag;
    View layout;
    boolean edit;
    EditText description;
    Dialog alertDialog;
    public VideoPlayDialog(@NonNull Context mContext, DataPreview dataPreview, boolean edit) {
        super(mContext);
prjcttags=Main2Activity.prjcttags;
        context=mContext;
        this.dataPreview=dataPreview;
        android.app.AlertDialog.Builder builder;
  alertDialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.dialog_videopreview,
                null);




alertDialog.setContentView(layout);


        final VideoView videoView=layout.findViewById(R.id.videoView);








                videoView.setVisibility(View.VISIBLE);



                MediaController mediaController= new MediaController(mContext);
                mediaController.setAnchorView(videoView);

                videoView.setMediaController(mediaController);
                videoView.setVideoURI(Uri.parse(dataPreview.getPath()));
     //   videoView.seekTo(10);
       videoView.setFocusable(true);
       videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mp) {
               videoView.start();
           }
       });




//        builder = new android.app.AlertDialog.Builder(mContext);
//        builder.setView(layout);
//        alertDialog = builder.create();

//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//
//        // Copy the alert dialog window attributes to new layout parameter instance
//        layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
//
//        // Set the width and height for the layout parameters
//        // This will bet the width and height of alert dialog
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
//        alertDialog.getWindow().setAttributes(layoutParams);
        //  alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }






}
