package com.example.pentagon.appbar.AdapterClass;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.Fragments.CreateReport;
import com.example.pentagon.appbar.Fragments.PageReport2;
import com.example.pentagon.appbar.Fragments.SettingsFragments.FragmentSettingTag;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class RecyclerViewAdapterTags extends RecyclerView.Adapter<RecyclerViewAdapterTags.MyViewHolder>  {

    private  ArrayList<DataTag> albumList;
  public  ArrayList<DataTag> mArrayList;
    private Context mContext;
  //  private List<S> albumList;

private String type;
    public class MyViewHolder extends RecyclerView.ViewHolder {
TextView tag;
CheckBox check;



        public MyViewHolder(View view) {
            super(view);

            tag=(TextView) view.findViewById(R.id.tag);

            check=(CheckBox) view.findViewById(R.id.checkBox);
        }
    }


    public RecyclerViewAdapterTags(Context mContext, ArrayList<DataTag> albumList,String type) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.mArrayList = albumList;
this.type=type;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tag_row, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DataTag product = albumList.get(position);


        try {
holder.tag.setText(product.getTag());
if(product.getSelected())
holder.check.setChecked(true);
else
    holder.check.setChecked(false);

        }catch (Exception e){e.printStackTrace();}
        holder.check.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Boolean b=false;
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    if(Utility.savemenu.getTitle().equals("edit"))
                return true;

                }
                return  false;
            }
        });
//        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                    if(Utility.savemenu.getTitle().equals("edit"))
//                      if(holder.check.isChecked())
//                          holder.check.setChecked(false);
//else
//
//                return false;
//            }
//        });
holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {




if(type.equals("tags"))
    PageReport2.prjcttags.get(position).setSelected(isChecked);
else if(type.equals("disciplines"))
    CreateReport.dataDisciplines.get(position).setSelected(isChecked);
else if (type.equals("area"))
    PageReport2.prjctareas.get(position).setSelected(isChecked);
else
    CreateReport.dataSystems.get(position).setSelected(isChecked);

    }
});


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
//@Override
//public Filter getFilter() {
//
//    return new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//
//            String charString = charSequence.toString().toLowerCase();
//
//            if (charString.isEmpty()) {
//
//                albumList = mArrayList;
//            } else {
//
//                ArrayList<DataTag> filteredList = new ArrayList<>();
//
//                for (DataTag androidVersion : mArrayList) {
//
//                    if (androidVersion.getTagid().toLowerCase().contains(charString.toLowerCase()) || androidVersion.getCustomername().toLowerCase().contains(charString.toLowerCase()) || androidVersion.getInovoiceno().toLowerCase().contains(charString.toLowerCase())) {
//
//                        filteredList.add(androidVersion);
//                    }
//                }
//
//                feedItemList = filteredList;
//            }
//
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = feedItemList;
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            feedItemList = (ArrayList<DataOrder>) filterResults.values;
//            notifyDataSetChanged();
//        }
//    };
//}
}
