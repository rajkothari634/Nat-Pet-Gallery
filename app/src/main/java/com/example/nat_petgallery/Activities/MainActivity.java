package com.example.nat_petgallery.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.nat_petgallery.Fragments.ImageFragment;
import com.example.nat_petgallery.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // TextView responseText;
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageFragment petFragment;
    ImageFragment natureFragment;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.image);
        petFragment = new ImageFragment("https://api.unsplash.com/collections/139386/photos/?client_id=9GFLmPQY7sbfnE2QSXJdgQjgKlRh6OZRvFkugRxYjkE");
        natureFragment = new ImageFragment("https://api.unsplash.com/collections/1580860/photos/?client_id=9GFLmPQY7sbfnE2QSXJdgQjgKlRh6OZRvFkugRxYjkE");
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        //responseText = findViewById(R.id.response);

        viewPager.setAdapter(new MainFragsPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);

        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    class MainFragsPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragments = new ArrayList<>();

        public MainFragsPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(petFragment);
            fragments.add(natureFragment);
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
