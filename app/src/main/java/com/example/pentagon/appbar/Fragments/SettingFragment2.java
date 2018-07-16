package com.example.pentagon.appbar.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterProjectSt;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTagSt;
import com.example.pentagon.appbar.AddTagDialog;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.HomeActivity;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SettingFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SettingFragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
Button addtag;

    static ArrayList<DataTag> tagData;
    ArrayList<PrjctData> prjctData;

    static Spinner spinner;
    static RecyclerView recyctags;
    private static RecyclerViewAdapterTagSt adaptertags;
    public SettingFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment2 newInstance(String param1, String param2) {
        SettingFragment2 fragment = new SettingFragment2();
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
        v= inflater.inflate(R.layout.fragmentsettagnew, container, false);

        recyctags=v.findViewById(R.id.recyprjcts);
        //  tagData= new SqliteDb(getActivity()).getTags();
        if(prjctData==null)
            prjctData= new SqliteDb(getActivity()).getPrjcts();
        spinner=v.findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter<PrjctData>(getActivity(),R.layout.spinner,prjctData);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        tagData=new ArrayList<>();
        setTagRecycle(getActivity());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Object item1 = parentView.getItemAtPosition(position);
                if (item1 instanceof PrjctData) {

                    PrjctData tag = (PrjctData) item1;

                    tagData=new SqliteDb(getContext()).getPrjctsTags(tag.getId(),"");
                    setTagRecycle(getActivity());
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
initilize();

        return v;
    }

    private void initilize() {

       addtag=v.findViewById(R.id.addtag) ;
       addtag.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if((PrjctData)spinner.getSelectedItem()==null)
                   Toast.makeText(getActivity(), "No projects found", Toast.LENGTH_SHORT).show();
                   else{
               AddTagDialog.prjctData=(PrjctData)spinner.getSelectedItem();
               new AddTagDialog(getActivity(),1);}
           }
       });
    }


    private static void setTagRecycle(Activity context) {


//        int n=tagData.size();
//        if(n>6)
//            n=6;

        adaptertags = new RecyclerViewAdapterTagSt(context,tagData,0);
        recyctags.setVisibility(View.VISIBLE);

        recyctags.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
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
    public static void loadtagset(Activity context) {

        tagData= new SqliteDb(context).getPrjctsTags(((PrjctData)spinner.getSelectedItem()).getId(),"");
        setTagRecycle(context);
        recyctags.scrollToPosition(tagData.size()-1);

    }
    @Override
    public void onDetach() {
        super.onDetach();

    }



//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.fragment_menu, menu);
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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
