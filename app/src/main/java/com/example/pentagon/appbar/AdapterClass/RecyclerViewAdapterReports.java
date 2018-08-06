package com.example.pentagon.appbar.AdapterClass;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pentagon.appbar.DataClass.DataReport;
import com.example.pentagon.appbar.R;

import java.util.ArrayList;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class RecyclerViewAdapterReports extends RecyclerView.Adapter<RecyclerViewAdapterReports.MyViewHolder> {
    private SparseBooleanArray mSelectedItemsIds;
    private final ArrayList<DataReport> albumList;
//    public static ArrayList<String> albumList1;
    private Context mContext;
  //  private List<S> albumList;

private String type;
    public class MyViewHolder extends RecyclerView.ViewHolder {
TextView prjctname,prjctdescr,date,reportname,datasize;
String type;
LinearLayout back;
ImageView data;



        public MyViewHolder(View view) {
            super(view);

            prjctname=(TextView) view.findViewById(R.id.prjctname);
back=(LinearLayout)view.findViewById(R.id.back);
            prjctdescr=(TextView) view.findViewById(R.id.prjctdescr);
            date=(TextView) view.findViewById(R.id.date);
            reportname=(TextView) view.findViewById(R.id.reportname);
            datasize=(TextView) view.findViewById(R.id.datasize);
        }
    }


    public RecyclerViewAdapterReports(Context mContext, ArrayList<DataReport> albumList,String type) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.type=type;
        mSelectedItemsIds = new SparseBooleanArray();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(!type.equals("list"))
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_reports, parent, false);
else

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.reportslist, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DataReport product = albumList.get(position);

        try {
            if(type.equals("list"))
holder.reportname.setText(product.getId()+"\n"+product.getReportname());
            else
                holder.reportname.setText(product.getReportname());
            holder.prjctdescr.setText(product.getPrjctdescr());
            holder.prjctname.setText(product.getPrjctname());
            holder.date.setText("Last updation:"+product.getUdate());
            if(Integer.parseInt(product.getFiles())>0)
            holder.datasize.setText(product.getFiles()+" Files");
            else
                holder.datasize.setText("No Files");

        }catch (Exception e){e.printStackTrace();}
        /** Change background color of the selected items in list view  **/
        holder.back
                .setBackgroundColor(mSelectedItemsIds.get(position) ? mContext.getResources().getColor(R.color.grey)
                        :mContext.getResources().getColor(R.color.rportrow) );
    }
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }


    //Remove selected selections
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }


    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        popup.getMenuInflater().inflate(R.menu.s, popup.getMenu());
////        MenuInflater inflater = popup.getMenuInflater();
////        inflater.inflate(R.menu.menu_product, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }

}
