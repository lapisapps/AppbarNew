package com.example.pentagon.appbar.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterProjectSt;
import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterTagSt;
import com.example.pentagon.appbar.AddAreaDialog;
import com.example.pentagon.appbar.AddProjectDialog;
import com.example.pentagon.appbar.AddTagDialog;
import com.example.pentagon.appbar.AreaListDialog;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.ImportListDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SettingFragment6#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SettingFragment6 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
Button addtag;
    LinearLayout prjct,tag,system,area,discipline;
    LinearLayout rowprjct;
    TableRow rowtag,rowsystem,rowarea,rowdiscipline;
    TextView prjcth;
    TextView tagh;
    TextView systemh;
    TextView areah;
    TextView disciplineh;
    Button addprjct,addarea;
    static TextView prjctcount, areacount;
    static RecyclerView recycnontags;
    static RecyclerView recycarea;
    static ArrayList<DataTag> tagData;
    static ArrayList<DataTag> etagData;
    static ArrayList<DataTag> nontagData;
    ArrayList<PrjctData> prjctData;
    static int height;
   public static Spinner spinner;
    static RecyclerView recycnonex,recycex;
    private static RecyclerViewAdapterTagSt adaptertags;
    private static RecyclerViewAdapterTagSt adapternontags;

    Button searchbtn,cancelbtn,loadbtn;
    AutoCompleteTextView searchtxt;
    static ConstraintLayout searchlay;
    public SettingFragment6() {
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
    public static SettingFragment6 newInstance(String param1, String param2) {
        SettingFragment6 fragment = new SettingFragment6();
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
        v= inflater.inflate(R.layout.fragmentsetnew1, container, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;

        //  tagData= new SqliteDb(getActivity()).getTags();
        if(prjctData==null)
            prjctData= new SqliteDb(getActivity()).getPrjcts();

initilize();

        return v;
    }


    public static void loadtagset(Activity context) {

        nontagData= new SqliteDb(context).getTagsExiting(((PrjctData)spinner.getSelectedItem()).getId(),"0");
        etagData= new SqliteDb(context).getTagsExiting(((PrjctData)spinner.getSelectedItem()).getId(),"1");


        adapternontags.notifyDataSetChanged();
setNonExistingRecycle(context);

      adaptertags.notifyDataSetChanged();
      setExistingRecycle(context);
       // recycnonex.scrollToPosition(nontagData.size()-1);

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

public void initilize(){


    prjct=v.findViewById(R.id.prjct);
    prjctcount=v.findViewById(R.id.prjctcount);
    areacount=v.findViewById(R.id.areacount);
    addprjct=v.findViewById(R.id.addprjct);
    addarea=v.findViewById(R.id.addarea);
//    addprjct.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if(prjcth.getTag().toString().equals("0"))
//                expandPrjctRow();
//
//            new AddProjectDialog(getActivity(),1);
//        }
//    });
    addtag=v.findViewById(R.id.addtag) ;
    addtag.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchtxt.setText("");
            if((PrjctData)spinner.getSelectedItem()==null)
                Toast.makeText(getActivity(), "No projects found", Toast.LENGTH_SHORT).show();
            else{

                PopupMenu popup = new PopupMenu(getContext(), addtag);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popupaddnew, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        PrjctData pp=(PrjctData) spinner.getSelectedItem();
                        if(item.getTitle().equals("New")) {
                            AddTagDialog.prjctData=pp;
                            new AddTagDialog(getActivity(),1);
                        }  else{

                            new AreaListDialog(getActivity(),1,pp.getId(),"setting");
                        }
                        return true;
                    }
                });

                popup.show();
//                AddTagDialog.prjctData=(PrjctData)spinner.getSelectedItem();
//                new AddTagDialog(getActivity(),1);

            }
        }
    });
    area=v.findViewById(R.id.area);

    rowarea=v.findViewById(R.id.rowarea);

    rowprjct=v.findViewById(R.id.rowprjcts);
    rowarea=v.findViewById(R.id.rowarea);


    rowtag=v.findViewById(R.id.rowtags);

    prjcth=v.findViewById(R.id.hprjct);
    areah=v.findViewById(R.id.harea);
    recycex=v.findViewById(R.id.recycprjcts);
    recycnonex=v.findViewById(R.id.recycarea);
       prjct.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

//               if(prjctData==null)
//                   prjctData= new SqliteDb(getActivity()).getPrjcts();
//               animate(rowprjct,prjcth,1);
//               if(areah.getTag().toString().equals("1"))
//               animate(rowarea,areah,2);
               expandPrjctRow();


           }
       });
//       discipline.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               animate(rowdiscipline,disciplineh);
//           }
//       });

        area.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



expandArea();

        }
    });

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
            new ImportListDialog(getActivity(),"tag");
        }
    });

    spinner=v.findViewById(R.id.spinner);
    ArrayAdapter aa = new ArrayAdapter<PrjctData>(getActivity(),R.layout.spinner,prjctData);
    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(aa);
    tagData=new ArrayList<>();
    //setTagRecycle(getActivity());
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            // your code here
            Object item1 = parentView.getItemAtPosition(position);
            if (item1 instanceof PrjctData) {

                PrjctData tag = (PrjctData) item1;

                etagData=new SqliteDb(getContext()).getTagsExiting(tag.getId(),"1");
                nontagData=new SqliteDb(getContext()).getTagsExiting(tag.getId(),"0");
                setExistingRecycle(getActivity());
                setNonExistingRecycle(getActivity());
                adaptertags.getFilter().filter(searchtxt.getText().toString());
                adapternontags.getFilter().filter(searchtxt.getText().toString());
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
//    setExistingRecycle(getActivity());
//
//    setNonExistingRecycle(getActivity());
  //expandPrjctRow();
}
    private void setSearchView() {
        cancelbtn.setVisibility(View.GONE);
        searchbtn.setTag("search");
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  searchlay.setVisibility(View.VISIBLE);
                loadbtn.setVisibility(View.GONE);
            //    searchbtn.setVisibility(View.GONE);
                addtag.setVisibility(View.GONE);
                searchtxt.setText("");


            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //searchbtn.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_search_green_24dp),null,null,null);
             //   searchlay.setVisibility(View.GONE);
                loadbtn.setVisibility(View.VISIBLE);
              //  searchbtn.setVisibility(View.VISIBLE);
                addtag.setVisibility(View.VISIBLE);
                searchtxt.setText("");
                cancelbtn.setVisibility(View.GONE);
                adaptertags.getFilter().filter("");
                adapternontags.getFilter().filter("");
            }
        });
        searchtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //     filter(searchtext.getText().toString());
                Log.i("textt",searchtxt.getText().toString()+s);
                adaptertags.getFilter().filter(searchtxt.getText().toString());
                adapternontags.getFilter().filter(searchtxt.getText().toString());
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

private static void setExistingRecycle(final Activity context) {


    prjctcount.setText(etagData.size()+" tags");
    adaptertags=null;

    adaptertags = new RecyclerViewAdapterTagSt(context,etagData,0);
    recycex.setVisibility(View.VISIBLE);

    recycex.setHasFixedSize(true);
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
    // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

    recycex.setNestedScrollingEnabled(true);

    // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
    recycex.setLayoutManager(mLayoutManager);
    final float scale = context.getResources().getDisplayMetrics().density;
    int pixels = (int) (40 * scale + 0.5f);
    //  if(prjctData.size()>6){
    ViewGroup.LayoutParams params=recycex.getLayoutParams();

    if(etagData.size()*pixels>height*55/100)
        params.height=height*55/100;
    else
    {
        params.height=etagData.size()*pixels;

    }
    // }
    //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
    recycex.setItemAnimator(new DefaultItemAnimator());
    recycex.setAdapter(adaptertags);


    adaptertags.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            prjctcount.setText(adaptertags.getSize()+" Tags");
//            final float scale = context.getResources().getDisplayMetrics().density;
//            int pixels = (int) (40 * scale + 0.5f);
//            //  if(prjctData.size()>6){
//            ViewGroup.LayoutParams params=recycex.getLayoutParams();
//            if(recycex.getChildCount()*pixels>height*50/100)
//                params.height=height*50/100;
//            else
//            {
//                params.height=recycex.getChildCount()*pixels;
//
//            }
//            if(searchlay.getVisibility()==View.VISIBLE)
//            recycex.setLayoutParams(params);
        }
    });



}
    private static void setNonExistingRecycle(final Activity context) {


        areacount.setText(nontagData.size()+" Tags");
        adapternontags=null;

        adapternontags = new RecyclerViewAdapterTagSt(context,nontagData,0);
        recycnonex.setVisibility(View.VISIBLE);

        recycnonex.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recycnonex.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recycnonex.setLayoutManager(mLayoutManager);
        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (40 * scale + 0.5f);
        //  if(prjctData.size()>6){
        ViewGroup.LayoutParams params=recycnonex.getLayoutParams();

        if(nontagData.size()*pixels>height*50/100)
            params.height=height*50/100;
        else
        {
            params.height=nontagData.size()*pixels;

        }
        recycnonex.setLayoutParams(params);
        // }
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycnonex.setItemAnimator(new DefaultItemAnimator());
        recycnonex.setAdapter(adapternontags);


        adapternontags.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                areacount.setText(adapternontags.getSize()+" Tags");
//                final float scale = context.getResources().getDisplayMetrics().density;
//                int pixels = (int) (40 * scale + 0.5f);
//                //  if(prjctData.size()>6){
//                ViewGroup.LayoutParams params=recycnonex.getLayoutParams();
//                if(recycex.getChildCount()*pixels>height*50/100)
//                    params.height=height*50/100;
//                else
//                {
//                    params.height=recycex.getChildCount()*pixels;
//
//                }
//                if(searchlay.getVisibility()==View.VISIBLE)
//                recycnonex.setLayoutParams(params);


            }
        });



    }
    private void expandArea() {
        if(areah.getTag().toString().equals("0")){


            areah.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_black_24dp, 0);
            areah.setTag("1");
            rowarea.setVisibility(View.VISIBLE);

            //setAreaRecycle();
            setNonExistingRecycle(getActivity());
            //adaptertags.getFilter().filter(searchtxt.getText().toString());
            adapternontags.getFilter().filter(searchtxt.getText().toString());
        }else {

            areah.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            areah.setTag("0");

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
            setExistingRecycle(getActivity());
         adaptertags.getFilter().filter(searchtxt.getText().toString());
            //adapternontags.getFilter().filter(searchtxt.getText().toString());
        }else
        {

            prjcth.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            prjcth.setTag("0");
            rowprjct.setVisibility(View.GONE);
        }
        if(areah.getTag().toString().equals("1")){
            areah.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            rowarea.setVisibility(View.GONE);
//            rowareaprjct.setVisibility(View.GONE);
            areah.setTag("0");
        }
    }
}
