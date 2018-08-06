package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.design.widget.TextInputLayout;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterProjects;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTagSelection;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTagSt;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTags;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.FragmentDataViewAudio;
import com.example.pentagon.appbar.Fragments.FragmentDataViewChild;
import com.example.pentagon.appbar.Fragments.FragmentDataViewImage;
import com.example.pentagon.appbar.Fragments.FragmentDataViewVideo;
import com.example.pentagon.appbar.Fragments.PageReport1;
import com.example.pentagon.appbar.Fragments.PageReport2;
import com.example.pentagon.appbar.Fragments.PageReportDiscipline;
import com.example.pentagon.appbar.Fragments.PageReportSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ProjectListDialog extends Dialog {

    private  int type;
    private  ArrayList<DataTag> otagData;
    private static ArrayList<DataTag> tagData;
    private  ArrayList<DataTag> existtagData;
Group group;

    public  ArrayList<PrjctData> prjctdatas;
    public    RecyclerViewAdapterProjects adapterprjcts;
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
    String hh="Discipline";
    public ProjectListDialog(@NonNull Activity mContext,int type) {
        super(mContext);

        context = mContext;

        android.app.AlertDialog.Builder builder;
        alertDialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.prjctlistdialog,
                null);


alertDialog.setContentView(layout);

this.type=type;


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
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){


            setDisciplineRecycle(context,layout,existtagData);
                }
                else {


                    setDisciplineRecycle(context,layout,tagData);
                }
            }
        });
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
                        DataTag model = otagData.get(selected.keyAt(i));
                        model.setSelected(true);
selectedtags.add(model);

                    }
                }
                if(type==2) {
                    //new SqliteDb(getContext()).insertReportDiscipline(selectedtags, PageReport1.reportid.getText().toString());
                   // tagData = new SqliteDb(context).getReportDiscipline(CreateReport.loaddata.getId());
                    CreateReport.dataDisciplines.addAll(selectedtags);
                   PageReportDiscipline.setView(context,CreateReport.dataDisciplines);
                }  else {
                    //new SqliteDb(getContext()).insertReportSystem(selectedtags, PageReport1.reportid.getText().toString());
                   // tagData = new SqliteDb(context).getReportSystem(CreateReport.loaddata.getId());
                    CreateReport.dataSystems.addAll(selectedtags);
                    PageReportSystem.setView(context,CreateReport.dataSystems);
                }
            alertDialog.dismiss();
            }
        });
        otagData=new ArrayList<>();
        head=layout.findViewById(R.id.head);

group=layout.findViewById(R.id.group2);
        prjctcount=layout.findViewById(R.id.prjctcount);
        selected=layout.findViewById(R.id.selected);
        group.setVisibility(View.GONE);
        if(type==0){
ch.setVisibility(View.GONE);
            prjctdatas = PageReport2.prjctdatas;

            setPrjctRecycle(context,layout);
        }
        else if(type==2){
            head.setText("Select Disciplines");
            tagData= new SqliteDb(context).getDiscipline("0");
        tagData=Utility.compare(tagData,CreateReport.dataDisciplines);
            existtagData= new SqliteDb(context).getDiscipline("");
            existtagData=Utility.compare(existtagData,CreateReport.dataDisciplines);


        setDisciplineRecycle(context,layout,tagData);
        }
        else{
            head.setText("Select Systems");
            tagData=new SqliteDb(context).getSystems("0");
            tagData=Utility.compare(tagData,CreateReport.dataSystems);
            Log.e("ffff1",otagData.size()+"");
            existtagData=new SqliteDb(context).getSystems("");
            existtagData=Utility.compare(existtagData,CreateReport.dataSystems);
            setDisciplineRecycle(context,layout,tagData);
        }


    }

    private  void setPrjctRecycle(final Activity context, View v) {


        prjctcount.setText(prjctdatas.size()+" Projects");
        adapterprjcts=null;
        recycprjcts=v.findViewById(R.id.recycprjcts);
        adapterprjcts = new RecyclerViewAdapterProjects(context,prjctdatas,recycprjcts);
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
                prjctcount.setText(prjctdatas.size()+" Projects");
            }
        });

        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {



                PageReport2.loadprjctset(context,prjctdatas.get(position));
alertDialog.dismiss();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    private  void setDisciplineRecycle(final Activity context, View v, final ArrayList<DataTag> dd) {

        if(type==1)
   hh="System";

otagData=dd;
        prjctcount.setText(otagData.size()+" "+hh);
        adaptertags=null;
        recycprjcts=v.findViewById(R.id.recycprjcts);
        adaptertags = new RecyclerViewAdapterTagSelection(context,otagData,2);
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
                prjctcount.setText(otagData.size()+""+hh);
            }
        });

        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adaptertags.toggleSelection(position);
                if(adaptertags.getSelectedCount()>0){
                 group.setVisibility(View.VISIBLE);
                selected.setText(adaptertags.getSelectedCount()+" item selected");}
                else
                {

                    group.setVisibility(View.GONE);
                }

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

}
