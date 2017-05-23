package com.example.kunsubin.foody;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by kunsubin on 3/29/2017.
 */
//để thiết lập cuộn chuyển qua lại giữa các tab ăn gì với ở đâu
public class PagerAdapter extends FragmentStatePagerAdapter {

    ODau odau;
    AnGi angi;
    MainActivity mainActivity;

    public PagerAdapter(FragmentManager fm, MainActivity mainActivity) {
        super(fm);
        this.mainActivity=mainActivity;
        odau=new ODau(mainActivity);
        angi=new AnGi(mainActivity);
    }
    //trả ra fragment được chọn
    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        android.support.v4.app.Fragment frag=null;
        switch (position){
            case 0:
                frag=odau;
                break;
            default:
                frag= angi;
                break;
        }
        return  frag;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return "";
    }
}
