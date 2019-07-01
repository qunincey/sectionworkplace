package qunincey.com.sectionwork.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.adapter.MyFragmentStatePagerAdapter;
import qunincey.com.sectionwork.fragment.BaseFragment;
import qunincey.com.sectionwork.fragment.ChartFragment;
import qunincey.com.sectionwork.fragment.HomeFragment;
import qunincey.com.sectionwork.fragment.MeFragment;
import qunincey.com.sectionwork.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.viewPager);

        ArrayList<BaseFragment> baseFragments = new ArrayList<>();
        baseFragments.add(new HomeFragment());
        baseFragments.add(new ChartFragment());
        baseFragments.add(new VideoFragment());
        baseFragments.add(new MeFragment());

        viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(),baseFragments));

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                navView.getMenu().getItem(position).setChecked(true);
            }
        });

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_chart:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_video:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_me:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };




}
