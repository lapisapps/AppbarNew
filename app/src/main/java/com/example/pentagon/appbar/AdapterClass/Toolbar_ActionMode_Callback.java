package com.example.pentagon.appbar.AdapterClass;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pentagon.appbar.CameraUtils;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.ReportsFragment;
import com.example.pentagon.appbar.HomeActivity;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by SONU on 22/03/16.
 */
public class Toolbar_ActionMode_Callback implements ActionMode.Callback {

    private Activity context;
    private RecyclerViewAdapterReports recyclerView_adapter;

    private ArrayList<DataReport> message_models;
    private boolean isListViewFragment;

ReportsFragment reportsFragment;
    public Toolbar_ActionMode_Callback(Activity context, RecyclerViewAdapterReports recyclerView_adapter, ArrayList<DataReport> message_models,ReportsFragment reportsFragment) {
        this.context = context;
        this.recyclerView_adapter = recyclerView_adapter;

        this.message_models = message_models;
this.reportsFragment=reportsFragment;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.action_main, menu);//Inflate the menu over action mode
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

        //Sometimes the meu will not be visible so for that we need to set their visibility manually in this method
        //So here show action menu according to SDK Levels
        if (Build.VERSION.SDK_INT < 11) {
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_delete), MenuItemCompat.SHOW_AS_ACTION_NEVER);
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_share), MenuItemCompat.SHOW_AS_ACTION_NEVER);
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_sync), MenuItemCompat.SHOW_AS_ACTION_NEVER);
        } else {
            menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_share).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_sync).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:

                //Check if current action mode is from ListView Fragment or RecyclerView Fragment


                    //If current fragment is recycler view fragment
                    Fragment recyclerFragment = new ReportsFragment();//Get recycler view fragment
                    if (recyclerFragment != null)
                        //If recycler fragment not null
                        ((ReportsFragment) recyclerFragment).deleteRows();//delete selected rows

                break;
            case R.id.action_share:

                //Get selected ids on basis of current fragment action mode
                SparseBooleanArray selected;

                    selected = recyclerView_adapter
                            .getSelectedIds();

                int selectedMessageSize = selected.size();

                //Loop to all selected items
                ArrayList<File> files;
                files=new ArrayList<>();
                for (int i = (selectedMessageSize - 1); i >= 0; i--) {
                    if (selected.valueAt(i)) {
                        //get selected data in Model
                      DataReport model = message_models.get(selected.keyAt(i));
                      String title = model.getReportname();
                       String subTitle = model.getPrjct();
File fil =getPdfPath(model,context);
if(fil!=null)
    files.add(fil);
                        //Print the data to show if its working properly or not
                        Log.e("Selected Items", "Title - " + title + "\n" + "Sub Title - " + subTitle);
//                        try {
//String filepath=new SqliteDb(context).getPdf(model.getId());
//                            if(filepath.isEmpty())
//                            {
//                                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//
//                                    File mm=    Utility.createPdf(model,new SqliteDb(context).getPrjctsTags(model.getPrjct(),model.getId()),new SqliteDb(context).getReportData(model.getId()));
//                                    new SqliteDb(context).savePdf( mm.getAbsolutePath(),model.getId())  ;
//                                    files.add(mm);
//                                }else {
//                                    HomeActivity.requestCameraPermission(context);
//
//                                }
//
//
//                            }else{
//
//                                File file =new File(filepath);
//                                files.add(file);
//                            }
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (DocumentException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
                if(files.size()>0)
                Utility.emailNote(context,files);
                else
               Toast.makeText(context, "File not Found", Toast.LENGTH_SHORT).show();//Show toast
                mode.finish();//Finish action mode
                break;
            case R.id.action_sync:
                Toast.makeText(context, "You selected Forward menu.", Toast.LENGTH_SHORT).show();//Show toast
                mode.finish();//Finish action mode
                break;


        }
        return false;
    }
public static File getPdfPath(DataReport model,Activity context){
       File ff = null;

    try {
        String filepath=new SqliteDb(context).getPdf(model.getId());
        if(filepath.isEmpty())
        {
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                ff=    Utility.createPdf(model,new SqliteDb(context).getPrjctsTags(model.getPrjct(),model.getId()),new SqliteDb(context).getReportData(model.getId()));
                new SqliteDb(context).savePdf( ff.getAbsolutePath(),model.getId())  ;

            }else {
                HomeActivity.requestCameraPermission(context);

            }


        }else{

             ff =new File(filepath);

        }


    } catch (IOException e) {
        e.printStackTrace();
    } catch (DocumentException e) {
        e.printStackTrace();
    }
       return ff;
}

    @Override
    public void onDestroyActionMode(ActionMode mode) {

        //When action mode destroyed remove selected selections and set action mode to null
        //First check current fragment action mode

            recyclerView_adapter.removeSelection();  // remove selection

              reportsFragment.setNullToActionMode();//Set action mode null

    }
}
