package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTagSelection;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.Fragments.SettingFragment3;
import com.example.pentagon.appbar.Fragments.SettingFragment5;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ImportListDialog extends Dialog {

    private  String type;
    private  ArrayList<DataTag> otagData;
    private static ArrayList<DataTag> tagData;
    private  ArrayList<DataTag> existtagData;
Group group;

    public  ArrayList<DataTag> areadatas;
    public    RecyclerViewAdapterTagSelection adapterprjcts;
    public RecyclerViewAdapterTagSelection adaptertags;

    static TextView prjctcount,selected;
    static RecyclerView recycprjcts;
    Activity context;
    Dialog alertDialog;
    TextView head;
    Button add;
    Button close;
CheckBox ch;
    View layout;
    Fragment fragment;
    String pid;
    TableRow plantdeskrow,csvrow,sfirow,norsokrow;
    ImageButton pload,cload,sload,nload;
    String hh="Discipline";
    public ImportListDialog(@NonNull Activity mContext, String type) {
        super(mContext);

        context = mContext;
this.fragment=fragment;
        android.app.AlertDialog.Builder builder;
        alertDialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.dialog_import,
                null);


alertDialog.setContentView(layout);

this.type=type;


initlize();


        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();


        Window window = alertDialog.getWindow();


 //   window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
       // alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    private void initlize() {
plantdeskrow=layout.findViewById(R.id.rowplantdesk);
        csvrow=layout.findViewById(R.id.rowcsv);
        norsokrow=layout.findViewById(R.id.rownorsok);
       sfirow=layout.findViewById(R.id.rowsfi);
        pload=layout.findViewById(R.id.loadp);
        sload=layout.findViewById(R.id.loads);
        cload=layout.findViewById(R.id.loadc);
        nload=layout.findViewById(R.id.loadn);

        plantdeskrow.setVisibility(View.GONE);
        csvrow.setVisibility(View.GONE);

        norsokrow.setVisibility(View.GONE);
        sfirow.setVisibility(View.GONE);

       if(type.equals("area")||type.equals("tag")){

           plantdeskrow.setVisibility(View.VISIBLE);
           csvrow.setVisibility(View.VISIBLE);
       }
       else if(type.equals("system")){

           plantdeskrow.setVisibility(View.VISIBLE);
           csvrow.setVisibility(View.VISIBLE);

           norsokrow.setVisibility(View.VISIBLE);
           sfirow.setVisibility(View.VISIBLE);
           setSystemView();




           sload.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String dd="SFI loaded Successfully";
                   if(sload.getTag().toString().equals("load")) {
                       new SqliteDb(context).loadSystem("sfi");
                   }  else{
                       dd="SFI unloaded Successfully";
                       new SqliteDb(context).unloadSystem("sfi");}
                       Utility.customToastSave(dd,context,"done");
                   SettingFragment3.loadSystemset(context);
                   alertDialog.dismiss();
               }
           });
           nload.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String dd="NORSOK loaded Successfully";
                   if(nload.getTag().toString().equals("load")) {
                       new SqliteDb(context).loadSystem("norsok");
                   }  else{
                       dd="NORSOK unloaded Successfully";
                       new SqliteDb(context).unloadSystem("norsok");}
                   Utility.customToastSave(dd,context,"done");
               SettingFragment3.loadSystemset(context);
                   alertDialog.dismiss();
               }
           });



       }
       else if(type.equals("discipline"))
       {
           plantdeskrow.setVisibility(View.VISIBLE);
           csvrow.setVisibility(View.VISIBLE);

           norsokrow.setVisibility(View.VISIBLE);

           setDisciplineView();




//           sload.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   String dd="SFI loaded Successfully";
//                   if(sload.getTag().toString().equals("load")) {
//                       new SqliteDb(context).loadSystem("sfi");
//                   }  else{
//                       dd="SFI unloaded Successfully";
//                       new SqliteDb(context).unloadSystem("sfi");}
//                   Utility.customToastSave(dd,context,1);
//                   SettingFragment3.loadSystemset(context);
//                   alertDialog.dismiss();
//               }
//           });
           nload.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String dd="NORSOK loaded Successfully";
                   if(nload.getTag().toString().equals("load")) {
                       new SqliteDb(context).loadDiscipline("norsok");
                   }  else{
                       dd="NORSOK unloaded Successfully";
                       new SqliteDb(context).unloadDiscipline("norsok");}
                   Utility.customToastSave(dd,context,"done");
                   SettingFragment5.loadDisciplineset(context);
                   alertDialog.dismiss();
               }
           });
       }

       else if(type.equals("project"))
       {
           plantdeskrow.setVisibility(View.VISIBLE);
           csvrow.setVisibility(View.VISIBLE);

           //norsokrow.setVisibility(View.VISIBLE);

          // setDisciplineView();




//           sload.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   String dd="SFI loaded Successfully";
//                   if(sload.getTag().toString().equals("load")) {
//                       new SqliteDb(context).loadSystem("sfi");
//                   }  else{
//                       dd="SFI unloaded Successfully";
//                       new SqliteDb(context).unloadSystem("sfi");}
//                   Utility.customToastSave(dd,context,1);
//                   SettingFragment3.loadSystemset(context);
//                   alertDialog.dismiss();
//               }
//           });
//           nload.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   String dd="NORSOK loaded Successfully";
//                   if(nload.getTag().toString().equals("load")) {
//                       new SqliteDb(context).loadDiscipline("norsok");
//                   }  else{
//                       dd="NORSOK unloaded Successfully";
//                       new SqliteDb(context).unloadDiscipline("norsok");}
//                   Utility.customToastSave(dd,context,1);
//                   SettingFragment5.loadDisciplineset(context);
//                   alertDialog.dismiss();
//               }
//           });
       }



    }

    private void setDisciplineView() {

//        if(new SqliteDb(context).IsDisplineLoaded("sfi")){
//
//            sload.setTag("unload");
//            sload.setBackgroundResource(R.drawable.ic_delete_black_24dp);
//
//        }
//        else{
//            sload.setBackgroundResource(R.drawable.ic_load_green_24dp);
//            sload.setTag("load");}

        if(new SqliteDb(context).IsDisciplineLoaded("norsok")){

            nload.setTag("unload");
            nload.setBackgroundResource(R.drawable.ic_delete_black_24dp);

        }
        else{
            nload.setBackgroundResource(R.drawable.ic_load_green_24dp);
            nload.setTag("load");}
    }

    private void setSystemView() {
        if(new SqliteDb(context).IsSystemLoaded("sfi")){

            sload.setTag("unload");
            sload.setBackgroundResource(R.drawable.ic_delete_black_24dp);

        }
        else{
            sload.setBackgroundResource(R.drawable.ic_load_green_24dp);
            sload.setTag("load");}

        if(new SqliteDb(context).IsSystemLoaded("norsok")){

           nload.setTag("unload");
            nload.setBackgroundResource(R.drawable.ic_delete_black_24dp);

        }
        else{
           nload.setBackgroundResource(R.drawable.ic_load_green_24dp);
            nload.setTag("load");}
    }


}
