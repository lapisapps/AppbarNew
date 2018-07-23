package com.example.pentagon.appbar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTags;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.Fragments.CreateReport;

import com.example.pentagon.appbar.Fragments.FragmentDataViewAudio;
import com.example.pentagon.appbar.Fragments.FragmentDataViewImage;
import com.example.pentagon.appbar.Fragments.FragmentDataViewVideo;
import com.example.pentagon.appbar.Fragments.PageReportAudio;
import com.example.pentagon.appbar.Fragments.PageReportImage;
import com.example.pentagon.appbar.Fragments.PageReportVideo;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.pentagon.appbar.Main2Activity.BITMAP_SAMPLE_SIZE;

public class PreviewDialog extends Dialog {
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
    public PreviewDialog(@NonNull Context mContext,DataPreview dataPreview,boolean edit) {
        super(mContext);
        prjcttags=Main2Activity.prjcttags;
        context=mContext;
        this.dataPreview=dataPreview;
        android.app.AlertDialog.Builder builder;
        alertDialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.dialog_preview,
                null);




        alertDialog.setContentView(layout);

        ImageView imageView=layout.findViewById(R.id.imageView);
        expand=layout.findViewById(R.id.expand);
        add=layout.findViewById(R.id.add);
        description=layout.findViewById(R.id.description);
        tagdetails=layout.findViewById(R.id.tagdetails);
        recyclerViewtag=layout.findViewById(R.id.tags);
        ImageView vidpreview=layout.findViewById(R.id.videopreview);
        final VideoView videoView=layout.findViewById(R.id.videoView);
        this.edit=edit;
        initialize();


        imageView.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);
        vidpreview.setVisibility(View.GONE);

        if(Integer.parseInt(dataPreview.getType())==Main2Activity.MEDIA_TYPE_IMAGE){

            imageView.setVisibility(View.VISIBLE);

            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, dataPreview.getPath());

            imageView.setImageBitmap(bitmap);
        }else if(Integer.parseInt(dataPreview.getType())==Main2Activity.MEDIA_TYPE_VIDEO) {
            vidpreview.setImageResource(R.drawable.ic_videopreview_24dp);
            vidpreview.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoPath(dataPreview.getPath());
            videoView.seekTo(10);
            vidpreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoView.start();
                }
            });
        }else {
            vidpreview.setVisibility(View.VISIBLE);

            vidpreview.setImageResource(R.drawable.audiorecorder);


        }

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

    private void initialize() {
        View navigationView=layout;




        Button save=(Button) navigationView.findViewById(R.id.btnsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPreview.setDescr(description.getText().toString());
                CreateReport.dataPreviews.add(dataPreview);
                CreateReport.dataPreviews.get(CreateReport.dataPreviews.size()-1).setDescr(description.getText().toString());
                if(Integer.parseInt(dataPreview.getType())==Utility.MEDIA_TYPE_AUDIO)
                    FragmentDataViewAudio.addImage(dataPreview,-1);
                else if(Integer.parseInt(dataPreview.getType())==Utility.MEDIA_TYPE_VIDEO)
                    FragmentDataViewVideo.addImage(dataPreview,-1);
                else

                FragmentDataViewImage.addImage(dataPreview,-1);
                alertDialog.dismiss();
            }
        });
//        if(prjcttags.size()>0){
//        setProjectTags();
//        loadTag(prjcttags.get(0));}
        if(edit)
            save.setVisibility(View.VISIBLE);
        else{
            description.setText(dataPreview.getDescr());
            description.setFocusable(false);
            save.setVisibility(View.GONE);}
    }




}