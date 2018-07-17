package com.example.pentagon.appbar;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.DataClass.DataSystems;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.CreateReport;

import java.util.ArrayList;


/**
 * Created by asus on 05-Feb-17.
 */


public class SqliteDb extends SQLiteOpenHelper {
    public static String mob;
    public static String type="mmm";
    public static String db="Cab.db";
// "id": "1",
//         "name": "Cash In Hand",
//         "code": "Cash",
//         "ref_id": "0",
//         "ref_code": "yes",
//         "is_default": "0",
//         "acc_main_id": "4",
//         "openning_bal": "0.00",
//         "published": "yes"
//id
//
//        voucher_id
//
//    from voucher table
//
//            acc_ledgers_id
//
//    from screen and cash
//
//    credit_amounnt
//
//0 or from screen
//
//            debit_amount
//
//0 or from screen
//
//            published
//
//    yes
public static String CREATE_TABLE_REPORT="CREATE TABLE tblreport("+
        "id INTEGER PRIMARY  KEY,"+
        "name TEXT DEFAULT NULL,"+
        "descr TEXT DEFAULT NULL,"+
        "prjctid TEXT DEFAULT NULL,"+
        "cdate TEXT DEFAULT NULL,"+
        "udate TEXT DEFAULT NULL,"+
        "prepared TEXT DEFAULT NULL,"+
        "checked TEXT DEFAULT NULL,"+
        "approved TEXT DEFAULT NULL,"+
        "summary TEXT DEFAULT NULL,"+
        "pdf TEXT DEFAULT NULL"+")";
    public static String CREATE_TABLE_REPORT_TAG="CREATE TABLE tblreporttag("+
            "id INTEGER PRIMARY KEY,"+
            "rid TEXT DEFAULT NULL,"+
            "tagid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_REPORT_AREA="CREATE TABLE tblreportarea("+
            "id INTEGER PRIMARY KEY,"+
            "rid TEXT DEFAULT NULL,"+
            "areaid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_PROJECT_AREA="CREATE TABLE tblprojectarea("+
            "id INTEGER PRIMARY KEY,"+
            "prjctid TEXT DEFAULT NULL,"+
            "areaid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_REPORT_SYSTEM="CREATE TABLE tblreportsystem("+
            "id INTEGER PRIMARY KEY,"+
            "rid TEXT DEFAULT NULL,"+
            "systemid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_REPORT_DISCIPLINE="CREATE TABLE tblreportdiscipline("+
            "id INTEGER PRIMARY KEY,"+
            "rid TEXT DEFAULT NULL,"+
            "disciplineid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_PROJECT_TAG="CREATE TABLE tblprojecttag("+
            "id INTEGER PRIMARY KEY,"+
            "prjctid TEXT DEFAULT NULL,"+
            "tagid TEXT DEFAULT NULL"+")";
    public static String CREATE_TABLE_REPORT_DATA="CREATE TABLE tblreportdata("+
            "id INTEGER PRIMARY KEY,"+
            "rid TEXT DEFAULT NULL,"+
            "descr TEXT DEFAULT NULL,"+
            "type TEXT DEFAULT NULL,"+
            "path TEXT DEFAULT NULL,"+
            "sync INTEGER DEFAULT 0"+")";
public static String CREATE_TABLE_PROJECT="CREATE TABLE tblproject("+
        "id Text PRIMARY KEY,"+
        "prjct TEXT DEFAULT NULL,"+
        "descr TEXT DEFAULT NULL"+")";

//    public static String CREATE_TABLE_SYSTEMS="CREATE TABLE tblsystems("+
//            "code TEXT PRIMARY KEY,"+
//            "name TEXT DEFAULT NULL)";
    public static String CREATE_TABLE_TAGS="CREATE TABLE tbltags("+
            "id INTEGER PRIMARY KEY,"+
            "tag TEXT DEFAULT NULL)";

    public static String CREATE_TABLE_AREA="CREATE TABLE tblareas("+
            "id Text PRIMARY KEY,"+
            "code TEXT DEFAULT NULL)";

    public static String CREATE_TABLE_SYSTEM="CREATE TABLE tblsystem("+
            "id TEXT PRIMARY KEY,"+
            "code TEXT DEFAULT NULL)";

    public static String CREATE_TABLE_DISCIPLINE="CREATE TABLE tbldiscipline("+
            "id TEXT PRIMARY KEY,"+
            "code TEXT DEFAULT NULL)";

//    public static String CREATE_TABLE_TAGS="CREATE TABLE tbltags("+
//            "id INTEGER PRIMARY KEY,"+
//            "type TEXT DEFAULT NULL,"+
//            "sysarea TEXT DEFAULT NULL,"+
//            "fn TEXT DEFAULT NULL,"+
//            "seq TEXT DEFAULT NULL,"+
//            "paritmsuf TEXT DEFAULT NULL,"+
//            "partag TEXT DEFAULT NULL)";

//    public static String CREATE_TABLE_AREA="CREATE TABLE tblareas("+
//            "id TEXT PRIMARY KEY,"+
//            "part TEXT DEFAULT NULL,"+
//            "elem TEXT DEFAULT NULL,"+
//            "detail TEXT DEFAULT NULL"+")";
//
//    public static String CREATE_TABLE_FUNCTIONS="CREATE TABLE tblfunctions("+
//            "code TEXT PRIMARY KEY,"+
//            "name TEXT DEFAULT NULL,"+
//            "type TEXT DEFAULT NULL"+")";
//    public static String CREATE_TABLE_PARTFACILITY="CREATE TABLE tblpartfacility("+
//            "code TEXT PRIMARY KEY,"+
//            "name TEXT DEFAULT NULL)";

    SQLiteDatabase dd;
    Context co;
   public SqliteDb(Context context)
   {
       super(context,db,null,19);
       co=context;

   }
    @Override
    public void onCreate(SQLiteDatabase db) {

try{
    Log.i("error","gggggg");


    db.execSQL(CREATE_TABLE_REPORT);

    db.execSQL(CREATE_TABLE_PROJECT);
    db.execSQL(CREATE_TABLE_TAGS);


    db.execSQL(CREATE_TABLE_PROJECT_TAG);

    db.execSQL(CREATE_TABLE_REPORT_DATA);
    db.execSQL(CREATE_TABLE_REPORT_TAG);
    db.execSQL(CREATE_TABLE_REPORT_AREA);
    db.execSQL(CREATE_TABLE_REPORT_SYSTEM);
    db.execSQL(CREATE_TABLE_REPORT_DISCIPLINE);
    db.execSQL(CREATE_TABLE_PROJECT_AREA);

    db.execSQL(CREATE_TABLE_DISCIPLINE);
    db.execSQL(CREATE_TABLE_AREA);
    db.execSQL(CREATE_TABLE_SYSTEM);
}catch (Exception e){
e.printStackTrace();
Log.i("error",e.toString());}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{



            db.execSQL(CREATE_TABLE_REPORT);

            db.execSQL(CREATE_TABLE_PROJECT);
            db.execSQL(CREATE_TABLE_TAGS);

            db.execSQL(CREATE_TABLE_REPORT_TAG);
            db.execSQL(CREATE_TABLE_PROJECT_TAG);
            db.execSQL(CREATE_TABLE_REPORT_DATA);
            db.execSQL(CREATE_TABLE_REPORT_AREA);
            db.execSQL(CREATE_TABLE_REPORT_SYSTEM);
            db.execSQL(CREATE_TABLE_REPORT_DISCIPLINE);
            db.execSQL(CREATE_TABLE_PROJECT_AREA);

            db.execSQL(CREATE_TABLE_DISCIPLINE);
            db.execSQL(CREATE_TABLE_AREA);
            db.execSQL(CREATE_TABLE_SYSTEM);
            Log.i("dbcreated","sss");


            //   Toast.makeText(co, "success", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Log.i("error",e.toString());}
    }
    public void insertTemp(){
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select * from tbltags";

        Cursor cursor=dd.rawQuery(dde,null );

        if(cursor.getCount()>0)
            return;
String ss="INSERT OR REPLACE INTO tbltags(id,tag) VALUES(1,'test1'),(2,'test2'),(3,'test3'),(4,'test4')";


        // Cursor cursor=dd.rawQuery(ss,null );
     dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblareas(id,code) VALUES(1,'1'),(2,'2'),(3,'3'),(4,'4')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblsystem(id,code) VALUES(1,'1'),(2,'2'),(3,'3'),(4,'4')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tbldiscipline(id,code) VALUES(1,'1'),(2,'2'),(3,'3'),(4,'4')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);

ss="INSERT OR REPLACE INTO tblproject(id,'prjct','descr') VALUES(1,'prjct1','abcde'),(2,'prjct2','abcde2'),(3,'prjct3','abcd3'),(4,'prjct4','abcde4')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblprojecttag(id,prjctid,tagid) VALUES(1,'1','1'),(2,'2','2'),(3,'3','3'),(4,'4','4'),(5,'1','2'),(6,'1','3')";


        //         "id TEXT PRIMARY KEY,"+
        //        "name TEXT DEFAULT NULL,"+
        //        "descr TEXT DEFAULT NULL,"+
        //        "prjctid TEXT DEFAULT NULL,"+
        //        "cdate TEXT DEFAULT NULL,"+
        //        "udate TEXT DEFAULT NULL"+")";
        dd.execSQL(ss);
        ss="INSERT OR REPLACE INTO tblprojectarea(id,prjctid,areaid) VALUES(1,'1','1'),(2,'2','2'),(3,'3','3'),(4,'4','4')";


        //         "id TEXT PRIMARY KEY,"+
        //        "name TEXT DEFAULT NULL,"+
        //        "descr TEXT DEFAULT NULL,"+
        //        "prjctid TEXT DEFAULT NULL,"+
        //        "cdate TEXT DEFAULT NULL,"+
        //        "udate TEXT DEFAULT NULL"+")";
        dd.execSQL(ss);


        ss="INSERT OR REPLACE INTO tblreporttag(id,rid,tagid) VALUES(1,'2','1')";


        //         "id TEXT PRIMARY KEY,"+
        //        "name TEXT DEFAULT NULL,"+
        //        "descr TEXT DEFAULT NULL,"+
        //        "prjctid TEXT DEFAULT NULL,"+
        //        "cdate TEXT DEFAULT NULL,"+
        //        "udate TEXT DEFAULT NULL"+")";
        dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblreportarea(id,rid,areaid) VALUES(1,'2','1')";


        //         "id TEXT PRIMARY KEY,"+
        //        "name TEXT DEFAULT NULL,"+
        //        "descr TEXT DEFAULT NULL,"+
        //        "prjctid TEXT DEFAULT NULL,"+
        //        "cdate TEXT DEFAULT NULL,"+
        //        "udate TEXT DEFAULT NULL"+")";
        dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblreportdiscipline(id,rid,disciplineid) VALUES(1,'2','1')";


        //         "id TEXT PRIMARY KEY,"+
        //        "name TEXT DEFAULT NULL,"+
        //        "descr TEXT DEFAULT NULL,"+
        //        "prjctid TEXT DEFAULT NULL,"+
        //        "cdate TEXT DEFAULT NULL,"+
        //        "udate TEXT DEFAULT NULL"+")";
        dd.execSQL(ss);


        ss="INSERT OR REPLACE INTO tblreportsystem(id,rid,systemid) VALUES(1,'2','1')";


        //         "id TEXT PRIMARY KEY,"+
        //        "name TEXT DEFAULT NULL,"+
        //        "descr TEXT DEFAULT NULL,"+
        //        "prjctid TEXT DEFAULT NULL,"+
        //        "cdate TEXT DEFAULT NULL,"+
        //        "udate TEXT DEFAULT NULL"+")";
        dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblreport(id,name,descr,prjctid,cdate,udate) VALUES(1,'Report1','testreport',null,'2018/12/12','2018/12/12'),(2,'Report2','testreport2','1','2018/12/12','2018/12/12'),(3,'Report3','testreport3','2','2018/12/12','2018/12/12')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);

    }




    public ArrayList<DataReport> getReports() {

        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select r.id,r.name,r.descr,r.prjctid,r.cdate,r.udate,(select count(*) from tblreportdata where rid=r.id) as files,p.prjct,p.descr,r.prepared,r.checked,r.approved,r.summary  from tblreport as r left join tblproject as p on r.prjctid=p.id";

        Cursor cursor=dd.rawQuery(dde,null );

//        "id TEXT PRIMARY KEY,"+
//                "name TEXT DEFAULT NULL,"+
//                "descr TEXT DEFAULT NULL,"+
//                "prjctid TEXT DEFAULT NULL,"+
//                "cdate TEXT DEFAULT NULL,"+
//                "udate TEXT DEFAULT NULL"+")";
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataReport dt;
        ArrayList<DataReport> dta=new ArrayList<DataReport>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                [{ "amount":"1000",
//                        "narration":"shyam",
//                        "date":"2018-02-11",
//                        "created_by":"1",
//                        "type":"pay",
//                        "vid":"137",
//                        "toid":"1"}]


//                    "prepared TEXT DEFAULT NULL,"+
//                            "checked TEXT DEFAULT NULL,"+
//                            "approved TEXT DEFAULT NULL"+")";
                    dt = new DataReport();
                    dt.setId(cursor.getString(0));
                    dt.setReportname(cursor.getString(1));
                    dt.setDescrpotion(cursor.getString(2));
                    dt.setPrjct(cursor.getString(3));
                    dt.setCdate(cursor.getString(4));
                    dt.setUdate(cursor.getString(5));
                    dt.setFiles(cursor.getString(6));
                    dt.setPrjctname(cursor.getString(7));
                    dt.setPrjctdescr(cursor.getString(8));
                    dt.setPrepared(cursor.getString(9));
                    dt.setChecked(cursor.getString(10));
                    dt.setApproved(cursor.getString(11));
                    dt.setSummary(cursor.getString(12));
                    //     dt.setDate(cursor.getString(2));


                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;

    }


















    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }

}

//    public ArrayList<DataSystems> getSystems() {
//
//                SQLiteDatabase dd=this.getReadableDatabase();
//        String dde;
//
//            dde="select * from tblsystems";
//
//        Cursor cursor=dd.rawQuery(dde,null );
//
//
//        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
//        DataSystems dt;
//        ArrayList<DataSystems> dta=new ArrayList<DataSystems>();
//        if (cursor.moveToFirst()) {
//            do {
//                try {
////                [{ "amount":"1000",
////                        "narration":"shyam",
////                        "date":"2018-02-11",
////                        "created_by":"1",
////                        "type":"pay",
////                        "vid":"137",
////                        "toid":"1"}]
//
//                    dt = new DataSystems();
//                    dt.setCode(cursor.getString(0));
//                    dt.setName(cursor.getString(1));
//               //     dt.setDate(cursor.getString(2));
//
//
//                    dta.add(dt);
//
//
//                } catch (Exception ee) {
//                    ee.printStackTrace();
//                    Log.e("er",ee.toString()+"");
//                }
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        dd.close();
//        return dta;
//
//    }

    public ArrayList<DataSystems> getFunctions() {


        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select * from tblfunctions";

        Cursor cursor=dd.rawQuery(dde,null );


        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataSystems dt;
        ArrayList<DataSystems> dta=new ArrayList<DataSystems>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                [{ "amount":"1000",
//                        "narration":"shyam",
//                        "date":"2018-02-11",
//                        "created_by":"1",
//                        "type":"pay",
//                        "vid":"137",
//                        "toid":"1"}]

                    dt = new DataSystems();
                    dt.setCode(cursor.getString(0));
                    dt.setName(cursor.getString(1));
                    //     dt.setDate(cursor.getString(2));


                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;
    }

    public ArrayList<PrjctData> getPrjcts() {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select * from tblproject";

        Cursor cursor=dd.rawQuery(dde,null );


        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        PrjctData dt;
        ArrayList<PrjctData> dta=new ArrayList<PrjctData>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                [{ "amount":"1000",
//                        "narration":"shyam",
//                        "date":"2018-02-11",
//                        "created_by":"1",
//                        "type":"pay",
//                        "vid":"137",
//                        "toid":"1"}]

                    dt = new PrjctData();
                    dt.setId(cursor.getString(0));
                    dt.setPrjct(cursor.getString(1));
                       dt.setDescr(cursor.getString(2));


                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;
    }

    public ArrayList<DataTag> getPrjctsTags(String prjctid,String reportid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select p.id,p.prjctid,p.tagid,t.tag,(select count(*) from tblreporttag where tagid=p.tagid and rid='"+reportid+"') as pp from tblprojecttag as p join tbltags as t on t.id=p.tagid where p.prjctid='"+prjctid+"'";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();

                    dt.setId(cursor.getString(0));
                    dt.setPrjctid(cursor.getString(1));
                    dt.setTagid(cursor.getString(2));
                    dt.setTag(cursor.getString(3));
                    if(cursor.getInt(4)>0)
                        dt.setSelected(true);
                    else
                        dt.setSelected(false);
                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;

    }

    public ArrayList<DataTag> getPrjctsAreas(String prjctid,String reportid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select p.id,p.prjctid,p.areaid,t.code,(select count(*) from tblreportarea where areaid=p.areaid and rid='"+reportid+"') as pp from tblprojectarea as p join tblareas as t on t.id=p.areaid where p.prjctid='"+prjctid+"'";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();

                    dt.setId(cursor.getString(0));
                    dt.setPrjctid(cursor.getString(1));
                    dt.setTagid(cursor.getString(2));
                    dt.setTag(cursor.getString(3));
                    if(cursor.getInt(4)>0)
                        dt.setSelected(true);
                    else
                        dt.setSelected(false);
                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;

    }

    public ArrayList<DataTag> getReportDiscipline(String reportid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select id,code,(select count(*) from tblreportdiscipline where disciplineid=tbldiscipline.id and rid='"+reportid+"') as pp from tbldiscipline";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();



                    dt.setTagid(cursor.getString(0));
                    dt.setTag(cursor.getString(1));
                    if(cursor.getInt(2)>0)
                        dt.setSelected(true);
                    else
                        dt.setSelected(false);
                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;

    }

    public ArrayList<DataTag> getSystems() {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select id,code from tblsystem";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();



                    dt.setTagid(cursor.getString(0));
                    dt.setTag(cursor.getString(1));
//                    if(cursor.getInt(2)>0)
//                        dt.setSelected(true);
//                    else
//                        dt.setSelected(false);
                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;

    }
    public ArrayList<DataTag> getDiscipline() {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select id,code from tbldiscipline";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();



                    dt.setTagid(cursor.getString(0));
                    dt.setTag(cursor.getString(1));
//                    if(cursor.getInt(2)>0)
//                        dt.setSelected(true);
//                    else
//                        dt.setSelected(false);
                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;

    }
    public ArrayList<DataTag> getReportSystem(String reportid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select id,code,(select count(*) from tblreportsystem where systemid=tblsystem.id and rid='"+reportid+"') as pp from tblsystem";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();



                    dt.setTagid(cursor.getString(0));
                    dt.setTag(cursor.getString(1));
                    if(cursor.getInt(2)>0)
                        dt.setSelected(true);
                    else
                        dt.setSelected(false);
                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;

    }

    public ArrayList<DataTag> getTags() {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select * from tbltags";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();

                    dt.setTagid(cursor.getString(0));


                    dt.setTag(cursor.getString(1));

dta.add(dt);
                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;


    }

    public boolean addTag(String prjct,String tag,boolean isnew) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=0;
        Cursor cursor=dd.rawQuery("select max(id) from tbltags",null );
        if(isnew){
            if(cursor.moveToFirst())
                transactioncount=cursor.getInt(0)+1;
            else
            transactioncount =1;}
else
    transactioncount=Integer.parseInt(tag);
        cursor.close();
        boolean	status=false;
        cursor=dd.rawQuery("select max(id) from tblprojecttag",null );
        int prjctcount=1;
        if(cursor.moveToFirst())
            prjctcount=cursor.getInt(0);

        String insertCategoryQuery1 = "INSERT INTO tblprojecttag(" +
                "id,"+
                "prjctid,"+
                "tagid"+")"+
                " VALUES (?,?,?)";
        String insertCategoryQuery = "INSERT INTO tbltags(" +
                "id,"+
                "tag"+")"+
                " VALUES (?,?)";
        dd= this.getWritableDatabase();
        SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);
        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);





            try {

                insertCategory.clearBindings();

                insertCategory.bindLong(1,transactioncount);
                insertCategory.bindString(2,tag);


                prjctcount++;
                insertCategory1.bindLong(1,prjctcount);
                insertCategory1.bindString(2,prjct);

                insertCategory1.bindString(3,String.valueOf(transactioncount));
                //insertCategory.bindString(9,actor.getString("published"));

                Log.i("error1","111");
if(isnew)
                insertCategory.execute();//Insert();
                insertCategory1.execute();
                status=true;

            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;

    }
    public boolean insertReport(DataReport dataReport) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;

        Cursor cursor=dd.rawQuery("select id from tblreport",null );
        int transactioncount=cursor.getCount();

        cursor.close();
        boolean	status=false;
//        public static String CREATE_TABLE_REPORT="CREATE TABLE tblreport("+
//                "id INTEGER PRIMARY KEY,"+
//                "name TEXT DEFAULT NULL,"+
//                "descr TEXT DEFAULT NULL,"+
//                "prjctid TEXT DEFAULT NULL,"+
//                "cdate TEXT DEFAULT NULL,"+
//                "udate TEXT DEFAULT NULL,"+
//                "prepared TEXT DEFAULT NULL,"+
//                "checked TEXT DEFAULT NULL,"+
//                "approved TEXT DEFAULT NULL"+")";

        String insertCategoryQuery1 = "INSERT or Replace INTO tblreport(" +
                "id,"+
                "name,"+
                "descr,"+
                "prjctid,"+
                "cdate,"+
                "udate,"+
                "prepared,"+
                "checked,"+
                "approved,"+
                "summary"+")"+
                " VALUES (?,?,?,?,?,?,?,?,?,?)";

        dd= this.getWritableDatabase();

        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);





            try {

                insertCategory1.clearBindings();
                transactioncount++;
                insertCategory1.bindLong(1,Integer.parseInt(dataReport.getId()));
                insertCategory1.bindString(2,dataReport.getReportname());



                insertCategory1.bindString(3,dataReport.getDescrpotion());
                insertCategory1.bindString(4,dataReport.getPrjct());
                insertCategory1.bindString(5,dataReport.getCdate());
                insertCategory1.bindString(6,dataReport.getUdate());
                insertCategory1.bindString(7,dataReport.getPrepared());
                insertCategory1.bindString(8,dataReport.getChecked());
                insertCategory1.bindString(9,dataReport.getApproved());
                insertCategory1.bindString(10,dataReport.getSummary());

                Log.i("error1","111");


                insertCategory1.execute();
                status=true;

            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;

    }

    public String getReportID() {

        SQLiteDatabase dd=this.getReadableDatabase();
        int transactioncount=1;

        Cursor cursor=dd.rawQuery("select id from tblreport",null );
        if(cursor.moveToFirst())
         transactioncount=cursor.getInt(0)+1;

        cursor.close();
       return String.valueOf(transactioncount);

    }

    public boolean insertReportTags(ArrayList<DataTag> prjcttags) {
        int transactioncount=0;
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
dd.execSQL("delete from tblreporttag where rid='"+CreateReport.loaddata.getId()+"'");

        Cursor cursor=dd.rawQuery("select MAX(id) from tblreporttag",null );
        if(cursor.moveToFirst())
            transactioncount=cursor.getInt(0);

        cursor.close();
        boolean	status=false;
//        public static String CREATE_TABLE_REPORT_TAG="CREATE TABLE tblreporttag("+
//                "id INTEGER PRIMARY KEY,"+
//                "rid TEXT DEFAULT NULL,"+
//                "tagid TEXT DEFAULT NULL"+")";

        String insertCategoryQuery1 = "INSERT or Replace INTO tblreporttag(" +
                "id,"+
                "rid,"+
                "tagid"+")"+
                " VALUES (?,?,?)";

        dd= this.getWritableDatabase();

        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);





            try {
for(int j=0;j<prjcttags.size();j++) {
    insertCategory1.clearBindings();
    Log.i("tag", "111"+prjcttags.get(j).getSelected()+ prjcttags.get(j).getTagid());
    if(prjcttags.get(j).getSelected()) {
        transactioncount++;
        insertCategory1.bindLong(1, transactioncount);
        insertCategory1.bindString(2, CreateReport.loaddata.getId());


        insertCategory1.bindString(3, prjcttags.get(j).getTagid());


        Log.i("error111", "111");


        insertCategory1.execute();
    }
    status = true;
}
            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;
    }
    public boolean insertReportArea(ArrayList<DataTag> prjcttags) {
        int transactioncount = 0;
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        dd.execSQL("delete from tblreportarea where rid='"+CreateReport.loaddata.getId()+"'");
        Cursor cursor=dd.rawQuery("select MAX(id) from tblreportarea",null );
        if(cursor.moveToFirst())
            transactioncount=cursor.getInt(0);

     ;

        cursor.close();
        boolean	status=false;
//        public static String CREATE_TABLE_REPORT_TAG="CREATE TABLE tblreporttag("+
//                "id INTEGER PRIMARY KEY,"+
//                "rid TEXT DEFAULT NULL,"+
//                "tagid TEXT DEFAULT NULL"+")";

        String insertCategoryQuery1 = "INSERT or Replace INTO tblreportarea(" +
                "id,"+
                "rid,"+
                "areaid"+")"+
                " VALUES (?,?,?)";

        dd= this.getWritableDatabase();

        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);





            try {
                for(int j=0;j<prjcttags.size();j++) {
                    insertCategory1.clearBindings();
                    Log.i("area", "111"+prjcttags.get(j).getSelected());
                    if(prjcttags.get(j).getSelected())
                    {
                    transactioncount++;
                    insertCategory1.bindLong(1, transactioncount);
                    insertCategory1.bindString(2, CreateReport.loaddata.getId());


                    insertCategory1.bindString(3, prjcttags.get(j).getTagid());


                    Log.i("error1", "111");


                    insertCategory1.execute();}
                    status = true;
                }
            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;
    }

    public boolean insertReportDiscipline(ArrayList<DataTag> prjcttags) {
        int transactioncount=0;
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        dd.execSQL("delete from tblreportdiscipline where rid='"+CreateReport.loaddata.getId()+"'");
        Cursor cursor=dd.rawQuery("select MAX(id) from tblreportdiscipline",null );
        if(cursor.moveToFirst())
            transactioncount=cursor.getInt(0);



        cursor.close();
        boolean	status=false;
//        public static String CREATE_TABLE_REPORT_TAG="CREATE TABLE tblreporttag("+
//                "id INTEGER PRIMARY KEY,"+
//                "rid TEXT DEFAULT NULL,"+
//                "tagid TEXT DEFAULT NULL"+")";

        String insertCategoryQuery1 = "INSERT or Replace INTO tblreportdiscipline(" +
                "id,"+
                "rid,"+
                "disciplineid"+")"+
                " VALUES (?,?,?)";

        dd= this.getWritableDatabase();

        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);





            try {
                for(int j=0;j<prjcttags.size();j++) {
                    insertCategory1.clearBindings();
                    if(prjcttags.get(j).getSelected())
                    {
                    transactioncount++;
                    insertCategory1.bindLong(1, transactioncount);
                    insertCategory1.bindString(2, CreateReport.loaddata.getId());


                    insertCategory1.bindString(3, prjcttags.get(j).getTagid());


                    Log.i("error1", "111");


                    insertCategory1.execute();}
                    status = true;
                }
            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;
    }
    public boolean insertReportSystem(ArrayList<DataTag> prjcttags) {
        int transactioncount=0;
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        dd.execSQL("delete from tblreportsystem where rid='"+CreateReport.loaddata.getId()+"'");

        Cursor cursor=dd.rawQuery("select id from tblreportsystem",null );


      //  Cursor cursor=dd.rawQuery("select MAX(id) from tblreportsystem",null );
        if(cursor.moveToFirst())
            transactioncount=cursor.getInt(0);

        cursor.close();
        boolean	status=false;
//        public static String CREATE_TABLE_REPORT_TAG="CREATE TABLE tblreporttag("+
//                "id INTEGER PRIMARY KEY,"+
//                "rid TEXT DEFAULT NULL,"+
//                "tagid TEXT DEFAULT NULL"+")";

        String insertCategoryQuery1 = "INSERT or Replace INTO tblreportsystem(" +
                "id,"+
                "rid,"+
                "systemid"+")"+
                " VALUES (?,?,?)";

        dd= this.getWritableDatabase();

        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);





            try {
                for(int j=0;j<prjcttags.size();j++) {
                    insertCategory1.clearBindings();
                    if(prjcttags.get(j).getSelected())
                    {
                    transactioncount++;
                    insertCategory1.bindLong(1, transactioncount);
                    insertCategory1.bindString(2, CreateReport.loaddata.getId());


                    insertCategory1.bindString(3, prjcttags.get(j).getTagid());


                    Log.i("error1", "111");


                    insertCategory1.execute();}
                    status = true;
                }
            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;
    }
    public boolean insertReportData(ArrayList<DataPreview> dataPreviews) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
      //  dd.execSQL("delete from tblreporttag where rid='"+CreateReport.loaddata.getId()+"'");

        Cursor cursor=dd.rawQuery("select id from tblreportdata",null );
        int transactioncount=cursor.getCount();

        cursor.close();
        boolean	status=false;
//        public static String CREATE_TABLE_REPORT_DATA="CREATE TABLE tblreportdata("+
//                "id INTEGER PRIMARY KEY,"+
//                "rid TEXT DEFAULT NULL,"+
//                "descr TEXT DEFAULT NULL,"+
//                "type TEXT DEFAULT NULL,"+
//                "path TEXT DEFAULT NULL,"+
//                "sync INTEGER DEFAULT 0"+")";

        String insertCategoryQuery1 = "INSERT or Replace INTO tblreportdata(" +
                "id,"+
                "rid,"+
                "descr,"+
                "type,"+
                "path,"+
                "sync"+")"+
                " VALUES (?,?,?,?,?,?)";

        dd= this.getWritableDatabase();

        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);





            try {
                for(int j=0;j<dataPreviews.size();j++) {
                    insertCategory1.clearBindings();
                    transactioncount++;
                    if(dataPreviews.get(j).getId().equals("-1"))
                    insertCategory1.bindLong(1, transactioncount);
                    else
                        insertCategory1.bindLong(1, Integer.parseInt(dataPreviews.get(j).getId()));
                    insertCategory1.bindString(2, CreateReport.loaddata.getId());


                    insertCategory1.bindString(3, dataPreviews.get(j).getDescr());
                    insertCategory1.bindString(4, dataPreviews.get(j).getType());
                    insertCategory1.bindString(5, dataPreviews.get(j).getPath());
                    insertCategory1.bindString(6, dataPreviews.get(j).getSync());


                    Log.i("error1", "111");


                    insertCategory1.execute();
                    status = true;
                }
            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;

    }

    public ArrayList<DataPreview> getReportData(String rid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select * from tblreportdata where rid='"+rid+"'";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataPreview dt;
        ArrayList<DataPreview> dta=new ArrayList<DataPreview>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id,"+
//                            "rid,"+
//                            "descr,"+
//                            "type,"+
//                            "path,"+
//                            "sync"+")"+

                    dt = new DataPreview();

                    dt.setId(cursor.getString(0));


                    dt.setDescr(cursor.getString(2));
                    dt.setType(cursor.getString(3));
                    dt.setPath(cursor.getString(4));
                    dt.setSync(cursor.getString(5));
                    dta.add(dt);
                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;


    }

    public ArrayList<DataTag> getAreas() {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select * from tblareas";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();

                    dt.setTagid(cursor.getString(0));


                    dt.setTag(cursor.getString(1));

                    dta.add(dt);
                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;

    }

    public boolean addArea(String prjct, String tag, boolean isnew, String s) {


        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=null;
        if(isnew) {
          cursor = dd.rawQuery("select id from tblareas where id='" + s + "'", null);
            if (cursor.getCount() > 0){
                cursor.close();
                return false;

            }

            cursor.close();
        }



        boolean	status=false;
        cursor=dd.rawQuery("select max(id) from tblprojectarea",null );
        int prjctcount=0;
if(cursor.moveToFirst())
    prjctcount=cursor.getInt(0);
        String insertCategoryQuery1 = "INSERT INTO tblprojectarea(" +
                "id,"+
                "prjctid,"+
                "areaid"+")"+
                " VALUES (?,?,?)";
        String insertCategoryQuery = "INSERT INTO tblareas(" +
                "id,"+
                "code"+")"+
                " VALUES (?,?)";
        dd= this.getWritableDatabase();
        SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);
        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);





            try {

                insertCategory.clearBindings();

                insertCategory.bindString(1,s);
                insertCategory.bindString(2,tag);


                prjctcount++;
                insertCategory1.bindLong(1,prjctcount);
                insertCategory1.bindString(2,prjct);

                insertCategory1.bindString(3,s);
                //insertCategory.bindString(9,actor.getString("published"));

                Log.i("error1","111");
                if(isnew)
                    insertCategory.execute();//Insert();
                insertCategory1.execute();
                status=true;

            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);
//   cursor=dd.rawQuery("select max(id) from tblareas",null );
//        DataTag dt=new DataTag();
//        dt.setTag(tag);
//        if(cursor.moveToFirst())
//        dt.setTagid(cursor.getString(0));
        cursor.close();
        dd.close();
        return status;
    }

    public boolean insertPrjct(String prjctid,String prjct, String desc) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        //  dd.execSQL("delete from tblreporttag where rid='"+CreateReport.loaddata.getId()+"'");
//        Cursor cursor=dd.rawQuery("select id from tblproject where prjct='"+prjct+"'",null );
//
//if(cursor.getCount()>0){
//    cursor.close();
//    return false;
//}

        Cursor  cursor=dd.rawQuery("select * from tblproject where id='"+prjctid+"'",null );
//        int transactioncount=0;
//                if(cursor.moveToFirst()){
//                    transactioncount=cursor.getInt(0);
//
//                }
if(cursor.getCount()>0)
    return false;
        cursor.close();
        boolean	status=false;
//        public static String CREATE_TABLE_REPORT_DATA="CREATE TABLE tblreportdata("+
//                "id INTEGER PRIMARY KEY,"+
//                "rid TEXT DEFAULT NULL,"+
//                "descr TEXT DEFAULT NULL,"+
//                "type TEXT DEFAULT NULL,"+
//                "path TEXT DEFAULT NULL,"+
//                "sync INTEGER DEFAULT 0"+")";

        String insertCategoryQuery1 = "INSERT or Replace INTO tblproject(" +
                "id,"+
                "prjct,"+
                "descr"+")"+
                " VALUES (?,?,?)";

        dd= this.getWritableDatabase();

        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);





            try {

                    insertCategory1.clearBindings();


                    insertCategory1.bindString(1,prjctid);
                    insertCategory1.bindString(2,prjct );


                    insertCategory1.bindString(3,desc);



                    Log.i("error1", "111");


                    insertCategory1.execute();
                    status = true;

            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;
    }

    public String getPdf(String id) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="select pdf from tblreport where id='"+id+"'";

        Cursor cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";
                    if(cursor.getString(0)!=null)
pdf=cursor.getString(0);

                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return pdf;

    }

    public void savePdf(String absolutePath,String rid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="update tblreport set pdf='"+absolutePath+"' where id='"+rid+"'";

       dd.execSQL(dde);
    }

    public void deletePrjct(String id) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="delete from tblproject where id='"+id+"'";
        dd.execSQL(dde);
    }

    public boolean editPrjct(String prjct, String descr, String id) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="update tblproject set prjct='"+prjct+"',descr='"+descr+"' where id='"+id+"'";

        dd.execSQL(dde);
        return true;
    }

    public void deleteArea(String id) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";
try{
Log.e("oidd",id);
//    dde="delete from tblareas where id='"+id+"'";
//    dd.compileStatement(dde);
//    dd.execSQL(dde);
    dde="delete from tblprojectarea where areaid='"+id+"'";
    dd.execSQL(dde);
    dd.close();
}catch (Exception e){e.printStackTrace();}

    }

    public boolean updateArea(String tagid, String s, boolean b) {


        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="update tblareas set code='"+s+"' where id='"+tagid+"'";

        dd.execSQL(dde);
        return true;
    }

    public void deleteTag(String tagid) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";
        try{
            Log.e("oidd",tagid);
//            dde="delete from tbltags where id='"+tagid+"'";
//            dd.compileStatement(dde);
//            dd.execSQL(dde);
            dde="delete from tblprojecttag where tagid='"+tagid+"'";
            dd.execSQL(dde);
            dd.close();
        }catch (Exception e){e.printStackTrace();}
    }

    public boolean updateTag(String tagid, String s, boolean b) {


        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="update tbltags set tag='"+s+"' where id='"+tagid+"'";

        dd.execSQL(dde);
        return true;


    }

    public boolean updateSystem(String s1,String s) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="update tblsystem set code='"+s+"' where id='"+s1+"'";

        dd.execSQL(dde);
        return true;


    }
    public boolean updateDiscipline(String s1,String s) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="update tbldiscipline set code='"+s+"' where id='"+s1+"'";

        dd.execSQL(dde);
        return true;


    }

    public boolean addDiscipline(String code,String name) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        boolean	status=false;
        Cursor cursor=dd.rawQuery("select * from tbldiscipline where id='"+code+"'",null );
        if(cursor.getCount()>0)
        {
            cursor.close();
            return status;
        }




        String insertCategoryQuery = "INSERT INTO tbldiscipline(" +
                "id,"+
                "code"+")"+
                " VALUES (?,?)";
        dd= this.getWritableDatabase();
        SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);

        try {
            //JSONArray jsn=new JSONArray(data);





            try {

                insertCategory.clearBindings();

                insertCategory.bindString(1,code);
                insertCategory.bindString(2,name);



                insertCategory.execute();//Insert();

                status=true;

            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;
    }
    public boolean addSystem(String code,String name) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=dd.rawQuery("select id from tblsystem where id='"+code+"'",null );
        boolean	status=false;
       if(cursor.getCount()>0)

        {
            cursor.close();
            return status;
        }




        String insertCategoryQuery = "INSERT INTO tblsystem(" +
                "id,"+
                "code"+")"+
                " VALUES (?,?)";
        dd= this.getWritableDatabase();
        SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);

        try {
            //JSONArray jsn=new JSONArray(data);





            try {

                insertCategory.clearBindings();

                insertCategory.bindString(1,code);
                insertCategory.bindString(2,name);



                    insertCategory.execute();//Insert();

                status=true;

            }catch (android.database.sqlite.SQLiteConstraintException ee){
                ee.printStackTrace();
                Log.i("error",ee.toString()+"");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );


            cursor.close();


        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);

        return status;
    }
    public void deleteSystem(String id) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="delete from tblsystem where id='"+id+"'";
        dd.execSQL(dde);
    }
    public void deleteDiscipline(String id) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="delete from tbldiscipline where id='"+id+"'";
        dd.execSQL(dde);
    }
}