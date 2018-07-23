package com.example.pentagon.appbar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.pentagon.appbar.Fragments.FragmentDataViewChild;
import com.example.pentagon.appbar.Fragments.FragmentDataViewImage;
import com.example.pentagon.appbar.Fragments.FragmentDataViewVideo;
import com.example.pentagon.appbar.Fragments.PageReport1;
import com.example.pentagon.appbar.Fragments.PageReport2;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.pentagon.appbar.Main2Activity.BITMAP_SAMPLE_SIZE;

public class DescriptionDialog extends Dialog {
    Spinner prjcts;
    ArrayList<DataTag> prjcttags;
    LinearLayout arealayout;
    AutoCompleteTextView suf,functions,system,sequence,parttag,part,elem,details;
    RadioButton rdequip,rdpipe,rdpipesup;
    TextInputLayout sufixinp,systeminp;
    Context context;
 public static    DataPreview dataPreview;
    TextView expand,add;
    LinearLayout tagdetails;
    RecyclerView recyclerViewtag;
    View layout;
    boolean edit;
    EditText description;
public static int viewpos=-1;
    Button save;
    Dialog alertDialog;
    public DescriptionDialog(@NonNull Context mContext, final Fragment fragment,String desc, boolean edit) {
        super(mContext);
        prjcttags = Main2Activity.prjcttags;
        context = mContext;

        android.app.AlertDialog.Builder builder;
        alertDialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.descriptiondialog,
                null);
        description = (EditText) layout.findViewById(R.id.editText);
        description.setText(desc);
        save = (Button) layout.findViewById(R.id.button9);
        if(Utility.savemenu.getTitle().equals("edit")) {
            edit = false;
            description.setFocusable(false);
        }  else
            edit=true;
        if (!edit) {

            save.setText("Close");
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
        } else{

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment.getClass().equals(PageReport1.class)) {

                    PageReport1.description.setText(description.getText());
                }
                else if(fragment.getClass().equals(PageReport2.class)){

                    PageReport2.description.setText(description.getText().toString());
                }

                else if(fragment.getClass().equals(FragmentDataViewChild.class)){
                    int i=0,j=-1;
                    for (DataPreview dd:CreateReport.dataPreviews
                            ) {
                        if(dd.getId().equals(dataPreview.getId())){
j=i;
                            CreateReport.dataPreviews.get(i).setDescr(description.getText().toString());
                            break;
                        }
                        i++;
                    }

                    if(Integer.parseInt(dataPreview.getType())== Main2Activity.MEDIA_TYPE_IMAGE){

                        FragmentDataViewImage.addImage(CreateReport.dataPreviews.get(i),viewpos);
                    }else if(Integer.parseInt(dataPreview.getType())==Main2Activity.MEDIA_TYPE_VIDEO) {

                        FragmentDataViewVideo.addImage(CreateReport.dataPreviews.get(i),viewpos);}
                        else {


                        FragmentDataViewAudio.addImage(CreateReport.dataPreviews.get(i),viewpos);


                    }
                }
               alertDialog.dismiss();
            }
        });
    }
alertDialog.setContentView(layout);








        alertDialog.show();
        description.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(description, InputMethodManager.SHOW_IMPLICIT);
        Window window = alertDialog.getWindow();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
     window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }






}
