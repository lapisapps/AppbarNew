package com.example.pentagon.appbar.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.CustomPagerAdapter;
import com.example.pentagon.appbar.AdapterClass.VerticalViewPager;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.DescriptionDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageReport1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageReport1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageReport1 extends Fragment {
    String rprefix="pdesk";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
  public static    AutoCompleteTextView reportname,reportid;
    public static AutoCompleteTextView description;
View view;
    private OnFragmentInteractionListener mListener;

    public PageReport1() {
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
    public static PageReport1 newInstance(String param1, String param2) {
        PageReport1 fragment = new PageReport1();
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
        view=  inflater.inflate(R.layout.viewreport, container, false);
        reportid=view.findViewById(R.id.reportno);

//reportid.setValidator();




        reportname=view.findViewById(R.id.reportname);
        description=view.findViewById(R.id.description);
        reportname.setNextFocusForwardId(description.getId());
description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus)
            new DescriptionDialog(getActivity(),PageReport1.this,description.getText().toString(),true);
    }
});
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DescriptionDialog(getActivity(),PageReport1.this,description.getText().toString(),true);
            }
        });
        if(CreateReport.loaddata!=null&&CreateReport.loaddata.getId()!=null){

            CreateReport.loaddata.setNewreport(false);
        setData();
        }else {


            //Toast.makeText(getActivity(), "fffff", Toast.LENGTH_SHORT).show();
            CreateReport.loaddata=new DataReport();
            CreateReport.loaddata.setNewreport(true);

            reportname.setEnabled(true);
            description.setEnabled(true);
            String rid=rprefix+new SqliteDb(getActivity()).getReportID();
            Log.e("isNewreport","1111"+ CreateReport.loaddata.getPrjct()+rid);

        reportid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {

                  //  reportid.setFocusable(true);
             //  Toast.makeText(getActivity(), "Got the focus", Toast.LENGTH_LONG).show();
                } else {

                    if(!new SqliteDb(getActivity()).getReport(reportid.getText().toString())){

                     //   Toast.makeText(getActivity(), "Report number duplication", Toast.LENGTH_SHORT).show();
                  //   if(!CreateReport.loaddata.getId().equals(reportid.getText().toString()))
                      reportid.setError("Report number duplication");
              //   CreateReport.viewPager.setCurrentItem(0);
                 View v=reportid;
                        reportname.clearFocus();
                        v.setFocusable(true);
                        v.setFocusableInTouchMode(true);
                        v.requestFocus();


                    }
                   // Toast.makeText(getActivity(), "Lost the focus", Toast.LENGTH_LONG).show();
                }
            }
        });
            reportid.setText(rid);
        }

        return view;

    }

    private void setData() {
        DataReport dd=CreateReport.loaddata;
        Log.e("ddd",dd.getReportname());
reportid.setText(dd.getId());
        reportname.setText(dd.getReportname());
        description.setText(dd.getDescrpotion());
        if(Utility.savemenu.getTitle().equals("edit")){
            reportid.setFocusable(false);
            reportname.setFocusable(false);
          //  description.setFocusable(false);
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public  void setEnable(boolean b) {
       // Toast.makeText(getContext(), "ffff", Toast.LENGTH_SHORT).show();
reportname.setEnabled(b);


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
}
