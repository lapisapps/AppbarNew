package com.example.pentagon.appbar.Fragments;

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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterProjectSt;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.HomeActivity;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    RecyclerViewAdapterProjectSt adapterprjcts;
ArrayList<PrjctData> prjctData;
TableRow prjct,tag,system,area,discipline;
    TableRow rowprjct,rowtag,rowsystem,rowarea,rowdiscipline;
   TextView prjcth,tagh,systemh,areah,disciplineh;
   RecyclerView recycprjcts;
    public SettingFragment() {
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
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
        v= inflater.inflate(R.layout.fragment_setting, container, false);

initilize();

        return v;
    }



    public void initilize(){
recycprjcts=v.findViewById(R.id.recycprjcts);
       prjct=v.findViewById(R.id.prjct);
       discipline=v.findViewById(R.id.discipline);
       tag=v.findViewById(R.id.tag);
       area=v.findViewById(R.id.area);
       system=v.findViewById(R.id.system);
       rowarea=v.findViewById(R.id.rowarea);
    rowprjct=v.findViewById(R.id.rowprjcts);
    rowarea=v.findViewById(R.id.rowarea);
    rowsystem=v.findViewById(R.id.rowsys);
    rowdiscipline=v.findViewById(R.id.rowdisc);
    rowtag=v.findViewById(R.id.rowtags);

   prjcth=v.findViewById(R.id.hprjct);
    areah=v.findViewById(R.id.harea);
    systemh=v.findViewById(R.id.hsystem);
   disciplineh=v.findViewById(R.id.hdiscipline);
    tagh=v.findViewById(R.id.htag);
    rowdiscipline=v.findViewById(R.id.rowdisc);
    rowtag=v.findViewById(R.id.rowtags);
       prjct.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(prjctData==null)
                   prjctData= new SqliteDb(getActivity()).getPrjcts();

               animate(rowprjct,prjcth);
           }
       });
       discipline.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               animate(rowdiscipline,disciplineh);
           }
       });
    tag.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            animate(rowtag,tagh);
        }
    });
    area.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            animate(rowarea,areah);
        }
    });

    system.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            animate(rowsystem,systemh);
        }
    });


}
    public void animate(View view,final TextView icon){
        Animation animation = null;
        if(icon.getTag().toString().equals("0")) {
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animexpand);
            icon.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_black_24dp, 0);
            icon.setTag("1");

            view.setVisibility(View.VISIBLE);
            setPrjctRecycle();
        }   else {
            icon.setTag("0");
            icon.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_24dp, 0);
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animless);
            view.setVisibility(View.GONE);
        }



        animation.setDuration(400);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    private void setPrjctRecycle() {


int n=prjctData.size();
if(n>6)
    n=6;

        adapterprjcts = new RecyclerViewAdapterProjectSt(getActivity(),new ArrayList<PrjctData>(prjctData.subList(0,n)),recycprjcts);
        recycprjcts.setVisibility(View.VISIBLE);

        recycprjcts.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recycprjcts.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recycprjcts.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycprjcts.setItemAnimator(new DefaultItemAnimator());
        recycprjcts.setAdapter(adapterprjcts);




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
        ActionBar actionBar = ((HomeActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Settings");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

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
