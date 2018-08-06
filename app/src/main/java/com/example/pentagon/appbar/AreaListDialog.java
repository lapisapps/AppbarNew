package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterProjects;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTagSelection;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.PageReport1;
import com.example.pentagon.appbar.Fragments.PageReport2;
import com.example.pentagon.appbar.Fragments.PageReportArea;
import com.example.pentagon.appbar.Fragments.PageReportDiscipline;
import com.example.pentagon.appbar.Fragments.PageReportSystem;
import com.example.pentagon.appbar.Fragments.PageReportTag;
import com.example.pentagon.appbar.Fragments.SettingFragment4;
import com.example.pentagon.appbar.Fragments.SettingFragment6;

import java.io.File;
import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AreaListDialog extends Dialog {

    private  int type;
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
    String from;
    String hh="Discipline";
    public AreaListDialog(@NonNull Activity mContext, int type,String pid,String from) {
        super(mContext);

        context = mContext;
this.fragment=fragment;
        android.app.AlertDialog.Builder builder;
        alertDialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.prjctlistdialog,
                null);


alertDialog.setContentView(layout);

this.type=type;
this.pid=pid;
this.from=from;
initlize();



        alertDialog.show();


        Window window = alertDialog.getWindow();


    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
       // alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
close=layout.findViewById(R.id.close);
close.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        alertDialog.dismiss();
    }
});
    }

    private void initlize() {
       ch=layout.findViewById(R.id.norsok);
//        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//
//
//            setDisciplineRecycle(context,layout,existtagData);
//                }
//                else {
//
//
//                    setDisciplineRecycle(context,layout,tagData);
//                }
//            }
//        });
        ch.setVisibility(View.GONE);
        add=layout.findViewById(R.id.button11);

        otagData=new ArrayList<>();
        head=layout.findViewById(R.id.head);

group=layout.findViewById(R.id.group2);
        prjctcount=layout.findViewById(R.id.prjctcount);
        selected=layout.findViewById(R.id.selected);
        group.setVisibility(View.GONE);
        ch.setVisibility(View.GONE);
        if(type==0){
head.setText("Select area");
            areadatas =   new SqliteDb(context).getAreas(pid);

            setAreaRecycle(context,layout);
        }else
        {
            head.setText("Select Tag");
            areadatas =   new SqliteDb(context).getTags(pid);

            setTagRecycle(context,layout);

        }



    }

    private  void setAreaRecycle(final Activity context, View v) {


        prjctcount.setText(areadatas.size()+" Areas");
        adapterprjcts=null;
        recycprjcts=v.findViewById(R.id.recycprjcts);
        adapterprjcts = new RecyclerViewAdapterTagSelection(context,areadatas,2);
        recycprjcts.setVisibility(View.VISIBLE);

        recycprjcts.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recycprjcts.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recycprjcts.setLayoutManager(mLayoutManager);

        //  if(prjctData.size()>6){
//        ViewGroup.LayoutParams params=recycprjcts.getLayoutParams();
//        params.height=height*62/100;
//        if(prjctData.size()*35>height*62/100)
//                    recycprjcts.setLayoutParams(params);
        // }
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycprjcts.setItemAnimator(new DefaultItemAnimator());
        recycprjcts.setAdapter(adapterprjcts);


        adapterprjcts.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                prjctcount.setText(areadatas.size()+" Areas");
            }
        });

        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (!from.equals("setting")) {
                    DataTag addedarea = areadatas.get(position);
                    if ((new SqliteDb(context).addArea(CreateReport.loaddata.getPrjct(), addedarea.getTag(), false, addedarea.getTagid()))) {
                        Utility.customToastSave("Area added to project", context, "done");


                        // Toast.makeText(context, "Area added to project", Toast.LENGTH_SHORT).show();


                        addedarea.setSelected(true);
                        alertDialog.dismiss();
                        PageReport2.prjctareas.add(addedarea);
                        PageReportArea.setView(getOwnerActivity(), PageReport2.prjctareas);
                    }

                } else
                    if ((new SqliteDb(context).addArea(pid, areadatas.get(position).getTag(), false, areadatas.get(position).getTagid()))) {

                    Utility.customToastSave("Area added to project", context, "done");

                    // Toast.makeText(context, "Area added to project", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    SettingFragment4.loadareaset(context);


            }
//               AddAreaDialog.selectedtag=areadatas.get(position);
//                AddAreaDialog.tag.setText(areadatas.get(position).getTag());
//alertDialog.dismiss();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }
    private  void setTagRecycle(final Activity context, View v) {


        prjctcount.setText(areadatas.size()+" Tags");
        adapterprjcts=null;
        recycprjcts=v.findViewById(R.id.recycprjcts);
        adapterprjcts = new RecyclerViewAdapterTagSelection(context,areadatas,2);
        recycprjcts.setVisibility(View.VISIBLE);

        recycprjcts.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recycprjcts.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recycprjcts.setLayoutManager(mLayoutManager);

        //  if(prjctData.size()>6){
//        ViewGroup.LayoutParams params=recycprjcts.getLayoutParams();
//        params.height=height*62/100;
//        if(prjctData.size()*35>height*62/100)
//                    recycprjcts.setLayoutParams(params);
        // }
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycprjcts.setItemAnimator(new DefaultItemAnimator());
        recycprjcts.setAdapter(adapterprjcts);


        adapterprjcts.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                prjctcount.setText(areadatas.size()+" Areas");
            }
        });

        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DataTag addedtag = areadatas.get(position);
                if (from.equals("setting")) {

                    if((new SqliteDb(context).addTag(pid,addedtag.getTag(),false, addedtag.getTagid()))){
                        //  Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                        Utility.customToastSave("Tag added to project",context,"done");
                        alertDialog.dismiss();
                        SettingFragment6.loadtagset(context);
                    }
                } else
                if((new SqliteDb(context).addTag(pid,addedtag.getTag(),false,addedtag.getTagid()))){
                    // Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                    Utility.customToastSave("Tag added to project",context,"done");

                    addedtag.setSelected(true);
                    alertDialog.dismiss();
                    PageReport2.prjcttags.add(addedtag);
                    PageReportTag.setView(getOwnerActivity(),  PageReport2.prjcttags);
                }

//                AddTagDialog.selectedtag=areadatas.get(position);
//                AddTagDialog.tag.setText(areadatas.get(position).getTag());

                alertDialog.dismiss();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }


}
