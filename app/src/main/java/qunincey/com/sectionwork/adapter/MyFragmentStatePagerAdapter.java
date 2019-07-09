package qunincey.com.sectionwork.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

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
