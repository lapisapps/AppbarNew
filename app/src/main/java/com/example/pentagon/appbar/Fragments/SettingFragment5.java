package com.example.pentagon.appbar.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterDisciplineSt;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterSystemSt;
import com.example.pentagon.appbar.AddDisciplineDialog;
import com.example.pentagon.appbar.AddSystemDialog;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.HomeActivity;
import com.example.pentagon.appbar.ImportListDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SettingFragment5#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SettingFragment5 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int expandpos=-1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static View v;
    public   static RecyclerViewAdapterDisciplineSt adapterdiscipline;
    static ArrayList<DataTag> disciplineData;
    static ArrayList<DataTag> systemData;
    TableRow discipline,tag,system;
    LinearLayout rowsystem,rowarea,rowdiscipline;
    TextView prjcth;
    TextView tagh;
    TextView systemh;
    TextView areah;
    TextView disciplineh;
    Button adddiscipline,addsystem;
    static TextView disciplinecount, systemcount;
    static RecyclerView recycdiscipline;
    static RecyclerView recycsystem;
    private static RecyclerViewAdapterSystemSt adapterSystem;
    static int height;
    static Spinner spinner;

    Button searchbtn,cancelbtn,loadbtn;
    AutoCompleteTextView searchtxt;
    ConstraintLayout searchlay;
    public SettingFragment5() {
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
    public static SettingFragment5 newInstance(String param1, String param2) {
        SettingFragment5 fragment = new SettingFragment5();
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
        v= inflater.inflate(R.layout.fragment_setdiscipline, container, false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        //  width = displayMetrics.widthPixels;
        initilize();

        return v;
    }

//    private void setDisciplineView() {
//
//        if(prjctData==null)
//            prjctData= new SqliteDb(getActivity()).getPrjcts();
//        spinner=v.findViewById(R.id.spinner);
//        ArrayAdapter aa = new ArrayAdapter<PrjctData>(getActivity(),R.layout.spinner,prjctData);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(aa);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                // your code here
//                Object item1 = parentView.getItemAtPosition(position);
//                if (item1 instanceof PrjctData) {
//
//                    PrjctData tag = (PrjctData) item1;
//
//                    areaData=new SqliteDb(getContext()).getPrjctsAreas(tag.getId(),"");
//                    setAreaRecycle(getActivity());
//                    //setProjectTags(0);
////                if(prjcttags.size()>0){
////                    prjcttags.get(0).setSelected(true);
////                    loadTag(prjcttags.get(0));}
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // your code here
//            }
//
//        });
//    }


    public void initilize(){

        recycsystem=v.findViewById(R.id.recycarea);
        discipline=v.findViewById(R.id.prjct);
        disciplinecount=v.findViewById(R.id.prjctcount);
        systemcount=v.findViewById(R.id.areacount);
        adddiscipline=v.findViewById(R.id.addprjct);
        addsystem=v.findViewById(R.id.addarea);
        adddiscipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtxt.setText("");
                if(disciplineh.getTag().toString().equals("0"))
                    expandPrjctRow();

                new AddDisciplineDialog(getActivity(),1);
            }
        });

        system=v.findViewById(R.id.area);

        rowsystem=v.findViewById(R.id.rowarea);
       // rowareaprjct=v.findViewById(R.id.areaprjct);
        rowdiscipline=v.findViewById(R.id.rowprjcts);
        rowsystem=v.findViewById(R.id.rowarea);


       // rowtag=v.findViewById(R.id.rowtags);

       disciplineh=v.findViewById(R.id.hprjct);
        systemh=v.findViewById(R.id.harea);


        searchtxt=v.findViewById(R.id.searchtext);
        searchlay=v.findViewById(R.id.searchlay);
      //  searchlay.setVisibility(View.GONE);
        loadbtn=v.findViewById(R.id.load);

        searchbtn=v.findViewById(R.id.search);
        cancelbtn=v.findViewById(R.id.cancel);
        setSearchView();

        loadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtxt.setText("");
                new ImportListDialog(getActivity(),"discipline");
            }
        });

        disciplineData= new SqliteDb(getActivity()).getDiscipline("");
        Log.e("ddd",disciplineData.size()+"");
       // systemData= new SqliteDb(getActivity()).getSystems();
        setDisplineRecycle(getActivity());

      //  setSystemRecycle(getActivity());
        expandPrjctRow();
    }
    private void setSearchView() {
        cancelbtn.setVisibility(View.GONE);
        searchbtn.setTag("search");
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //searchlay.setVisibility(View.VISIBLE);
                loadbtn.setVisibility(View.GONE);
               // searchbtn.setVisibility(View.GONE);
                adddiscipline.setVisibility(View.GONE);
                searchtxt.setText("");


            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //searchbtn.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_search_green_24dp),null,null,null);
               // searchlay.setVisibility(View.GONE);
                loadbtn.setVisibility(View.VISIBLE);
               // searchbtn.setVisibility(View.VISIBLE);
                adddiscipline.setVisibility(View.VISIBLE);
                searchtxt.setText("");
                cancelbtn.setVisibility(View.GONE);
                adapterdiscipline.getFilter().filter("");

            }
        });
        searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //     filter(searchtext.getText().toString());
                Log.i("textt",searchtxt.getText().toString()+s);
                adapterdiscipline.getFilter().filter(searchtxt.getText().toString());
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


    private void expandPrjctRow() {
        if(disciplineh.getTag().toString().equals("0")) {

            disciplineh.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_black_24dp, 0);
            disciplineh.setTag("1");
            rowdiscipline.setVisibility(View.VISIBLE);
            setDisplineRecycle(getActivity());
        }else
        {

            disciplineh.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            disciplineh.setTag("0");
            rowdiscipline.setVisibility(View.GONE);
        }
        if(systemh.getTag().toString().equals("1")){
            systemh.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            rowsystem.setVisibility(View.GONE);
            //rowareaprjct.setVisibility(View.GONE);
            systemh.setTag("0");
        }
    }


    private static void setSystemRecycle(Activity context) {

        systemcount.setText(systemData.size()+" Systems");
        adapterSystem = new RecyclerViewAdapterSystemSt(context,systemData,0);
        recycsystem.setVisibility(View.VISIBLE);

        recycsystem.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recycsystem.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recycsystem.setLayoutManager(mLayoutManager);
        //    if(areaData.size()>4){
        ViewGroup.LayoutParams params=recycsystem.getLayoutParams();
        params.height=height*60/100;
        if(systemData.size()*35>height*60/100)
            recycsystem.setLayoutParams(params);
        //}
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycsystem.setItemAnimator(new DefaultItemAnimator());
        recycsystem.setAdapter(adapterSystem);

        adapterSystem.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                systemcount.setText(systemData.size()+" Systems");
            }
        });


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

    private static void setDisplineRecycle(final Activity context) {


        disciplinecount.setText(disciplineData.size()+" discipline");
        adapterdiscipline=null;
        recycdiscipline=v.findViewById(R.id.recycprjcts);
        adapterdiscipline = new RecyclerViewAdapterDisciplineSt(context,disciplineData,0);
        recycdiscipline.setVisibility(View.VISIBLE);

        recycdiscipline.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recycdiscipline.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recycdiscipline.setLayoutManager(mLayoutManager);

        //  if(prjctData.size()>6){
        ViewGroup.LayoutParams params=recycdiscipline.getLayoutParams();
//        params.height=height*62/100;
//        if(disciplineData.size()*35>height*62/100)
//            recycdiscipline.setLayoutParams(params);
        // }
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycdiscipline.setItemAnimator(new DefaultItemAnimator());
        recycdiscipline.setAdapter(adapterdiscipline);


        adapterdiscipline.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                disciplinecount.setText(adapterdiscipline.getSize()+" discipline");
            }
        });

//        recycprjcts.addOnItemTouchListener(new RecyclerItemClickListener(context, recycprjcts, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//               TextView desc=view.findViewById(R.id.textView7);
////                if (expandpos == position) {
////                    Log.i("ddd","onclick2exp"+expandpos);
//////                         rcview.getChildAt(expandpos).findViewById(R.id.expand).setVisibility(View.GONE);
//////
//////                         rcview.getChildAt(expandpos).findViewById(R.id.expand).startAnimation(animationUp);
//////                         expandpos=-1;
////                    return;
////                }
////                for(int j=0;j<recycprjcts.getChildCount();j++){
////
////                    recycprjcts.getChildAt(j).findViewById(R.id.textView7).setVisibility(View.GONE);
////
////                  //  recycprjcts.getChildAt(j).findViewById(R.id.expand).startAnimation(animationUp);
////                }
////
////                recycprjcts.scrollToPosition(position);
//             //animate(desc,context);
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

    public static void setImages(final Context context) {

    }
    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onResume() {

        super.onResume();
//        ActionBar actionBar = ((HomeActivity)getActivity()).getSupportActionBar();
//        actionBar.setTitle("Settings");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.fragment_menu, menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getActivity(), "Back from fragment", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void loadDisciplineset(Activity context) {

        disciplineData= new SqliteDb(context).getDiscipline("");
        setDisplineRecycle(context);
        recycdiscipline.scrollToPosition(disciplineData.size()-1);

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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
