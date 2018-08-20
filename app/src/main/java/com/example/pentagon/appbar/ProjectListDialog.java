package com.example.pentagon.appbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
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
import com.example.pentagon.appbar.Fragments.SettingFragment3;
import com.example.pentagon.appbar.Fragments.SettingFragment4;
import com.example.pentagon.appbar.Fragments.SettingFragment5;
import com.example.pentagon.appbar.Fragments.SettingFragment6;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ProjectListDialog extends Dialog {

    private Fragment type;
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
  public  Fragment fragment;
    TextView head;
    Button add;
    Button close;
CheckBox ch;
    View layout;
    String hh="Discipline";
    private AutoCompleteTextView searchtxt;
    Button cancelbtn;

    public ProjectListDialog(@NonNull Activity mContext,Fragment type) {
        super(mContext);

        context = mContext;

        android.app.AlertDialog.Builder builder;
        alertDialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.prjctlistdialog,
                null);


alertDialog.setContentView(layout);

this.fragment=type;


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
        setSearchView();
    }

    private void initlize() {
        ((FloatingActionButton)  layout.findViewById(R.id.addnew)).setVisibility(View.GONE);
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
                if(fragment.getClass().equals(PageReportDiscipline.class)) {
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
        if(fragment.getClass().equals(PageReport2.class)||fragment.getClass().equals(SettingFragment4.class)||fragment.getClass().equals(SettingFragment6.class)||fragment.getClass().equals(SettingFragment3.class)||fragment.getClass().equals(SettingFragment5.class)){
ch.setVisibility(View.GONE);
            prjctdatas = new SqliteDb(context).getPrjcts();

            setPrjctRecycle(context,layout);
        }
        else if(fragment.getClass().equals(PageReportDiscipline.class)){
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

                if (fragment.getClass().equals(PageReport2.class)) {

                    PageReport2.loadprjctset(context,prjctdatas.get(position));
                }
                else if (fragment.getClass().equals(SettingFragment4.class)) {
                    SettingFragment4.selectedprjct.setTag(prjctdatas.get(position));
                    SettingFragment4.selectedprjct.setText(prjctdatas.get(position).getPrjct());
                    SettingFragment4.loadareaset(context);
                }
                else if (fragment.getClass().equals(SettingFragment6.class)) {
                    SettingFragment6.selectedprjct.setTag(prjctdatas.get(position));
                    SettingFragment6.selectedprjct.setText(prjctdatas.get(position).getPrjct());
                    SettingFragment6.loadtagset(context);
                }
                else if (fragment.getClass().equals(SettingFragment5.class)) {
                    SettingFragment5.selectedprjct.setTag(prjctdatas.get(position));
                    SettingFragment5.selectedprjct.setText(prjctdatas.get(position).getPrjct());
                    SettingFragment5.loadDisciplineset(context);
                }
                else if (fragment.getClass().equals(SettingFragment3.class)) {
                    SettingFragment3.selectedprjct.setTag(prjctdatas.get(position));
                    SettingFragment3.selectedprjct.setText(prjctdatas.get(position).getPrjct());
                    SettingFragment3.loadSystemset(context);
                }
alertDialog.dismiss();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    private  void setDisciplineRecycle(final Activity context, View v, final ArrayList<DataTag> dd) {

        if(fragment.getClass().equals(PageReportSystem.class))
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
    private void setSearchView() {
        searchtxt=layout.findViewById(R.id.searchtext);
        cancelbtn=layout.findViewById(R.id.cancel);
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




                searchtxt.setText("");
                cancelbtn.setVisibility(View.GONE);
                adapterprjcts.getFilter().filter("");

            }
        });
        searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //     filter(searchtext.getText().toString());
                Log.i("textt",searchtxt.getText().toString()+s);
               // adapterprjcts.getFilter().filter(searchtxt.getText().toString());

                filter(searchtxt.getText().toString());
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

            prjctdatas = new SqliteDb(context).getPrjcts();
        } else {
            ArrayList<PrjctData> filteredList = new ArrayList<>();
            for (PrjctData row : prjctdatas) {

                // name match condition. this might differ depending on your requirement
                // here we are looking for name or phone number match
                if (row.getPrjct().toLowerCase().contains(charString.toLowerCase()) || row.getId().toLowerCase().contains(charString.toLowerCase())) {
                    filteredList.add(row);
                }
            }

            prjctdatas = filteredList;
        }
        adapterprjcts = new RecyclerViewAdapterProjects(context,prjctdatas,recycprjcts);

        recycprjcts.setAdapter(adapterprjcts);
        prjctcount.setText(prjctdatas.size()+"Project");
    }
}
