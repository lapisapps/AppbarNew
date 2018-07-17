package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.SettingFragment3;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AddDisciplineDialog extends Dialog {
    Activity context;
    public  RecyclerView recyclerView,recyclerViewtag;
    public DataTag selectedtag;
    public static Main2Activity main2Activity;
    public  ArrayList<DataTag> dataTags;

    DataPreview dataPreview;
    LinearLayout idlay;
    AutoCompleteTextView tag,code;
    TextView addtag;
    RadioButton rdex,rdnew;
    TextInputLayout sufixinp,systeminp;
    android.app.AlertDialog.Builder builder;
    android.app.AlertDialog alertDialog;
public static PrjctData prjctData;
    public static DataTag systemdata;
int type;

    public AddDisciplineDialog(@NonNull Activity mContext, int type) {
        super(mContext);

        context=mContext;
        this.dataPreview=dataPreview;
this.type=type;


        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = null;
        layout = inflater.inflate(R.layout.dialog_adddiscpline,
                null);



        setView(layout);

        builder = new android.app.AlertDialog.Builder(mContext);
        builder.setView(layout);
        alertDialog = builder.create();
        //  alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();
    }

    private void setView(View layout) {

        code=layout.findViewById(R.id.id);
        tag=layout.findViewById(R.id.tag);
        addtag=layout.findViewById(R.id.addtag);

        if(type==2){
            tag.setText(systemdata.getTag());
//idlay.setVisibility(View.VISIBLE);
code.setText(systemdata.getTagid());
code.setFocusable(false);
          //  addtag.setText("Save");
        }



        addtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataTag addedarea=new DataTag();
                if(tag.getText().toString().isEmpty())
                    tag.setError("Enter name");
                else if(code.getText().toString().isEmpty())
                    code.setError("Enter code");
                else {

                    if(type==2){

                        if(tag.getText().toString().isEmpty())
                            tag.setError("Enter name");
                        else
                        if((new SqliteDb(context).updateDiscipline(systemdata.getTagid(),tag.getText().toString()))){
                            Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            SettingFragment3.loadDisciplineset(context);
                        }
                        return;
                    }
                    else {

                        if((new SqliteDb(context).addDiscipline(code.getText().toString(),tag.getText().toString()))){
                            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();

                            SettingFragment3.loadDisciplineset(context);
                        }

                    }
                }




            }
        });


    }


}
