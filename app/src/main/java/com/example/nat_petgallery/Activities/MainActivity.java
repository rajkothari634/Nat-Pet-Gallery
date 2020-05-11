package com.example.nat_petgallery.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nat_petgallery.Classes.MySingleTon;
import com.example.nat_petgallery.Fragments.NatureFragment;
import com.example.nat_petgallery.Fragments.PetFragment;
import com.example.nat_petgallery.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // TextView responseText;
    ViewPager viewPager;
    TabLayout tabLayout;
    PetFragment petFragment;
    NatureFragment natureFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        //responseText = findViewById(R.id.response);

        String url = "https://api.unsplash.com/collections/1580860/photos/?client_id=9GFLmPQY7sbfnE2QSXJdgQjgKlRh6OZRvFkugRxYjkE";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //responseText.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        //responseText.setText("Response: " + error);
                    }
                });
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        MySingleTon.getInstance(this).addToRequestQue(jsonObjectRequest);
    }
    class MainFragsPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragments = new ArrayList<>();

        public MainFragsPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(addRequestFragment);
            fragments.add(noticesFragment);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

    }
}
