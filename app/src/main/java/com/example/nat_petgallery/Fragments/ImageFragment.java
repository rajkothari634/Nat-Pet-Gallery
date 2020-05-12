package com.example.nat_petgallery.Fragments;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nat_petgallery.Adapters.RecyclerAdapterGallery;
import com.example.nat_petgallery.Classes.ImageData;
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
        imageRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        populateRecycler(url);
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
                                //Log.i("response",student.toString());
                            }
                            recyclerAdapterGallery = new RecyclerAdapterGallery(getActivity(), imgAddress);
                            imageRecycler.setAdapter(recyclerAdapterGallery);
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
}
