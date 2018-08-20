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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.FilterWithSpaceAdapter;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTagSelection;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.PageReport2;
import com.example.pentagon.appbar.Fragments.PageReportArea;
import com.example.pentagon.appbar.Fragments.PageReportDiscipline;
import com.example.pentagon.appbar.Fragments.PageReportSystem;
import com.example.pentagon.appbar.Fragments.SettingFragment1;
import com.example.pentagon.appbar.Fragments.SettingFragment3;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AddSystemDialog extends Dialog {
    Activity context;
    public  RecyclerView recyclerView,recyclerViewtag;
    public DataTag selectedtag;
    public static Main2Activity main2Activity;
    public  ArrayList<DataTag> dataTags;

    DataPreview dataPreview;
    LinearLayout layid;
    AutoCompleteTextView tag,code;
    TextView addtag;
    RadioButton rdex,rdnew;
    TextInputLayout sufixinp,systeminp;
    android.app.AlertDialog.Builder builder;
    android.app.AlertDialog alertDialog;
public static PrjctData prjctData;
    public static DataTag systemdata;
int type;
public static AreaListDialog areaListDialog;
    private ImageButton nextcode,nextname;

    public AddSystemDialog(@NonNull Activity mContext, int type) {
        super(mContext);

        context=mContext;
        this.dataPreview=dataPreview;
this.type=type;


        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = null;
        layout = inflater.inflate(R.layout.dialog_addsystem,
                null);


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
        code=layout.findViewById(R.id.id);
        tag=layout.findViewById(R.id.tag);
        addtag=layout.findViewById(R.id.addtag);

        if(type==2){
            tag.setText(systemdata.getTag());
code.setText(systemdata.getTagid());
code.setFocusable(false);
          //  addtag.setText("Save");
        }



        addtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataTag addedarea=new DataTag();
                if(tag.getText().toString().isEmpty())
                    tag.setError("Enter system");
                else if(code.getText().toString().isEmpty())
                    code.setError("Enter code");
                else {

                    if(type==2){

                        if(tag.getText().toString().isEmpty())
                            tag.setError("Enter System");

                        else
                        if((new SqliteDb(context).updateSystem(systemdata.getTagid(),tag.getText().toString()))){
                            Utility.customToastSave("Changes Saved",context,"done");
                            //Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            SettingFragment3.loadSystemset(context);
                        }
                        return;
                    }
                    else if(type==0){

                        if((new SqliteDb(context).addSystem(prjctData.getId(),tag.getText().toString(),true,code.getText().toString()))){
                            Utility.customToastSave("Saved",context,"done");
                          //  Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                            addedarea.setTagid(code.getText().toString());
                            addedarea.setTag(tag.getText().toString());
                            addedarea.setSelected(true);
                            alertDialog.dismiss();
                            CreateReport.dataSystems.add(addedarea);
                            PageReportSystem.setView(getOwnerActivity(),    CreateReport.dataSystems);
                        }
                        else {

                            code.setError("code already taken");
                            code.requestFocus();
                        }
                    }
                    else if(type==1){

                        if((new SqliteDb(context).addSystem(prjctData.getId(),tag.getText().toString(),true,code.getText().toString()))){
                            Utility.customToastSave("Saved",context,"done");
                          //  Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();

                            SettingFragment3.loadSystemset(context);
                        }
                        else {

                            code.setError("code already taken");
                            code.requestFocus();
                        }

                    }
                    else if(type==3){
                        if((new SqliteDb(context).addSystem(null,tag.getText().toString(),true,code.getText().toString()))){
                            Utility.customToastSave("Saved",context,"done");
                            //  Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();

                            areaListDialog.areadatas =   new SqliteDb(context).getSystems(areaListDialog.pid);
//                            AreaListDialog.adaptertags.notifyDataSetChanged();
//                           AreaListDialog.adaptertags = new RecyclerViewAdapterTagSelection(context,areaListDialog.areadatas,2);
//                            areaListDialog.recycprjcts.setAdapter( AreaListDialog.adaptertags);
                            areaListDialog.filter(areaListDialog.searchtxt.getText().toString());
                        }
                        else {

                            code.setError("code already taken");
                            code.requestFocus();
                        }



                    }
                }




            }
        });


    }


}
