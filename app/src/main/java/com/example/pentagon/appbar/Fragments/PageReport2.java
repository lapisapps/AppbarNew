package com.example.pentagon.appbar.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.pentagon.appbar.AdapterClass.FilterWithSpaceAdapter;
import com.example.pentagon.appbar.AddProjectDialog;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.R;
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
View view;
    static FilterWithSpaceAdapter adapter1;
    private OnFragmentInteractionListener mListener;
   public static AutoCompleteTextView prjctname;
   public static AutoCompleteTextView description;
    public static ArrayList<DataTag> prjcttags;
    public static ArrayList<DataTag> prjctareas;
    public static ArrayList<PrjctData> prjctdatas;
    private Button addprjct;

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
        prjctdatas= new SqliteDb(getActivity()).getPrjcts();
        prjctname=view.findViewById(R.id.prjctname);
        description=view.findViewById(R.id.description);
      adapter1 = new FilterWithSpaceAdapter<PrjctData> (getActivity(),android.R.layout.select_dialog_item,prjctdatas);
        prjctname.setThreshold(1);//will start working from first character
        prjctname.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView
        prjctname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item1 = parent.getItemAtPosition(position);
                if (item1 instanceof PrjctData) {

                    PrjctData studentInfo = (PrjctData) item1;
               loadprjctset(getActivity(),studentInfo);
                }
            }
        });

        addprjct=view.findViewById(R.id.addprjct);
        addprjct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utility.savemenu.getTitle().equals("edit")){

                    Utility.optionItemSave(getActivity());
                }
                new AddProjectDialog(getActivity(),0);
            }
        });

    }
public static void loadprjctset(Activity context, PrjctData studentInfo){

    description.setText(studentInfo.getDescr());
    CreateReport.loaddata.setPrjct(studentInfo.getId());

    Log.e("isNewreport","1111"+ CreateReport.loaddata.getPrjct());
    prjcttags= new SqliteDb(context).getPrjctsTags(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId());
    prjctareas= new SqliteDb(context).getPrjctsAreas(CreateReport.loaddata.getPrjct(),CreateReport.loaddata.getId());

    PageReportTag.setView(context,prjcttags);
    PageReportArea.setView(context,prjctareas);
    adapter1.notifyDataSetChanged();

}
    private void setData() {
        DataReport dd=CreateReport.loaddata;

        prjctname.setText(dd.getPrjctname());
        description.setText(dd.getPrjctdescr());


       prjcttags= new SqliteDb(getActivity()).getPrjctsTags(dd.getPrjct(),dd.getId());
        prjctareas= new SqliteDb(getActivity()).getPrjctsAreas(dd.getPrjct(),dd.getId());

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
}
