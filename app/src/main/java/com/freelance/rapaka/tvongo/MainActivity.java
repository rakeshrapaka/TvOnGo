package com.freelance.rapaka.tvongo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.freelance.rapaka.tvongo.util.Constants;
import com.freelance.rapaka.tvongo.view.slidemenu.SideMenu;
import com.freelance.rapaka.tvongo.view.slidemenu.SideMenuItem;


public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private SideMenu sideMenu;
    private SideMenuItem itemXtraCode;
    private SideMenuItem itemProfile;
    private SideMenuItem itemShopnServices;
    private SideMenuItem itemSearchShop;
    private SideMenuItem itemSettings;

    private int selectedScreen = 0;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedPreferences sharedpreferences = getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
        setUpMenu();
        int screenNum =  sharedpreferences.getInt(Constants.SCREEN,0);
        if(screenNum == 5){
            selectedScreen = 5;
            changeFragment(new SettingsFragment(), getString(R.string.settings_title),true);
        }else if( savedInstanceState == null ) {
            selectedScreen = 1;
            changeFragment(new QRCodeContainerFragment(),"",false);
        }else{
            switch(screenNum){
                case 1:
                    selectedScreen = 1;
                    changeFragment(new QRCodeContainerFragment(),"",false);
                    break;
                case 2:
                    selectedScreen = 2;
                    changeFragment(new ProfileFragment(), getString(R.string.profile_manage_title),true);
                    break;
                case 3:
                    selectedScreen = 3;
                    changeFragment(new ShopsnServicesFragment(), getString(R.string.commerces_title),true);
                    break;
                case 4:
                    selectedScreen = 4;
                    changeFragment(new StoreLocatorContainerFragment(), getString(R.string.storelocator_title),true);
                    break;
                default:
                    break;
            }
        }




    }

    private void setUpMenu() {

        // attach to current activity;
        sideMenu = new SideMenu(this);
        sideMenu.setUse3D(false);
        sideMenu.setBackground(R.drawable.background);
        sideMenu.attachToActivity(this);
        sideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        sideMenu.setScaleValue(0.6f);

        // create menu items;

        itemXtraCode     = new SideMenuItem(this, R.drawable.qrhomeicon,  getString(R.string.side_menu_xtracode));
        itemProfile  = new SideMenuItem(this,R.drawable.slide_profile, getString(R.string.side_menu_manageprofile) );
        itemShopnServices = new SideMenuItem(this,R.drawable.slide_commerces,getString(R.string.side_menu_shopsnservices));
        itemSearchShop = new SideMenuItem(this,R.drawable.slide_storelocator,getString(R.string.side_menu_searchshop));
        itemSettings = new SideMenuItem(this,  R.drawable.slide_settings, getString(R.string.side_menu_settings));

        itemXtraCode.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemShopnServices.setOnClickListener(this);
        itemSearchShop.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        sideMenu.setSwipeDirectionDisable(SideMenu.DIRECTION_RIGHT);

        sideMenu.addMenuItem(itemXtraCode, SideMenu.DIRECTION_LEFT);
        sideMenu.addMenuItem(itemProfile, SideMenu.DIRECTION_LEFT);
        sideMenu.addMenuItem(itemShopnServices, SideMenu.DIRECTION_LEFT);
        sideMenu.addMenuItem(itemSearchShop, SideMenu.DIRECTION_LEFT);
        sideMenu.addMenuItem(itemSettings, SideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        SharedPreferences sharedpreferences = getSharedPreferences(Constants.SHARED_PREF,
                Context.MODE_PRIVATE);
        String userName = sharedpreferences.getString("UserName","Xtra APP");
        sideMenu.setTitle(userName);
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sideMenu.openMenu(SideMenu.DIRECTION_LEFT);
            }
        });

    }

    public void disableSwipe(){
        sideMenu.setSwipeDirectionDisable(SideMenu.DIRECTION_LEFT);
    }

    public void enableSwipe(){
        sideMenu.setSwipeDirectionEnable();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return sideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        if (view == itemXtraCode){
            selectedScreen = 1;
            changeFragment(new QRCodeContainerFragment(),"",false);
        }else if (view == itemProfile){
            selectedScreen = 2;
            changeFragment(new ProfileFragment(), getString(R.string.profile_manage_title),true);
        }else if (view == itemShopnServices){
            selectedScreen = 3;
            changeFragment(new ShopsnServicesFragment(), getString(R.string.commerces_title),true);
        }else if (view == itemSearchShop){
            selectedScreen = 4;
            changeFragment(new StoreLocatorContainerFragment(), getString(R.string.storelocator_title),true);
        }else if (view == itemSettings){
            selectedScreen = 5;
            changeFragment(new SettingsFragment(), getString(R.string.settings_title),true);
        }

        sideMenu.closeMenu();
    }

    private SideMenu.OnMenuListener menuListener = new SideMenu.OnMenuListener() {
        @Override
        public void openMenu() {

        }

        @Override
        public void closeMenu() {

        }
    };

    public void changeFragment(Fragment targetFragment,String title,boolean isTitleVisbile){
        sideMenu.clearIgnoredViewList();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

        TextView fragmentTitle = (TextView)findViewById(R.id.title);

        fragmentTitle.setText(title);
        float size = 18;
        fragmentTitle.setTextSize(size);
        if(Build.VERSION.SDK_INT >= 23) {
            fragmentTitle.setTextColor(getColor(R.color.page_title_color));
        }else {
            fragmentTitle.setTextColor(ContextCompat.getColor(this,R.color.page_title_color));
        }

        if(isTitleVisbile){
            fragmentTitle.setVisibility(View.VISIBLE);
        }else{
            fragmentTitle.setVisibility(View.GONE);
        }


    }

    // What good method is to access resideMenu？
    public SideMenu getSideMenu(){
        return sideMenu;
    }

    @Override
    public void onBackPressed() {

    }

    protected void onSaveInstanceState(Bundle outState){

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        // etc.
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.SCREEN, selectedScreen);
        SharedPreferences sharedpreferences = getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(Constants.SCREEN,selectedScreen);
        editor.apply();
    }

    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


}

