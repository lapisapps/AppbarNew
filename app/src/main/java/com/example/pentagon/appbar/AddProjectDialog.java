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
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
    AutoCompleteTextView Prjctname,desc;
    Button addprjct;
    RadioButton rdex,rdnew;
    TextInputLayout sufixinp,systeminp;
    android.app.AlertDialog.Builder builder;
    android.app.AlertDialog alertDialog;
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
        //  alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();
    }

    private void setView(View layout) {

        Prjctname=layout.findViewById(R.id.prjctname);
        desc=layout.findViewById(R.id.description);
        addprjct=layout.findViewById(R.id.addtag);
if(type==2){
   TextView head=layout.findViewById(R.id.head);
   head.setText("Edit Project");
    Prjctname.setText(prjctData.getPrjct());
    desc.setText(prjctData.getDescr());
    addprjct.setText("Save");
}

        addprjct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(!Prjctname.getText().toString().isEmpty()&&!desc.getText().toString().isEmpty()){
    PrjctData pp=new PrjctData();

        if(type==0){
            if(new SqliteDb(context).insertPrjct(Prjctname.getText().toString(),desc.getText().toString())) {
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();

                PageReport2.prjctdatas = new SqliteDb(context).getPrjcts();
                pp = PageReport2.prjctdatas.get(PageReport2.prjctdatas.size() - 1);
                PageReport2.prjctname.setText(Prjctname.getText().toString());
                PageReport2.loadprjctset(context, pp);

            }
            else {

                Toast.makeText(context, "Project duplication", Toast.LENGTH_SHORT).show();
            }

            }
        else if(type==1) {
            if(new SqliteDb(context).insertPrjct(Prjctname.getText().toString(),desc.getText().toString())) {
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();


                SettingFragment1.loadPrjctsset(context);
            }
            else {

                Toast.makeText(context, "Project duplication", Toast.LENGTH_SHORT).show();
            }


        }
        else
        {
            if(new SqliteDb(context).editPrjct(Prjctname.getText().toString(),desc.getText().toString(),prjctData.getId())) {
                Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();


                SettingFragment1.loadPrjctsset(context);
            }
            else {

                Toast.makeText(context, "Project duplication", Toast.LENGTH_SHORT).show();
            }


        }
        alertDialog.dismiss();


}
else {

    if(Prjctname.getText().toString().isEmpty()){

        Prjctname.setError("Enter Project name");
    }
    else
    {

        desc.setError("Enter Description");
    }


}
            }
        });


    }


}
