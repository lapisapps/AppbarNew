package com.example.pentagon.appbar.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTags;
import com.example.pentagon.appbar.AddDisciplineDialog;
import com.example.pentagon.appbar.AddProjectDialog;
import com.example.pentagon.appbar.AddTagDialog;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.ProjectListDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SharedPreferenceClass;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageReportDiscipline.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageReportDiscipline#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageReportDiscipline extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
View view;
public static RecyclerView recyclerView;
    public static TextView addtag;

    private OnFragmentInteractionListener mListener;

    public PageReportDiscipline() {
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
    public static PageReportDiscipline newInstance(String param1, String param2) {
        PageReportDiscipline fragment = new PageReportDiscipline();
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
        view=  inflater.inflate(R.layout.viewdiscipline, container, false);

initilize();
if(CreateReport.dataDisciplines==null)
    CreateReport.dataDisciplines=new ArrayList<>();

    setView(getActivity(),CreateReport.dataDisciplines);
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
        recyclerView=view.findViewById(R.id.recyclerView);
        addtag=view.findViewById(R.id.addtag);
        addtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(getContext(), addtag);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popupaddnew, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("New")) {
                            if(Utility.savemenu.getTitle().equals("edit")){

                                Utility.optionItemSave(getActivity(),0);
                            }
                            new AddDisciplineDialog(getActivity(), 0);
                        }  else{
                            if(Utility.savemenu.getTitle().equals("edit")){

                                Utility.optionItemSave(getActivity(),0);
                            }
                            new ProjectListDialog(getActivity(),2);}
                        return true;
                    }
                });

                popup.show(); //showing popup menu

            }
        });
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

    public static void setView(final Activity context, ArrayList<DataTag> dataTags){
        CreateReport.dataDisciplines=dataTags;
        Log.e("tagss",dataTags.size()+"");
        RecyclerViewAdapterTags place = new RecyclerViewAdapterTags(context,dataTags,"disciplines");
        recyclerView.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recyclerView.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(place);




//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, final int position) {
//
//
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));

    }

    public void loadcopy(){

      String id=  new SharedPreferenceClass().getStoredValueLastDiscipline(getContext());
      ArrayList<DataTag> dataTags=new SqliteDb(getContext()).getReportDiscipline(id);
      CreateReport.dataDisciplines.addAll(Utility.compare(dataTags,CreateReport.dataDisciplines));
      setView(getActivity(),  CreateReport.dataDisciplines);

    }
}
