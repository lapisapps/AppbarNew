package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.FilterWithSpaceAdapter;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.PageReportTag;
import com.example.pentagon.appbar.Fragments.SettingFragment1;
import com.example.pentagon.appbar.Fragments.SettingFragment2;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AddTagDialog extends Dialog {
    Activity context;
    public  RecyclerView recyclerView,recyclerViewtag;
    public DataTag selectedtag;
    public static Main2Activity main2Activity;
    public  ArrayList<DataTag> dataTags;
    DataPreview dataPreview;
    LinearLayout arealayout;
    AutoCompleteTextView tag;
    TextView addtag;
    int type,ff;
    RadioButton rdex,rdnew;
    TextInputLayout sufixinp,systeminp;
    android.app.AlertDialog.Builder builder;
    android.app.AlertDialog alertDialog;
    public static PrjctData prjctData;
    public static DataTag tagdata;
    public AddTagDialog(@NonNull Activity mContext,int type) {
        super(mContext);

        context=mContext;
        this.dataPreview=dataPreview;
this.type=type;


        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_addtag,
                null);
        dataTags=new SqliteDb(mContext).getTags();
setView(layout);
        builder = new android.app.AlertDialog.Builder(mContext);
        builder.setView(layout);
        alertDialog = builder.create();
        //  alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();
    }

    private void setView(View layout) {

        tag=layout.findViewById(R.id.tag);
        addtag=layout.findViewById(R.id.addtag);
        rdex=layout.findViewById(R.id.rdex);
        rdnew=layout.findViewById(R.id.rdnew);
        if(type==2){

            addtag.setText("Save");
            tag.setText(tagdata.getTag());
            rdex.setVisibility(View.GONE);
            rdnew.setVisibility(View.GONE);
        }
        rdex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked&&type!=2){
Log.e("tagss",dataTags.size()+"");
                            FilterWithSpaceAdapter adapter1 = new FilterWithSpaceAdapter<DataTag> (context,android.R.layout.select_dialog_item,dataTags);
        tag.setThreshold(1);//will start working from first character
        tag.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView
                    tag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Object item1 = parent.getItemAtPosition(position);
                            if (item1 instanceof DataTag) {
selectedtag=new DataTag();
                                 selectedtag = (DataTag) item1;





                            }
                        }
                    });
       }
       else {

                    tag.setAdapter(null);
                }
            }
        });
        tag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
selectedtag=null;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isnew=false;
                String tagid=null;







                if(type==2){
                    if(tag.getText().toString().isEmpty())
                        Toast.makeText(context, "Enter tag", Toast.LENGTH_SHORT).show();
                    else
                if((new SqliteDb(context).updateTag(tagdata.getTagid(),tag.getText().toString(),true))){
                    Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    SettingFragment2.loadtagset(context);
                }
                return;
                }
                if(rdnew.isChecked()){

                    isnew=true;}

                String prjctid = null;

                if(type==0)
                    prjctid=CreateReport.loaddata.getPrjct();
                else if(type==1)
                    prjctid=prjctData.getId();

                boolean bb=true;
                if(tag.getText().toString().isEmpty()&&rdnew.isChecked()){
                    bb=false;
                    Toast.makeText(context, "Enter tag", Toast.LENGTH_SHORT).show();}
                    else
              if(selectedtag==null&&rdex.isChecked()){
                    bb=false;
                    Toast.makeText(context, "Invalid tag", Toast.LENGTH_SHORT).show();
                }

                if(bb){
                    if(rdex.isChecked())
                        tagid=selectedtag.getTagid();
                    else
                        tagid=tag.getText().toString();
                if(new SqliteDb(context).addTag(prjctid,tagid,isnew)){
                    Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    if(type==0)
                    PageReportTag.setView(context,   new SqliteDb(context).getPrjctsTags(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId()));
                    else if(type==1)
                        SettingFragment2.loadtagset(context);
                    else
                        SettingFragment2.loadtagset(context);

                }}


//                if(rdnew.isChecked()){
//                    if(tag.getText().toString().isEmpty())
//                        Toast.makeText(context, "Enter tag", Toast.LENGTH_SHORT).show();
//                    else
//                        if(type==0)
//                if(new SqliteDb(context).addTag(CreateReport.loaddata.getPrjct(),tag.getText().toString(),true)){
//                    Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
//                    alertDialog.dismiss();
//                    PageReportTag.setView(context,   new SqliteDb(context).getPrjctsTags(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId()));
//                }
//                        else
//                if(new SqliteDb(context).addTag(prjctData.getId(),tag.getText().toString(),true)){
//                    Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
//                    alertDialog.dismiss();
//                    SettingFragment2.loadtagset(context);
//                }
//                }
//                else {
//                    if(selectedtag==null)
//                        Toast.makeText(context, "Invalid tag", Toast.LENGTH_SHORT).show();
//                    else
//                    if(new SqliteDb(context).addTag(CreateReport.loaddata.getPrjct(),selectedtag.getTagid(),false)){
//                        Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
//                        alertDialog.dismiss();
//                        PageReportTag.setView(context,   new SqliteDb(context).getPrjctsTags(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId()));
//                    }
//
//                }

            }
        });
        rdex.setChecked(true);
    }


}
