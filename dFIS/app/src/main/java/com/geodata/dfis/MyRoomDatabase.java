package com.geodata.dfis;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.geodata.dfis.DAO.DaoDFIS;
import com.geodata.dfis.Model.DamageReport;
import com.geodata.dfis.Model.RegisterInfo;

/**
 * Created by jrvicedo on 5/22/2019.
 */


@Database(entities = {RegisterInfo.class, DamageReport.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract DaoDFIS daoDFIS();

}
