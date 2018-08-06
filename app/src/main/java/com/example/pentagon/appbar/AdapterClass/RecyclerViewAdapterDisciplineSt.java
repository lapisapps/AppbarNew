package com.example.pentagon.appbar.AdapterClass;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AddAreaDialog;
import com.example.pentagon.appbar.AddDisciplineDialog;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class RecyclerViewAdapterDisciplineSt extends RecyclerView.Adapter<RecyclerViewAdapterDisciplineSt.MyViewHolder> {

    private ArrayList<DataTag> albumList;
    private  ArrayList<DataTag> albumListf;
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
    public RecyclerViewAdapterDisciplineSt(Activity mContext, ArrayList<DataTag> albumList, int type) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.albumListf = albumList;
        this.type=type;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
if(type==0)
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_tagst, parent, false);
else
    itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.row_tagst1, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DataTag product = albumList.get(position);


        try {

holder.tag.setText(product.getTag());
            holder.tagid.setText(product.getTagid());

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddDisciplineDialog.systemdata=product;
                    new AddDisciplineDialog(mContext, 2);
                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.app.AlertDialog.Builder builder = Utility.alertdialog(mContext, "Delete", "Do you want to Delete?");
                    // final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                    //
                    // builder.setTitle("Autograde");
                    //  builder.setMessage(message);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new SqliteDb(mContext).deleteDiscipline(product.getTagid());
                            dialog.dismiss();
                            // new ResponseHandler(context,ResponseHandler.this,jsonObjectCall);
                            Utility.customToastSave("Deleted",mContext,"delete");
                          //  Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                            albumList.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.show();
                }
            });
        }catch (Exception e){e.printStackTrace();}



    }
    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);

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

    /**
     * Click listener for popup menu items
     */
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//        public MyMenuItemClickListener() {
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.action_add_favourite:
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.action_play_next:
//                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                    return true;
//                default:
//            }
//            return false;
//        }
//    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
//    private void deletearrang(final String noidd) {
//
//
//        Map<String, String> params = new HashMap<>();
//        //jObj = new JSONObject();
//        params.put("markid", noidd);
//
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.markremoveurl,params);
//        jsonObjectCall.clone().enqueue(new ResponseHandler(mContext, new ResponseCallback() {
//            @Override
//            public void getResponse(int code, JsonObject jsonObject) {
//
//                JSONObject jsonObj = null;
//                try {
//                    jsonObj = new JSONObject(jsonObject.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
////{"users":[{"admin_id":"admin","password":"admin"}]}
//
//                //JSONObject jsonObj = new JSONObject(datafromserver);
//
//                if (jsonObj != null) {
//                    Log.d("getfromserver", "b4 extract");
//                    JSONObject actor = null;
//
//
//                    try {
//                        if (jsonObj.has("mark")) {
//                            if(jsonObj.getString("mark").equals("true")){
//                                Toast.makeText(mContext, "Removed", Toast.LENGTH_SHORT).show();
////for(int i=0;i<station.size();i++){
////
////    if(station.get(i).getFaculty_id().equals(noid)){
////        station.remove(i);
////        break;
////
////    }
////}
//                                Utility.MarkViewActivity.getNotification();
//                                //  Log.i("ddd",station.size()+"");
////adapter1.notifyDataSetChanged();
////setView();
//
//                                //  JSONArray login_array = jsonObj.getJSONArray("faculty");
//
//                            }else {
//                                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
//
//
//                            }}
//
//
//                    } catch (Exception eee) {
//                        eee.printStackTrace();
//                    }
//                    //     setView();
//                }
//            }
//            @Override
//            public void getFailure(Call<JsonObject> call, int code) {
//                if (code==1) {
//
//                    Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(mContext, "Can't Connect with server", Toast.LENGTH_SHORT).show();
//
//                }
//                if( ResponseHandler.progressDialog!=null)
//                    ResponseHandler.progressDialog.dismiss();
//
//            }
//
//
//        }, jsonObjectCall,1));
//    }
public  int getSize(){

    return albumList.size();
}
}
