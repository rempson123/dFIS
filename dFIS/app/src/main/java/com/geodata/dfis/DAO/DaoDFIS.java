package com.geodata.dfis.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.geodata.dfis.Model.DamageReport;
import com.geodata.dfis.Model.RegisterInfo;

import java.util.List;

/**
 * Created by jrvicedo on 5/22/2019.
 */
@Dao
public interface DaoDFIS {

    @Insert
    void addUser(RegisterInfo register);

    @Insert
    void addDamageRecord(DamageReport damageReport);

    @Query("SELECT * FROM RegisterInfo where EMAIL = :Email and PASSWORD=:Password")
    RegisterInfo getUser(String Email, String Password);

    @Query("select * FROM RegisterInfo")
    List <RegisterInfo> getAllRegisterInfo();

    @Query("select * FROM RegisterInfo")
    Cursor getRegisterInfo();

    @Query("select * FROM DamageReport")
    List <DamageReport> getDamageRecord();

    @Query("SELECT * FROM DamageReport WHERE id = :id")
    List<DamageReport> getDamageReport(String id);

    @Query("select * from  DamageReport where STATUS ='SAVE' ORDER BY id DESC,null")
    Cursor getDamageSave();

    @Query("select * from  DamageReport where STATUS ='SENT' ORDER BY id DESC,null")
    Cursor getDamageSend();

    @Update
    void updateDamageReport(DamageReport damageReport);

}
