package com.mfeldsztejn.rxjavatest.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Person;
import com.mfeldsztejn.rxjavatest.dto.StarShip;
import com.mfeldsztejn.rxjavatest.main.fragments.BaseListFragment;
import com.mfeldsztejn.rxjavatest.main.interfaces.OnItemClickListener;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager, true);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onPersonSelected(Person person) {
        Toast.makeText(this, person.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStarShipSelected(StarShip starShip) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if (viewPager == null) {
            return;
        }
        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
        if (page != null) {
            ((BaseListFragment) page).scrollToTop();
        }
    }
}
