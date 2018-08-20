package com.example.pentagon.appbar.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pentagon.appbar.AdapterClass.CustomPagerAdapter;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingArea;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingDiscipline;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingSystem;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingTag;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingsProjects;
import com.example.pentagon.appbar.HomeActivity;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>

 * interface.
 */
public class SettinghomeFragmentNew extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    FloatingActionButton next,previous;
View view;
    ViewPager viewPager;
    private TabLayout tabLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */



            public SettinghomeFragmentNew() {
                // Required empty public constructor
            }

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                view= inflater.inflate(R.layout.fragment_settinghome_listnew, container, false);

             //   toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//               setSupportActionBar(toolbar);
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                Utility.fabhome.setVisibility(View.VISIBLE);
                viewPager = (ViewPager) view.findViewById(R.id.viewpager);
                setupViewPager(viewPager);
              next=view.findViewById(R.id.floatingActionButton);
                previous=view.findViewById(R.id.floatingActionButton2);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(viewPager.getCurrentItem()!=viewPager.getChildCount())
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
                    }
                });
                previous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(viewPager.getCurrentItem()!=0)
                        viewPager.setCurrentItem(viewPager.getCurrentItem()-1,true);
                    }
                });

                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        next.setVisibility(View.VISIBLE);
                        previous.setVisibility(View.VISIBLE);

                        if(position>=viewPager.getChildCount()-1) {
                            next.setVisibility(View.GONE);

                        }

                        if(position==0){
                            previous.setVisibility(View.GONE);

                        }
                        Log.e("position",position+"  "+viewPager.getChildCount());
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
//                tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//                tabLayout.setupWithViewPager(viewPager);
                return view;
            }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setupViewPager(ViewPager viewPager) {
        CustomPagerAdapter adapter = new CustomPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new SettingFragment1(), "Project");
        adapter.addFrag(new SettingFragment4(), "Area");
        adapter.addFrag(new SettingFragment6(), "Tags");
        adapter.addFrag(new SettingFragment5(), "Discipline");
        adapter.addFrag(new SettingFragment3(), "System");

viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onResume() {

        super.onResume();
        ActionBar actionBar = ((HomeActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Settings");
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setIcon(R.drawable.ic_settings_white_24dp);

    }
        }

