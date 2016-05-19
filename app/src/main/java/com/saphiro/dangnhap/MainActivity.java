package com.saphiro.dangnhap;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    Button mainlogin;
    Button mainregister;

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

//        mainlogin = (Button) findViewById(R.id.mainlogin);
//        mainregister = (Button) findViewById(R.id.mainregister);
//
//        mainlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment1 f1 = new Fragment1();
//                Bundle bundle = new Bundle();
//                bundle.putString("user", "1st Fragment");
//                f1.setArguments(bundle);
//
//                addFragment(f1, true);
//            }
//        });
//
//        mainregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment2 f2 = new Fragment2();
//                addFragment(f2, true);
//            }
//        });

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if (position == 0)
                return new Fragment1();
            else
                return new Fragment2();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            if (position == 0)
                return getString(R.string.login);
            else
                return getString(R.string.register);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        finish();
    }

    //    private void addFragment(Fragment fragment, boolean b) {
//
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.container, fragment);
//        transaction.commit();
//    }
}
