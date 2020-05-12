package com.example.nat_petgallery.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.nat_petgallery.Classes.ImageData;
import com.example.nat_petgallery.Classes.MySingleTon;
import com.example.nat_petgallery.R;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class RecyclerAdapterGallery extends RecyclerView.Adapter<RecyclerAdapterGallery.ViewHolder> {

    List<ImageData> imgAddress;
    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritemview,parent,false);
        return new ViewHolder(view);
    }

    public RecyclerAdapterGallery(Context context,List<ImageData> imgAddress){
        this.imgAddress = imgAddress;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageData imageData = imgAddress.get(position);
        holder.imgName.setText(imageData.getImgName());
        if(imageData.getBitmap() != null)
            holder.imgContainer.setImageBitmap(imageData.getBitmap());
        else
            Log.i("checkempty",position + "  require");

    }

    @Override
    public int getItemCount() {
        return imgAddress.size();
    }
//    public void LoadImageFromWebOperations(String url, final int index) {
//
//        final Bitmap[] btm = {null};
//        Log.i("detection","start");
//        ImageRequest request = new ImageRequest(url,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//                        btm[0] = bitmap;
//                        imgAddress.get(index).setBitmap(bitmap);
//                    }
//                }, 0, 0, null,
//                new Response.ErrorListener() {
//                    public void onErrorResponse(VolleyError error) {
//                       Log.i("detection","failed");
//                    }
//                });
//
//        MySingleTon.getInstance(this.context).addToRequestQue(request);
//
//
//
//// Access the RequestQueue through your singleton class.
//
//    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgContainer;
        public TextView imgName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContainer = itemView.findViewById(R.id.imageContainer);
            imgName = itemView.findViewById(R.id.imageName);
        }
    }
}
