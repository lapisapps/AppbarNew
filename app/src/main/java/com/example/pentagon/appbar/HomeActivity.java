package com.example.pentagon.appbar;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.pentagon.appbar.AdapterClass.Toolbar_ActionMode_Callback;
import com.example.pentagon.appbar.Fragments.BlankFragment;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.FragmentDataViewImage;
import com.example.pentagon.appbar.Fragments.PageReport1;
import com.example.pentagon.appbar.Fragments.ReportsFragment;
import com.itextpdf.text.DocumentException;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.pentagon.appbar.Main2Activity.MEDIA_TYPE_IMAGE;
import static com.example.pentagon.appbar.Main2Activity.MEDIA_TYPE_VIDEO;
import static com.example.pentagon.appbar.Utility.toolbar;

public class HomeActivity extends AppCompatActivity implements CreateReport.OnFragmentInteractionListener,ReportsFragment.OnFragmentInteractionListener, View.OnTouchListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintest);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new SqliteDb(this).insertTemp();
toolbar.setVisibility(View.VISIBLE);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Utility.newreport = (FloatingActionButton) findViewById(R.id.fab);
Utility.newreport.setVisibility(View.GONE);
        Utility.newreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, CreateReport.newInstance("",""))
//                        .commit();

//                Intent i=new Intent(HomeActivity.this,ReportActivity.class);
//                startActivity(i);
                CreateReport createReport = new CreateReport();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
        });
        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.e("count",getFragmentManager().getBackStackEntryCount()+"");
                if(getFragmentManager().getBackStackEntryCount() == 0) finish();
            }
        });

        Utility.camera = (FloatingActionButton) findViewById(R.id.fab1);
        Utility.fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        Utility.fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        Utility.fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
        Utility.fabhome = (FloatingActionButton) findViewById(R.id.fab_home);
        Utility.fabhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportsFragment.homeactivity=HomeActivity.this;
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                //this will clear the back stack and displays no animation on the screen
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, BlankFragment.newInstance("",""))

                        .commit();
            }
        });

        Utility.fab1.setVisibility(View.GONE);
        Utility.fab2.setVisibility(View.GONE);
        Utility.fab3.setVisibility(View.GONE);
//        Utility.camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, CreateReport.newInstance("",""))
//                        .commit();
//            }
//        });
        Utility.camera.setVisibility(View.GONE);

            if (null == savedInstanceState) {

ReportsFragment.homeactivity=this;
                getSupportFragmentManager().beginTransaction()

                        .replace(R.id.content_frame, BlankFragment.newInstance("",""))

                        .commit();

            }

//           DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

         //   Utility.emailNote(this);
        requestCameraPermission(this);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu, menu);
        Utility.savemenu = menu.getItem(0);
        Utility.savemenu.setVisible(false);
        Utility.sharemenu = menu.getItem(1);
        Utility.sharemenu.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent dbmanager = new Intent(HomeActivity.this,AndroidDatabaseManager.class);
            startActivity(dbmanager);

//          File ff= Toolbar_ActionMode_Callback.getPdfPath(CreateReport.loaddata,HomeActivity.this);
//          if(ff!=null){
//              ArrayList<File> fdd=new ArrayList<>();
//              fdd.add(ff);
//            Utility.emailNote(HomeActivity.this,fdd);}
//            else {
//
//              Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
//          }
            return true;
        }
        if (id == R.id.save) {
    Utility.optionItemSave(HomeActivity.this,1);
            return true;
        }
        if (id == android.R.id.home) {
          onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//    @Override
//    public void onBackPressed() {
//
//        int count = getFragmentManager().getBackStackEntryCount();
//
//        if (count == 0) {
//            super.onBackPressed();
//            //additional code
//        } else {
//            getFragmentManager().popBackStack();
//        }
//
//    }

@Override
public void onBackPressed() {
    super.onBackPressed();


}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


        //toggleSelection(position);

    private static void showPermissionsAlert(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(context);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
   public static void requestCameraPermission(final Activity context) {
        Dexter.withActivity(context)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                     if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionsAlert(context);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    public void replaceFragment(){
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        FragmentDataViewImage mFrag = new FragmentDataViewImage();
        t.replace(R.id.viewpager, mFrag);
        t.commit();
    }
}
