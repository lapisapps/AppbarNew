package com.example.pentagon.appbar.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.pentagon.appbar.CameraUtils;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.Main2Activity;
import com.example.pentagon.appbar.R;

import java.util.ArrayList;

import static com.example.pentagon.appbar.Main2Activity.BITMAP_SAMPLE_SIZE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDataViewChild#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDataViewChild extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
public DataPreview dataPreview;

    public FragmentDataViewChild() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDataViewChild.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDataViewChild newInstance(String param1, String param2) {
        FragmentDataViewChild fragment = new FragmentDataViewChild();
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

        dataPreview = (DataPreview) getArguments().getSerializable("data");
        view=inflater.inflate(R.layout.fragment_fragment_data_view, container, false);
        initial();
        return view;
    }
    public void initial(){

        ImageView imageView=view.findViewById(R.id.imageView);





        ImageView vidpreview=view.findViewById(R.id.videopreview);
        final VideoView videoView=view.findViewById(R.id.videoView);



        imageView.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);
        vidpreview.setVisibility(View.GONE);

        if(Integer.parseInt(dataPreview.getType())== Main2Activity.MEDIA_TYPE_IMAGE){

            imageView.setVisibility(View.VISIBLE);

            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, dataPreview.getPath());

            imageView.setImageBitmap(bitmap);
        }else if(Integer.parseInt(dataPreview.getType())==Main2Activity.MEDIA_TYPE_VIDEO) {
            vidpreview.setImageResource(R.drawable.ic_videopreview_24dp);
            vidpreview.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoPath(dataPreview.getPath());
            videoView.seekTo(10);
            vidpreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoView.start();
                }
            });
        }else {
            vidpreview.setVisibility(View.VISIBLE);

            vidpreview.setImageResource(R.drawable.audiorecorder);


        }

    }

}
