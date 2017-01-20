package com.example.m.designdemo;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Choreographer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.m.designdemo.R.drawable.ic_attachment;
import static com.example.m.designdemo.R.drawable.ic_emoticon;
import static com.example.m.designdemo.R.drawable.ic_image;

public class MainActivity extends AppCompatActivity {

    /* layout for drawer hamburger menu*/
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionbar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            //Hamburger Drawer
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){
                menuItem.setChecked(true);

                // * closDrawer after selection or no???
                //mDrawerLayout.closeDrawers();

                Toast.makeText(MainActivity.this, menuItem.getTitle(),Toast.LENGTH_LONG).show();
                return true;

            }
        });

        //Tabbed Activity
        DesignDemoPagerAdapter pagerAdapter = new DesignDemoPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    //Hamburger Drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle action bar item clicks here
        //Action bar will automatically handle clicks on
            // home/up button, as long as you specify a parent
            // activity in the manifest ( AndroidManifest.xml)
        int itemId = item.getItemId();

        switch (itemId){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            //From example may be typo
            /*case R.id.action_settings:
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }

    //*DesignDemoFragment
    //used for Tabbed Activity
    public static class DesignDemoFragment extends Fragment {
        private static final String TAB_POSISTION = "tab_position";

        public DesignDemoFragment(){}

        public static DesignDemoFragment newInstance(int tabPosition){
            DesignDemoFragment fragment = new DesignDemoFragment();
            Bundle args = new Bundle();
            args.putInt(TAB_POSISTION, tabPosition);
            fragment.setArguments(args);
            return fragment;
        }

        //Tabbed Activity
        //Fragment View being set up
        //each fragment has its own tab_position
        // onCreateView is created when switched between fragments
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            Bundle args = getArguments();
            int tabPosition = args.getInt(TAB_POSISTION);

            //TODO add/inflate the cards/codes here
            //Strings created and stored here
            ArrayList<String> itemNumAndPos = new ArrayList<String>();
            for(int i=0;i<50;i++){

                itemNumAndPos.add("Tab # " + tabPosition + " - item # " + i);

            }

            //storage for the icons on the cards
            //add various icons to iconArray
            ArrayList<Object> iconArray = new ArrayList<>();
            iconArray.add(ic_emoticon);
            iconArray.add(ic_attachment);
            iconArray.add(ic_image);

            View view = inflater.inflate(R.layout.fragment_list_view, container, false);
            RecyclerView recyclerView =(RecyclerView) view.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                //send the array of item numbers and icons
                // maybe use a Generic array
            recyclerView.setAdapter(new DesignDemoRecyclerAdapter(itemNumAndPos, iconArray));
            //recyclerView.setAdapter(new DesignDemoRecyclerAdapter(itemNumAndPos));

            return view;
        }

    }

    /* DesignDemoPagerAdapter
        used for Tabbed Activity
    */
    public class DesignDemoPagerAdapter extends FragmentStatePagerAdapter {
        public DesignDemoPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        /*getItem
            returns the Fragment that will be placed on the view page
             */
        @Override
        public Fragment getItem(int position){
            return DesignDemoFragment.newInstance(position);
        }

        //returns number of pages
        @Override
        public int getCount(){
            //three tabs
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return "Tab " + position;
        }
    }

}//Class




















