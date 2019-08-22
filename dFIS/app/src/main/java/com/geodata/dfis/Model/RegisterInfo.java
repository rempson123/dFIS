package com.geodata.dfis.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jrvicedo on 5/22/2019.
 */
@Entity(tableName = "RegisterInfo")
public class RegisterInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "FIRSTNAME")
    private  String FirstName;
    @ColumnInfo(name = "MIDDLENAME")
    private String MiddleName;
    @ColumnInfo(name = "LASTNAME")
    private String LastName;
    @ColumnInfo(name = "BIRTHDATE")
    private String Birthdate;
    @ColumnInfo(name = "GENDER")
    private String Gender;

    @ColumnInfo(name = "MOBILE")
    private String Mobile;
    @ColumnInfo(name = "EMAIL")
    private String Email;
    @ColumnInfo(name = "PASSWORD")
    private String Password;

    @ColumnInfo(name = "STREET")
    private String Street;
    @ColumnInfo(name = "BARANGAY")
    private String Barangay;
    @ColumnInfo(name = "TOWNORCITY")
    private String TownorCity;
    @ColumnInfo(name = "ZIPCODE")
    private String Zipcode;
    @ColumnInfo(name = "PROVINCE")
    private String Province;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        this.MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.Birthdate = birthdate;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        this.Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        this.Street = street;
    }

    public String getBarangay() {
        return this.Barangay;
    }

    public void setBarangay(String barangay) {
        this.Barangay = barangay;
    }

    public String getTownorCity() {
        return this.TownorCity;
    }

    public void setTownorCity(String townorCity) {
        this.TownorCity = townorCity;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        this.Zipcode = zipcode;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        this.Province = province;
    }

}
