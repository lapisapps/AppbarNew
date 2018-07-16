package com.example.pentagon.appbar.AdapterClass;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.transition.ChangeBounds;
import android.support.transition.TransitionManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.appbar.AddProjectDialog;
import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.DataClass.PrjctData;
import com.example.pentagon.appbar.R;
import com.example.pentagon.appbar.SqliteDb;
import com.example.pentagon.appbar.Utility;
import com.itextpdf.xmp.impl.Utils;

import java.util.ArrayList;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class RecyclerViewAdapterProjectSt extends RecyclerView.Adapter<RecyclerViewAdapterProjectSt.MyViewHolder> {

    private final ArrayList<PrjctData> albumList;
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


    public RecyclerViewAdapterProjectSt(Activity mContext, ArrayList<PrjctData> albumList, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.recyclerView=recyclerView;
        this.type=type;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_prjctst, parent, false);


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

            holder.desc.setVisibility(View.GONE);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean shouldExpand = holder.desc.getVisibility() == View.GONE;

                    ChangeBounds transition = new ChangeBounds();
                    transition.setDuration(125);

                    if (shouldExpand) {
                        holder.desc.setVisibility(View.VISIBLE);
                        // holder.imageView_toggle.setImageResource(R.drawable.collapse_symbol);
                    } else {
                        holder.desc.setVisibility(View.GONE);
                        //  viewHolder.imageView_toggle.setImageResource(R.drawable.expand_symbol);
                    }

                    TransitionManager.beginDelayedTransition(recyclerView, transition);
                    holder.itemView.setActivated(shouldExpand);
                }
            });

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddProjectDialog.prjctData = product;
                    new AddProjectDialog(mContext, 2);
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
                            new SqliteDb(mContext).deletePrjct(product.getId());
                            dialog.dismiss();
                            // new ResponseHandler(context,ResponseHandler.this,jsonObjectCall);
                            Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
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
}
