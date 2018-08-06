package com.example.pentagon.appbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Hashtable;

public class SharedPreferenceClass {
    public static final String lastreport = "lastreport";
    public static final String lastdiscipline = "lastdiscipline";
    public static final String lastsystem = "lastsystem";
    public static final String lastarea = "lastarea";
    public static final String lastprjct = "lastprjct";
    public static final String lasttags = "lasttags";

    public static final String PROPERTY_ITEMUPDATE = "itemupdate";

    public String getStoredValueLastTags(Context context) {

        final SharedPreferences prefs = getSharedPreferencesForServer(context);

        String storedvalue = prefs.getString(lasttags, null);
      //  Log.i("SharedPreferenceClass",storedvalue);
        if(storedvalue!=null) {
            if (storedvalue.isEmpty()) {
                Log.i("SharedPreferenceClass", "Server-IP not found.");
                return null;
            }
        }

        return storedvalue;

    }




    public void storeDatabaseLastTags(Context context,String rid) {

        try{  final SharedPreferences prefs = getSharedPreferencesForServer(context);

            SharedPreferences.Editor editor = prefs.edit();

            editor.putString(lasttags, rid);


            editor.apply();}
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getStoredValueLastPrjct(Context context) {

        final SharedPreferences prefs = getSharedPreferencesForServer(context);

        String storedvalue = prefs.getString(lastprjct, null);
       // Log.i("SharedPreferenceClass",storedvalue);
        if(storedvalue!=null) {
            if (storedvalue.isEmpty()) {
                Log.i("SharedPreferenceClass", "Server-IP not found.");
                return null;
            }
        }
        return storedvalue;

    }




    public void storeDatabaseLastPrjct(Context context,String rid) {

        try{  final SharedPreferences prefs = getSharedPreferencesForServer(context);

            SharedPreferences.Editor editor = prefs.edit();

            editor.putString(lastprjct, rid);


            editor.apply();}
        catch (Exception e){
            e.printStackTrace();
        }

    }



    public String getStoredValueLastArea(Context context) {

        final SharedPreferences prefs = getSharedPreferencesForServer(context);

        String storedvalue = prefs.getString(lastarea, null);
    //    Log.i("SharedPreferenceClass",storedvalue);
        if(storedvalue!=null) {
            if (storedvalue.isEmpty()) {
                Log.i("SharedPreferenceClass", "Server-IP not found.");
                return null;
            }
        }

        return storedvalue;

    }




    public void storeDatabaseLastArea(Context context,String rid) {

        try{  final SharedPreferences prefs = getSharedPreferencesForServer(context);

            SharedPreferences.Editor editor = prefs.edit();

            editor.putString(lastarea, rid);


            editor.apply();}
        catch (Exception e){
            e.printStackTrace();
        }

    }


    public String getStoredValueLastSystem(Context context) {

        final SharedPreferences prefs = getSharedPreferencesForServer(context);

        String storedvalue = prefs.getString(lastsystem, null);
//        Log.i("SharedPreferenceClass",storedvalue);

if(storedvalue!=null) {
    if (storedvalue.isEmpty()) {
        Log.i("SharedPreferenceClass", "Server-IP not found.");
        return null;
    }
}

        return storedvalue;

    }




    public void storeDatabaseLastSystem(Context context,String rid) {

        try{  final SharedPreferences prefs = getSharedPreferencesForServer(context);

            SharedPreferences.Editor editor = prefs.edit();

            editor.putString(lastsystem, rid);


            editor.apply();}
        catch (Exception e){
            e.printStackTrace();
        }

    }


    public String getStoredValueLastDiscipline(Context context) {

        final SharedPreferences prefs = getSharedPreferencesForServer(context);

        String storedvalue = prefs.getString(lastdiscipline, null);
        //Log.i("SharedPreferenceClass",storedvalue);
        if(storedvalue!=null) {
            if (storedvalue.isEmpty()) {
                Log.i("SharedPreferenceClass", "Server-IP not found.");
                return null;
            }
        }
        return storedvalue;

    }




    public void storeDatabaseLastDiscipline(Context context,String rid) {

        try{  final SharedPreferences prefs = getSharedPreferencesForServer(context);

            SharedPreferences.Editor editor = prefs.edit();

            editor.putString(lastdiscipline, rid);


            editor.apply();}
        catch (Exception e){
            e.printStackTrace();
        }

    }


    public String getStoredValueLastReport(Context context) {

        final SharedPreferences prefs = getSharedPreferencesForServer(context);

        String storedvalue = prefs.getString(lastreport, null);
      //  Log.i("SharedPreferenceClass",storedvalue);
        if(storedvalue!=null) {
            if (storedvalue.isEmpty()) {
                Log.i("SharedPreferenceClass", "Server-IP not found.");
                return null;
            }
        }

        return storedvalue;

    }




    public void storeDatabaseLastReport(Context context,String rid) {

      try{  final SharedPreferences prefs = getSharedPreferencesForServer(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(lastreport, rid);


        editor.apply();}
      catch (Exception e){
          e.printStackTrace();
      }

    }
    public void ClearAll(Context context ) {

        try{  final SharedPreferences prefs = getSharedPreferencesForServer(context);

            SharedPreferences.Editor editor = prefs.edit();



            editor.putString(lastreport, null);

            editor.apply();}
        catch (Exception e){
            e.printStackTrace();
        }

    }








    private SharedPreferences getSharedPreferencesForServer(Context context) {

        // This sample app persists the serverIp in shared preferences, but

        // how you store the regID in your app is up to you.

        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

    }


}
