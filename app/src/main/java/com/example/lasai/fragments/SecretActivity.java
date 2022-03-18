package com.example.lasai.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lasai.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecretActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secret_activity);

        bottomNavigationView=findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        viewPager=findViewById(R.id.fragment_container);
        setUpAdapter(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setUpAdapter(ViewPager viewPager){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPageAdapter.addFragment(new EmergencyFragment());
        viewPageAdapter.addFragment(new MessageFragment());
        viewPageAdapter.addFragment(new ContactsFragment());
        viewPageAdapter.addFragment(new EducationalFragment());
        viewPager.setAdapter(viewPageAdapter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.d("DEBUG", "Item clicked"+item.getItemId());
            switch (item.getItemId()){
                case R.id.nav_emergency:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.nav_messages:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.nav_contacts:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.nav_education:
                    viewPager.setCurrentItem(3);
                    return true;
                default:
                    return false;
            }
        }
    };

}
