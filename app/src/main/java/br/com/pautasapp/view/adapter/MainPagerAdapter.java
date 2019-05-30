package br.com.pautasapp.view.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.pautasapp.model.enums.MainScreen;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private List<MainScreen> screens = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return screens.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return screens.size();
    }

    public void setItems(List<MainScreen> screens) {
        this.screens.clear();
        this.screens.addAll(screens);
        notifyDataSetChanged();
    }

    public List<MainScreen> getItems() {
        return screens;
    }

}
