package com.example.pentagon.appbar.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.FilterWithSpaceAdapter;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterProjectSt;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterProjects;
import com.example.pentagon.appbar.AddProjectDialog;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.DescriptionDialog;
import com.example.pentagon.appbar.ProjectListDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.RecyclerItemClickListener;
import com.example.pentagon.appbar.SharedPreferenceClass;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageReport2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageReport2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public    RecyclerViewAdapterProjects adapterprjcts;
    static ArrayList<PrjctData> prjctData;

    TableRow prjct;
    LinearLayout rowprjct;
    TableRow rowtag,rowsystem,rowarea,rowdiscipline,rowareaprjct;
    TextView prjcth;

    Button addprjct;
    static TextView prjctcount;
    static RecyclerView recycprjcts;
    public Group group;
    public LinearLayout prjcts;
View view;
    static FilterWithSpaceAdapter adapter1;
    private OnFragmentInteractionListener mListener;
   public static AutoCompleteTextView prjctname;
   public static AutoCompleteTextView description;
    public static ArrayList<DataTag> prjcttags;
    public static ArrayList<DataTag> prjctareas;
    public static ArrayList<DataTag> prjctdiscipline;
    public static ArrayList<DataTag> prjctsystem;

    public static ArrayList<PrjctData> prjctdatas;


    public PageReport2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateReport.
     */
    // TODO: Rename and change types and number of parameters
    public static PageReport2 newInstance(String param1, String param2) {
        PageReport2 fragment = new PageReport2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.viewproject, container, false);
        initilize();



        return view;

    }

    private void initilize() {
        ((Button)view.findViewById(R.id.copy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utility.savemenu.getTitle().equals("edit")){

                    Utility.optionItemSave(getActivity(),0);
                }
            loadcopy();
            }
        });
        prjctcount=view.findViewById(R.id.prjctcount);
//        group=view.findViewById(R.id.group);
//        group.setVisibility(View.VISIBLE);
        prjcts=view.findViewById(R.id.prjcts);
        prjcts.setVisibility(View.GONE);

        prjctdatas= new SqliteDb(getActivity()).getPrjcts();
        prjctname=view.findViewById(R.id.prjctname);
        description=view.findViewById(R.id.description);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DescriptionDialog(getActivity(),PageReport2.this,description.getText().toString(),true);
            }
        });
      adapter1 = new FilterWithSpaceAdapter<PrjctData> (getActivity(),android.R.layout.select_dialog_item,prjctdatas);
        prjctname.setThreshold(1);//will start working from first character
        prjctname.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView
//        prjctname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Object item1 = parent.getItemAtPosition(position);
//                if (item1 instanceof PrjctData) {
//
//                    PrjctData studentInfo = (PrjctData) item1;
//               loadprjctset(getActivity(),studentInfo);
//                }
//            }
//        });
prjctname.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // setPrjctRecycle(getActivity(),view);
        if(!Utility.savemenu.getTitle().equals("edit"))
        new ProjectListDialog(getActivity(),PageReport2.this);
    }
});
        addprjct=view.findViewById(R.id.addprjct);
        addprjct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utility.savemenu.getTitle().equals("edit")){

                    Utility.optionItemSave(getActivity(),0);
                }
                new AddProjectDialog(getActivity(),0);
            }
        });

    }

    private void setPrjctListView() {

        setPrjctRecycle(getActivity(),view);

    }

    public static void loadprjctset(Activity context, PrjctData studentInfo){
prjctname.setText(studentInfo.getId()+"-"+studentInfo.getPrjct());
    description.setText(studentInfo.getDescr());
    CreateReport.loaddata.setPrjct(studentInfo.getId());

    Log.e("isNewreport","1111"+ CreateReport.loaddata.getPrjct());
    prjcttags= new SqliteDb(context).getPrjctsTags(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId());
    prjctareas= new SqliteDb(context).getPrjctsAreas(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId());
        prjctdiscipline= new SqliteDb(context).getPrjctsDiscipline(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId());
        prjctsystem= new SqliteDb(context).getPrjctsSystem(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId());

    PageReportTag.setView(context,prjcttags);
  PageReportArea.setView(context,prjctareas);
  PageReportDiscipline.setView(context,prjctdiscipline);
        PageReportSystem.setView(context,prjctsystem);
    adapter1.notifyDataSetChanged();

}
    private void setData() {
        DataReport dd=CreateReport.loaddata;

        prjctname.setText(dd.getPrjct()+"-"+dd.getPrjctname());
        description.setText(dd.getPrjctdescr());


       prjcttags= new SqliteDb(getActivity()).getPrjctsTags(dd.getPrjct(),dd.getId());
        prjctareas= new SqliteDb(getActivity()).getPrjctsAreas(dd.getPrjct(),dd.getId());
        prjctdiscipline= new SqliteDb(getActivity()).getPrjctsDiscipline(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId());
        prjctsystem= new SqliteDb(getActivity()).getPrjctsSystem(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId());

        if(Utility.savemenu.getTitle().equals("edit")){

            prjctname.setFocusable(false);
            description.setFocusable(false);
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(CreateReport.loaddata!=null)
            setData();

    }
    //    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static void editmode(){

        prjctname.setFocusable(false);
        description.setFocusable(false);
    }
    private  void setPrjctRecycle(final Activity context,View v) {

        prjcts.setVisibility(View.VISIBLE);
        //group.setVisibility(View.GONE);
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
                prjctcount.setText(prjctData.size()+" Projects");
            }
        });

        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            //group.setVisibility(View.VISIBLE);
            prjcts.setVisibility(View.GONE);

                prjctname.setText(prjctdatas.get(position).getPrjct());
                loadprjctset(getActivity(),prjctdatas.get(position));

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    public void loadcopy(){

        String id=  new SharedPreferenceClass().getStoredValueLastPrjct(getContext());
        if(id==null)
            return;
        ArrayList<DataReport> dataTags;
     // new SqliteDb(getActivity()).copyTagsPrjct(CreateReport.loaddata.getId(),id);
     // if(!(CreateReport.loaddata.getPrjct()==null||CreateReport.loaddata.getPrjct().equals("")))

        //  new SqliteDb(getActivity()).insertToPrjctTags(new SqliteDb(getActivity()).getPrjctsTags(id,""),CreateReport.loaddata.getPrjct());


ArrayList<PrjctData> pp= new SqliteDb(getActivity()).getPrjct(id);
      if(pp.size()>0)
 loadprjctset(getActivity(),pp.get(0));
      else
          Toast.makeText(getActivity(), "Project not existing", Toast.LENGTH_SHORT).show();
     //   prjcttags= new SqliteDb(getActivity()).getPrjctsTags(dd.getPrjct(),dd.getId());
      //  prjctareas= new SqliteDb(getActivity()).getPrjctsAreas(dd.getPrjct(),dd.getId());
      //  setView(getActivity(),  CreateReport.dataDisciplines);


}

}
