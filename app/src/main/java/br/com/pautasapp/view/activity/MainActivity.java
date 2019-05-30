package br.com.pautasapp.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.pautasapp.R;
import br.com.pautasapp.model.enums.MainScreen;
import br.com.pautasapp.view.adapter.MainPagerAdapter;
import br.com.pautasapp.view.base.BaseActivity;
import br.com.pautasapp.view.base.BaseToolbarActivity;
import butterknife.BindView;

public class MainActivity extends BaseToolbarActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private MainPagerAdapter mainPagerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.setItems(MainScreen.asList());

        MainScreen defaultScreen = MainScreen.OPENED;
        scrollToScreen(defaultScreen);
        selectBottomNavigationViewMenuItem(defaultScreen.getIdMenu());

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        viewPager.setAdapter(mainPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                MainScreen selectedScreen = mainPagerAdapter.getItems().get(position);
                selectBottomNavigationViewMenuItem(selectedScreen.getIdMenu());
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        MainScreen screen = MainScreen.getMainScreenForMenuItem(menuItem.getItemId());
        if (screen != null) {
            scrollToScreen(screen);
            return true;
        }
        return false;
    }

    private void scrollToScreen(MainScreen mainScreen) {
        int screenPosition = mainPagerAdapter.getItems().indexOf(mainScreen);
        if (screenPosition != viewPager.getCurrentItem())
            viewPager.setCurrentItem(screenPosition);
    }

    private void selectBottomNavigationViewMenuItem(int menuItemId) {
        bottomNavigationView.setOnNavigationItemSelectedListener(null);
        bottomNavigationView.setSelectedItemId(menuItemId);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

}
