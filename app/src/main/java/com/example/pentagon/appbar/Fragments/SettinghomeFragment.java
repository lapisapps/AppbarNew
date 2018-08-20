package com.example.pentagon.appbar.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
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


import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>

 * interface.
 */
public class SettinghomeFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
View view;
    ViewPager viewPager;
    private TabLayout tabLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */



            public SettinghomeFragment() {
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
                view= inflater.inflate(R.layout.fragment_settinghome_list, container, false);

             //   toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//               setSupportActionBar(toolbar);
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                viewPager = (ViewPager) view.findViewById(R.id.viewpager);
                setupViewPager(viewPager);

                tabLayout = (TabLayout) view.findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
                return view;
            }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setupViewPager(ViewPager viewPager) {
        CustomPagerAdapter adapter = new CustomPagerAdapter(getFragmentManager());
       // adapter.addFrag(new FragmentSettingsProjects(), "Project");
        adapter.addFrag(new FragmentSettingTag(), "Tags");
        adapter.addFrag(new FragmentSettingSystem(), "System");
        adapter.addFrag(new FragmentSettingArea(), "Area");
        adapter.addFrag(new FragmentSettingDiscipline(), "Discipline");
viewPager.setOffscreenPageLimit(4);
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

    }

        }

