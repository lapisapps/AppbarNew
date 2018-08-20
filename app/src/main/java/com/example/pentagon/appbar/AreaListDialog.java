package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.pentagon.appbar.Fragments.SettingFragment3;
import com.example.pentagon.appbar.Fragments.SettingFragment4;
import com.example.pentagon.appbar.Fragments.SettingFragment5;
import com.example.pentagon.appbar.Fragments.SettingFragment6;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AreaListDialog extends Dialog {

    private  int type;
    private  ArrayList<DataTag> otagData;
    private static ArrayList<DataTag> tagData;
    private  ArrayList<DataTag> selectedtags;
Group group,group1;

    public  ArrayList<DataTag> areadatas;
    public RecyclerViewAdapterTagSelection adapterprjcts;
    public static   RecyclerViewAdapterTagSelection adaptertags;

    static TextView prjctcount,selected;
  RecyclerView recycprjcts;
    Activity context;
    Dialog alertDialog;
    TextView head;
    Button add;
    Button close;
    TextView toast;
CheckBox ch;
FloatingActionButton addnew;
    View layout;
    Fragment fragment;
    String pid;
    String from;
    String hh="Discipline";
    private Button cancelbtn;
    public  AutoCompleteTextView searchtxt;

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }

    public AreaListDialog(@NonNull Activity mContext, int type, String pid1, Fragment from) {
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
this.pid=pid1;
this.fragment=from;
initlize();

        selectedtags=new ArrayList<>();

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
alertDialog.setOnDismissListener(new OnDismissListener() {
    @Override
    public void onDismiss(DialogInterface dialog) {
       // Toast.makeText(context,"ddf"+fragment.getClass(),Toast.LENGTH_SHORT).show();
        if(fragment.getClass().equals(PageReportDiscipline.class)) {
            //new SqliteDb(getContext()).insertReportDiscipline(selectedtags, PageReport1.reportid.getText().toString());
            // tagData = new SqliteDb(context).getReportDiscipline(CreateReport.loaddata.getId());
        //  CreateReport.dataDisciplines.addAll(selectedtags);
            PageReport2.prjctdiscipline.addAll(selectedtags);

            PageReportDiscipline.setView(context,PageReport2.prjctdiscipline);
        }  else if(fragment.getClass().equals(PageReportSystem.class)) {
            //new SqliteDb(getContext()).insertReportSystem(selectedtags, PageReport1.reportid.getText().toString());
            // tagData = new SqliteDb(context).getReportSystem(CreateReport.loaddata.getId());
         PageReport2.prjctsystem.addAll(selectedtags);
            PageReportSystem.setView(context,PageReport2.prjctsystem);
        }

        else if(fragment.getClass().equals(PageReportArea.class)) {
            //new SqliteDb(getContext()).insertReportSystem(selectedtags, PageReport1.reportid.getText().toString());
            // tagData = new SqliteDb(context).getReportSystem(CreateReport.loaddata.getId());
            PageReport2.prjctareas.addAll(selectedtags);
            PageReportArea.setView(context,PageReport2.prjctareas);
        }
        else if(fragment.getClass().equals(PageReportTag.class)) {
            //new SqliteDb(getContext()).insertReportSystem(selectedtags, PageReport1.reportid.getText().toString());
            // tagData = new SqliteDb(context).getReportSystem(CreateReport.loaddata.getId());
            PageReport2.prjcttags.addAll(selectedtags);
            PageReportTag.setView(context,PageReport2.prjcttags);
        }
        else if(fragment.getClass().equals(SettingFragment3.class)){


                //  Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();


                SettingFragment3.loadSystemset(context);

        }
        else if(fragment.getClass().equals(SettingFragment5.class)){


            //  Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();


            SettingFragment5.loadDisciplineset(context);

        }
        else if(fragment.getClass().equals(SettingFragment4.class)){


            //  Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();


            SettingFragment4.loadareaset(context);

        }
        else if(fragment.getClass().equals(SettingFragment6.class)){


            //  Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();


            SettingFragment6.loadtagset(context);

        }
    }
});
setSearchView();
    }

    private void initlize() {
        searchtxt=layout.findViewById(R.id.searchtext);
        cancelbtn=layout.findViewById(R.id.cancel);
        addnew=layout.findViewById(R.id.addnew);
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type==0){

                    AddAreaDialog.areaListDialog=AreaListDialog.this;
                    new AddAreaDialog(context,3);
                }
                else if(type==1){
                    AddTagDialog.areaListDialog=AreaListDialog.this;
                    new AddTagDialog(context,3);
                }
                else if(type==2){

                    AddDisciplineDialog.areaListDialog=AreaListDialog.this;
                    new AddDisciplineDialog(context,3);
                }
                else if(type==3){

                    AddSystemDialog.areaListDialog=AreaListDialog.this;
                    new AddSystemDialog(context,3);
                }

            }
        });
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
        toast=layout.findViewById(R.id.textView13);
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
        } else if(type==1)
        {
            head.setText("Select Tag");
            areadatas =   new SqliteDb(context).getTags(pid);

            setTagRecycle(context,layout);

        }
        else if(type==2){

            head.setText("Select Discipline");
            areadatas =   new SqliteDb(context).getDiscipline(pid);

            setDisciplineRecycle(context,layout);
        }

        else if(type==3){

            head.setText("Select System");
            areadatas =   new SqliteDb(context).getSystems(pid);

            setSystemRecycle(context,layout);
        }


        add=layout.findViewById(R.id.button11);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ArrayList<DataTag> selectedtags=new ArrayList<>();
                SparseBooleanArray selected;

                selected = adaptertags
                        .getSelectedIds();

                int selectedMessageSize = selected.size();

                //Loop to all selected items
                ArrayList<File> files;
                files=new ArrayList<>();
                for (int i = (selectedMessageSize - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        //get selected data in Model
                        DataTag model = areadatas.get(selected.keyAt(i));
                        model.setSelected(true);
                        selectedtags.add(model);

                    }
                }



                if(fragment.getClass().equals(PageReportDiscipline.class)) {
                    //new SqliteDb(getContext()).insertReportDiscipline(selectedtags, PageReport1.reportid.getText().toString());
                    // tagData = new SqliteDb(context).getReportDiscipline(CreateReport.loaddata.getId());
                    CreateReport.dataDisciplines.addAll(selectedtags);
                    PageReportDiscipline.setView(context,CreateReport.dataDisciplines);
                }  else if(fragment.getClass().equals(PageReportSystem.class)) {
                    //new SqliteDb(getContext()).insertReportSystem(selectedtags, PageReport1.reportid.getText().toString());
                    // tagData = new SqliteDb(context).getReportSystem(CreateReport.loaddata.getId());
                    CreateReport.dataSystems.addAll(selectedtags);
                    PageReportSystem.setView(context,CreateReport.dataSystems);
                }
                else if(fragment.getClass().equals(SettingFragment3.class)){

                                        if((new SqliteDb(context).addToPrjct(pid,selectedtags,"system"))){
                        //  Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
                        Utility.customToastSave("System added to project",context,"done");
                        alertDialog.dismiss();
                        SettingFragment3.loadSystemset(context);
                    }
                }
                alertDialog.dismiss();
            }
        });

    }

    public   void setAreaRecycle(final Activity context, View v) {

        hh=" Areas";
        prjctcount.setText(areadatas.size()+" Areas");
        adaptertags=null;
        recycprjcts=v.findViewById(R.id.recycprjcts);
        adaptertags = new RecyclerViewAdapterTagSelection(context,areadatas,2);
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
        recycprjcts.setAdapter(adaptertags);


        adaptertags.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                prjctcount.setText(areadatas.size()+" Areas");
            }
        });

        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                if (!from.equals("setting")) {
//                    DataTag addedarea = areadatas.get(position);
//                    if ((new SqliteDb(context).addArea(CreateReport.loaddata.getPrjct(), addedarea.getTag(), false, addedarea.getTagid()))) {
//                        Utility.customToastSave("Area added to project", context, "done");
//
//
//                        // Toast.makeText(context, "Area added to project", Toast.LENGTH_SHORT).show();
//
//
//                        addedarea.setSelected(true);
//                        alertDialog.dismiss();
//                        PageReport2.prjctareas.add(addedarea);
//                        PageReportArea.setView(getOwnerActivity(), PageReport2.prjctareas);
//                    }
//
//                } else
//                    if ((new SqliteDb(context).addArea(pid, areadatas.get(position).getTag(), false, areadatas.get(position).getTagid()))) {
//
//                    Utility.customToastSave("Area added to project", context, "done");
//
//                    // Toast.makeText(context, "Area added to project", Toast.LENGTH_SHORT).show();
//                    alertDialog.dismiss();
//                    SettingFragment4.loadareaset(context);
//
//
//            }
//               AddAreaDialog.selectedtag=areadatas.get(position);
//                AddAreaDialog.tag.setText(areadatas.get(position).getTag());
//alertDialog.dismiss();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                ArrayList<DataTag> dd=new ArrayList<>();
                dd.add(areadatas.get(position));
                selectedtags.add(areadatas.get(position));
                new SqliteDb(context).addToPrjct(pid,dd,"area");


                Timer t = new Timer(false);
                if(toast.getVisibility()==View.GONE)
                {
                    toast.setVisibility(View.VISIBLE);
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            context.runOnUiThread(new Runnable() {
                                public void run() {
                                    toast.setVisibility(View.GONE);

                                }
                            });
                        }
                    }, 1000);}

                areadatas.remove(position);
                adaptertags.notifyItemRemoved(position);
                prjctcount.setText(areadatas.size()+" Area");
            }
        }));

    }
    public   void setTagRecycle(final Activity context, View v) {

hh=" Tags";
        prjctcount.setText(areadatas.size()+hh);
        adaptertags=null;
        recycprjcts=v.findViewById(R.id.recycprjcts);
        adaptertags = new RecyclerViewAdapterTagSelection(context,areadatas,2);
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
        recycprjcts.setAdapter(adaptertags);


        adaptertags.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                prjctcount.setText(areadatas.size()+" Tag");
            }
        });

        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                DataTag addedtag = areadatas.get(position);
//                if (from.equals("setting")) {
//
//                    if((new SqliteDb(context).addTag(pid,addedtag.getTag(),false, addedtag.getTagid()))){
//                        //  Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
//                        Utility.customToastSave("Tag added to project",context,"done");
//                        alertDialog.dismiss();
//                        SettingFragment6.loadtagset(context);
//                    }
//                } else
//                if((new SqliteDb(context).addTag(pid,addedtag.getTag(),false,addedtag.getTagid()))){
//                    // Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
//                    Utility.customToastSave("Tag added to project",context,"done");
//
//                    addedtag.setSelected(true);
//                    alertDialog.dismiss();
//                    PageReport2.prjcttags.add(addedtag);
//                    PageReportTag.setView(getOwnerActivity(),  PageReport2.prjcttags);
//                }
//
////                AddTagDialog.selectedtag=areadatas.get(position);
////                AddTagDialog.tag.setText(areadatas.get(position).getTag());
//
//                alertDialog.dismiss();
            }

            @Override
            public void onLongItemClick(View view, int position) {

                ArrayList<DataTag> dd=new ArrayList<>();
                dd.add(areadatas.get(position));
                selectedtags.add(areadatas.get(position));
                new SqliteDb(context).addToPrjct(pid,dd,"tag");


                Timer t = new Timer(false);
                if(toast.getVisibility()==View.GONE)
                {
                    toast.setVisibility(View.VISIBLE);
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            context.runOnUiThread(new Runnable() {
                                public void run() {
                                    toast.setVisibility(View.GONE);

                                }
                            });
                        }
                    }, 1000);}

                areadatas.remove(position);
                adaptertags.notifyItemRemoved(position);
                prjctcount.setText(areadatas.size()+" Tag");

            }
        }));

    }
    public   void setDisciplineRecycle(final Activity context, View v) {
        hh=" Discipline";

        prjctcount.setText(areadatas.size()+hh);
        adaptertags=null;
        recycprjcts=v.findViewById(R.id.recycprjcts);
        adaptertags = new RecyclerViewAdapterTagSelection(context,areadatas,2);
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
        recycprjcts.setAdapter(adaptertags);


        adaptertags.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                prjctcount.setText(areadatas.size()+" Discipline");
            }
        });

        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DataTag addedtag = areadatas.get(position);
//                if (from.equals("setting")) {
//
//                    if((new SqliteDb(context).addDiscipline(pid,addedtag.getTag(),false, addedtag.getTagid()))){
//                        //  Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
//                        Utility.customToastSave("Discipline added to project",context,"done");
//                        alertDialog.dismiss();
//                        SettingFragment5.loadDisciplineset(context);
//                    }
//                } else
//                if((new SqliteDb(context).addDiscipline(pid,addedtag.getTag(),false,addedtag.getTagid()))){
//                    // Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
//                    Utility.customToastSave("Discipline added to project",context,"done");
//
//                    addedtag.setSelected(true);
//                    alertDialog.dismiss();
//                    PageReport2.prjcttags.add(addedtag);
//                    PageReportTag.setView(getOwnerActivity(),  PageReport2.prjcttags);
//                }
//
////                AddTagDialog.selectedtag=areadatas.get(position);
////                AddTagDialog.tag.setText(areadatas.get(position).getTag());
//
//                alertDialog.dismiss();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                ArrayList<DataTag> dd=new ArrayList<>();
                dd.add(areadatas.get(position));
                selectedtags.add(areadatas.get(position));
                new SqliteDb(context).addToPrjct(pid,dd,"discipline");


                Timer t = new Timer(false);
                if(toast.getVisibility()==View.GONE)
                {
                    toast.setVisibility(View.VISIBLE);
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            context.runOnUiThread(new Runnable() {
                                public void run() {
                                    toast.setVisibility(View.GONE);

                                }
                            });
                        }
                    }, 1000);}

                areadatas.remove(position);
                adaptertags.notifyItemRemoved(position);
                prjctcount.setText(areadatas.size()+" Discipline");
            }
        }));

    }


    public   void setSystemRecycle(final Activity context, View v) {

hh="System";
        prjctcount.setText(areadatas.size()+hh);
        adaptertags=null;
        recycprjcts=v.findViewById(R.id.recycprjcts);
        adaptertags = new RecyclerViewAdapterTagSelection(context,areadatas,2);
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
        recycprjcts.setAdapter(adaptertags);


        adaptertags.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                prjctcount.setText(areadatas.size()+" System");
            }
        });

        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DataTag addedtag = areadatas.get(position);
//                if (from.equals("setting")) {
//
//                    if((new SqliteDb(context).addSystem(pid,addedtag.getTag(),false, addedtag.getTagid()))){
//                        //  Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
//                        Utility.customToastSave("System added to project",context,"done");
//                        alertDialog.dismiss();
//                        SettingFragment3.loadSystemset(context);
//                    }
//                } else
//                if((new SqliteDb(context).addSystem(pid,addedtag.getTag(),false,addedtag.getTagid()))){
//                    // Toast.makeText(context, "Tag added to project", Toast.LENGTH_SHORT).show();
//                    Utility.customToastSave("System added to project",context,"done");
//
//                    addedtag.setSelected(true);
//                    alertDialog.dismiss();
//                    PageReport2.prjcttags.add(addedtag);
//                    PageReportTag.setView(getOwnerActivity(),  PageReport2.prjcttags);
//                }
//
////                AddTagDialog.selectedtag=areadatas.get(position);
////                AddTagDialog.tag.setText(areadatas.get(position).getTag());
//
//                alertDialog.dismiss();



//                adaptertags.toggleSelection(position);
//                if(adaptertags.getSelectedCount()>0){
//                    group.setVisibility(View.VISIBLE);
//                    selected.setText(adaptertags.getSelectedCount()+" item selected");}
//                else
//                {
//
//                    group.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
                ArrayList<DataTag> dd=new ArrayList<>();
                dd.add(areadatas.get(position));
                selectedtags.add(areadatas.get(position));
                new SqliteDb(context).addToPrjct(pid,dd,"system");


                Timer t = new Timer(false);
                if(toast.getVisibility()==View.GONE)
                {
                    toast.setVisibility(View.VISIBLE);
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            context.runOnUiThread(new Runnable() {
                                public void run() {
                                    toast.setVisibility(View.GONE);

                                }
                            });
                        }
                    }, 1000);}

                areadatas.remove(position);
                adaptertags.notifyItemRemoved(position);
                prjctcount.setText(areadatas.size()+" System");
            }
        }));

    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        Toast.makeText(context, ""+hasFocus, Toast.LENGTH_SHORT).show();
//    }

    private void setSearchView() {

        cancelbtn.setVisibility(View.GONE);
//        searchbtn.setTag("search");
//        searchbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //  searchlay.setVisibility(View.VISIBLE);
//                loadbtn.setVisibility(View.GONE);
//                // searchbtn.setVisibility(View.GONE);
//                addprjct.setVisibility(View.GONE);
//                searchtxt.setText("");
//
//
//            }
//        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //searchbtn.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_search_green_24dp),null,null,null);
                // searchlay.setVisibility(View.GONE);
                // loadbtn.setVisibility(View.VISIBLE);
                //searchbtn.setVisibility(View.VISIBLE);


//                addprjct.setVisibility(View.VISIBLE);
////
               searchtxt.setText("");
                cancelbtn.setVisibility(View.GONE);
                //adaptertags.getFilter().filter("");
                if(type==0){
                    areadatas =   new SqliteDb(context).getAreas(pid);

                }
                else if(type==1){
                    areadatas =   new SqliteDb(context).getTags(pid);
                }
                else if(type==2){
                    areadatas =   new SqliteDb(context).getDiscipline(pid);

                }
                else if(type==3){

                    areadatas =   new SqliteDb(context).getSystems(pid);
                }
                adaptertags = new RecyclerViewAdapterTagSelection(context,areadatas,2);
                recycprjcts.setAdapter( AreaListDialog.adaptertags);

            }
        });
        searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //     filter(searchtext.getText().toString());
                Log.i("textt",searchtxt.getText().toString()+s);
                String charString = s.toString();
                filter(charString);

                //prjctcount.setText(areadatas.size()+" System");
                if(searchtxt.getText().toString().isEmpty())
                    cancelbtn.setVisibility(View.GONE);
                else
                    cancelbtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }


        });
    }

   public void filter(String charString) {
        if (charString.isEmpty()) {
            if(type==0){
                areadatas =   new SqliteDb(context).getAreas(pid);

            }
            else if(type==1){
                areadatas =   new SqliteDb(context).getTags(pid);
            }
            else if(type==2){
                areadatas =   new SqliteDb(context).getDiscipline(pid);

            }
            else if(type==3){

                areadatas =   new SqliteDb(context).getSystems(pid);
            }

        } else {
            ArrayList<DataTag> filteredList = new ArrayList<>();
            for (DataTag row : areadatas) {

                // name match condition. this might differ depending on your requirement
                // here we are looking for name or phone number match
                if (row.getTagid().toLowerCase().contains(charString.toLowerCase()) || row.getTag().toLowerCase().contains(charString.toLowerCase())) {
                    filteredList.add(row);
                }
            }

            areadatas = filteredList;
        }
        adaptertags = new RecyclerViewAdapterTagSelection(context,areadatas,2);
        recycprjcts.setAdapter( AreaListDialog.adaptertags);
        prjctcount.setText(areadatas.size()+hh);
    }
}
