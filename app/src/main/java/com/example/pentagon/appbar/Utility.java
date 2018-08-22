package com.example.pentagon.appbar;

import com.itextpdf.text.Chapter;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.PageReport2;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingTag;

import com.example.pentagon.appbar.Fragments.PageReport5;
import com.itextpdf.text.BaseColor;

import com.itextpdf.text.DocListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;

import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.pentagon.appbar.Fragments.PageReport1.description;
import static com.example.pentagon.appbar.Fragments.PageReport1.reportid;
import static com.example.pentagon.appbar.Fragments.PageReport1.reportname;
import static com.example.pentagon.appbar.Fragments.PageReport2.prjctname;

import static com.example.pentagon.appbar.Fragments.PageReportTag.addtag;
import static com.example.pentagon.appbar.Main2Activity.BITMAP_SAMPLE_SIZE;
import static com.itextpdf.text.html.HtmlTags.ALIGN_CENTER;
import static com.itextpdf.text.html.HtmlTags.FONT;

public class Utility extends Activity {
    public static MenuItem savemenu;
   public static Toolbar toolbar;
   // public static  PageReport1 pageReport1;
   public static FloatingActionButton  fab1,fab2,fab3,fabhome;
   public static  FloatingActionButton newreport;
    public static  FloatingActionButton camera;
    private static String imageStoragePath;
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";
    public static final String AUDIO_EXTENSION = "mp3";
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private static final int CAMERA_CAPTURE_AUDIO_REQUEST_CODE = 300;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final int MEDIA_TYPE_AUDIO =3 ;
    public static final int MEDIA_TYPE_PDF =4 ;
    public static String TAGEQUIP="equip";
    public static String TAGPIPE="pipe";
    public static String TAGEPIPSUP="pipsup";
    public static PrjctData CURRENTPRJCT;
    public static MenuItem sharemenu;
    private int status = ColumnText.START_COLUMN;
    private float y_position = 0;
    public void requestCameraPermission(final int type, final Activity context) {
        Dexter.withActivity(context)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            if (type == MEDIA_TYPE_IMAGE) {
                                // capture picture
                                captureImage();
                            } else if (type == MEDIA_TYPE_VIDEO) {
                                captureVideo();
                            }
                            else
                                audioDialog(context);

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionsAlert(context);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    public void audioDialog(Activity context) {

        new AudioRecordDialog(context,false);
    }

    public void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }
    public void captureVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_VIDEO);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);

        // set video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file

        // start the video capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }
    public void showPermissionsAlert(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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


    public static void optionItemSave(Activity activity,int from) {
        if(Utility.savemenu.getTitle().equals("edit")){
          ///  Toast.makeText(activity, "ffff"+reportname.getText().toString(), Toast.LENGTH_SHORT).show();

            setEnable(true);
            //Utility.camera.setVisibility(View.VISIBLE);
            Utility.savemenu.setTitle("save");
            Utility.savemenu.setIcon(activity.getResources().getDrawable(R.drawable.ic_save_black_24dp));
        }
        else {

            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            if(CreateReport.loaddata.isNewreport())
            {
//                String rid=new SqliteDb(activity).getReportID();
//                Log.e("isNewreport","1111"+ CreateReport.loaddata.getPrjct()+rid);
//                CreateReport.loaddata.setId(rid);



                CreateReport.loaddata.setCdate(year+"-"+month+"-"+day);

if(!new SqliteDb(activity).getReport(reportid.getText().toString())){
CreateReport.viewPager.setCurrentItem(0);
reportid.setFocusable(true);
reportid.setError("Report number duplication");
reportid.setTag("error");
    //Toast.makeText(activity, "Report number duplication", Toast.LENGTH_SHORT).show();
    return;
}
                CreateReport.loaddata.setId(reportid.getText().toString());
//new SharedPreferenceClass().storeDatabaseLastReport(activity,reportid.getText().toString());
            }


            CreateReport.loaddata.setReportname(reportname.getText().toString());

            CreateReport.loaddata.setDescrpotion(description.getText().toString());

            CreateReport.loaddata.setChecked(description.getText().toString());


            CreateReport.loaddata.setApproved(PageReport5.approved.getText().toString());
            CreateReport.loaddata.setSummary(PageReport5.summary.getText().toString());


            CreateReport.loaddata.setPrepared(PageReport5.prepared.getText().toString());
            CreateReport.loaddata.setChecked(PageReport5.checked.getText().toString());


            CreateReport.loaddata.setUdate(year+"-"+month+"-"+day);

            if(CreateReport.loaddata.isNewreport()){

                if(!checkValuesNull()){
                    if(from==0)
                        Utility.customToastSave("Report Discarded",activity,"");
                   else
                       Utility.customToastSave("Empty Report Details",activity,"");
                    return;

                }}
            if(!CreateReport.loaddata.getReportname().trim().isEmpty()||!CreateReport.loaddata.getId().trim().isEmpty()){
                Log.e("Reportid",CreateReport.loaddata.getId()+"tag size:"+ PageReport2.prjcttags.size());
new SqliteDb(activity).insertReport(CreateReport.loaddata);
if(!CreateReport.loaddata.getPrjct().equals(""))
                new SharedPreferenceClass().storeDatabaseLastPrjct(activity,CreateReport.loaddata.getPrjct());

                new SqliteDb(activity).insertReportTags(PageReport2.prjcttags);
            if(PageReport2.prjcttags.size()>0)
                new SharedPreferenceClass().storeDatabaseLastTags(activity,reportid.getText().toString());

                new SqliteDb(activity).insertReportArea(PageReport2.prjctareas);
                if(PageReport2.prjctareas.size()>0)
                    new SharedPreferenceClass().storeDatabaseLastArea(activity,reportid.getText().toString());

                new SqliteDb(activity).insertReportSystem(PageReport2.prjctsystem,CreateReport.loaddata.getId());
                if(PageReport2.prjctsystem.size()>0)
                    new SharedPreferenceClass().storeDatabaseLastSystem(activity,reportid.getText().toString());


                new SqliteDb(activity).insertReportDiscipline(PageReport2.prjctdiscipline,CreateReport.loaddata.getId());
                if(PageReport2.prjctdiscipline.size()>0)
                    new SharedPreferenceClass().storeDatabaseLastDiscipline(activity,reportid.getText().toString());

                //                for (int i = 0; i <CreateReport.dataPreviews.size(); i++) {
//                    if(CreateReport)
//
//                }
            new SqliteDb(activity).insertReportData(CreateReport.dataPreviews);
               // Toast.makeText(activity, "Report Saved", Toast.LENGTH_SHORT).show();
           Utility.customToastSave("Report Saved",activity,"other");
            }

        }

    }

    private static boolean checkValuesNull() {
        boolean check=false;
        if(!CreateReport.loaddata.getEmpty().isEmpty())
            return true;
        else if(CreateReport.dataSystems.size()>0||CreateReport.dataSystems.size()>0||CreateReport.dataPreviews.size()>0)
            return true;
   else
       return false;

    }

    private static void setEnable(boolean b) {
        reportname.setFocusableInTouchMode(true);
        reportname.setFocusable(true);
        description.setFocusableInTouchMode(true);
        description.setFocusable(true);
//        prjctname.setFocusableInTouchMode(true);
//        prjctname.setFocusable(true);
//   PageReport2.description.setFocusableInTouchMode(true);
//        PageReport2.description.setFocusable(true);
        PageReport5.checked.setFocusableInTouchMode(true);
        PageReport5.checked.setFocusable(true);

        PageReport5.prepared.setFocusableInTouchMode(true);
        PageReport5.prepared.setFocusable(true);

        PageReport5.approved.setFocusableInTouchMode(true);
        PageReport5.approved.setFocusable(true);

//        PageReport5.summary.setFocusableInTouchMode(true);
//        PageReport5.summary.setFocusable(true);
        addtag.setEnabled(true);
    }
public static Paragraph tablepara(PdfPTable pp1){

    Paragraph pp=new Paragraph();
    pp.setAlignment(Element.ALIGN_CENTER);
    pp.add(pp1);
    return pp;
}
    public static File createPdf(final Activity activity, DataReport data, ArrayList<DataTag> dataTag, ArrayList<DataPreview> dataPreview) throws IOException, DocumentException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      ArrayList<DataTag> dataDisciplines = new SqliteDb(activity).getReportDiscipline(data.getId());
        ArrayList<DataTag> dataSystems = new SqliteDb(activity).getReportSystem(data.getId());
        ArrayList<DataTag> prjctareas= new SqliteDb(activity).getPrjctsAreas(data.getPrjct(),data.getId());
        CameraUtils.reportid=data.getId()+"_"+data.getReportname();
        File myFile = CameraUtils.getOutputMediaFile(MEDIA_TYPE_PDF);



        assert myFile != null;
        OutputStream output = new FileOutputStream(myFile);
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
        Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 18);
        //Step 1




        final Document document = new Document();

final PdfWriter pdfWriter= PdfWriter.getInstance(document, byteArrayOutputStream);


        pdfWriter.setPageEvent(new PdfPageEvent() {
            @Override
            public void onOpenDocument(PdfWriter writer, Document document) {

            }

            @Override
            public void onStartPage(PdfWriter writer, Document document) {

            }

            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                Rectangle boderrect= new Rectangle(50,50,pdfWriter.getPageSize().getWidth()-50,pdfWriter.getPageSize().getHeight()-50); // you can resize rectangle
                boderrect.enableBorderSide(1);
                boderrect.enableBorderSide(2);
                boderrect.enableBorderSide(4);
                boderrect.enableBorderSide(8);
                boderrect.setBorderColor(BaseColor.BLACK);
                boderrect.setBorderWidth(1);
                try {
                    document.add(boderrect);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCloseDocument(PdfWriter writer, Document document) {

            }

            @Override
            public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {

            }

            @Override
            public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {

            }

            @Override
            public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {

            }

            @Override
            public void onChapterEnd(PdfWriter writer, Document document, float paragraphPosition) {

            }

            @Override
            public void onSection(PdfWriter writer, Document document, float paragraphPosition, int depth, Paragraph title) {

            }

            @Override
            public void onSectionEnd(PdfWriter writer, Document document, float paragraphPosition) {

            }

            @Override
            public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text) {

            }
        });




       document.setMargins(0, 0, 60, 0);
     document.setMarginMirroring(false);
document.open();

        Rectangle rect1= new Rectangle(12,12);
        Rectangle boderrect= new Rectangle(50,50,pdfWriter.getPageSize().getWidth()-50,pdfWriter.getPageSize().getHeight()-50); // you can resize rectangle
        boderrect.enableBorderSide(1);
        boderrect.enableBorderSide(2);
        boderrect.enableBorderSide(4);
        boderrect.enableBorderSide(8);
        boderrect.setBorderColor(BaseColor.BLACK);
        boderrect.setBorderWidth(1);
       // document.add(boderrect);
        document.add(tablepara(setpage1("Report",bf12,data.getId()+"-"+data.getReportname(),data.getDescrpotion(),bf12,document.getPageSize().getHeight())));

       document.newPage();
       // document.add(boderrect);
        document.add( tablepara(setpage1("Project Details",bf12,data.getPrjct()+"-"+data.getPrjctname(),data.getPrjctdescr(),bf12,document.getPageSize().getHeight())));


     document.newPage();

//Tags---------------------------------------------------------------------------------
        //document.add(boderrect);
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell1 = new PdfPCell(new Phrase("Project Tags", bf12));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);
        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
        cell1.setMinimumHeight(50f);

        //add the call to the table
        table.addCell(cell1);
        document.add( tablepara(table));
//        Chapter cc=new Chapter(tablepara(table), 1);
//document.add(cc);
        document.add(setSpace());
     table = new PdfPTable(2);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);
  table.setHeaderRows(0);
        insertCellHead(table,"Tag id",Element.ALIGN_CENTER,1,bf12);
        insertCellHead(table,"Code",Element.ALIGN_CENTER,1,bf12);

        for(int j=0;j<dataTag.size();j++){
        //    document.add(new Paragraph(dataTag.get(j).getTag()));
            insertCell(table,dataTag.get(j).getTagid(),Element.ALIGN_CENTER,1,bf12);
            insertCell(table,dataTag.get(j).getTag(),Element.ALIGN_CENTER,1,bf12);

        }

        document.add( tablepara(table));

        document.newPage();
//Area-----------------------------------------------------------------------------
       // document.add(boderrect);
     table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

  cell1 = new PdfPCell(new Phrase("Area", bf12));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);
        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
        cell1.setMinimumHeight(50f);

        //add the call to the table
        table.addCell(cell1);
        document.add(tablepara(table));
        document.add( setSpace());
        table = new PdfPTable(2);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setHeaderRows(0);
        insertCellHead(table,"Code",Element.ALIGN_CENTER,1,bf12);
        insertCellHead(table,"Name",Element.ALIGN_CENTER,1,bf12);
        for(int j=0;j<prjctareas.size();j++){
           // document.add(new Paragraph(prjctareas.get(j).getTag()));
            insertCell(table,prjctareas.get(j).getTagid(),Element.ALIGN_CENTER,1,bf12);
            insertCell(table,prjctareas.get(j).getTag(),Element.ALIGN_CENTER,1,bf12);

        }
        document.add( tablepara(table));

        document.newPage();

   //system-----------------------------------------------------------------------
     //   document.add(boderrect);
        table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell1 = new PdfPCell(new Phrase("System", bf12));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);
        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
        cell1.setMinimumHeight(50f);

        //add the call to the table
        table.addCell(cell1);
        document.add(tablepara(table));
document.add( setSpace());
        table = new PdfPTable(2);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setHeaderRows(0);
        insertCellHead(table,"Code",Element.ALIGN_CENTER,1,bf12);
        insertCellHead(table,"Name",Element.ALIGN_CENTER,1,bf12);
        for(int j=0;j<dataSystems.size();j++){
           // document.add(new Paragraph(dataTag.get(j).getTag()));
            insertCell(table,dataSystems.get(j).getTagid(),Element.ALIGN_CENTER,1,bf12);
            insertCell(table,dataSystems.get(j).getTag(),Element.ALIGN_CENTER,1,bf12);

        }
        document.add(tablepara(table));

       document.newPage();

        //Discipline--------------------------------------------------------------------
       // document.add(boderrect);
        table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell1 = new PdfPCell(new Phrase("Discipline", bf12));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);
        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
        cell1.setMinimumHeight(50f);

        //add the call to the table
        table.addCell(cell1);
        document.add( tablepara(table));
document.add(setSpace());
        table = new PdfPTable(2);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setHeaderRows(0);
        insertCellHead(table,"Code",Element.ALIGN_CENTER,1,bf12);
        insertCellHead(table,"Name",Element.ALIGN_CENTER,1,bf12);
        for(int j=0;j<dataDisciplines.size();j++){
          //  document.add(new Paragraph(dataDisciplines.get(j).getTag()));
            insertCell(table,dataDisciplines.get(j).getTagid(),Element.ALIGN_CENTER,1,bf12);
            insertCell(table,dataDisciplines.get(j).getTag(),Element.ALIGN_CENTER,1,bf12);

        }
        document.add(tablepara(table));

       document.newPage();

//file-----------------------------------------------------------------------------------
     //  document.add(boderrect);
int flag=0;
        PdfPTable image = new PdfPTable(1);
        PdfPTable video = new PdfPTable(1);
        PdfPTable audio = new PdfPTable(1);
for(int j=0;j<dataPreview.size();j++){
    if(dataPreview.get(j).isSelected())
if(Integer.parseInt(dataPreview.get(j).getType())==MEDIA_TYPE_IMAGE) {
        Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, dataPreview.get(j).getPath());


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();

    Image img = Image.getInstance(byteArray);
    PdfPCell cell = new PdfPCell(Image.getInstance(byteArray));
        //set the cell alignment
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //set the cell column span in case you want to merge two or more cells

        cell.setBorderWidth(0f);
    img.setAlignment(Image.TEXTWRAP);
    img.scaleAbsolute(200f, 50f);
    //img.setAbsolutePosition(imageStartX, imageStartY); // Adding Image


    PdfPCell cellImage= new PdfPCell(img, true);
        image.addCell(cellImage);
image.addCell(dataPreview.get(j).getDescr());
    flag=1;
    }
   else{

    PdfPCell  cell = new PdfPCell(new Phrase(dataPreview.get(j).getPath(), bfBold12));
        //set the cell alignment
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //set the cell column span in case you want to merge two or more cells

        cell.setBorderWidth(0f);
        if(Integer.parseInt(dataPreview.get(j).getType())==MEDIA_TYPE_VIDEO){
        video.addCell(cell);
            video.addCell(dataPreview.get(j).getDescr());
    } else{
            audio.addCell(cell);
            audio.addCell(dataPreview.get(j).getDescr());
        }


    }
}

        table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell1 = new PdfPCell(new Phrase("Images", bf12));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);
        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
        cell1.setMinimumHeight(50f);

        //add the call to the table
        table.addCell(cell1);
        if(flag==1) {
            document.add( tablepara(table));

            document.add( tablepara(image));

            document.newPage();
        }



//summary-----------------------------------------------------------------------------
     //   document.add(boderrect);
        PdfPTable tables=setpageSummary(data,bf12,bf12,0);
        document.add(tablepara(tables));
document.newPage();
document.add(tablepara(setFrontPage(pdfWriter.getCurrentPageNumber(),pdfWriter.getPageSize().getWidth())));


        document.close();
        PdfReader reader = new PdfReader(byteArrayOutputStream.toByteArray());
        int startToc = reader.getNumberOfPages();
        int n = reader.getNumberOfPages();

        reader.selectPages(String.format("1,%s, 2-%s",
                startToc, n - 1));
        PdfStamper stamper = new PdfStamper(reader, output);
        stamper.close();
        return myFile;

    }

    private static PdfPTable  setFrontPage(int currentPageNumber,float width) {
BaseColor whiteclr=BaseColor.WHITE;
new BaseColor(217,217,217);
        BaseColor greyclr=BaseColor.LIGHT_GRAY;
        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, new BaseColor(0, 0, 0));

        float[] columnWidths = {1.5f, 3f};
        //create PDF table with the given widths
        PdfPTable main = new PdfPTable(1);
        main.getDefaultCell().setBorder(0);
        main.getDefaultCell().setBorderWidth(0f);
        PdfPTable table = new PdfPTable(new float[] {2,3,6,1,1,1});

        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell1 = new PdfPCell(new Phrase("PROJECT", bfBold12));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);
        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
        cell1.setMinimumHeight(30f);

        //add the call to the table
        main.addCell(cell1);

table.addCell(getPdfCell("Rev.",bfBold12,greyclr));


        table.addCell(getPdfCell("Date",bfBold12,greyclr));
        table.addCell(getPdfCell("Reason for issue",bfBold12,greyclr));
        table.addCell(getPdfCell("org.",bfBold12,greyclr));
        table.addCell(getPdfCell("chk.",bfBold12,greyclr));
        table.addCell(getPdfCell("app.",bfBold12,greyclr));
        table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        table.addCell(getPdfCell("",bfBold12,whiteclr));
        table.addCell(getPdfCell("",bfBold12,whiteclr));
        table.addCell(getPdfCell("",bfBold12,whiteclr));
        table.addCell(getPdfCell("",bfBold12,whiteclr));
        table.addCell(getPdfCell("",bfBold12,whiteclr));
        table.addCell(getPdfCell("",bfBold12,whiteclr));

        cell1 = new PdfPCell();

cell1.setColspan(1);
        cell1.setBorderWidth(0f);
        cell1.addElement(table);

      main.addCell(table);
        table = new PdfPTable(new float[] {2,3});
       table.getDefaultCell().setPadding(0f);
//        table.getDefaultCell().setBorderWidth(0f);
        table.addCell(getPdfCell("",bfBold12,whiteclr));
     PdfPTable   table1 = new PdfPTable(1);
        table1.addCell(getPdfCell("Project Name",bfBold12,greyclr));
        table1.addCell(getPdfCell("",bfBold12,whiteclr));
        table1.addCell(getPdfCell("Document Number",bfBold12,greyclr));
        table1.addCell(getPdfCell("",bfBold12,whiteclr));
        cell1 = new PdfPCell();


        cell1.setBorderWidth(0f);
        cell1.addElement(table1);
        table.addCell(table1);
        table.addCell(getPdfCell("",bfBold12,whiteclr));
        table1 = new PdfPTable(1);
        table1.addCell(getPdfCell("Document Title",bfBold12,greyclr));
        table1.addCell(getPdfCell("",bfBold12,whiteclr));
//        table1.addCell(getPdfCell("Document Number",bfBold12));
//        table1.addCell(getPdfCell("",bfBold12));
        cell1 = new PdfPCell();


        cell1.setBorderWidth(0f);
        cell1.addElement(table1);
        table.addCell(table1);
        table.addCell(getPdfCell("",bfBold12,greyclr));
        table1 = new PdfPTable(new float[] {3,1});
        table1.addCell(getPdfCell("Contract No:",bfBold12,greyclr));

        table1.addCell(getPdfCell("No of Pages",bfBold12,greyclr));

        cell1 = new PdfPCell();


        cell1.setBorderWidth(0f);
        cell1.addElement(table1);
        table.addCell(table1);
        table.addCell(getPdfCell("",bfBold12,whiteclr));
        table1 = new PdfPTable(new float[] {3,1});


        table1.addCell(getPdfCell("",bfBold12,whiteclr));
        table1.addCell(getPdfCell("",bfBold12,whiteclr));
        cell1 = new PdfPCell();


        cell1.setBorderWidth(0f);
        cell1.addElement(table1);
        table.addCell(table1);
        main.addCell(table);
        return main;
    }

    private static PdfPCell getPdfCell(String s,Font hfont,BaseColor bb) {
       PdfPCell cell1 = new PdfPCell(new Phrase(s, hfont));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);
        if(s.equals(""))
cell1.setMinimumHeight(20f);
        cell1.setBorderWidth(0.5f);
        cell1.setRowspan(1);

        cell1.setBackgroundColor(bb);
        //in case there is no text and you wan to create an empty row


        //add the call to the table
    return cell1;
    }

    private static PdfPTable setSpace() {
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell1 = new PdfPCell();
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);
        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
        cell1.setMinimumHeight(50f);

        //add the call to the table
        table.addCell(cell1);
        return table;
    }

    private static PdfPTable setpage1(String head,Font hfont,String content1,String content2,Font cfont,float pagersize) {

        Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0));
        float[] columnWidths = {1.5f, 3f};
        //create PDF table with the given widths
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell1 = new PdfPCell(new Phrase(head, hfont));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);
        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
        cell1.setMinimumHeight(50f);

        //add the call to the table
        table.addCell(cell1);




        PdfPCell cell = new PdfPCell(new Phrase(content1, bfBold12));
        //set the cell alignment
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //set the cell column span in case you want to merge two or more cells
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell.setBorderWidth(0f);

        cell.setRowspan(1);
        //in case there is no text and you wan to create an empty row
   //     cell.setMinimumHeight((pagersize-150f));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(content2, cfont));
        //set the cell alignment
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //set the cell column span in case you want to merge two or more cells
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell.setBorderWidth(0f);

        cell.setRowspan(1);
        //in case there is no text and you wan to create an empty row
        cell.setMinimumHeight((pagersize-250f));
        table.addCell(cell);


        //add the call to the table
//        PdfPTable table1 = new PdfPTable(1);
//        table1.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//        cell1 = new PdfPCell(new Phrase(head, hfont));
//        //set the cell alignment
//        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//        //set the cell column span in case you want to merge two or more cells
//        cell1.setVerticalAlignment(Element.ALIGN_TOP);
//
//        cell1.setBorderWidth(1f);
//        cell1.setRowspan(1);
//        //in case there is no text and you wan to create an empty row
//        cell1.setMinimumHeight(pagersize);
//
//        //add the call to the table
//        table1.addCell(cell1);
//
//table1.addCell(table);

return table;
    }
//private static PdfPTable setTable(ArrayList<String> contents,float minimumht,int hal,int val){
//
//
//}
    private static PdfPTable setpageSummary(DataReport dd,Font hfont,Font cfont,float pagersize) {


        float[] columnWidths = {1.5f, 3f};
        //create PDF table with the given widths
        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell1 = new PdfPCell(new Phrase("Summary", hfont));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);
        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
       //cell1.setMinimumHeight(50f);

        //add the call to the table
        table.addCell(cell1);




    cell1 = new PdfPCell(new Phrase(dd.getSummary(), cfont));
        //set the cell alignment
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        //set the cell column span in case you want to merge two or more cells
        cell1.setVerticalAlignment(Element.ALIGN_TOP);

        cell1.setBorderWidth(0f);

        cell1.setRowspan(1);
        //in case there is no text and you wan to create an empty row
       // cell1.setMinimumHeight((pagersize-200));
        table.addCell(cell1);

        PdfPTable table1 = new PdfPTable(2);
        //add the call to the table
        insertCell(table1,"Checked By:",Element.ALIGN_LEFT,1,cfont);

        insertCell(table1,dd.getChecked(),Element.ALIGN_RIGHT,1,cfont);
        insertCell(table1,"Prepared By:",Element.ALIGN_LEFT,1,cfont);
        insertCell(table1,dd.getPrepared(),Element.ALIGN_RIGHT,1,cfont);
        insertCell(table1,"Approved By:",Element.ALIGN_LEFT,1,cfont);
        insertCell(table1,dd.getApproved(),Element.ALIGN_RIGHT,1,cfont);
table.addCell(table1);

        return table;
    }

    private static void insertCellHead(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        cell.setBorderWidth(1f);
        cell.setPadding(3f);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(15f);

        }

        //add the call to the table


        table.addCell(cell);
    }

    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        cell.setBorderWidth(0f);
        cell.setPadding(5f);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);

        }

        //add the call to the table


        table.addCell(cell);
    }

    private static Paragraph getPara(String i) {
        Paragraph p1 = new Paragraph(i);
        Font paraFont= new Font(Font.FontFamily.COURIER);
        paraFont.setSize(10.9f);
        p1.setAlignment(Element.ALIGN_CENTER);

        p1.setFont(paraFont);

        return p1;
    }
    private static Paragraph getPara1(String i) {
        Paragraph p1 = new Paragraph(i);
        Font paraFont= new Font(Font.FontFamily.COURIER);
        paraFont.setSize(20f);
        p1.setAlignment(Element.ALIGN_MIDDLE);

        p1.setFont(paraFont);

        return p1;
    }
//    public void addTable(Document document, PdfContentByte canvas, PdfPTable table) throws DocumentException {
//        Rectangle pagedimension = new Rectangle(36, 36, 559, 806);
//        drawColumnText(document, canvas, pagedimension, table, true);
//        Rectangle rect;
//        if (ColumnText.hasMoreText(status)) {
//            rect = pagedimension;
//        }
//        else {
//            rect = new Rectangle(36, 36, 559, 806 - ((y_position - 36) / 2));
//        }
//        drawColumnText(document, canvas, rect, table, false);
//    }

    public static float drawColumnText(PdfContentByte canvas, Rectangle rect, Paragraph p, boolean simulate) throws DocumentException {
        ColumnText ct = new ColumnText(canvas);
        ct.setSimpleColumn(rect);
        ct.addElement(p);
        ct.go(simulate);
        return ct.getYLine();
    }
    public static void emailNote(Activity activity, ArrayList<File> file)
    {
       File myFile = null;

        ArrayList<Uri> urr=new ArrayList<>();
        //        Intent email = new Intent(Intent.ACTION_SEND);
//        email.putExtra(Intent.EXTRA_SUBJECT,"Report1");
//        email.putExtra(Intent.EXTRA_TEXT, "sample");
//        Uri fileUri = CameraUtils.getOutputMediaFileUri(activity, myFile);
//        email.putExtra(Intent.EXTRA_STREAM, fileUri);
//        email.setType("message/rfc822");
//        activity.startActivity(email);


        String filename="contacts_sid.vcf";
        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);

     //   Uri path = Uri.fromFile(myFile);


        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
// set the type to 'email'
        emailIntent.setType("text/plain");
       // emailIntent .setType("vnd.android.cursor.dir/email");
        String to[] = {"asd@gmail.com"};
     //   emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
        Log.e("filesize",file.size()+"");
        for(int j=0;j<file.size();j++){

            urr.add(Uri.fromFile(file.get(j)));
           // emailIntent .putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file.get(j)));
        }

// the mail subject
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, urr);
        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Plantdesk Report");
        activity.startActivity(Intent.createChooser(emailIntent , "Send email..."));
    }
    public static PdfPTable getParatable(String ss){

        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Paragraph para = new Paragraph(ss, font);
        para.setLeading(0, 1);
        PdfPTable table = new PdfPTable(1);

        table.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell();
        cell.setMinimumHeight(50);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.addElement(para);

        table.addCell(cell);
        return table;
    }
    public static android.app.AlertDialog.Builder alertdialog(Context c, String title, String msg)
    {

        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(c);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //alertDialog.setView(inflater.inflate(R.layout.deleteallmessagerequest_dialog, null));
        assert inflater != null;
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.alertdialog, null);
        alertDialog.setView(dialogView);

        TextView tt=(TextView)dialogView.findViewById(R.id.titleq);
        tt.setText(title);
        TextView ms=(TextView)dialogView.findViewById(R.id.msg);
        ms.setText(msg);

        return alertDialog;
    }
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 12;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    private void copyFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File (outputPath);
            if (!dir.exists())
            {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file (You have now copied the file)
            out.flush();
            out.close();
            out = null;

        }  catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        }
        catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }
public static ArrayList<DataTag> compare(ArrayList<DataTag> first, ArrayList<DataTag> second){
    ArrayList<DataTag> result = new ArrayList<>();
    int j,i;
    for ( i =0; i <first.size() ; i++) {
Log.e("i",i+""+first.get(i).getTagid());
        for ( j = 0; j <second.size() ; j++) {
            if(first.get(i).getTagid().equals(second.get(j).getTagid())){


                break;
            }

            Log.e("j",j+" "+second.get(j).getTagid());
        }
if(j==second.size())
    result.add(first.get(i));
    }
//    for (DataTag dd:first
//         ) {
//        for (DataTag d1:second
//             ) {
//            if(dd.getTagid().equals(d1.getTagid())){
//
//                result.remove(dd);
//            }
//        }
//
//}
        return result;
}
public static void customToastSave(String text,Activity activity,String n){

    View toastView = activity.getLayoutInflater().inflate(R.layout.toastcustomview, null);
    Drawable dd=activity.getResources().getDrawable(R.drawable.ic_delete_black_24dp);

    dd.setColorFilter(new
            PorterDuffColorFilter(activity.getResources().getColor(R.color.white),PorterDuff.Mode.SRC_IN));
    // Initiate the Toast instance.
    Toast toast = new Toast(activity);
    // Set custom view in toast.
    toast.setView(toastView);
    TextView tt=(TextView)toastView.findViewById(R.id.customToastText);
  tt.setText(text);
  if(n.equals("save"))
      tt.setCompoundDrawablesWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.ic_save_black_24dp),null,null,null);

  else if(n.equals("done"))
  tt.setCompoundDrawablesWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.ic_done_white_24dp),null,null,null);

  else if(n.equals("delete"))
      tt.setCompoundDrawablesWithIntrinsicBounds(dd,null,null,null);
  else
      tt.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);

    toast.setDuration(Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.BOTTOM, 0,100);

    toast.show();
}
    static class Header extends PdfPageEventHelper {
        Font font;
        PdfTemplate t;
        Image total;

        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            t = writer.getDirectContent().createTemplate(30, 16);
            try {
                total = Image.getInstance(t);
                total.setRole(PdfName.ARTIFACT);
                font = new Font(Font.FontFamily.TIMES_ROMAN, 10);
                //font =  new Font(BaseFont.createFont(FONT, BaseFont.COURIER_BOLD, BaseFont.EMBEDDED), 10);
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            //table.getDefaultCell().setBorder(Rectangle.BOTTOM);
            //table.addCell(new Phrase("Test", font));
            table.setTotalWidth(writer.getPageSize().getWidth());
            table.addCell(new Phrase(String.format("%d", writer.getPageNumber()), font));


            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            table.writeSelectedRows(0, -1, 36, 30, canvas);
            canvas.endMarkedContentSequence();
        }

        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(t, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber()), font),
                    2, 4, 0);
        }
    }
}
