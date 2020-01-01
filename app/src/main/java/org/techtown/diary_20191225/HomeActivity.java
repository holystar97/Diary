package org.techtown.diary_20191225;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

  TabLayout tabLayout;
  HomeFragment homeFragment;
  DailyFragment dailyFragment;
  ShareFragment shareFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        homeFragment=new HomeFragment();
        dailyFragment=new DailyFragment();
        shareFragment=new ShareFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        tabLayout=findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos=tab.getPosition();
                switch (pos){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,dailyFragment).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,shareFragment).commit();
                        break;


                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });












    }
}
