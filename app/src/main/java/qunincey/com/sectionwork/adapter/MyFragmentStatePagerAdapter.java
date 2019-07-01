package qunincey.com.sectionwork.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import qunincey.com.sectionwork.fragment.BaseFragment;


public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<BaseFragment> data;

    public MyFragmentStatePagerAdapter(FragmentManager fm,ArrayList<BaseFragment> data) {
        super(fm);
        this.data = data;
    }


    @Override
    public Fragment getItem(int i) {
        return data.get(i);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
