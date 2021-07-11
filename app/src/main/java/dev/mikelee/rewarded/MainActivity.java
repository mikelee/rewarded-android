package dev.mikelee.rewarded;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import dev.mikelee.rewarded.fragments.RewardFragment;
import dev.mikelee.rewarded.fragments.TodoFragment;
import dev.mikelee.rewarded.fragments.adapters.ViewPagerAdapter;
import dev.mikelee.rewarded.todo.Todo;
import dev.mikelee.rewarded.todo.TodoAdapter;
import dev.mikelee.rewarded.todo.TodoViewModel;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabs;
    TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabs = findViewById(R.id.tabs);

        setUpTabs();

        TodoAdapter todoAdapter = new TodoAdapter();
    }

    private void setUpTabs() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        adapter.addFragment(new TodoFragment(), "To Do");
        adapter.addFragment(new RewardFragment(), "Rewards");
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }
}