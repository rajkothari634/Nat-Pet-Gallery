package com.example.nat_petgallery.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nat_petgallery.Adapters.RecyclerAdapterGallery;
import com.example.nat_petgallery.Classes.ImageData;
import com.example.nat_petgallery.Classes.MySingleTon;
import com.example.nat_petgallery.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends Fragment {

    List<ImageData> imgAddress;
    RecyclerView imageRecycler;
    RecyclerAdapterGallery recyclerAdapterGallery;
    String url;
    int page=1;

    public ImageFragment(String url){
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        imgAddress = new ArrayList<>();
        imageRecycler = getView().findViewById(R.id.imageRecycler);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        imageRecycler.setLayoutManager(linearLayoutManager);
        populateRecycler(url + "&page=" + page);
        RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if(totalItemCount==lastVisibleItemPosition+1){
                    populateRecycler(url + "&page=" + page);
                    page = page + 1;
                }
            }
        };
        imageRecycler.setOnScrollListener(recyclerViewOnScrollListener);
    }

    public void populateRecycler(String url){
        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int i=0;i<response.length();i++){
                                JSONObject imageInfo = response.getJSONObject(i);
                                ImageData imgdata = new ImageData();
                                imgdata.setImgName(imageInfo.getString("description"));
                                imgdata.setImgUrl(imageInfo.getJSONObject("urls").getString("small"));
                                imgAddress.add(imgdata);
                                LoadImageFromWebOperations(imgdata.getImgUrl(),imgAddress.size()-1);
                                //Log.i("response",student.toString());
                            }
                            if(page==1){
                                recyclerAdapterGallery = new RecyclerAdapterGallery(getActivity(), imgAddress);
                                imageRecycler.setAdapter(recyclerAdapterGallery);
                                page = page + 1;
                            }else{
                                recyclerAdapterGallery.notifyDataSetChanged();
                            }
                            Log.i("responsedata", String.valueOf(imgAddress.size()));
                        }catch (JSONException e){
                            Log.i("response",e.toString());
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.i("response","cancelled");
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void LoadImageFromWebOperations(String url, final int index) {

        final Bitmap[] btm = {null};
        Log.i("detection","start");
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        btm[0] = bitmap;
                        imgAddress.get(index).setBitmap(bitmap);
                        recyclerAdapterGallery.notifyItemChanged(index);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.i("detection","failed");
                    }
                });
        MySingleTon.getInstance(getActivity()).addToRequestQue(request);
    }
}
