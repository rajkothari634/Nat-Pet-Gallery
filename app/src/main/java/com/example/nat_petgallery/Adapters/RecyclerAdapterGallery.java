package com.example.nat_petgallery.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nat_petgallery.Classes.ImageData;
import com.example.nat_petgallery.R;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class RecyclerAdapterGallery extends RecyclerView.Adapter<RecyclerAdapterGallery.ViewHolder> {

    List<ImageData> imgAddress;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritemview,parent,false);
        return new ViewHolder(view);
    }

    public RecyclerAdapterGallery(Context context,List<ImageData> imgAddress){
        this.imgAddress = imgAddress;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageData imageData = imgAddress.get(position);
        holder.imgName.setText(imageData.getImgName());
        holder.imgContainer.setImageDrawable(LoadImageFromWebOperations(imageData.getImgUrl()));
    }

    @Override
    public int getItemCount() {
        return imgAddress.size();
    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable drawable = Drawable.createFromStream(is, "src name");
            return drawable;
        } catch (Exception e) {
            return null;
        }
    }
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
