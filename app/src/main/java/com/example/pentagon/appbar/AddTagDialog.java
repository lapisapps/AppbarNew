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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.CustomAutocompleteTextViewAd;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.PageReport2;
import com.example.pentagon.appbar.Fragments.PageReportArea;
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
    AutoCompleteTextView tag,code;
    TextView addtag;
    RadioButton rdex,rdnew;
    TextInputLayout sufixinp,systeminp;
    android.app.AlertDialog.Builder builder;
    android.app.AlertDialog alertDialog;
public static PrjctData prjctData;
LinearLayout layid;
    public static DataTag areadata;
int type;

    public AddTagDialog(@NonNull Activity mContext, int type) {
        super(mContext);

        context=mContext;
        this.dataPreview=dataPreview;
this.type=type;


        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = null;
        layout = inflater.inflate(R.layout.dialog_addtag,
                null);


        dataTags=new SqliteDb(mContext).getTags(CreateReport.loaddata.getPrjct());
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
        layid=layout.findViewById(R.id.layoutid);
        tag=layout.findViewById(R.id.tag);
        code=layout.findViewById(R.id.id);
        addtag=layout.findViewById(R.id.addtag);
        rdex=layout.findViewById(R.id.rdex);
        rdnew=layout.findViewById(R.id.rdnew);
        if(type==2){
            layid.setVisibility(View.VISIBLE);
            code.setText(areadata.getTagid());
            code.setFocusable(false);
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
                    CustomAutocompleteTextViewAd adapter1 = new CustomAutocompleteTextViewAd(context,R.layout.dialog_addarea,R.id.lbl_name,dataTags);
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
                    layid.setVisibility(View.GONE);
                }
                else {
layid.setVisibility(View.VISIBLE);
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

                    if(tag.getText().toString().isEmpty())
                        tag.setError("Enter tag");
                    else if(code.getText().toString().isEmpty())
                        code.setError("Enter tag_no");
                    else
                    if((new SqliteDb(context).updateTag(areadata.getTagid(),tag.getText().toString(),true))){
                        Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        SettingFragment2.loadtagset(context);
                    }
                    return;
                }
                if(rdnew.isChecked()){
                    if(tag.getText().toString().isEmpty())
                  tag.setError("Enter tag");
                    else if(code.getText().toString().isEmpty())
                        code.setError("Enter tag_no");
                    else
                        if(type==0){
                            if(new SqliteDb(context).addTag(CreateReport.loaddata.getPrjct(),tag.getText().toString(),true,code.getText().toString())){
                                Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                                addedarea.setTag(tag.getText().toString());
                                addedarea.setTagid(code.getText().toString());
                                alertDialog.dismiss();
                                PageReport2.prjcttags.add(addedarea);
                                PageReportTag.setView(context,  PageReport2.prjcttags);
                            }

                        }else if(type==1){
Log.e("ffff",prjctData.getId());
                            if(new SqliteDb(context).addTag(prjctData.getId(),tag.getText().toString(),true, code.getText().toString())){
                            Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                                SettingFragment2.loadtagset(context);
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
                        tag.setError("invalid entry");
                    else
                        if(type==0){
                    if((new SqliteDb(context).addTag(CreateReport.loaddata.getPrjct(),tag.getText().toString(),false,selectedtag.getTagid()))){
                        Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                        addedarea.setTag(tag.getText().toString());
                        addedarea.setTagid(selectedtag.getTagid());
                        alertDialog.dismiss();
                        PageReport2.prjcttags.add(addedarea);
                        PageReportTag.setView(getOwnerActivity(),  PageReport2.prjcttags);
                    }

                }else if(type==1){

                            if((new SqliteDb(context).addTag(prjctData.getId(),tag.getText().toString(),false, selectedtag.getTagid()))){
                                Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                                SettingFragment2.loadtagset(context);
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
