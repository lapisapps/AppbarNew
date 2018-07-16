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
import com.example.pentagon.appbar.Fragments.PageReport2;
import com.example.pentagon.appbar.Fragments.SettingFragment1;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingTag;
import com.example.pentagon.appbar.Fragments.PageReportArea;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AddAreaDialog extends Dialog {
    Activity context;
    public  RecyclerView recyclerView,recyclerViewtag;
    public DataTag selectedtag;
    public static Main2Activity main2Activity;
    public  ArrayList<DataTag> dataTags;

    DataPreview dataPreview;
    LinearLayout arealayout;
    AutoCompleteTextView tag;
    TextView addtag;
    RadioButton rdex,rdnew;
    TextInputLayout sufixinp,systeminp;
    android.app.AlertDialog.Builder builder;
    android.app.AlertDialog alertDialog;
public static PrjctData prjctData;
    public static DataTag areadata;
int type;

    public AddAreaDialog(@NonNull Activity mContext,int type) {
        super(mContext);

        context=mContext;
        this.dataPreview=dataPreview;
this.type=type;


        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = null;
        layout = inflater.inflate(R.layout.dialog_addarea,
                null);


        dataTags=new SqliteDb(mContext).getAreas();
        setView(layout);

        builder = new android.app.AlertDialog.Builder(mContext);
        builder.setView(layout);
        alertDialog = builder.create();
        //  alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
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

        tag=layout.findViewById(R.id.tag);
        addtag=layout.findViewById(R.id.addtag);
        rdex=layout.findViewById(R.id.rdex);
        rdnew=layout.findViewById(R.id.rdnew);
        if(type==2){
            tag.setText(areadata.getTag());
            rdex.setVisibility(View.GONE);
            rdnew.setVisibility(View.GONE);
            addtag.setText("Save");
        }else
            addtag.setText("Add to project");

        rdex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
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
                DataTag addedarea=new DataTag();

                if(type==2){


                    if((new SqliteDb(context).updateArea(areadata.getTagid(),tag.getText().toString(),true))){
                        Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        SettingFragment1.loadareaset(context);
                    }
                    return;
                }
                if(rdnew.isChecked()){
                    if(tag.getText().toString().isEmpty())
                  tag.setError("Enter area code");
                    else
                        if(type==0){
                            if((addedarea=new SqliteDb(context).addArea(CreateReport.loaddata.getPrjct(),tag.getText().toString(),true))!=null){
                                Toast.makeText(context, "Area added to project", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                                PageReport2.prjctareas.add(addedarea);
                                PageReportArea.setView(context,  PageReport2.prjctareas);
                            }

                        }else if(type==1){
Log.e("ffff",prjctData.getId());
                            if((addedarea=new SqliteDb(context).addArea(prjctData.getId(),tag.getText().toString(),true))!=null){
                            Toast.makeText(context, "Area added to project", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                                SettingFragment1.loadareaset(context);
                }

                    }
                        else {
//                            if((new SqliteDb(context).updateArea(areadata.getTagid(),tag.getText().toString(),true))){
//                                Toast.makeText(context, "Area added to project", Toast.LENGTH_SHORT).show();
//                                alertDialog.dismiss();
//                                SettingFragment1.loadareaset(context);
//                            }

                            }

                }
                else {
                    if(selectedtag==null)
                        Toast.makeText(context, "Invalid code", Toast.LENGTH_SHORT).show();
                    else
                        if(type==0){
                    if((addedarea=new SqliteDb(context).addArea(CreateReport.loaddata.getPrjct(),selectedtag.getTagid(),false))!=null){
                        Toast.makeText(context, "Area added to project", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        PageReport2.prjctareas.add(addedarea);
                        PageReportArea.setView(getOwnerActivity(),  PageReport2.prjctareas);
                    }

                }else if(type==1){

                            if((addedarea=new SqliteDb(context).addArea(prjctData.getId(),tag.getText().toString(),false))!=null){
                                Toast.makeText(context, "Area added to project", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                                SettingFragment1.loadareaset(context);
                            }

                        }
                        else {


                        }

                }

            }
        });
        rdex.setChecked(true);

    }


}
