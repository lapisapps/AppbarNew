package com.example.pentagon.appbar.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.OnswipeListener;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.TestActivity;
import com.example.pentagon.appbar.Utility;

import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuItem;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuWidget;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.pentagon.appbar.Fragments.BlankFragment.Direction.inRange;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    private RadialMenuWidget pieMenu;
    public RadialMenuItem menunew, menucenter,menusettings,menureview,menuexit;
    private OnFragmentInteractionListener mListener;


    private String[] mItemTexts = new String[] { "Reviews", "New", "Settings",
            "Exit" };
    private int[] mItemImgs = new int[] { R.drawable.ic_review_black_24dp,
            R.drawable.ic_create_new_24dp, R.drawable.ic_settings_black_24dp,
            R.drawable.ic_exit_24dp
    };
    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
        view=inflater.inflate(R.layout.homemenunew, container, false);

        if(Utility.savemenu!=null) {
            Utility.savemenu.setVisible(false);
            Utility.sharemenu.setVisible(false);
        }
        Utility.toolbar.setVisibility(View.GONE);
        Utility.newreport.setVisibility(View.GONE);
        Utility.camera.setVisibility(View.GONE);
        Utility.fab1.setVisibility(View.GONE);
        Utility.fab2.setVisibility(View.GONE);
        Utility.fab3.setVisibility(View.GONE);
        Utility.fabhome.setVisibility(View.GONE);
//        mCircleMenuLayout = (CircleMenuLayout) view.findViewById(R.id.id_menulayout);
//        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
//
//
//        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
//        {
//
//            @Override
//            public void itemClick(View view, int pos)
//            {
//                Toast.makeText(getActivity(), mItemTexts[pos],
//                        Toast.LENGTH_SHORT).show();
//
//                if(pos==1){
//                    openCreateReport();
//
//                }
//                else if(pos==0){
//                    openReport();
//
//                }
//                else if(pos==2){
//                    Utility.toolbar.setVisibility(View.GONE);
//                    Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
//                }
//                else {
//
//                    getActivity().finish();
//                }
//
//            }
//
//            @Override
//            public void itemCenterClick(View view)
//            {
//                Toast.makeText(getActivity(),
//                        "you can do something just like ccb  ",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });



        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        Log.i("dd", "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
//                        try {
//                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
//                                return false;
//                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
//                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                                Log.i("dd", "Right to Left");
//                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
//                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                                Log.i("dd", "Left to Right");
//                            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
//                                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
//                                Log.i("dd", "Top to Bottom");
//                            }
//                            else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
//                                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
//                                Log.i("dd", "Bottom to Top");
//                            }
//                        } catch (Exception e) {
//                            // nothing
//                        }

                        switch(get(getAngle(e1.getX(),e1.getY(),e2.getX(),e2.getY()))){

                          case  0:
                              Utility.fabhome.setVisibility(View.VISIBLE);
                              openCreateReport();
                         //   Toast.makeText(getActivity(), "Create report", Toast.LENGTH_SHORT).show();
                            break;
                            case  1:
                                Utility.fabhome.setVisibility(View.VISIBLE);
openSettings();
                              //  Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
                                break;
                            case  2:
                                getActivity().finish();

                              //  Toast.makeText(getActivity(), "down", Toast.LENGTH_SHORT).show();
                                break;
                            case  3:
                                Utility.fabhome.setVisibility(View.VISIBLE);
                                openReport();
                              // Toast.makeText(getActivity(), "Open reports", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

//        mCircleMenuLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return gesture.onTouchEvent(event);
//            }
//        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

        return view;
    }

    private void openSettings() {
        Utility.toolbar.setVisibility(View.VISIBLE);
        SettinghomeFragmentNew createReport = new SettinghomeFragmentNew();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, createReport);
        //  getSupportFragmentManager().popBackStack();
        Bundle bundle=new Bundle();

        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void openReport() {
        Utility.toolbar.setVisibility(View.VISIBLE);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, ReportsFragment.newInstance("",""))
                .addToBackStack(null)
                .commit();
    }

    private void openCreateReport() {
        Utility.toolbar.setVisibility(View.VISIBLE);
        CreateReport createReport = new CreateReport();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, createReport);
        //  getSupportFragmentManager().popBackStack();
        Bundle bundle=new Bundle();
        bundle.putSerializable("report", (Serializable) null);
        Utility.savemenu.setIcon(getResources().getDrawable(R.drawable.ic_save_black_24dp));

        Utility.savemenu.setTitle("save")     ;
        createReport.setArguments(bundle);
        Utility.savemenu.setVisible(true);
        Utility.sharemenu.setVisible(false);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public double getAngle(float x1, float y1, float x2, float y2) {

        double rad = Math.atan2(y1-y2,x2-x1) + Math.PI;
        return (rad*180/Math.PI + 180)%360;
    }
    public static int get(double angle){
        if(inRange(angle, 45, 135)){
            return 0;
        }
        else if(inRange(angle, 0,45) || inRange(angle, 315, 360)){
            return 1;
        }
        else if(inRange(angle, 225, 315)){
            return 2;
        }
        else{
            return 3;
        }

    }
    public enum Direction{
        up,
        down,
        left,
        right;

        /**
         * Returns a direction given an angle.
         * Directions are defined as follows:
         *
         * Up: [45, 135]
         * Right: [0,45] and [315, 360]
         * Down: [225, 315]
         * Left: [135, 225]
         *
         * @param angle an angle from 0 to 360 - e
         * @return the direction of an angle
         */


        /**
         * @param angle an angle
         * @param init the initial bound
         * @param end the final bound
         * @return returns true if the given angle is in the interval [init, end).
         */
        static boolean inRange(double angle, float init, float end){
            return (angle >= init) && (angle < end);
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public void createMenu(View homemenu){


        pieMenu = new RadialMenuWidget(getContext());
        menucenter = new RadialMenuItem("Center", null);
        menucenter
                .setDisplayIcon(android.R.drawable.ic_menu_close_clear_cancel);
        menunew = new RadialMenuItem("new",
                "NEW");
        menunew.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                Toast.makeText(getContext(), "MENU NEW", Toast.LENGTH_SHORT).show();
            }
        });
        menusettings = new RadialMenuItem("settings",
                "NEW");
        menusettings.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                Toast.makeText(getContext(), "MENU settings", Toast.LENGTH_SHORT).show();
            }
        });

        menuexit = new RadialMenuItem("exit",
                "EXIT");
        menuexit.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                Toast.makeText(getContext(), "MENU exit", Toast.LENGTH_SHORT).show();
            }
        });
        menureview = new RadialMenuItem("exit",
                "EXIT");
        menureview.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                Toast.makeText(getContext(), "MENU exit", Toast.LENGTH_SHORT).show();
            }
        });



        // pieMenu.setDismissOnOutsideClick(true, menuLayout);
        pieMenu.setAnimationSpeed(0L);
        pieMenu.setSourceLocation(200, 200);
        pieMenu.setIconSize(15, 30);
        pieMenu.setTextSize(13);
        pieMenu.setOutlineColor(Color.BLACK, 225);
        pieMenu.setInnerRingColor(0xAA66CC, 180);
        pieMenu.setOuterRingColor(0x0099CC, 180);
        //pieMenu.setHeader("Test Menu", 20);
        pieMenu.setCenterCircle(menucenter);
        pieMenu.setInnerRingRadius(180,120);
        pieMenu.setCenterCircleRadius(180);

        pieMenu.addMenuEntry(new ArrayList<RadialMenuItem>() {
            {
                add(menuexit);
                add(menunew);
                add(menureview);
                add(menusettings);
            }
        });

        // pieMenu.addMenuEntry(menuItem);
        // pieMenu.addMenuEntry(menuExpandItem);



        try{

//            testButton.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    pieMenu.show(v);
//
//                }
//            });

//            LayoutInflater layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final View view=layoutInflater.inflate(R.layout.homemenu, null);
            Log.e("ff",view+"");
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    pieMenu.show(view);
//                }
//            });
            pieMenu.show(this.view);


        }catch (Exception e){e.printStackTrace();}


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
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
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
