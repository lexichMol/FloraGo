package Fragments;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentStateAdapter {
    List<Fragment> mListFragment = new ArrayList<>();
    List<String> mListFragmentTitle = new ArrayList<>();
    public ViewPageAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return mListFragment.size();
    }
    public void add (Fragment fragment, String title){
        mListFragment.add(fragment);
        mListFragmentTitle.add(title);
    }

    public TabLayoutMediator.TabConfigurationStrategy configurationTitle ()
    {
        TabLayoutMediator.TabConfigurationStrategy configuration = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(mListFragmentTitle.get(position));
            }
        };
        return configuration;
    }

}





















