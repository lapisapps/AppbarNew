package com.example.pentagon.appbar.Fragments.SettingsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTagSt;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;


public class FragmentSettingTag extends Fragment{
    View v;

    ArrayList<DataTag> tagData;
    ArrayList<PrjctData> prjctData;


    RecyclerView recyctags;
    private RecyclerViewAdapterTagSt adaptertags;

    public FragmentSettingTag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   v= inflater.inflate(R.layout.fragment_settags, container, false);

        recyctags=v.findViewById(R.id.recyprjcts);
      //  tagData= new SqliteDb(getActivity()).getTags();
        if(prjctData==null)
            prjctData= new SqliteDb(getActivity()).getPrjcts();
Spinner spinner=v.findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter<PrjctData>(getActivity(),R.layout.spinner,prjctData);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        tagData=new ArrayList<>();
        setTagRecycle();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Object item1 = parentView.getItemAtPosition(position);
                if (item1 instanceof PrjctData) {

                    PrjctData tag = (PrjctData) item1;

                 tagData=new SqliteDb(getContext()).getPrjctsTags(tag.getId(),"");
setTagRecycle();
                    //setProjectTags(0);
//                if(prjcttags.size()>0){
//                    prjcttags.get(0).setSelected(true);
//                    loadTag(prjcttags.get(0));}
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        return  v ;
    }

    private void setTagRecycle() {


//        int n=tagData.size();
//        if(n>6)
//            n=6;

        adaptertags = new RecyclerViewAdapterTagSt(getActivity(),tagData,0);
        recyctags.setVisibility(View.VISIBLE);

        recyctags.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyctags.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recyctags.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyctags.setItemAnimator(new DefaultItemAnimator());
        recyctags.setAdapter(adaptertags);




//        recyimages.addOnItemTouchListener(new RecyclerItemClickListener(context, recyimages, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                new PreviewDialog(context,ddimage.get(position),false);
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));

    }

}

