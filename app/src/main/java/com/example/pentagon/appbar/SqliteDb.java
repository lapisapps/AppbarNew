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
        "rid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
        "id TEXT NOT  NULL,"+
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

//    public static String CREATE_TABLE_REPORT_DISCIPLINE="CREATE TABLE tblreportdiscipline("+
//            "id INTEGER PRIMARY KEY,"+
//            "rid TEXT DEFAULT NULL,"+
//            "areaid TEXT DEFAULT NULL"+")";
//
//    public static String CREATE_TABLE_REPORT_SYSTEM="CREATE TABLE tblreportdiscipline("+
//            "id INTEGER PRIMARY KEY,"+
//            "rid TEXT DEFAULT NULL,"+
//            "areaid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_PROJECT_AREA="CREATE TABLE tblprojectarea("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "prjctid TEXT DEFAULT NULL,"+
            "areaid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_PROJECT_DISCIPLINE="CREATE TABLE tblprojectdiscipline("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "prjctid TEXT DEFAULT NULL,"+
            "disciplineid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_PROJECT_SYSTEM="CREATE TABLE tblprojectsystem("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "prjctid TEXT DEFAULT NULL,"+
            "systemid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_REPORT_SYSTEM="CREATE TABLE tblreportsystem("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "rid TEXT DEFAULT NULL,"+
            "systemid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_REPORT_DISCIPLINE="CREATE TABLE tblreportdiscipline("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "rid TEXT DEFAULT NULL,"+
            "disciplineid TEXT DEFAULT NULL"+")";

    public static String CREATE_TABLE_PROJECT_TAG="CREATE TABLE tblprojecttag("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "prjctid TEXT DEFAULT NULL,"+
            "tagid TEXT DEFAULT NULL"+")";
    public static String CREATE_TABLE_REPORT_DATA="CREATE TABLE tblreportdata("+
            "id INTEGER PRIMARY KEY,"+
            "rid TEXT DEFAULT NULL,"+
            "descr TEXT DEFAULT NULL,"+
            "type TEXT DEFAULT NULL,"+
            "path TEXT DEFAULT NULL,"+
            "sync TEXT DEFAULT 0,"+
            "selected Boolean DEFAULT 'false'"+")";
public static String CREATE_TABLE_PROJECT="CREATE TABLE tblproject("+
        "id Text PRIMARY KEY,"+
        "prjct TEXT DEFAULT NULL,"+
        "descr TEXT DEFAULT NULL"+")";

//    public static String CREATE_TABLE_SYSTEMS="CREATE TABLE tblsystems("+
//            "code TEXT PRIMARY KEY,"+
//            "name TEXT DEFAULT NULL)";
    public static String CREATE_TABLE_TAGS="CREATE TABLE tbltags("+
            "id Text PRIMARY KEY,"+
            "tag TEXT DEFAULT NULL,"+
        "exist TEXT DEFAULT '0')";
    public static String CREATE_TABLE_AREA="CREATE TABLE tblareas("+
            "id Text PRIMARY KEY,"+
            "code TEXT DEFAULT NULL,"+
            "exist TEXT DEFAULT '0')";

    public static String CREATE_TABLE_SYSTEM="CREATE TABLE tblsystem("+
            "id TEXT PRIMARY KEY,"+
            "code TEXT DEFAULT NULL,"+
            "exist TEXT DEFAULT '0')";
    public static String CREATE_TABLE_SYSTEMSFI="CREATE TABLE tblsystem_sfi("+
            "id TEXT PRIMARY KEY,"+
            "code TEXT DEFAULT NULL,"+
            "exist TEXT DEFAULT '0')";
    public static String CREATE_TABLE_SYSTEMNORSOK="CREATE TABLE tblsystem_norsok("+
            "id TEXT PRIMARY KEY,"+
            "code TEXT DEFAULT NULL,"+
            "exist TEXT DEFAULT '0')";
    public static String CREATE_TABLE_DISCIPLINE="CREATE TABLE tbldiscipline("+
            "id TEXT PRIMARY KEY,"+
            "code TEXT DEFAULT NULL,"+
            "exist TEXT DEFAULT '0')";
    public static String CREATE_TABLE_DISCIPLINENORSOK="CREATE TABLE tbldiscipline_norsok("+
            "id TEXT PRIMARY KEY,"+
            "code TEXT DEFAULT NULL,"+
            "exist TEXT DEFAULT '0')";

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

    db.execSQL(CREATE_TABLE_PROJECT_DISCIPLINE);
    db.execSQL(CREATE_TABLE_PROJECT_SYSTEM);

    db.execSQL(CREATE_TABLE_DISCIPLINE);
    db.execSQL(CREATE_TABLE_DISCIPLINENORSOK);
    db.execSQL(CREATE_TABLE_AREA);
    db.execSQL(CREATE_TABLE_SYSTEM);
    db.execSQL(CREATE_TABLE_SYSTEMSFI);
    db.execSQL(CREATE_TABLE_SYSTEMNORSOK);

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
            db.execSQL(CREATE_TABLE_DISCIPLINENORSOK);

            db.execSQL(CREATE_TABLE_PROJECT_AREA);

            db.execSQL(CREATE_TABLE_DISCIPLINE);
            db.execSQL(CREATE_TABLE_AREA);
            db.execSQL(CREATE_TABLE_SYSTEM);
            db.execSQL(CREATE_TABLE_SYSTEMSFI);
            db.execSQL(CREATE_TABLE_SYSTEMNORSOK);

            db.execSQL(CREATE_TABLE_PROJECT_DISCIPLINE);
            db.execSQL(CREATE_TABLE_PROJECT_SYSTEM);
            Log.i("dbcreated","sss");


            //   Toast.makeText(co, "success", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Log.i("error",e.toString());}
    }


    public void insertSFISystem(){
        SQLiteDatabase dd=this.getReadableDatabase();
       String ss="INSERT OR REPLACE INTO tblsystem_sfi(id,code,exist) VALUES(100,'SPECIFICATION, ESTIMATING, DRAWINGS, INSTRUCTION, COURSES','1'),(101,'CONTRACT/SPECIFIC. WORK, GENERAL DESIGN, MODEL TESTING','1'),(102,'DRAWING ORDERING ETC. WHIT REGARD TO HULL (SFI GROUP 2)','1'),(103,'DRAWING ORDERING ETC WITH REGARD TO EQUIPMENT (SFI GROUP 3)','1'),(104,'DRAWING ORDERING ETC. WITH REGARD TO SSP EQUIPMENT (SFI GROUP 4)','1'),(105,'DRAWING ORDERING, ETC. WITH REGARD TO EQUIPMENT FOR CREW (SFI GROUP 5)','1'),(106,'DRAWING ORDERING WITH REGARD TO MACH. COMPONENTS, MACH/SHIP SYSTEMS (SFI GROUP 6, 7 & 8)','1'),(107,'ESTIMATING, DRAWING & OFFERS W.R.T. CHANGE ORDERS','1'),(109,'MAINTENANCE SYSTEMS, INSTRUCTION MATERIAL & OPERATION MANUALS','1'),(110,'INSURANCE, FEES, CERTIFICATES, REPRESENTATION','1'),(111,'GENERAL INSURANCE','1'),(112,'CLASSIFICATION & STATUTORY FEES & CERTIFICATES','1'),(113,'BUILDING ADVANCE INTEREST, DISCOUNT, FINANCE COSTS','1'),(117,'REPRESENTATION & TRAVELS','1'),(119,'REALLOCATION','1'),(120,'QUALITY INSURANCE, GENERAL WORK, MODELS','1'),(125,'TRANSPORTATION','1'),(130,'PROVISIONAL RIGGING','1'),(140,'WORK ON WAYS, LAUNCHING, DOCKING','1'),(147,'MARINE OPERATIONS','1'),(150,'QUALITY CONTROL, MEASUREMENTS, TESTS, TRIALS','1'),(154,'SEA TRIAL','1'),(156,'COMMISSIONING','1'),(160,'GUARANTEE/MENDING WORK','1'),(170,'YARD OUTFITTING AND INSTALLATION','1'),(180,'3D-MODELLING','1'),(190,'CONSUMPTION ARTICLES','1'),(200,'HULL MATERIALS, GENERAL HULL WORK','1'),(220,'ENGINE AREA / CENTRAL SHAFT','1'),(230,'CARGO AREA','1'),(250,'DECK HOUSES AND SUPERSTRUCTURE','1'),(251,'DECK HOUSES','1'),(252,'MAST, PUMP, FAN AND WINCH HOUSES, COMPANION WAYS','1'),(256,'ACCOMMODATION','1'),(260,'HULL OUTFITTING','1'),(261,'HULL AND HOUSE MARKING','1'),(262,'BOTTOM PLUGS, SEA CHESTS, MANHOLES, BILGE WELLS','1'),(267,'GUTTER BARS, BULWARK, BREAKWATERS','1'),(270,'MATERIAL PROTECTION - EXTERNAL','1'),(271,'COATING','1'),(278,'EXTERNAL CATHODIC PROTECTION','1'),(280,'MATERIAL PROTECTION - INTERNAL','1'),(288,'INTERNAL CATHODIC PROTECTION','1'),(290,'MISCELLANEOUS HULL WORK','1'),(330,'DECK CRANES FOR CARGO','1'),(331,'ROTATING CRANES WITH CRANE PILLARS','1'),(334,'OTHER CRANES WITH EQUIPMENT FOR CARGO','1'),(350,'LOADING/DISCHARGING SYSTEMS FOR LIQUID CARGO','1'),(351,'LOADING/DISCHARGING PUMPS','1'),(352,'LOADING/DISCHARGING SYSTEMS ON MAIN DECK','1'),(356,'SEPARATE STRIPPING SYSTEMS','1'),(360,'FREEZIN, REFRIGERATING AND HEATING SYSTEMS FOR CARGO','1'),(370,'GAS/VENTILATION SYSTEMS FOR CARGO HOLDS/TANKS','1'),(372,'CLOSED VENTILATION/RETURN VAPOUR SYSTEMS FOR CARGO HOLDS','1'),(374,'VENTILATION/GAS FREEING SYSTEMS FOR TANKS WITH EQUIPMENT','1'),(376,'INERT GAS SYSTEMS','1'),(380,'AXILIARY SYSTEMS & EQUIPMENT FOR CARGO (INCLUDING TANK CLEANING SYSTEMS)','1'),(381,'SOUNDING AND OPERATING EQUIPMENT FOR CARGO SYSTEMS','1'),(382,'TANK CLEANING SYSTEMS AND EQUIPMENT','1'),(400,'MANOUVERING MACHINERY & EQUIPMENT','1'),(405,'STABILIZERS','1'),(410,'NAVIGATION & SEARCHING EQUIPMENT','1'),(411,'RADAR PLANTS','1'),(412,'GPS, DECCA, LORAN, OMEGA, RADIO, DIRECTION FINDER-EQUIPMENT','1'),(413,'GYRO PLANTS, AUTOPILOTS, COMPASSES','1'),(417,'NAUTICAL UTILITY EQUIPMENT, CLOCKWORKS, WEATHER FAKSIMILE','1'),(420,'COMMUNICATION EQUIPMENT','1'),(421,'RADIO PLANT, GMDSS','1'),(422,'LIFEBOAT RADION TRANSMITTERS, EPIRBS','1'),(423,'DATA TRANSMISSION PLANTS, COMMUNICATION','1'),(424,'VHF/UHF TELEPHONES','1'),(425,'CALLING/COMMAND/CREW CALL TELEPHONE PLANT, WALKIE-TALKIES','1'),(427,'LIGHT EQUIPMENT, LANTERNS, TYPHONS','1'),(430,'ANCHORING MOORING & TOWING EQUIPMENT','1'),(431,'ANCHORS WITH CHAINS AND EQUIPMENT','1'),(432,'WINDLASSES WUTH CHAIN STOPPERS, ROLLERS','1'),(435,'FIXED MOORING EQUIPMENT','1'),(437,'TOWING EQUIPMENT','1'),(440,'REPAIR/MAINTENANCE/CLEANING EQUIPMENT WORKSHOP/STORE OUTFITTING, NAME PLATES','1'),(442,'TOOLS/EQUIPMENT FOR ENGINEERS, ELECTRICIANS, BOATSWAINS, CARPENTERS','1'),(445,'GARBAGE DISPOSAL PLANTS, INCINERATORS','1'),(446,'OUTFITTING IN STORE ROOMS AND WORKSHOPS','1'),(447,'CLAMPS/FOUNDATIONSFOR SPARE PARTS','1'),(448,'NAME PLATES/MARKING ON MACHINERY, EQUIPMENT, PIPES, CABLES','1'),(450,'LIFTING & TRANSPORT EQUIPMENT FOR MACHINERY COMPONENTS','1'),(451,'ENGINE ROOM LIFTS','1'),(452,'TRAVELLING CRANES AND LIFTING GEAR IN ENGINE ROOM','1'),(480,'SPECIAL EQUIPMENT','1'), (484,'LABORATORY EQUIPMENT','1'),(500,'LIFESAVING, PROTECTING & MEDICAL EQUIPMENT','1'),(501,'LIFE BOATS WITH EQUIPMENT','1'),(502,'LIFERAFTS WITH EQUIPMENT','1'),(503,'LIFESAVING, SAFETY AND EMERGENCY EQUIPMENT','1'),(504,'MEDICAL, FIRST AID & DENTAL EQUIPMENT, MEDICINES','1'),(505,'LOOSE FIREFIGHTING APPARATUS AND EQUIPMENT, FIREMEN OUTFITTING','1'),(510,'INSULATION, PANLES, BULKHEADS, DOORS, WINDOWS, SIDE CUTTLES, SKYLIGHTS','1'),(514,'EXTERNAL DOORS','1'),(515,'WINDOWS','1'),(517,'INSULATION','1'),(520,'INTERNAL DECK COVERING, LADDERS, STEPS, RAILING','1'),(521,'DECK COVERING AND FLOOR FINISH','1'),(524,'STAIRS AND LADDERS IN ACCOMMODATION','1'),(530,'EXTERNAL DECK COVERING, LADDERS, STEPS, GANGWAYS','1'),(540,'FURNITURE, INVENTORY, ENTERTAINMENT EQUIPMENT','1'),(541,'FURNITURE EQUIPMENT AND FABRICS','1'),(546,'HOBBY, SPORTS AND ENTERTAINMENT EQUIPMENT','1'),(550,'GALLEY/PANTRY EQUIPMENT, PROVISION PLANTS, LAUNDRY/IRONING EQUIPMENT','1'),(551,'GALLEY MACHINERY','1'),(554,'FREEZING/REFRIGERATING SYSTEMS','1'),(560,'TRANSPORT EQUIPMENT FOR CREW, PASSENGERS & PROVISIONS','1'),(561,'PERSONNEL LIFT','1'),(566,'HELICOPTER PLATFORMS WITH EQUIPMENT','1'),(570,'VENTILATION, AIR-CONDITIONING & HEATING SYSTEMS','1'),(571,'VENTILATION/AIR CONDITIONING SYSTEMS FOR ACCOMMODATION','1'),(572,'VENTILATION/AIR CONDITIONING SYSTEMS FOR PROVISION','1'),(573,'VENTILATION/AIR CONDITIONING SYSTEMS FOR CONTROL ROOMS','1'),(574,'VENTILATION/AIR CONDITIONING SYSTEMS FOR BOILER/ENGINE ROOMS','1'),(575,'VENTILATION/AIR CONDITIONING SYSTEMS FOR PUMP ROOMS','1'),(580,'SANITARY SYSTEM WITH DISCHARGES, ACCOMMODATION DRAWIN SYSTEM','1'),(581,'SANITARY SUPPLY SYSTEMS','1'),(582,'SANITARY DISCHARGE SYSTEMS, ACCOMMODATION DRAINAGE SYSTEMS','1'),(583,'BATHTUBS, BIDETS, SHOWER CABINETS, WC, WASHBASINS','1'),(640,'BOILERS, STEAM & GAS GENERATORS','1'),(650,'MOTOR AGGREGATES FOR MAIN ELECTRIC POWER PRODUCTION','1'),(651,'MOTOR AGGREGATES FOR MAIN ELECTRIC POWER PRODUCTION','1'),(660,'OTHER AGGREGATES & GENERATORS FOR MAIN & EMERGENCY ELECTRIC POWER PRODUCTION','1'),(663,'GAS TURBO AGGREGATES','1'),(665,'EMERGENCY DIESEL GENERATOR SET','1'),(700,'FUEL SYSTEMS','1'),(701,'FUEL OIL TRANSFER AND DRAIN SYSTEMS','1'),(702,'FUEL OIL PURIFICATION PLANTS','1'),(703,'FUEL OIL SUPPLY SYSTEMS','1'),(720,'COOLING SYSTEM','1'),(721,'SEA WATER COOLING SYSTEMS','1'),(722,'MAIN ENGINE FW COOLING SYSTEMS','1'),(730,'COMPRESSED AIR SYSTEMS','1'),(731,'STARTING AIR SYSTEMS','1'),(732,'GENERAL PURPOSE AIR SYSTEMS FOR ENGINE ROOM','1'),(733,'GENERAL PURPOSE AIR SYSTEMS FOR DECK','1'),(734,'INSTRUMENT AIR SUPPLY SYSTEMS','1'),(740,'EXHAUST SYSTEMS & AIR INTAKES','1'),(750,'STEAM, CONDENSATE & FEED WATER SYSTEMS','1'),(756,'PRIMARY FEED WATER SYSTEMS','1'),(760,'FRESH AND TECHNICAL WATER SYSTEMS','1'),(761,'FRESH WATER PRODUCTION SYSTEM','1'),(790,'AUTOMATION SYSTEM FOR MACHINERY','1'),(791,'MONOUVRE CONSOLES, MAIN CONSOLES','1'),(792,'COMMON AUTOMATION EQUIPMENT, ENGINE ROOM ALARM SYSTEMS','1'),(800,'BALLAST & BILGE SYSTEMS, GUTTER PIPES OUTSIDE ACCOMMODATION','1'),(801,'BALLAST SYSTEMS','1'),(803,'BILGE SYSTEMS','1'),(804,'DRAIN SYSTEM','1'),(810,'FIRE & LIFEBOAT ALARM, FIRE FIGHTING & WASH DOWN SYSTEM','1'),(811,'FIRE DETECTION, FIRE& LIFEBOAT ALARM SYSTEMS','1'),(812,'EMERGENCY SHUTDOWN SYSTEM','1'),(813,'FIRE/WASH DOWN SYSTEM, EMERGENCY FIRE PUMPS, SPRINKLER SYSTEM','1'),(816,'FIREFIGHTING SYSTEMS w/FOAM','1'),(819,'NO PRESSURISED DELUGE SYSTEM OR FIRE FIGHTING WITH OTHER MEANS','1'),(820,'AIR & SOUNDING SYSTEMS','1'),(830,'HYDRAULIC SYSTEM FOR VALVE OPERATION','1'),(831,'SPCIAL COMMON HYDRAULIC OIL SYSTEMS','1'),(850,'COMMON ELECTRIC & ELECTRONIC SYSTEMS','1'),(860,'ELECTRIC POWER SUPPLY','1'),(865,'TRANSFORMERS','1'),(870,'COMMON ELECTRIC DISTRIBUTION SYSTEMS','1'),(871,'MAIN SWITCHBOARDS','1'),(872,'EMERGENCY SWITCHBOARDS','1'),(873,'GROUP STARTERS','1'),(875,'DISTRIBUTION PANELS AND BOARDS','1'),(880,'ELECTRIC CONSUMER SYSTEMS','1'),(891,'ELECTRIC LIGHTING SYSTEMS FOR ENGINE AND BOILER ROOM','1')";
        dd.execSQL(ss);
    }
    public void insertTemp(){
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select * from tbltags";

        Cursor cursor=dd.rawQuery(dde,null );

        if(cursor.getCount()>0)
            return;
String ss="INSERT OR REPLACE INTO tbltags(id,tag,exist) VALUES(1,'10BA001a','1'),(2,'10BA100','1'),(3,'10L0001a','1'),(4,'10L1000','1')";


        // Cursor cursor=dd.rawQuery(ss,null );
     dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblareas(id,code,exist) VALUES(1,'B123','1'),(2,'B145','1'),(3,'B156','1'),(4,'B789','1')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblsystem_norsok(id,code,exist) VALUES('10','Drilling','1'),('11','Driling process','1'),('12','Drilling well control','1'),('13','Riser and well topside','1'),('14','Drilling control and monitoring','1'),('15','Drilling drain','1'),('18','Well and riser subsea','1'),('19','subsea installation-maintains and work over','1'),('20','Separation and Stabilisation','1'),('21','Crude Handling and Metering','1'),('23','Gas Compression and Re-Injection','1'),('24','Gas Treatment','1'),('25','Gas Conditioning','1'),('27','Gas Export and Metering','1'),('28','Gas Sweetening','1'),('29','Water Injection','1'),('30','Oil Export Line','1'),('31','Condensate Export Line','1'),('32','Gas Export Pipeline','1'),('33','Oil Storage','1'),('34','Injection Water Pipeline','1'),('35','Chemical Injection Pipeline','1'),('36','Wellstream Pipeline','1'),('37','Gas Injection Pipeline','1'),('38','Glycol/Methanol Regeneration','1'),('39','Loading','1'),('40','Cooling Medium','1'),('41','Heating Medium','1'),('42','Chemical Injection','1'),('43','Flare','1'),('44','Oily Water','1'),('45','Fuel Gas','1'),('46','Methanol Injection','1'),('47','Chlorination','1'),('50','Sea Water','1'),('52','Ballast Water','1'),('53','Fresh Water','1'),('55','Steam','1'),('56','Open Drain','1'),('57','Closed Drain','1'),('58','Thruster and Propulsion','1'),('61','Jet Fuel','1'),('62','Diesel Oil','1'),('63','Compressed Air','1'),('64','Inert Gas','1'),('65','Hydraulic Power','1'),('66','Sewage Treatment','1'),('69','Lubrication Oil','1'),('70','Fire and Gas Detection','1'),('71','Fire Water','1'),('72','Fire Fighting','1'),('73','Material Handling','1'),('75','Passive Fire Protection','1'),('76','Emergency Preparedness','1'),('77','Environment Protection','1'),('79','Emergency shutdown and blow down','1'),('80','Main Power(u=or > 6.6 KV)','1'),('81','Main Power(1.0 KV< u < 6.6 KV)','1'),('82','Main Power(u=or<1.0 KV)','1'),('83','Emergency power(u=or>1.0 KV)','1'),('84','Emergency power(u<1.0 KV)','1'),('85','Battery and no-break','1'),('86','Telecommunication','1'),('87','Control','1'),('88','Earthing and Lightning','1'),('90','Topside structure','1'),('91','Sub structure','1'),('92','Temporary','1'),('93','Architectural incl. Workshop and storage','1'),('94','Mooring and Positioning','1'),('95','Onshore/Civil','1'),('96','Subsea structure and template','1'),('97','HVAC','1'),('98','Corrosion Protection','1'),('99','Area Completion','1')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tbldiscipline_norsok(id,code,exist) VALUES('A','Administration','1'),('B','Procurement','1'),('C','Civil/architect','1'),('D','Drilling','1'),('E','Electrical','1'),('F','Project control/cost/economy','1'),('G','Geology','1'),('H','HVAC','1'),('I','Instrumentation/metering','1'),('J','Marine operation','1'),('K','Inspection','1'),('L','Piping/layout','1'),('M','Material Technology','1'),('N','Structural','1'),('O','Operation','1'),('P','Process','1'),('Q','Quality Management','1'),('R','Mechanical','1'),('S','Health, Safety and Environment(HSE)','1'),('T','Telecommunication','1'),('U','Subsea','1'),('W','Weight Control','1'),('X','Reservoir','1'),('Y','Pipeline','1'),('Z','Multidiscipline','1')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);

ss="INSERT OR REPLACE INTO tblproject(id,'prjct','descr') VALUES(1,'prjct1','abcde'),(2,'prjct2','abcde2'),(3,'prjct3','abcd3'),('fpso','FPSO BOX','FPSO Box Project')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblprojecttag(id,prjctid,tagid) VALUES(1,'1','1'),(2,'2','2'),(3,'3','3'),(4,'4','4'),(5,'1','2'),(6,'1','3')";


        //         "id TEXT PRIMARY KEY,"+
        //        "name TEXT DEFAULT NULL,"+
        //        "descr TEXT DEFAULT NULL,"+
        //        "prjctid TEXT DEFAULT NULL,"+
        //        "cdate TEXT DEFAULT NULL,"+
        //        "udate TEXT DEFAULT NULL"+")";
      //  dd.execSQL(ss);
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

        ss="INSERT OR REPLACE INTO tblreportdiscipline(id,rid,disciplineid) VALUES(1,'2','A')";


        //         "id TEXT PRIMARY KEY,"+
        //        "name TEXT DEFAULT NULL,"+
        //        "descr TEXT DEFAULT NULL,"+
        //        "prjctid TEXT DEFAULT NULL,"+
        //        "cdate TEXT DEFAULT NULL,"+
        //        "udate TEXT DEFAULT NULL"+")";
      //  dd.execSQL(ss);


        ss="INSERT OR REPLACE INTO tblreportsystem(id,rid,systemid) VALUES(1,'2','10')";


        //         "id TEXT PRIMARY KEY,"+
        //        "name TEXT DEFAULT NULL,"+
        //        "descr TEXT DEFAULT NULL,"+
        //        "prjctid TEXT DEFAULT NULL,"+
        //        "cdate TEXT DEFAULT NULL,"+
        //        "udate TEXT DEFAULT NULL"+")";
      //  dd.execSQL(ss);

        ss="INSERT OR REPLACE INTO tblreport(id,name,descr,prjctid,cdate,udate) VALUES(1,'Report1','testreport',null,'2018/12/12','2018/12/12'),(2,'Report2','testreport2','1','2018/12/12','2018/12/12'),(3,'Report3','testreport3','2','2018/12/12','2018/12/12')";


        // Cursor cursor=dd.rawQuery(ss,null );
        dd.execSQL(ss);
        insertSFISystem();

    }




    public ArrayList<DataReport> getReports(String id) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;
if(id==null)
        dde="select r.id,r.name,r.descr,r.prjctid,r.cdate,r.udate,(select count(*) from tblreportdata where rid=r.id) as files,p.prjct,p.descr,r.prepared,r.checked,r.approved,r.summary  from tblreport as r left join tblproject as p on r.prjctid=p.id";
else
    dde="select r.id,r.name,r.descr,r.prjctid,r.cdate,r.udate,(select count(*) from tblreportdata where rid=r.id) as files,p.prjct,p.descr,r.prepared,r.checked,r.approved,r.summary  from tblreport as r left join tblproject as p on r.prjctid=p.id where='"+id+"'";
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
    public ArrayList<PrjctData> getPrjct(String id) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select * from tblproject where id='"+id+"'";

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
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        dde="select count(tagid) as cont from tblprojecttag";


        Cursor cursor=dd.rawQuery(dde,null );
        cursor.moveToFirst();
        if(cursor.getInt(0)==0)
            return dta;
        dde="select p.id,p.prjctid,p.tagid,t.tag,(select count(*) from tblreporttag where tagid=p.tagid and rid='"+reportid+"') as pp from tblprojecttag as p join tbltags as t on t.id=p.tagid where p.prjctid='"+prjctid+"'";

       cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;

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
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        dde="select count(areaid) as cont from tblprojectarea";


        Cursor cursor=dd.rawQuery(dde,null );
        cursor.moveToFirst();
        if(cursor.getInt(0)==0)
            return dta;
        cursor.close();
        dde="select p.id,p.prjctid,p.areaid,t.code,(select count(*) from tblreportarea where areaid=p.areaid and rid='"+reportid+"') as pp,t.exist from tblprojectarea as p join tblareas as t on t.id=p.areaid where p.prjctid='"+prjctid+"'";

        cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;

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
                    dt.setExist(cursor.getString(5));
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
    public ArrayList<DataTag> getPrjctsDiscipline(String prjctid,String reportid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        dde="select count(disciplineid) as cont from tblprojectdiscipline";


        Cursor cursor=dd.rawQuery(dde,null );
        cursor.moveToFirst();
        if(cursor.getInt(0)==0)
            return dta;
        dde="select p.id,p.prjctid,p.disciplineid,t.code,(select count(*) from tblreportdiscipline where disciplineid=p.disciplineid and rid='"+reportid+"') as pp,t.exist from tblprojectdiscipline as p join tbldiscipline as t on t.id=p.disciplineid where p.prjctid='"+prjctid+"'";

        cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;

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
                    dt.setExist(cursor.getString(5));
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

    public ArrayList<DataTag> getPrjctsSystem(String prjctid,String reportid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        dde="select count(systemid) as cont from tblprojectsystem";


        Cursor cursor=dd.rawQuery(dde,null );
        cursor.moveToFirst();
        if(cursor.getInt(0)==0)
            return dta;
        dde="select p.id,p.prjctid,p.systemid,t.code,(select count(*) from tblreportsystem where systemid=p.systemid and rid='"+reportid+"') as pp,t.exist from tblprojectsystem as p join tblsystem as t on t.id=p.systemid where p.prjctid='"+prjctid+"'";

       cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;

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
                    dt.setExist(cursor.getString(5));
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



                    if(cursor.getInt(2)>0) {
                        dt.setTagid(cursor.getString(0));
                        dt.setTag(cursor.getString(1));
                        if (cursor.getInt(2) > 0)
                            dt.setSelected(true);
                        else
                            dt.setSelected(false);
                        dta.add(dt);
                    }

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

//    public ArrayList<DataTag> getSystems(String id) {
//        SQLiteDatabase dd=this.getReadableDatabase();
//        String dde;
//if(id.equals(""))
//        dde="select id,code from tblsystem";
//else
//    dde="select id,code from tblsystem where exist='"+id+"'";
//        Cursor cursor=dd.rawQuery(dde,null );
//
//        Cursor cursor1,cursor2;
//        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
//        DataTag dt;
//        ArrayList<DataTag> dta=new ArrayList<DataTag>();
//        if (cursor.moveToFirst()) {
//            do {
//                try {
////                    "id TEXT PRIMARY KEY,"+
////                            "prjctid TEXT DEFAULT NULL,"+
////                            "tagid TEXT DEFAULT NULL"+")";
//
//                    dt = new DataTag();
//
//
//
//                    dt.setTagid(cursor.getString(0));
//                    dt.setTag(cursor.getString(1));
////                    if(cursor.getInt(2)>0)
////                        dt.setSelected(true);
////                    else
////                        dt.setSelected(false);
//                    dta.add(dt);
//
//
//                } catch (Exception ee) {
//                    ee.printStackTrace();
//                    Log.e("er",ee.toString()+"");
//                }
//
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        dd.close();
//        return dta;
//
//    }


    public ArrayList<DataTag> getSystems(String pid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        dde="select count(systemid) as cont from tblprojectsystem";


        Cursor cursor=dd.rawQuery(dde,null );
        cursor.moveToFirst();
        if(cursor.getInt(0)==0)
            dde="select * from tblsystem";
        else
        dde="select * from tblsystem where id NOT IN(select systemid from tblprojectsystem where prjctid='"+pid+"')";

        cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;

        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();

                    dt.setTagid(cursor.getString(0));


                    dt.setTag(cursor.getString(1));
                    dt.setExist(cursor.getString(2));
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

//    public ArrayList<DataTag> getDiscipline(String n) {
//        SQLiteDatabase dd=this.getReadableDatabase();
//        String dde;
//if(n.equals(""))
//        dde="select id,code from tbldiscipline";
//else
//    dde="select id,code from tbldiscipline where exist='"+n+"'";
//        Cursor cursor=dd.rawQuery(dde,null );
//
//        Cursor cursor1,cursor2;
//        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
//        DataTag dt;
//        ArrayList<DataTag> dta=new ArrayList<DataTag>();
//        if (cursor.moveToFirst()) {
//            do {
//                try {
////                    "id TEXT PRIMARY KEY,"+
////                            "prjctid TEXT DEFAULT NULL,"+
////                            "tagid TEXT DEFAULT NULL"+")";
//
//                    dt = new DataTag();
//
//
//
//                    dt.setTagid(cursor.getString(0));
//                    dt.setTag(cursor.getString(1));
////                    if(cursor.getInt(2)>0)
////                        dt.setSelected(true);
////                    else
////                        dt.setSelected(false);
//                    dta.add(dt);
//
//
//                } catch (Exception ee) {
//                    ee.printStackTrace();
//                    Log.e("er",ee.toString()+"");
//                }
//
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        dd.close();
//        return dta;
//
//    }

    public ArrayList<DataTag> getDiscipline(String pid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        dde="select count(disciplineid) as cont from tblprojectdiscipline";


        Cursor cursor=dd.rawQuery(dde,null );
        cursor.moveToFirst();
        if(cursor.getInt(0)==0)
            dde="select * from tbldiscipline";
        else
        dde="select * from tbldiscipline where id NOT IN(select disciplineid from tblprojectdiscipline where prjctid='"+pid+"')";
      cursor=dd.rawQuery(dde,null );
        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;

        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();

                    dt.setTagid(cursor.getString(0));


                    dt.setTag(cursor.getString(1));
                    dt.setExist(cursor.getString(2));
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


                    if(cursor.getInt(2)>0) {
                        dt.setTagid(cursor.getString(0));
                        dt.setTag(cursor.getString(1));
                        if (cursor.getInt(2) > 0)
                            dt.setSelected(true);
                        else
                            dt.setSelected(false);
                        dta.add(dt);
                    }

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

    public ArrayList<DataTag> getTags(String pid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();
        dde="select count(tagid) as cont from tblprojecttag";


        Cursor cursor=dd.rawQuery(dde,null );
        cursor.moveToFirst();
        if(cursor.getInt(0)==0)
            dde="select * from tbltags";
        else
        dde="select * from tbltags where id NOT IN(select tagid from tblprojecttag where prjctid='"+pid+"' )";

         cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;

        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();

                    dt.setTagid(cursor.getString(0));


                    dt.setTag(cursor.getString(1));
                    dt.setExist(cursor.getString(2));
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

    public boolean addTagN(String prjct,String tag,boolean isnew) {
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

    public boolean addTag(String prjct, String tag, boolean isnew, String s) {


        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=null;
        if(isnew) {
            cursor = dd.rawQuery("select id from tbltags where id='" + s + "'", null);
            if (cursor.getCount() > 0){
                cursor.close();
                return false;

            }

            cursor.close();
        }else {
            cursor = dd.rawQuery("select id from tblprojecttag where prjctid='" + prjct + "' and tagid='"+s+"'", null);
            if (cursor.getCount() > 0){
                cursor.close();
                return false;

            }

            cursor.close();

        }



        boolean	status=false;
//        cursor=dd.rawQuery("select max(id) from tblprojecttag",null );
//        int prjctcount=0;
//        if(cursor.moveToFirst())
//            prjctcount=cursor.getInt(0);
        String insertCategoryQuery1 = "INSERT INTO tblprojecttag(" +
                "prjctid,"+
                "tagid"+")"+
                " VALUES (?,?)";
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

                insertCategory.bindString(1,s);
                insertCategory.bindString(2,tag);


if(prjct!=null){

    insertCategory1.bindString(1,prjct);

    insertCategory1.bindString(2,s);
    insertCategory1.execute();
}

                //insertCategory.bindString(9,actor.getString("published"));

                Log.i("error1","111");
                if(isnew)
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
//   cursor=dd.rawQuery("select max(id) from tblareas",null );
//        DataTag dt=new DataTag();
//        dt.setTag(tag);
//        if(cursor.moveToFirst())
//        dt.setTagid(cursor.getString(0));
        cursor.close();
        dd.close();
        return status;
    }
    public boolean insertReport(DataReport dataReport) {
        SQLiteDatabase dd = this.getReadableDatabase();
        String systemarea;

        Cursor cursor = dd.rawQuery("select rid from tblreport where id='" + dataReport.getId() + "'", null);
     int transactioncount=0;
        boolean replace = false;
        if (cursor.getCount() > 0){
            replace = true;
            cursor.moveToFirst();
            transactioncount=cursor.getInt(0);
        }
        cursor.close();
        boolean status = false;
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
        SQLiteStatement insertCategory1;

        if (replace){
            String insertCategoryQuery1 = "INSERT or Replace INTO tblreport(" +
                    "rid," +
                    "id," +
                    "name," +
                    "descr," +
                    "prjctid," +
                    "cdate," +
                    "udate," +
                    "prepared," +
                    "checked," +
                    "approved," +
                    "summary" + ")" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        dd = this.getWritableDatabase();

     insertCategory1 = dd.compileStatement(insertCategoryQuery1);
    }
    else {

            String insertCategoryQuery1 = "INSERT or Replace INTO tblreport(" +

                    "id," +
                    "name," +
                    "descr," +
                    "prjctid," +
                    "cdate," +
                    "udate," +
                    "prepared," +
                    "checked," +
                    "approved," +
                    "summary" + ")" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?)";

            dd = this.getWritableDatabase();

            insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        }
        try {
            //JSONArray jsn=new JSONArray(data);





            try {
if(replace){

    insertCategory1.clearBindings();

    insertCategory1.bindLong(1,transactioncount);
    insertCategory1.bindString(2,dataReport.getId());

    insertCategory1.bindString(3,dataReport.getReportname());



    insertCategory1.bindString(4,dataReport.getDescrpotion());
    insertCategory1.bindString(5,dataReport.getPrjct());
    insertCategory1.bindString(6,dataReport.getCdate());
    insertCategory1.bindString(7,dataReport.getUdate());
    insertCategory1.bindString(8,dataReport.getPrepared());
    insertCategory1.bindString(9,dataReport.getChecked());
    insertCategory1.bindString(10,dataReport.getApproved());
    insertCategory1.bindString(11,dataReport.getSummary());

    Log.i("error1","111");


    insertCategory1.execute();
}else {


    insertCategory1.clearBindings();
    transactioncount++;
    insertCategory1.bindString(1,dataReport.getId());
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
}

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

        Cursor cursor=dd.rawQuery("select rid from tblreport",null );
        if(cursor.moveToLast())
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

    public boolean insertReportDiscipline(ArrayList<DataTag> prjcttags,String rid) {
        int transactioncount=0;
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        dd.execSQL("delete from tblreportdiscipline where rid='"+rid+"'");
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
                    insertCategory1.bindString(2, rid);


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
    public boolean insertReportSystem(ArrayList<DataTag> prjcttags,String rid) {
        int transactioncount=0;
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        dd.execSQL("delete from tblreportsystem where rid='"+rid+"'");

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
                    insertCategory1.bindString(2, rid);


                    insertCategory1.bindString(3, prjcttags.get(j).getTagid());


                    Log.i("error1", "111");


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
    public boolean insertReportData(ArrayList<DataPreview> dataPreviews) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
      //  dd.execSQL("delete from tblreporttag where rid='"+CreateReport.loaddata.getId()+"'");

        Cursor cursor=dd.rawQuery("select max(id) from tblreportdata",null );
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
                "sync,"+
                "selected"+")"+
                " VALUES (?,?,?,?,?,?,?)";

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
                    insertCategory1.bindString(7, String.valueOf(dataPreviews.get(j).isSelected()));
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

        dde="select * from tblreportdata where rid='"+rid+"' order by id desc";

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
                    dt.setSelected(Boolean.parseBoolean(cursor.getString(6)));
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

    public ArrayList<DataTag> getAreas(String pid) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;
        Cursor cursor;
        ArrayList<DataTag> dta=new ArrayList<DataTag>();



            dde="select count(areaid) as cont from tblprojectarea";


           cursor=dd.rawQuery(dde,null );
            cursor.moveToFirst();
            if(cursor.getInt(0)==0)
                dde="select * from tblareas";
            else
        dde="select * from tblareas where id NOT IN(select areaid from tblprojectarea where prjctid='"+pid+"')";

     cursor=dd.rawQuery(dde,null );

        Cursor cursor1,cursor2;
        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataTag dt;

        if (cursor.moveToFirst()) {
            do {
                try {
//                    "id TEXT PRIMARY KEY,"+
//                            "prjctid TEXT DEFAULT NULL,"+
//                            "tagid TEXT DEFAULT NULL"+")";

                    dt = new DataTag();

                    dt.setTagid(cursor.getString(0));


                    dt.setTag(cursor.getString(1));
                    dt.setExist(cursor.getString(2));
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
        }else {
            cursor = dd.rawQuery("select id from tblprojectarea where prjctid='" + prjct + "' and areaid='"+s+"'", null);
            if (cursor.getCount() > 0){
                cursor.close();
                return false;

            }

            cursor.close();

        }



        boolean	status=false;
//        cursor=dd.rawQuery("select max(id) from tblprojecttag",null );
//        int prjctcount=0;
//        if(cursor.moveToFirst())
//            prjctcount=cursor.getInt(0);
        String insertCategoryQuery1 = "INSERT INTO tblprojectarea(" +
                "prjctid,"+
                "areaid"+")"+
                " VALUES (?,?)";
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


if(prjct!=null){

    insertCategory1.bindString(1,prjct);

    insertCategory1.bindString(2,s);
    insertCategory1.execute();
}

                //insertCategory.bindString(9,actor.getString("published"));

                Log.i("error1","111");
                if(isnew)
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

        dde="update tblproject set prjct=?,descr=? where id=?";

        SQLiteStatement stmt = dd.compileStatement(dde);

        stmt.bindString(1,prjct);
        stmt.bindString(2, descr);
        stmt.bindString(3, id);
        stmt.execute();

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

        dde="update tblareas set code=? where id=?";
        SQLiteStatement stmt = dd.compileStatement(dde);

        stmt.bindString(1,s);
        stmt.bindString(2, tagid);

        stmt.execute();

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



    public boolean addToPrjct(String prjct, ArrayList<DataTag> systems,String table) {


        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=null;




        boolean	status=false;
//        cursor=dd.rawQuery("select max(id) from tblprojecttag",null );
//        int prjctcount=0;
//        if(cursor.moveToFirst())
//            prjctcount=cursor.getInt(0);
        String insertCategoryQuery1="";
        if(table.equals("system")){

       insertCategoryQuery1 = "INSERT INTO tblprojectsystem(" +
                    "prjctid,"+
                    "systemid"+")"+
                    " VALUES (?,?)";

        }
        else if(table.equals("tag")){

         insertCategoryQuery1 = "INSERT INTO tblprojecttag(" +
                    "prjctid,"+
                    "tagid"+")"+
                    " VALUES (?,?)";

        }
        else if(table.equals("discipline")){

            insertCategoryQuery1 = "INSERT INTO tblprojectdiscipline(" +
                    "prjctid,"+
                    "disciplineid"+")"+
                    " VALUES (?,?)";

        }
        else if(table.equals("area")){

            insertCategoryQuery1 = "INSERT INTO tblprojectarea(" +
                    "prjctid,"+
                    "areaid"+")"+
                    " VALUES (?,?)";

        }
        dd= this.getWritableDatabase();

        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);

            for (DataTag System:systems
                 ) {


                try {





                    insertCategory1.bindString(1,prjct);

                    insertCategory1.bindString(2,System.getTagid());
                    //insertCategory.bindString(9,actor.getString("published"));

                    Log.i("error1","111");

                    insertCategory1.execute();
                    status=true;

                }catch (android.database.sqlite.SQLiteConstraintException ee){
                    ee.printStackTrace();
                    Log.i("error",ee.toString()+"");
                }



            }





            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );





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

        dd.close();
        return status;
    }


    public boolean addSystem(String prjct, String tag, boolean isnew, String s) {


        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=null;
        if(isnew) {
            cursor = dd.rawQuery("select id from tblsystem where id='" + s + "'", null);
            if (cursor.getCount() > 0){
                cursor.close();
                return false;

            }

            cursor.close();
        }else {
            cursor = dd.rawQuery("select id from tblprojectsystem where prjctid='" + prjct + "' and systemid='"+s+"'", null);
            if (cursor.getCount() > 0){
                cursor.close();
                return false;

            }

            cursor.close();

        }



        boolean	status=false;
//        cursor=dd.rawQuery("select max(id) from tblprojecttag",null );
//        int prjctcount=0;
//        if(cursor.moveToFirst())
//            prjctcount=cursor.getInt(0);
        String insertCategoryQuery1 = "INSERT INTO tblprojectsystem(" +
                "prjctid,"+
                "systemid"+")"+
                " VALUES (?,?)";
        String insertCategoryQuery = "INSERT INTO tblsystem(" +
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


                if(prjct!=null){
                insertCategory1.bindString(1,prjct);

                insertCategory1.bindString(2,s);
                    insertCategory1.execute();

                }
                //insertCategory.bindString(9,actor.getString("published"));

                Log.i("error1","111");
                if(isnew)
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
//   cursor=dd.rawQuery("select max(id) from tblareas",null );
//        DataTag dt=new DataTag();
//        dt.setTag(tag);
//        if(cursor.moveToFirst())
//        dt.setTagid(cursor.getString(0));
        cursor.close();
        dd.close();
        return status;
    }

    public boolean addDiscipline(String prjct, String tag, boolean isnew, String s) {


        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=null;
        if(isnew) {
            cursor = dd.rawQuery("select id from tbldiscipline where id='" + s + "'", null);
            if (cursor.getCount() > 0){
                cursor.close();
                return false;

            }

            cursor.close();
        }else {
            cursor = dd.rawQuery("select id from tblprojectdiscipline where prjctid='" + prjct + "' and disciplineid='"+s+"'", null);
            if (cursor.getCount() > 0){
                cursor.close();
                return false;

            }

            cursor.close();

        }



        boolean	status=false;
//        cursor=dd.rawQuery("select max(id) from tblprojecttag",null );
//        int prjctcount=0;
//        if(cursor.moveToFirst())
//            prjctcount=cursor.getInt(0);
        String insertCategoryQuery1 = "INSERT INTO tblprojectdiscipline(" +
                "prjctid,"+
                "disciplineid"+")"+
                " VALUES (?,?)";
        String insertCategoryQuery = "INSERT INTO tbldiscipline(" +
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


if(prjct!=null){

    insertCategory1.bindString(1,prjct);

    insertCategory1.bindString(2,s);
    insertCategory1.execute();
}

                //insertCategory.bindString(9,actor.getString("published"));

                Log.i("error1","111");
                if(isnew)
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
//   cursor=dd.rawQuery("select max(id) from tblareas",null );
//        DataTag dt=new DataTag();
//        dt.setTag(tag);
//        if(cursor.moveToFirst())
//        dt.setTagid(cursor.getString(0));
        cursor.close();
        dd.close();
        return status;
    }
//    public boolean addSystem(String code,String name) {
//
//        SQLiteDatabase dd=this.getReadableDatabase();
//        String systemarea;
//        int transactioncount=1;
//        Cursor cursor=dd.rawQuery("select id from tblsystem where id='"+code+"'",null );
//        boolean	status=false;
//       if(cursor.getCount()>0)
//
//        {
//            cursor.close();
//            return status;
//        }
//
//
//
//
//        String insertCategoryQuery = "INSERT INTO tblsystem(" +
//                "id,"+
//                "code"+")"+
//                " VALUES (?,?)";
//        dd= this.getWritableDatabase();
//        SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);
//
//        try {
//            //JSONArray jsn=new JSONArray(data);
//
//
//
//
//
//            try {
//
//                insertCategory.clearBindings();
//
//                insertCategory.bindString(1,code);
//                insertCategory.bindString(2,name);
//
//
//
//                    insertCategory.execute();//Insert();
//
//                status=true;
//
//            }catch (android.database.sqlite.SQLiteConstraintException ee){
//                ee.printStackTrace();
//                Log.i("error",ee.toString()+"");
//            }
//
//            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";
//
//            // Cursor cursor=dd.rawQuery(ss,null );
//
//
//            cursor.close();
//
//
//        }catch(Exception e){
//
//            e.printStackTrace();
//        }
//
////Utility.addReminder(df,co);
////Utility.setAlaram1(co,df);
//
//        return status;
//    }
    public void deleteSystem(String id) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="delete from tblprojectsystem where systemid='"+id+"'";
        dd.execSQL(dde);
    }
    public void deleteDiscipline(String id) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String dde,pdf="";

        dde="delete from tblprojectdiscipline where disciplineid='"+id+"'";
        dd.execSQL(dde);
    }

    public boolean getReport(String id) {
       boolean status=false;
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        //  dd.execSQL("delete from tblreporttag where rid='"+CreateReport.loaddata.getId()+"'");
//        Cursor cursor=dd.rawQuery("select id from tblproject where prjct='"+prjct+"'",null );
//
//if(cursor.getCount()>0){
//    cursor.close();
//    return false;
//}

        Cursor  cursor=dd.rawQuery("select * from tblreport where id='"+id+"'",null );
//        int transactioncount=0;
//                if(cursor.moveToFirst()){
//                    transactioncount=cursor.getInt(0);
//
        if(cursor.getCount()>0)
       return false;
        else
            return true;
    }

    public void deleteReports(ArrayList<DataReport> dltreports) {

        boolean status=false;
        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        //  dd.execSQL("delete from tblreporttag where rid='"+CreateReport.loaddata.getId()+"'");
//        Cursor cursor=dd.rawQuery("select id from tblproject where prjct='"+prjct+"'",null );
//
//if(cursor.getCount()>0){
//    cursor.close();
//    return false;
//}
        for (DataReport ee:dltreports
             ) {
            dd.execSQL("delete from tblreportdiscipline where rid='"+ee.getId()+"'" );
            dd.execSQL("delete from tblreportdata where rid='"+ee.getId()+"'");
            dd.execSQL("delete from tblreportsystem where rid='"+ee.getId()+"'" );
            dd.execSQL("delete from tblreporttag where rid='"+ee.getId()+"'" );
            dd.execSQL("delete from tblreport where id='"+ee.getId()+"'" );

        }


//        int transactioncount=0;
//                if(cursor.moveToFirst()){
//                    transactioncount=cursor.getInt(0);
//

    }

    public ArrayList<DataTag> getTagsExiting(String prjctid,String ex) {
String reportid="";
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select p.id,p.prjctid,p.tagid,t.tag,(select count(*) from tblreporttag where tagid=p.tagid and rid='"+reportid+"') as pp from tblprojecttag as p join tbltags as t on t.id=p.tagid where p.prjctid='"+prjctid+"' and t.exist='"+ex+"'";

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



//    public void copyTagsPrjct(String id, String prjct) {
//
//        String reportid="";
//        SQLiteDatabase dd=this.getReadableDatabase();
//        String dde;
//
//        Cursor cursor=dd.rawQuery("select * from tbltags where id='"+prjct+"'",null );
//
//        //Cursor cursor=dd.rawQuery(dde,null );
//
//        Cursor cursor1,cursor2;
//        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
//        DataTag dt;
//        ArrayList<DataTag> dta=new ArrayList<DataTag>();
//        if (cursor.moveToFirst()) {
//            do {
//                try {
////                    "id TEXT PRIMARY KEY,"+
////                            "prjctid TEXT DEFAULT NULL,"+
////                            "tagid TEXT DEFAULT NULL"+")";
//
//                    dt = new DataTag();
//
//
//
//                    dt.setTagid(cursor.getString(0));
//                    dt.setTag(cursor.getString(1));
//
//                    dta.add(dt);
//
//
//                } catch (Exception ee) {
//                    ee.printStackTrace();
//                    Log.e("er",ee.toString()+"");
//                }
//
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        dd.close();
//        return dta;
//    }

    public void insertToPrjctTags(ArrayList<DataTag> prjctsTags, String prjct) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=null;





        boolean	status=false;
        cursor=dd.rawQuery("select max(id) from tblprojecttag",null );
        int prjctcount=0;
        if(cursor.moveToFirst())
            prjctcount=cursor.getInt(0);
        String insertCategoryQuery1 = "INSERT INTO tblprojecttag(" +
                "id,"+
                "prjctid,"+
                "tagid"+")"+
                " VALUES (?,?,?)";

        dd= this.getWritableDatabase();
       // SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);
        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);



            for (int i = 0; i <prjctsTags.size() ; i++) {
                try {

                    insertCategory1.clearBindings();


                    cursor=dd.rawQuery("select id from tblprojecttag where prjctid='"+prjct+"' and tagid='"+prjctsTags.get(i).getTagid()+"'",null );

if(cursor.getCount()==0){
                    prjctcount++;
                    insertCategory1.bindLong(1,prjctcount);
                    insertCategory1.bindString(2,prjct);

                    insertCategory1.bindString(3,prjctsTags.get(i).getTagid());
                    //insertCategory.bindString(9,actor.getString("published"));



                    insertCategory1.execute();}
                    status=true;

                }catch (android.database.sqlite.SQLiteConstraintException ee){
                    ee.printStackTrace();
                    Log.i("error",ee.toString()+"");
                }
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

    }


    public void insertToPrjctSystem(ArrayList<DataTag> prjctsAreas, String prjct) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=null;





        boolean	status=false;
        cursor=dd.rawQuery("select max(id) from tblprojectarea",null );
        int prjctcount=0;
        if(cursor.moveToFirst())
            prjctcount=cursor.getInt(0);
        String insertCategoryQuery1 = "INSERT INTO tblprojectsystem(" +
                "prjctid,"+
                "systemid"+")"+
                " VALUES (?,?)";

        dd= this.getWritableDatabase();
        // SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);
        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);



            for (int i = 0; i <prjctsAreas.size() ; i++) {
                try {

                    insertCategory1.clearBindings();


                    cursor=dd.rawQuery("select id from tblprojectsystem where prjctid='"+prjct+"' and systemid='"+prjctsAreas.get(i).getTagid()+"'",null );

                    if(cursor.getCount()==0) {

                        prjctcount++;
                        //insertCategory1.bindLong(1, prjctcount);
                        insertCategory1.bindString(2, prjct);

                        insertCategory1.bindString(3, prjctsAreas.get(i).getTagid());
                        //insertCategory.bindString(9,actor.getString("published"));


                        insertCategory1.execute();
                        status = true;
                    }
                }catch (android.database.sqlite.SQLiteConstraintException ee){
                    ee.printStackTrace();
                    Log.i("error",ee.toString()+"");
                }
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
    }


    public void insertToPrjctDiscipline(ArrayList<DataTag> prjctsAreas, String prjct) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=null;





        boolean	status=false;
        cursor=dd.rawQuery("select max(id) from tblprojectarea",null );
        int prjctcount=0;
        if(cursor.moveToFirst())
            prjctcount=cursor.getInt(0);
        String insertCategoryQuery1 = "INSERT INTO tblprojectdiscipline(" +
                "prjctid,"+
                "disciplineid"+")"+
                " VALUES (?,?)";

        dd= this.getWritableDatabase();
        // SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);
        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);



            for (int i = 0; i <prjctsAreas.size() ; i++) {
                try {

                    insertCategory1.clearBindings();


                    cursor=dd.rawQuery("select id from tblprojectdiscipline where prjctid='"+prjct+"' and disciplineid='"+prjctsAreas.get(i).getTagid()+"'",null );

                    if(cursor.getCount()==0) {

                        prjctcount++;
                        //insertCategory1.bindLong(1, prjctcount);
                        insertCategory1.bindString(2, prjct);

                        insertCategory1.bindString(3, prjctsAreas.get(i).getTagid());
                        //insertCategory.bindString(9,actor.getString("published"));


                        insertCategory1.execute();
                        status = true;
                    }
                }catch (android.database.sqlite.SQLiteConstraintException ee){
                    ee.printStackTrace();
                    Log.i("error",ee.toString()+"");
                }
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
    }
    public void insertToPrjctAreas(ArrayList<DataTag> prjctsAreas, String prjct) {

        SQLiteDatabase dd=this.getReadableDatabase();
        String systemarea;
        int transactioncount=1;
        Cursor cursor=null;





        boolean	status=false;
        cursor=dd.rawQuery("select max(id) from tblprojectarea",null );
        int prjctcount=0;
        if(cursor.moveToFirst())
            prjctcount=cursor.getInt(0);
        String insertCategoryQuery1 = "INSERT INTO tblprojectarea(" +

                "prjctid,"+
                "areaid"+")"+
                " VALUES (?,?)";

        dd= this.getWritableDatabase();
        // SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);
        SQLiteStatement insertCategory1 = dd.compileStatement(insertCategoryQuery1);
        try {
            //JSONArray jsn=new JSONArray(data);



            for (int i = 0; i <prjctsAreas.size() ; i++) {
                try {

                    insertCategory1.clearBindings();


                    cursor=dd.rawQuery("select id from tblprojectarea where prjctid='"+prjct+"' and areaid='"+prjctsAreas.get(i).getTagid()+"'",null );

                    if(cursor.getCount()==0) {

                        prjctcount++;
                        //insertCategory1.bindLong(1, prjctcount);
                        insertCategory1.bindString(2, prjct);

                        insertCategory1.bindString(3, prjctsAreas.get(i).getTagid());
                        //insertCategory.bindString(9,actor.getString("published"));


                        insertCategory1.execute();
                        status = true;
                    }
                }catch (android.database.sqlite.SQLiteConstraintException ee){
                    ee.printStackTrace();
                    Log.i("error",ee.toString()+"");
                }
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
    }
public void loadSystem(String  ss){
    SQLiteDatabase dd=this.getReadableDatabase();

    Cursor cursor=null;





    boolean	status=false;
    if(ss.equals("sfi"))
    cursor=dd.rawQuery("select * from tblsystem_sfi",null );
    else
        cursor=dd.rawQuery("select * from tblsystem_norsok",null );
    String insertCategoryQuery = "INSERT OR REPLACE INTO tblsystem(" +
            "id,"+
            "code,"+
            "exist"+")"+
            " VALUES (?,?,?)";
    dd= this.getWritableDatabase();
    SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);
if(cursor.moveToFirst())
    do {
        try {
            //JSONArray jsn=new JSONArray(data);


            try {

                insertCategory.clearBindings();

                insertCategory.bindString(1, cursor.getString(0));
                insertCategory.bindString(2, cursor.getString(1));
                insertCategory.bindString(3, ss);

                insertCategory.execute();//Insert();

                status = true;

            } catch (android.database.sqlite.SQLiteConstraintException ee) {
                ee.printStackTrace();
                Log.i("error", ee.toString() + "");
            }

            //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            // Cursor cursor=dd.rawQuery(ss,null );



        } catch (Exception e) {

            e.printStackTrace();
        }
    }while (cursor.moveToNext());
//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);
//   cursor=dd.rawQuery("select max(id) from tblareas",null );
//        DataTag dt=new DataTag();
//        dt.setTag(tag);
//        if(cursor.moveToFirst())
//        dt.setTagid(cursor.getString(0));
    cursor.close();
    dd.close();

}

    public boolean IsSystemLoaded(String ss) {
        SQLiteDatabase dd=this.getReadableDatabase();

        Cursor cursor=null;
        cursor=dd.rawQuery("select * from tblsystem where exist='"+ss+"'",null );
       if(cursor.getCount()>0)
           return true;
       else
           return false;
    }

    public void unloadSystem(String ss) {

        SQLiteDatabase dd=this.getReadableDatabase();


    dd.execSQL("delete from tblsystem where exist='"+ss+"'" );
       // dd.execSQL("delete from tblprojectsystem where exist='"+ss+"'" );
    }

    public void loadDiscipline(String  ss){
        SQLiteDatabase dd=this.getReadableDatabase();

        Cursor cursor=null;





        boolean	status=false;
//
//            cursor=dd.rawQuery("select * from tbldiscpline_sfi",null );
//        else
        if(ss.equals("norsok"))
            cursor=dd.rawQuery("select * from tbldiscipline_norsok",null );
        String insertCategoryQuery = "INSERT OR REPLACE INTO tbldiscipline(" +
                "id,"+
                "code,"+
                "exist"+")"+
                " VALUES (?,?,?)";
        dd= this.getWritableDatabase();
        SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);
        if(cursor.moveToFirst())
            do {
                try {
                    //JSONArray jsn=new JSONArray(data);


                    try {

                        insertCategory.clearBindings();

                        insertCategory.bindString(1, cursor.getString(0));
                        insertCategory.bindString(2, cursor.getString(1));
                        insertCategory.bindString(3, ss);

                        insertCategory.execute();//Insert();

                        status = true;

                    } catch (android.database.sqlite.SQLiteConstraintException ee) {
                        ee.printStackTrace();
                        Log.i("error", ee.toString() + "");
                    }

                    //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

                    // Cursor cursor=dd.rawQuery(ss,null );



                } catch (Exception e) {

                    e.printStackTrace();
                }
            }while (cursor.moveToNext());
//Utility.addReminder(df,co);
//Utility.setAlaram1(co,df);
//   cursor=dd.rawQuery("select max(id) from tblareas",null );
//        DataTag dt=new DataTag();
//        dt.setTag(tag);
//        if(cursor.moveToFirst())
//        dt.setTagid(cursor.getString(0));
        cursor.close();
        dd.close();

    }

    public boolean IsDisciplineLoaded(String ss) {
        SQLiteDatabase dd=this.getReadableDatabase();

        Cursor cursor=null;
        cursor=dd.rawQuery("select * from tbldiscipline where exist='"+ss+"'",null );
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void unloadDiscipline(String ss) {

        SQLiteDatabase dd=this.getReadableDatabase();


        dd.execSQL("delete from tbldiscipline where exist='"+ss+"'" );

    }


    public ArrayList<DataTag> getPrjctTags(String id) {
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;

        dde="select * from tbltags where id IN(select tagid from tblprojecttag where prjctid='"+id+"' )";

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
dt.setExist(cursor.getString(2));
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
}