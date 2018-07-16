package com.example.pentagon.appbar.Fragments;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterCameraPreviews;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.PreviewDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.RecyclerItemClickListener;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

import static com.example.pentagon.appbar.Fragments.CreateReport.dataPreviews;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageReportImage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageReportImage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageReportImage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
  public  static RecyclerViewAdapterCameraPreviews images;
 public    static RecyclerViewAdapterCameraPreviews vidoes;
  public   static RecyclerViewAdapterCameraPreviews audios;
    static RecyclerView recyimages;
    static RecyclerView recyvideos;
    static RecyclerView recyaudios;
    static ArrayList<DataPreview> ddimage;
 static TextView noimage;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

View view;
    private OnFragmentInteractionListener mListener;

    public PageReportImage() {
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
    public static PageReportImage newInstance(String param1, String param2) {
        PageReportImage fragment = new PageReportImage();
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
        view=  inflater.inflate(R.layout.viewcontentimage, container, false);
        noimage=view.findViewById(R.id.textView4) ;

        recyimages=view.findViewById(R.id.recyclerView2);
        if(dataPreviews==null)
        dataPreviews=new ArrayList<>();
        Log.e("ddd",dataPreviews.size()+"");
        setImages(getActivity());

        return view;

    }



    public static void setImages(final Context context) {
        ddimage=new ArrayList<>();
        for(int j=0;j<dataPreviews.size();j++){

            if(Integer.parseInt(dataPreviews.get(j).getType())==(Utility.MEDIA_TYPE_IMAGE)){
                ddimage.add(dataPreviews.get(j));

            }
        }
        if(ddimage.size()>0)
            noimage.setVisibility(View.GONE);
        images = new RecyclerViewAdapterCameraPreviews(context,ddimage);
        recyimages.setVisibility(View.VISIBLE);

        recyimages.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyimages.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recyimages.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyimages.setItemAnimator(new DefaultItemAnimator());
        recyimages.setAdapter(images);




        recyimages.addOnItemTouchListener(new RecyclerItemClickListener(context, recyimages, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                new PreviewDialog(context,ddimage.get(position),false);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        images.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(ddimage.size()>0){
                    noimage.setVisibility(View.GONE);
                }
else {

                    noimage.setVisibility(View.VISIBLE);
                }
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

}
