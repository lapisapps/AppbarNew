package com.example.pentagon.appbar.AdapterClass;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.transition.ChangeBounds;
import android.support.transition.TransitionManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AddProjectDialog;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;

import java.util.ArrayList;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class RecyclerViewAdapterProjects extends RecyclerView.Adapter<RecyclerViewAdapterProjects.MyViewHolder> implements Filterable {

    private ArrayList<PrjctData> albumList;
    private ArrayList<PrjctData> albumListf;
    //    public static ArrayList<String> albumList1;
    private Activity mContext;
    //  private List<S> albumList;

    private String type;
    private int mExpandedPosition=-1;
    RecyclerView recyclerView;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView prjct,desc,id,edit,delete;

View v;



        public MyViewHolder(View view) {
            super(view);
            v=view;
            id=(TextView) view.findViewById(R.id.id);
            prjct=(TextView) view.findViewById(R.id.tag);
            desc=(TextView) view.findViewById(R.id.textView7);

            edit=(TextView) view.findViewById(R.id.edit);
            delete=(TextView) view.findViewById(R.id.delete);
         //   recyclerView=(RecyclerView) view.findViewById(R.id.prjcttags);
        }
    }


    public RecyclerViewAdapterProjects(Activity mContext, ArrayList<PrjctData> albumList, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.albumListf = albumList;
        this.recyclerView=recyclerView;
        this.type=type;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowprjctselect, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final PrjctData product = albumList.get(position);


        try {
            holder.id.setText(product.getId());
            holder.prjct.setText(product.getPrjct());
//holder.prjct.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        Toast.makeText(mContext, "ddd", Toast.LENGTH_SHORT).show();
//    }
//});
            holder.desc.setText(product.getDescr());
           // holder.prjct.setBackgroundColor(mContext.getResources().getColor(R.color.row));
holder.desc.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.desc.setVisibility(View.VISIBLE);

holder.edit.setVisibility(View.GONE);
holder.delete.setVisibility(View.GONE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);

    }
    private void setPrjctTagRecycle(RecyclerView rr,String prjct) {


ArrayList<DataTag> dataTags=new SqliteDb(mContext).getPrjctsTags(prjct,"");

        RecyclerViewAdapterTagSt adapterprjcttags = new RecyclerViewAdapterTagSt(mContext,dataTags,1);
        rr.setVisibility(View.VISIBLE);

        rr.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,1,GridLayoutManager.HORIZONTAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rr.setNestedScrollingEnabled(true);

        // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        rr.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        rr.setItemAnimator(new DefaultItemAnimator());
        rr.setAdapter(adapterprjcttags);




//        recyimages.addOnItemTouchListener(new RecyclerItemClickListener(context, recyimages, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                new PreviewDialog(context,ddimage.get(position),false);
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));

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
@Override

public Filter getFilter() {
    return new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();
            if (charString.isEmpty()) {
                albumList = albumListf;
            } else {
                ArrayList<PrjctData> filteredList = new ArrayList<>();
                for (PrjctData row : albumListf) {

                    // name match condition. this might differ depending on your requirement
                    // here we are looking for name or phone number match
                    if (row.getPrjct().toLowerCase().contains(charString.toLowerCase()) || row.getId().toLowerCase().contains(charString.toLowerCase())) {
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
            albumList = (ArrayList<PrjctData>) filterResults.values;
            //  size=albumList.size();
            // refresh the list with filtered data
            notifyDataSetChanged();


        }
    };
}
}
