package dev.mikelee.rewarded;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import dev.mikelee.rewarded.fragments.RewardFragment;
import dev.mikelee.rewarded.fragments.TodoFragment;
import dev.mikelee.rewarded.fragments.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabs  = findViewById(R.id.tabs);

        setUpTabs();
    }

    private void setUpTabs() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        adapter.addFragment(new TodoFragment(), "To Do");
        adapter.addFragment(new RewardFragment(), "Rewards");
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }
}