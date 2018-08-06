package com.example.pentagon.appbar.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTags;
import com.example.pentagon.appbar.AddTagDialog;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SharedPreferenceClass;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

import static com.example.pentagon.appbar.Fragments.PageReport2.loadprjctset;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageReportTag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageReportTag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageReportTag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
View view;
public static RecyclerView recyclerView;
    public static Button addtag;

    private OnFragmentInteractionListener mListener;

    public PageReportTag() {
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
    public static PageReportTag newInstance(String param1, String param2) {
        PageReportTag fragment = new PageReportTag();
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
        view=  inflater.inflate(R.layout.viewtags, container, false);

initilize();
if(PageReport2.prjcttags!=null)
    setView(getActivity(), PageReport2.prjcttags);
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

                if(Utility.savemenu.getTitle().equals("edit")){

                    Utility.optionItemSave(getActivity(),0);
                }
                if(CreateReport.loaddata.getPrjct()!=null&&!CreateReport.loaddata.getPrjct().equals(""))
                new AddTagDialog(getActivity(),0);
                else
                    Toast.makeText(getContext(), "Select Project", Toast.LENGTH_SHORT).show();
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
        PageReport2.prjcttags=dataTags;
        Log.e("tagss1", PageReport2.prjcttags.size()+"");
        RecyclerViewAdapterTags place = new RecyclerViewAdapterTags(context,dataTags,"tags");
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

        String id=  new SharedPreferenceClass().getStoredValueLastPrjct(getContext());
        if(id==null)
            return;
        ArrayList<DataReport> dataTags;
        // new SqliteDb(getActivity()).copyTagsPrjct(CreateReport.loaddata.getId(),id);
         if((CreateReport.loaddata.getPrjct()==null||CreateReport.loaddata.getPrjct().equals("")))
         {  Toast.makeText(getContext(), "Select Project", Toast.LENGTH_SHORT).show();

         return;}
          new SqliteDb(getActivity()).insertToPrjctTags(new SqliteDb(getActivity()).getPrjctsTags(id,""),CreateReport.loaddata.getPrjct());


        ArrayList<PrjctData> pp= new SqliteDb(getActivity()).getPrjct(CreateReport.loaddata.getPrjct());
        if(pp.size()>0)
            loadprjctset(getActivity(),pp.get(0));
        else
            Toast.makeText(getActivity(), "Project not existing", Toast.LENGTH_SHORT).show();
        //   prjcttags= new SqliteDb(getActivity()).getPrjctsTags(dd.getPrjct(),dd.getId());
        //  prjctareas= new SqliteDb(getActivity()).getPrjctsAreas(dd.getPrjct(),dd.getId());
        //  setView(getActivity(),  CreateReport.dataDisciplines);


    }
}
