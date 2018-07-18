package com.example.pentagon.appbar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.pentagon.appbar.Fragments.PageReport1.description;
import static com.example.pentagon.appbar.Fragments.PageReport1.reportname;
import static com.example.pentagon.appbar.Fragments.PageReport2.prjctname;

import static com.example.pentagon.appbar.Fragments.PageReportTag.addtag;
import static com.example.pentagon.appbar.Main2Activity.BITMAP_SAMPLE_SIZE;

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
    public void audioDialog(Context context) {

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


    public static void optionItemSave(Activity activity) {
        if(Utility.savemenu.getTitle().equals("edit")){
            Toast.makeText(activity, "ffff"+reportname.getText().toString(), Toast.LENGTH_SHORT).show();

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
                Log.e("isNewreport","1111"+ CreateReport.loaddata.getPrjct());
                CreateReport.loaddata.setId(new SqliteDb(activity).getReportID()+1);


                CreateReport.loaddata.setCdate(year+"-"+month+"-"+day);



            }


            CreateReport.loaddata.setReportname(reportname.getText().toString());

            CreateReport.loaddata.setDescrpotion(description.getText().toString());

            CreateReport.loaddata.setChecked(description.getText().toString());


            CreateReport.loaddata.setApproved(PageReport5.approved.getText().toString());
            CreateReport.loaddata.setSummary(PageReport5.summary.getText().toString());


            CreateReport.loaddata.setPrepared(PageReport5.prepared.getText().toString());
            CreateReport.loaddata.setChecked(PageReport5.checked.getText().toString());


            CreateReport.loaddata.setUdate(year+"-"+month+"-"+day);
            if(CreateReport.loaddata.getReportname()!=null){
                Log.e("Reportid",CreateReport.loaddata.getId()+"tag size:"+ PageReport2.prjcttags.size());
new SqliteDb(activity).insertReport(CreateReport.loaddata);
            new SqliteDb(activity).insertReportTags(PageReport2.prjcttags);
                new SqliteDb(activity).insertReportArea(PageReport2.prjctareas);
                new SqliteDb(activity).insertReportSystem(CreateReport.dataSystems);
                new SqliteDb(activity).insertReportDiscipline(CreateReport.dataDisciplines);
            new SqliteDb(activity).insertReportData(CreateReport.dataPreviews);
                Toast.makeText(activity, "Report Saved", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private static void setEnable(boolean b) {
        reportname.setFocusableInTouchMode(true);
        reportname.setFocusable(true);
        description.setFocusableInTouchMode(true);
        description.setFocusable(true);
        prjctname.setFocusableInTouchMode(true);
        prjctname.setFocusable(true);
   PageReport2.description.setFocusableInTouchMode(true);
        PageReport2.description.setFocusable(true);
        PageReport5.checked.setFocusableInTouchMode(true);
        PageReport5.checked.setFocusable(true);

        PageReport5.prepared.setFocusableInTouchMode(true);
        PageReport5.prepared.setFocusable(true);

        PageReport5.approved.setFocusableInTouchMode(true);
        PageReport5.approved.setFocusable(true);

        PageReport5.summary.setFocusableInTouchMode(true);
        PageReport5.summary.setFocusable(true);
        addtag.setEnabled(true);
    }

    public static File createPdf(DataReport data, ArrayList<DataTag> dataTag, ArrayList<DataPreview> dataPreview) throws IOException, DocumentException {



        File myFile = CameraUtils.getOutputMediaFile(MEDIA_TYPE_PDF);
//        if (file != null) {
//            imageStoragePath = file.getAbsolutePath();
//        }

        assert myFile != null;
        OutputStream output = new FileOutputStream(myFile);

        //Step 1



        Document document = new Document();

PdfWriter pdfWriter= PdfWriter.getInstance(document, output);
        document.open();
        //Step 2
        PdfContentByte cb =   pdfWriter.getDirectContent();

        //Step 3


        //Step 4 Add content
//first page--report

        document.add(getPara("Report name:"+data.getReportname()));
        document.add(getPara("Report description:"+data.getDescrpotion()));
      String ss=  "Report name:"+data.getReportname()+"\n"+"Project description:"+data.getDescrpotion();

        document.newPage();

        //second page--project
        document.add(getParatable(ss));
     //   document.add(getPara("Project description:"+data.getDescrpotion()));
        document.newPage();


        //Third page--Tags
        for(int j=0;j<dataTag.size();j++){
            document.add(new Paragraph(dataTag.get(j).getTag()));

        }

        document.newPage();
//Fouth page




for(int j=0;j<dataPreview.size();j++){
if(Integer.parseInt(dataPreview.get(j).getType())==MEDIA_TYPE_IMAGE) {
    Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, dataPreview.get(j).getPath());


    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
    byte[] byteArray = stream.toByteArray();
    bitmap.recycle();
    document.add(Image.getInstance(byteArray));
}
}
        //Fifth page
        PdfPTable table =new PdfPTable(1);
table.setHorizontalAlignment(table.ALIGN_CENTER);
        document.add(getPara("Summary:"+data.getSummary()));
        document.add(getPara("Checked:"+data.getChecked()));
        document.add(getPara("Prepared:"+data.getPrepared()));
        document.add(getPara("Approved:"+data.getApproved()));

        document.newPage();

        //Step 5: Close the document
        document.close();
        return myFile;

    }

    private static Paragraph getPara(String i) {
        Paragraph p1 = new Paragraph(i);
        Font paraFont= new Font(Font.FontFamily.COURIER);
        paraFont.setSize(10.9f);
        p1.setAlignment(Element.ALIGN_CENTER);
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
        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
        Log.e("filesize",file.size()+"");
        for(int j=0;j<file.size();j++){

            urr.add(Uri.fromFile(file.get(j)));
           // emailIntent .putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file.get(j)));
        }

// the mail subject
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, urr);
        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");
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
}
