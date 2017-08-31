package com.jimmy.www.viewpageranalyze;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> listFragment = new ArrayList<>();

    ViewPagerAnalyze viewPagerAnalyze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPagerAnalyze = (ViewPagerAnalyze) findViewById(R.id.viewpager);


        listFragment.add(ViewPagerFragment.newInstance("TextViewFirst"));
        listFragment.add(ViewPagerFragment.newInstance("TextViewSecond"));
        listFragment.add(ViewPagerFragment.newInstance("TextViewThirst"));

        viewPagerAnalyze.setAdapter(new Adapter(getSupportFragmentManager()));
    }


    private class Adapter extends SFragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }
    }
}
