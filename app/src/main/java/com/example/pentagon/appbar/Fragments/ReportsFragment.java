package com.example.pentagon.appbar.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.Toolbar_ActionMode_Callback;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.HomeActivity;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.RecyclerItemClickListener;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterReports;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReportsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReportsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ActionMode mActionMode;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
View view;
  public   RecyclerViewAdapterReports Radpater;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private ArrayList<DataReport> dataReports;
   public static HomeActivity homeactivity;

    public ReportsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportsFragment newInstance(String param1, String param2) {
        ReportsFragment fragment = new ReportsFragment();
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
        view=inflater.inflate(R.layout.fragment_reports, container, false);
       if(Utility.savemenu!=null) {
           Utility.savemenu.setVisible(false);
           Utility.sharemenu.setVisible(false);
       }
        initlize();
        Utility.newreport.setVisibility(View.VISIBLE);
        Utility.camera.setVisibility(View.GONE);
        Utility.fab1.setVisibility(View.GONE);
        Utility.fab2.setVisibility(View.GONE);
        Utility.fab3.setVisibility(View.GONE);
        dataReports=new ArrayList<>();
        dataReports=  new SqliteDb(getContext()).getReports(null);
        Log.e("reports",dataReports.size()+"");
        ((FloatingActionButton)view.findViewById(R.id.fab1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( ((FloatingActionButton)view.findViewById(R.id.fab1)).getTag().equals("list")){

                    setReportsGrid();
                    ((FloatingActionButton)view.findViewById(R.id.fab1)).setTag("grid");
                }
                else
                {

                    setReportsList();
                    ((FloatingActionButton)view.findViewById(R.id.fab1)).setTag("list");
                }
            }
        });
        setReportsList();
        return view;
    }

    private void setReportsList() {
        Radpater = new RecyclerViewAdapterReports(getActivity(),dataReports,"list");
        recyclerView.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recyclerView.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Radpater);




        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                if (mActionMode != null)
                    onListItemSelect(position);
                else {
                    CreateReport createReport = new CreateReport();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, createReport);
                    //  getSupportFragmentManager().popBackStack();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("report", (Serializable) dataReports.get(position));
                    Utility.savemenu.setIcon(getActivity().getResources().getDrawable(R.drawable.ic_edit_black_24dp));
                    Utility.savemenu.setTitle("edit");
                    createReport.setArguments(bundle);
                    Utility.savemenu.setVisible(true);
                    Utility.sharemenu.setVisible(true);
                    transaction.addToBackStack(null);
                    transaction.commit();
//                CreateReport fragment = new CreateReport();
//                FragmentTransaction ft =getFragmentManager().beginTransaction();
//
//
//                ft.replace(R.id.container, CreateReport.newInstance(dataReports.get(position),""));
//
//                ft.addToBackStack(null);
//                getFragmentManager().popBackStack();
//                ft.commit();
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
                onListItemSelect(position);
            }
        }));
    }

    private void initlize() {
        recyclerView=view.findViewById(R.id.recyclerview);
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

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

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
    public void setReportsGrid(){




   Radpater = new RecyclerViewAdapterReports(getActivity(),dataReports,"grid");
        recyclerView.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recyclerView.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Radpater);




        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                if (mActionMode != null)
                    onListItemSelect(position);
                else {
                    CreateReport createReport = new CreateReport();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, createReport);
                    //  getSupportFragmentManager().popBackStack();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("report", (Serializable) dataReports.get(position));
                    Utility.savemenu.setIcon(getActivity().getResources().getDrawable(R.drawable.ic_edit_black_24dp));
                    Utility.savemenu.setTitle("edit");
                    createReport.setArguments(bundle);
                    Utility.savemenu.setVisible(true);
                    Utility.sharemenu.setVisible(true);
                    transaction.addToBackStack(null);
                    transaction.commit();
//                CreateReport fragment = new CreateReport();
//                FragmentTransaction ft =getFragmentManager().beginTransaction();
//
//
//                ft.replace(R.id.container, CreateReport.newInstance(dataReports.get(position),""));
//
//                ft.addToBackStack(null);
//                getFragmentManager().popBackStack();
//                ft.commit();
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
                onListItemSelect(position);
            }
        }));
    }
    public void replace(){
        //  android.support.v4.app.Fragment  fragment = null;
//        NewSaleFragment fragment = new NewSaleFragment();
//        FragmentTransaction ft =getFragmentManager().beginTransaction();
//        Utility.ft=ft;
//
//        ft.replace(R.id.content_frame, fragment);
//        ft.commit();
    }
    private void onListItemSelect(int position) {
        Radpater.toggleSelection(position);//Toggle the selection

        boolean hasCheckedItems = Radpater.getSelectedCount() > 0;//Check if any items are already selected or not


        if (hasCheckedItems && mActionMode == null)
            // there are some selected items, start the actionMode
            mActionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(new Toolbar_ActionMode_Callback(getActivity(),Radpater, dataReports,this));
        else if (!hasCheckedItems && mActionMode != null)
            // there no selected items, finish the actionMode
            mActionMode.finish();

        if (mActionMode != null)
            //set action mode title on item selection
            mActionMode.setTitle(String.valueOf(Radpater
                    .getSelectedCount()) + " selected");


    }
    //Set action mode null after use
    public void setNullToActionMode() {
     //   Toast.makeText(getActivity(), "Ttttt", Toast.LENGTH_SHORT).show();
        if (mActionMode != null)
            mActionMode = null;
    }

    //Delete selected rows
    public void deleteRows() {
        Log.e("adapter",Radpater+"");
//Get selected ids

        //Loop all selected ids

        android.app.AlertDialog.Builder builder = Utility.alertdialog(getActivity(), "Delete", "Selected Reports data will be deleted");
        // final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        //
        // builder.setTitle("Autograde");
        //  builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SparseBooleanArray selected = Radpater.getSelectedIds();
                ArrayList<DataReport> dltreports=new ArrayList<>();
                for (int i = (selected.size() - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        //If current id is selected remove the item via key

                        dltreports.add(dataReports.get(selected.keyAt(i)));
                        dataReports.remove(selected.keyAt(i));
                        Radpater.notifyDataSetChanged();//notify adapter

                    }
                }


                new SqliteDb(getActivity()).deleteReports(dltreports);
                Toast.makeText(getActivity(), selected.size() + " reports deleted.", Toast.LENGTH_SHORT).show();//Show Toast
                mActionMode.finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        builder.show();

       //Finish action mode after use

    }
    @Override
    public void onResume() {

        super.onResume();
        ActionBar actionBar = ((HomeActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Reports");

        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setIcon(null);
    }
}
