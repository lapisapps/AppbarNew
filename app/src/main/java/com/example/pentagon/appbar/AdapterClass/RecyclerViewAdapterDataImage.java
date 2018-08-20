package com.example.pentagon.appbar.AdapterClass;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.pentagon.appbar.CameraUtils;
import com.example.pentagon.appbar.DataClass.DataPreview;
import com.example.pentagon.appbar.DescriptionDialog;
import com.example.pentagon.appbar.Fragments.FragmentDataViewChild;
import com.example.pentagon.appbar.R;

import java.util.ArrayList;

import static com.example.pentagon.appbar.Main2Activity.BITMAP_SAMPLE_SIZE;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class RecyclerViewAdapterDataImage extends RecyclerView.Adapter<RecyclerViewAdapterDataImage.MyViewHolder> {

    private final ArrayList<DataPreview> albumList;
//    public static ArrayList<String> albumList1;
    private Context mContext;
  //  private List<S> albumList;

public  int type;
    public class MyViewHolder extends RecyclerView.ViewHolder {
     ImageView imageView,vidpreview;
     VideoView videoView;
     TextView desc;




        public MyViewHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.imageView);
         desc=view.findViewById(R.id.description);
//            vidpreview=(ImageView) view.findViewById(R.id.videopreview);
//            imageView=(ImageView) view.findViewById(R.id.imageView3);
//            videoView = (VideoView) view.findViewById(R.id.videoView);
//            description = (TextView) view.findViewById(R.id.description);
        }
    }


    public RecyclerViewAdapterDataImage(Context mContext, ArrayList<DataPreview> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;


        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fragment_data_view, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DataPreview product = albumList.get(position);




        holder.desc.setText(product.getDescr());

        holder.desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // DescriptionDialog.viewpos=viewpagerpos;
                DescriptionDialog.dataPreview=product;
                new DescriptionDialog(mContext,null, holder.desc.getText().toString(),true);
            }
        });

        //imageView.setVisibility(View.VISIBLE);

        Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, product.getPath());

        holder.imageView.setImageBitmap(bitmap);

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
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);

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
