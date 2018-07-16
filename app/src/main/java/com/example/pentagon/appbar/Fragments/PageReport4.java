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

import com.example.pentagon.appbar.AdapterClass.RecyclerViewAdapterCameraPreviews;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.Main2Activity;
import com.example.pentagon.appbar.PreviewDialog;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.RecyclerItemClickListener;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

import static com.example.pentagon.appbar.Fragments.CreateReport.dataPreviews;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageReport4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageReport4#newInstance} factory method to
 * create an instance of this fragment.
 */
//public class PageReport4 extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//  public  static RecyclerViewAdapterCameraPreviews images;
// public    static RecyclerViewAdapterCameraPreviews vidoes;
//  public   static RecyclerViewAdapterCameraPreviews audios;
//    static RecyclerView recyimages;
//    static RecyclerView recyvideos;
//    static RecyclerView recyaudios;
//    static ArrayList<DataPreview> ddimage;
//    static ArrayList<DataPreview> ddvideo;
//    static ArrayList<DataPreview> ddaudio;
//   //public static ArrayList<DataPreview> dataPreviews;
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//View view;
//    private OnFragmentInteractionListener mListener;
//
//    public PageReport4() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CreateReport.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static PageReport4 newInstance(String param1, String param2) {
//        PageReport4 fragment = new PageReport4();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view=  inflater.inflate(R.layout.viewcontent, container, false);
//recyaudios=view.findViewById(R.id.recyclerView4);
//        recyvideos=view.findViewById(R.id.recyclerView3);
//        recyimages=view.findViewById(R.id.recyclerView2);
//        if(dataPreviews==null)
//        dataPreviews=new ArrayList<>();
//        Log.e("ddd",dataPreviews.size()+"");
//        setImages(getActivity());
//       setVideos(getActivity());
//     setAudios(getActivity());
//        return view;
//
//    }
//
//    public static void setVideos(final Context context) {
//        ddvideo=new ArrayList<>();
//        for(int j=0;j<dataPreviews.size();j++){
//
//            if(Integer.parseInt(dataPreviews.get(j).getType())==(Utility.MEDIA_TYPE_VIDEO)){
//                ddvideo.add(dataPreviews.get(j));
//
//            }
//        }
//        vidoes = new RecyclerViewAdapterCameraPreviews(context,ddvideo);
//        recyvideos.setVisibility(View.VISIBLE);
//
//        recyvideos.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false);
//        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyvideos.setNestedScrollingEnabled(true);
//
//        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
//        recyvideos.setLayoutManager(mLayoutManager);
//        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyvideos.setItemAnimator(new DefaultItemAnimator());
//        recyvideos.setAdapter(vidoes);
//
//
//
//
//        recyvideos.addOnItemTouchListener(new RecyclerItemClickListener(context, recyvideos, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view,int position) {
//               new PreviewDialog(context,ddvideo.get(position),false);
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));
//    }
//
//    public static void setImages(final Context context) {
//        ddimage=new ArrayList<>();
//        for(int j=0;j<dataPreviews.size();j++){
//
//            if(Integer.parseInt(dataPreviews.get(j).getType())==(Utility.MEDIA_TYPE_IMAGE)){
//                ddimage.add(dataPreviews.get(j));
//
//            }
//        }
//        images = new RecyclerViewAdapterCameraPreviews(context,ddimage);
//        recyimages.setVisibility(View.VISIBLE);
//
//        recyimages.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false);
//        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyimages.setNestedScrollingEnabled(true);
//
//        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
//        recyimages.setLayoutManager(mLayoutManager);
//        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyimages.setItemAnimator(new DefaultItemAnimator());
//        recyimages.setAdapter(images);
//
//
//
//
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
//    }
//    public static void setAudios(final Context context) {
// ddaudio=new ArrayList<>();
//        for(int j=0;j<dataPreviews.size();j++){
//
//            if(Integer.parseInt(dataPreviews.get(j).getType())==(Utility.MEDIA_TYPE_AUDIO)){
//                ddaudio.add(dataPreviews.get(j));
//
//            }
//        }
//        audios = new RecyclerViewAdapterCameraPreviews(context,ddaudio);
//        recyaudios.setVisibility(View.VISIBLE);
//
//        recyaudios.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false);
//        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyaudios.setNestedScrollingEnabled(true);
//
//        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
//        recyaudios.setLayoutManager(mLayoutManager);
//        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyaudios.setItemAnimator(new DefaultItemAnimator());
//        recyaudios.setAdapter(audios);
//
//
//
//
//        recyaudios.addOnItemTouchListener(new RecyclerItemClickListener(context, recyaudios, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view,  int position) {
//                new PreviewDialog(context,ddaudio.get(position),false);
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));
//    }
//
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
////    @Override
////    public void onAttach(Context context) {
////        super.onAttach(context);
////        if (context instanceof OnFragmentInteractionListener) {
////            mListener = (OnFragmentInteractionListener) context;
////        } else {
////            throw new RuntimeException(context.toString()
////                    + " must implement OnFragmentInteractionListener");
////        }
////    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//
//}
