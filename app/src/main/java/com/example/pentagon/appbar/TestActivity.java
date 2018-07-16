package com.example.pentagon.appbar;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pentagon.appbar.Fragments.BlankFragment;
import com.example.pentagon.appbar.Fragments.ReportsFragment;

import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuItem;
import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuWidget;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {


    private String[] mItemTexts = new String[] { "Reviews", "New", "Settings",
            "Exit" };
    private int[] mItemImgs = new int[] { R.drawable.ic_review_black_24dp,
            R.drawable.ic_create_new_24dp, R.drawable.ic_settings_black_24dp,
            R.drawable.ic_exit_24dp
    };
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homemenunew);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
//        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
//
//
//        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
//        {
//
//            @Override
//            public void itemClick(View view, int pos)
//            {
//                Toast.makeText(TestActivity.this, mItemTexts[pos],
//                        Toast.LENGTH_SHORT).show();
//
//                if(pos==0){
//
//
//                }
//
//            }
//
//            @Override
//            public void itemCenterClick(View view)
//            {
//                Toast.makeText(TestActivity.this,
//                        "you can do something just like ccb  ",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
       // createMenu(R.layout.homemenu);
    }


}
