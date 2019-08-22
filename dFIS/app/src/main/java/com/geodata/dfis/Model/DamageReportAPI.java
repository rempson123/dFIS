package com.geodata.dfis.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rdulitin on 07/08/2019.
 */


public class DamageReportAPI {

    @SerializedName("fullName")
    private  String fullName;

    @SerializedName("damageId")
    private String damageId;

    @SerializedName("damageType")
    private String damageType;

    @SerializedName("contactNo")
    private String contactNo;

    @SerializedName("description")
    private String description;

    @SerializedName("AssessmentID")
    private String status;

    @SerializedName("address")
    private  String address;

    @SerializedName("xCoordinate")
    private String XCoordinates;

    @SerializedName("yCoordinate")
    private String YCoordinates;


    @SerializedName("imageString")
    private String imageString;

    @SerializedName("dateAndTime")
    private String dateAndTime;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDamageId() {
        return damageId;
    }

    public void setDamageId(String damageId) {
        this.damageId = damageId;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getXCoordinates() {
        return XCoordinates;
    }

    public void setXCoordinates(String XCoordinates) {
        this.XCoordinates = XCoordinates;
    }

    public String getYCoordinates() {
        return YCoordinates;
    }

    public void setYCoordinates(String YCoordinates) {
        this.YCoordinates = YCoordinates;
    }


    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
