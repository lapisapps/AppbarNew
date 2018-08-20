package com.example.pentagon.appbar.AdapterClass;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AddTagDialog;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class RecyclerViewAdapterTagSelection extends RecyclerView.Adapter<RecyclerViewAdapterTagSelection.MyViewHolder> {
    private SparseBooleanArray mSelectedItemsIds;
    private ArrayList<DataTag> albumList;
    private ArrayList<DataTag> albumListf;
    //    public static ArrayList<String> albumList1;
    private Activity mContext;
    //  private List<S> albumList;

    private int type;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tag,tagid;
        ImageButton edit,delete;



        public MyViewHolder(View view) {
            super(view);

            tag=(TextView) view.findViewById(R.id.tag);
            tagid=(TextView) view.findViewById(R.id.id);
            edit= view.findViewById(R.id.edit);
            delete= view.findViewById(R.id.delete);
        }
    }


    public RecyclerViewAdapterTagSelection(Activity mContext, ArrayList<DataTag> albumList, int type) {
        this.mContext = mContext;
        this.albumList = albumList;
        albumListf=albumList;
        this.type=type;
        mSelectedItemsIds=new SparseBooleanArray();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
//if(type==0)
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_tagst, parent, false);
//else
//    itemView = LayoutInflater.from(parent.getContext())
//            .inflate(R.layout.row_tagst1, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DataTag product = albumList.get(position);


        try {

holder.tag.setText(product.getTag());
            holder.tagid.setText(product.getTagid());

if(type==2) {
    holder.edit.setVisibility(View.GONE);
    holder.delete.setVisibility(View.GONE);
}
            if(product.getExist().equals("0"))
                holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.row));
            else
                holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.white));

//            holder.itemView
//                    .setBackgroundColor((product.getExist().equals("0")) ? mContext.getResources().getColor(R.color.grey1)
//                            :mContext.getResources().getColor(R.color.white) );
//            holder.itemView
//                    .setBackgroundColor(mSelectedItemsIds.get(position) ? mContext.getResources().getColor(R.color.grey1)
//                            :mContext.getResources().getColor(R.color.row) );
        }catch (Exception e){e.printStackTrace();}



    }

    /**
     * Showing popup menu when tapping on 3 dots
     */

    @Override
    public int getItemCount() {
        return albumList.size();
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
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    albumList = albumListf;
                } else {
                    ArrayList<DataTag> filteredList = new ArrayList<>();
                    for (DataTag row : albumListf) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTagid().toLowerCase().contains(charString.toLowerCase()) || row.getTag().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    albumList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = albumList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                albumList = (ArrayList<DataTag>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
