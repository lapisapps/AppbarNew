package com.example.pentagon.appbar.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterAreaSt;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterProjectSt;
import com.example.pentagon.appbar.AddAreaDialog;
import com.example.pentagon.appbar.AddDisciplineDialog;
import com.example.pentagon.appbar.AddProjectDialog;
import com.example.pentagon.appbar.AreaListDialog;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.HomeActivity;
import com.example.pentagon.appbar.ImportListDialog;
import com.example.pentagon.appbar.ProjectListDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SettingFragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment4 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int expandpos=-1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static View v;


    static ArrayList<DataTag> areaData;
TableRow prjct,tag,system,area,discipline;
    TableRow rowprjct,rowtag,rowsystem,rowarea,rowdiscipline,rowareaprjct;
   TextView prjcth;
    TextView tagh;
    TextView systemh;
    TextView areah;
    TextView disciplineh;
    Button addprjct,addarea;
    static TextView prjctcount, areacount;
   static RecyclerView recycprjcts;
    static RecyclerView recycarea;
    private static RecyclerViewAdapterAreaSt adapterareas;
    static int height;
    public static Spinner spinner;
public static TextView selectedprjct;

    Button searchbtn,cancelbtn,loadbtn;
    AutoCompleteTextView searchtxt;
    ConstraintLayout searchlay;
    private ArrayList<PrjctData> prjctData;

    public SettingFragment4() {
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
    public static SettingFragment4 newInstance(String param1, String param2) {
        SettingFragment4 fragment = new SettingFragment4();
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
        v= inflater.inflate(R.layout.fragmentsetarea, container, false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
       height = displayMetrics.heightPixels;
      //  width = displayMetrics.widthPixels;
initilize();

        return v;
    }

    private void setAreaView() {

        if(prjctData==null)
            prjctData= new SqliteDb(getActivity()).getPrjcts();
   spinner=v.findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter<PrjctData>(getActivity(),R.layout.spinner,prjctData);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Object item1 = parentView.getItemAtPosition(position);
                if (item1 instanceof PrjctData) {

                    PrjctData tag = (PrjctData) item1;

                    areaData=new SqliteDb(getContext()).getPrjctsAreas(tag.getId(),"");
                   setAreaRecycle(getActivity());
                    adapterareas.getFilter().filter(searchtxt.getText().toString());
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
    }


    public void initilize(){

        recycarea=v.findViewById(R.id.recyclerView5);
      // prjct=v.findViewById(R.id.prjct);

        areacount=v.findViewById(R.id.textView11);
        selectedprjct=v.findViewById(R.id.prjct);
    selectedprjct.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new ProjectListDialog(getActivity(),SettingFragment4.this);
        }
    });
//    selectedprjct.addTextChangedListener(new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    });
        addarea=v.findViewById(R.id.addtag);

addarea.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        searchtxt.setText("");

if(selectedprjct.getTag().toString().equals("null"))
    Toast.makeText(getActivity(), "No projects found", Toast.LENGTH_SHORT).show();
     else
{

//    PopupMenu popup = new PopupMenu(getContext(), addarea);
//    //Inflating the Popup using xml file
//    popup.getMenuInflater()
//            .inflate(R.menu.popupaddnew, popup.getMenu());
//
//    //registering popup with OnMenuItemClickListener
//    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//        public boolean onMenuItemClick(MenuItem item) {
//            PrjctData pp=(PrjctData) selectedprjct.getTag();
//            if(item.getTitle().equals("New")) {
//                AddAreaDialog.prjctData=pp;
//                new AddAreaDialog(getActivity(),1);
//            }  else{
//
//                new AreaListDialog(getActivity(),0,pp.getId(),SettingFragment4.this);
//              }
//            return true;
//        }
//    });
//
//
//
//    popup.show();


    PrjctData pp=(PrjctData) selectedprjct.getTag();
    new AreaListDialog(getActivity(),0,pp.getId(),SettingFragment4.this);

}


    }
});
       area=v.findViewById(R.id.area);

       rowarea=v.findViewById(R.id.rowarea);
        rowareaprjct=v.findViewById(R.id.areaprjct);
    rowprjct=v.findViewById(R.id.rowprjcts);
    rowarea=v.findViewById(R.id.rowarea);


    rowtag=v.findViewById(R.id.rowtags);

   prjcth=v.findViewById(R.id.hprjct);
    areah=v.findViewById(R.id.harea);


        searchtxt=v.findViewById(R.id.searchtext);
        searchlay=v.findViewById(R.id.searchlay);
       // searchlay.setVisibility(View.GONE);
        loadbtn=v.findViewById(R.id.load);

        searchbtn=v.findViewById(R.id.search);
        cancelbtn=v.findViewById(R.id.cancel);
        setSearchView();

        loadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtxt.setText("");
new ImportListDialog(getActivity(),"area");
            }
        });



      prjctData= new SqliteDb(getActivity()).getPrjcts();
        areaData= new SqliteDb(getActivity()).getAreas("");
     //   setPrjctRecycle(getActivity());

//        setAreaRecycle(getActivity());
//setAreaView();
}
    private void setSearchView() {
        cancelbtn.setVisibility(View.GONE);
        searchbtn.setTag("search");
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // searchlay.setVisibility(View.VISIBLE);
                loadbtn.setVisibility(View.GONE);
              //  searchbtn.setVisibility(View.GONE);
                addprjct.setVisibility(View.GONE);
                searchtxt.setText("");


            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //searchbtn.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_search_green_24dp),null,null,null);
              //  searchlay.setVisibility(View.GONE);
                 loadbtn.setVisibility(View.VISIBLE);
               // searchbtn.setVisibility(View.VISIBLE);

                searchtxt.setText("");
                cancelbtn.setVisibility(View.GONE);
                adapterareas.getFilter().filter("");

            }
        });
        searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //     filter(searchtext.getText().toString());
                Log.i("textt",searchtxt.getText().toString()+s);
                if(adapterareas!=null){
                adapterareas.getFilter().filter(searchtxt.getText().toString());
                if(searchtxt.getText().toString().isEmpty())
                    cancelbtn.setVisibility(View.GONE);
                else
                    cancelbtn.setVisibility(View.VISIBLE);}
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
    private void expandArea() {
        if(areah.getTag().toString().equals("0")){


            areah.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_black_24dp, 0);
            areah.setTag("1");
            rowarea.setVisibility(View.VISIBLE);
            rowareaprjct.setVisibility(View.VISIBLE);
            //setAreaRecycle();
            setAreaView();

        }else {

            areah.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            areah.setTag("0");
            rowareaprjct.setVisibility(View.GONE);
            rowarea.setVisibility(View.GONE);
        }

        if(prjcth.getTag().toString().equals("1")){
            prjcth.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            rowprjct.setVisibility(View.GONE);
            prjcth.setTag("0");
        }

    }

    private void expandPrjctRow() {
        if(prjcth.getTag().toString().equals("0")) {

            prjcth.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_black_24dp, 0);
            prjcth.setTag("1");
            rowprjct.setVisibility(View.VISIBLE);
            //setPrjctRecycle(getActivity());
        }else
        {

            prjcth.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            prjcth.setTag("0");
            rowprjct.setVisibility(View.GONE);
        }
        if(areah.getTag().toString().equals("1")){
            areah.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            rowarea.setVisibility(View.GONE);
            rowareaprjct.setVisibility(View.GONE);
            areah.setTag("0");
        }
    }


public void setVisiblefalse(View view,TextView icon){

    icon.setTag("0");
    icon.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
 Animation   animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animless);
    view.setVisibility(View.GONE);




        animation.setDuration(400);
        view.setAnimation(animation);
        view.animate();
        animation.start();

}
    private static void setAreaRecycle(Activity context) {

        areacount.setText(areaData.size()+" Area");
        adapterareas = new RecyclerViewAdapterAreaSt(context,areaData,0);
        recycarea.setVisibility(View.VISIBLE);

        recycarea.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recycarea.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recycarea.setLayoutManager(mLayoutManager);
    //    if(areaData.size()>4){
//            ViewGroup.LayoutParams params=recycarea.getLayoutParams();
//            params.height=height*60/100;
//            if(areaData.size()*35>height*60/100)
//            recycarea.setLayoutParams(params);
    //}
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycarea.setItemAnimator(new DefaultItemAnimator());
        recycarea.setAdapter(adapterareas);

        adapterareas.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                areacount.setText(adapterareas.getSize()+" Areas");
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


    public static void loadareaset(Activity context) {

        areaData= new SqliteDb(context).getPrjctsAreas(((PrjctData)selectedprjct.getTag()).getId(),"");
        setAreaRecycle(context);
        recycarea.scrollToPosition(areaData.size()-1);

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
