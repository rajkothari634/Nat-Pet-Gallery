package com.example.nat_petgallery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nat_petgallery.R;

import java.util.HashMap;

public class RecyclerAdapterGallery extends RecyclerView.Adapter<RecyclerAdapterGallery.ViewHolder> {

    HashMap<String,String> imgAddress;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritemview,parent,false);
        return new ViewHolder(view);
    }

    public RecyclerAdapterGallery(Context context,HashMap<String,String> imgAddress){
        this.imgAddress = imgAddress;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        
    }

    @Override
    public int getItemCount() {
        return imgAddress.size();
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
