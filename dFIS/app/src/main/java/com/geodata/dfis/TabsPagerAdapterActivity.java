package com.geodata.dfis;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.geodata.dfis.Fragments.All;
import com.geodata.dfis.Fragments.Damage_Buildings;
import com.geodata.dfis.Fragments.Damage_Hard;
import com.geodata.dfis.Fragments.Damage_Soft;

/**
 * Created by jrvicedo on 5/21/2019.
 */

public class TabsPagerAdapterActivity extends FragmentStatePagerAdapter {

    String[] Title = new String [] {"All","Damage Buildings","Damage HARD","DAMAGE SOFT"};
    Integer tabnumber = 4;
    public TabsPagerAdapterActivity(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  Title[position];
    }

    @Override
    public Fragment getItem(int position) {

            switch (position){
                case 0:
                    All all = new All();
                    return all;
                case  1:
                    Damage_Buildings damage_buildings = new Damage_Buildings();
                    return damage_buildings;
                case 2:
                    Damage_Hard damage_hard = new Damage_Hard();
                    return damage_hard;
                case 3:
                    Damage_Soft damage_soft = new Damage_Soft();
                    return damage_soft;
            }
        return  null;
    }

    @Override
    public int getCount() {
        return tabnumber;
    }
}
