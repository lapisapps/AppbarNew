package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.CustomAutocompleteTextViewAd;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTagSelection;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.PageReport2;
import com.example.pentagon.appbar.Fragments.PageReportArea;
import com.example.pentagon.appbar.Fragments.PageReportTag;
import com.example.pentagon.appbar.Fragments.SettingFragment1;
import com.example.pentagon.appbar.Fragments.SettingFragment2;
import com.example.pentagon.appbar.Fragments.SettingFragment6;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AddTagDialog extends Dialog {
    public static AreaListDialog areaListDialog;
    Activity context;
    public  RecyclerView recyclerView,recyclerViewtag;
    public static DataTag selectedtag;
    public static Main2Activity main2Activity;
    public  ArrayList<DataTag> dataTags;

    DataPreview dataPreview;
    LinearLayout arealayout;
    public static  AutoCompleteTextView tag;
    AutoCompleteTextView code;
    TextView addtag;
    RadioButton rdex,rdnew;
    TextInputLayout sufixinp,systeminp;
    android.app.AlertDialog.Builder builder;
    android.app.AlertDialog alertDialog;
public static PrjctData prjctData;
LinearLayout layid;
    ImageButton nextcode,nextname;
    public static DataTag areadata;
int type;

    public AddTagDialog(@NonNull Activity mContext, int type) {
        super(mContext);

        context=mContext;
        this.dataPreview=dataPreview;
this.type=type;
//        if(type==0)
//            pid=CreateReport.loaddata.getPrjct();
//        else
//            pid=prjctData.getId();

        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = null;
        layout = inflater.inflate(R.layout.dialog_addtag,
                null);
        dataTags=new ArrayList<>();
if(type==0)

        dataTags=new SqliteDb(mContext).getTags(CreateReport.loaddata.getPrjct());
else
    if(prjctData!=null)
    dataTags=new SqliteDb(mContext).getTags(prjctData.getId());
        setView(layout);

        builder = new android.app.AlertDialog.Builder(mContext);
        builder.setView(layout);
        alertDialog = builder.create();
         alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();
    }
    private void setView1(View layout) {

        tag=layout.findViewById(R.id.tag);
        addtag=layout.findViewById(R.id.addtag);


        addtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataTag addedarea=new DataTag();
         if(!tag.getText().toString().isEmpty()){

            // new SqliteDb()
         }

            }
        });
        rdex.setChecked(true);

    }
    private void setView(View layout) {
        nextcode=layout.findViewById(R.id.nextcode);
        nextname=layout.findViewById(R.id.nextname);
        nextname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code.requestFocus();
            }
        });
        nextcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag.requestFocus();
            }
        });
        layid=layout.findViewById(R.id.layoutid);
        tag=layout.findViewById(R.id.tag);
        code=layout.findViewById(R.id.id);
        addtag=layout.findViewById(R.id.addtag);
        rdex=layout.findViewById(R.id.rdex);
        rdnew=layout.findViewById(R.id.rdnew);
        layid.setVisibility(View.VISIBLE);
        if(type==2){
            layid.setVisibility(View.VISIBLE);
            code.setText(areadata.getTagid());
            code.setFocusable(false);

            tag.setText(areadata.getTag());
            rdex.setVisibility(View.GONE);
            rdnew.setVisibility(View.GONE);
            addtag.setText("");
        }else
            addtag.setText("");

        layid.setVisibility(View.VISIBLE);
        rdex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
//                    tag.setText("");
//                    Log.e("tagss",dataTags.size()+"");
//                    CustomAutocompleteTextViewAd adapter1 = new CustomAutocompleteTextViewAd(context,R.layout.dialog_addarea,R.id.lbl_name,dataTags);
//                    tag.setThreshold(1);//will start working from first character
//
//                    tag.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView
//                    tag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Object item1 = parent.getItemAtPosition(position);
//                            if (item1 instanceof DataTag) {
//                                selectedtag=new DataTag();
//                                selectedtag = (DataTag) item1;
//
//
//
//
//
//                            }
//                        }
//                    });
                    layid.setVisibility(View.GONE);
                }
                else {

layid.setVisibility(View.VISIBLE);
                    tag.setAdapter(null);
                }
            }
        });
//        tag.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                selectedtag=null;
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(rdex.isChecked())
//                    new AreaListDialog(context,1,pid);
            }
        });

        addtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataTag addedarea=new DataTag();

                if(type==2){

                    if(tag.getText().toString().isEmpty())
                        tag.setError("Enter tag");
                    else if(code.getText().toString().isEmpty())
                        code.setError("Enter tag_no");
                    else
                    if((new SqliteDb(context).updateTag(areadata.getTagid(),tag.getText().toString(),true))){
                        Utility.customToastSave("Changes Saved",context,"done");
                      //  Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        SettingFragment6.loadtagset(context);
                    }
                    return;
                }
            //    if(rdnew.isChecked()){
                    if(tag.getText().toString().isEmpty())
                  tag.setError("Enter tag");
                    else if(code.getText().toString().isEmpty())
                        code.setError("Enter tag_no");
                    else
                        if(type==0){
                            if(new SqliteDb(context).addTag(prjctData.getId(),tag.getText().toString(),true,code.getText().toString())){
                                Utility.customToastSave("Tag added to project",context,"done");
                               // Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                                addedarea.setTag(tag.getText().toString());
                                addedarea.setTagid(code.getText().toString());
addedarea.setSelected(true);
                                alertDialog.dismiss();
                                PageReport2.prjcttags.add(addedarea);
                                PageReportTag.setView(context,  PageReport2.prjcttags);
                            }
                            else {

                                code.setError("Tag no already taken");
                            }

                        }else if(type==1){
Log.e("ffff",prjctData.getId());
                            if(new SqliteDb(context).addTag(prjctData.getId(),tag.getText().toString(),true, code.getText().toString())){
                                Utility.customToastSave("Tag added to project",context,"done");
                           // Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                                SettingFragment6.loadtagset(context);
                }
                            else {

                                code.setError("Tag no already taken");
                            }

                    }
                        else if(type==3){
                            if((new SqliteDb(context).addTag(null,tag.getText().toString(),true,code.getText().toString()))){
                                Utility.customToastSave("Saved",context,"done");
                                //  Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();

                                areaListDialog.areadatas =   new SqliteDb(context).getTags(areaListDialog.pid);
//                                AreaListDialog.adaptertags.notifyDataSetChanged();
//                                AreaListDialog.adaptertags = new RecyclerViewAdapterTagSelection(context,areaListDialog.areadatas,2);
//                                areaListDialog.recycprjcts.setAdapter( AreaListDialog.adaptertags);
                                areaListDialog.filter(areaListDialog.searchtxt.getText().toString());
                            }
                            else {

                                code.setError("code already taken");
                                code.requestFocus();
                            }



                        }



            }
        });


    }


}
