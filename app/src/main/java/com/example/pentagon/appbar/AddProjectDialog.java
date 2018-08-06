package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.PageReport2;
import com.example.pentagon.appbar.Fragments.SettingFragment1;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingTag;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AddProjectDialog extends Dialog {
    Activity context;
    public  RecyclerView recyclerView,recyclerViewtag;
    public DataTag selectedtag;
    public static PrjctData prjctData;
    public  ArrayList<DataTag> dataTags;
    DataPreview dataPreview;
    LinearLayout arealayout;
    AutoCompleteTextView Prjctname,desc,prjctid;
    Button addprjct;
    RadioButton rdex,rdnew;
ImageButton nextname,nextid,nextdesc;
    TextInputLayout sufixinp,systeminp;
    android.app.AlertDialog.Builder builder;
    android.app.AlertDialog alertDialog;
    RelativeLayout laydescmore;
    LinearLayout layviewless;
    EditText descexpanded;
    ImageButton btnviewless;
int type;
    public AddProjectDialog(@NonNull Activity mContext,int n) {
        super(mContext);

        context=mContext;
        this.dataPreview=dataPreview;
type=n;

        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_addprjct,
                null);

        setView(layout);
        builder = new android.app.AlertDialog.Builder(mContext);
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
         alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();
    }

    private void setView(View layout) {
        layviewless=layout.findViewById(R.id.viewless);
       laydescmore=layout.findViewById(R.id.laydescmore);
        btnviewless=layout.findViewById(R.id.descless);
 descexpanded=layout.findViewById(R.id.viewdesc);
        prjctid=layout.findViewById(R.id.prjctid);
        Prjctname=layout.findViewById(R.id.prjctname);
        desc=layout.findViewById(R.id.description);
        nextdesc=layout.findViewById(R.id.nextdescription);
        nextid=layout.findViewById(R.id.nextproject);
        nextname=layout.findViewById(R.id.nextname);
nextname.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        desc.requestFocus();
    }
});
        nextid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prjctname.requestFocus();
            }
        });
        nextdesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prjctid.requestFocus();
            }
        });
        addprjct=layout.findViewById(R.id.addtag);
if(type==2){
   TextView head=layout.findViewById(R.id.head);
   head.setText("Edit Project");
    Prjctname.setText(prjctData.getPrjct());
    desc.setText(prjctData.getDescr());
    prjctid.setText(prjctData.getId());
    prjctid.setVisibility(View.VISIBLE);
    prjctid.setFocusable(false);
    addprjct.setText("");
}

        addprjct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(!Prjctname.getText().toString().isEmpty()&&!desc.getText().toString().isEmpty()&&!prjctid.getText().toString().isEmpty()){
    PrjctData pp=new PrjctData();

        if(type==0){
            if(new SqliteDb(context).insertPrjct(prjctid.getText().toString(),Prjctname.getText().toString(),desc.getText().toString())) {
              //  Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                Utility.customToastSave("Added",context,"done");
                PageReport2.prjctdatas = new SqliteDb(context).getPrjcts();
                pp = PageReport2.prjctdatas.get(PageReport2.prjctdatas.size() - 1);
            //    PageReport2.prjctname.setText(Prjctname.getText().toString());
                PageReport2.loadprjctset(context, pp);

            }
            else {
                Utility.customToastSave("Project duplication",context,"other");
               // Toast.makeText(context, "Project duplication", Toast.LENGTH_SHORT).show();
            }

            }
        else if(type==1) {
            if(new SqliteDb(context).insertPrjct(prjctid.getText().toString(),Prjctname.getText().toString(),desc.getText().toString())) {
               // Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                Utility.customToastSave("Added",context,"done");


                SettingFragment1.loadPrjctsset(context);
            }
            else {
                Utility.customToastSave("Project duplication",context,"other");
              //  Toast.makeText(context, "Project duplication", Toast.LENGTH_SHORT).show();
            }


        }
        else
        {
            if(new SqliteDb(context).editPrjct(Prjctname.getText().toString(),desc.getText().toString(),prjctData.getId())) {
               // Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();
                Utility.customToastSave("Changes Saved",context,"done");

                SettingFragment1.loadPrjctsset(context);
            }
            else {

               // Toast.makeText(context, "Project duplication", Toast.LENGTH_SHORT).show();
                Utility.customToastSave("Project duplication",context,"other");
            }


        }
        alertDialog.dismiss();


}
else {

    if(Prjctname.getText().toString().isEmpty()){

        Prjctname.setError("Enter Project name");
    }
   if(desc.getText().toString().isEmpty())
    {

        desc.setError("Enter Description");
    }
    if(prjctid.getText().toString().isEmpty()){
        prjctid.setError("Enter Project id");

    }


}
            }
        });
desc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        layviewless.setVisibility(View.GONE);
        descexpanded.setText(desc.getText().toString());
        laydescmore.setVisibility(View.VISIBLE);
    }
});

btnviewless.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        layviewless.setVisibility(View.VISIBLE);
        desc.setText(descexpanded.getText().toString());
        laydescmore.setVisibility(View.GONE);
    }
});
    }


}
