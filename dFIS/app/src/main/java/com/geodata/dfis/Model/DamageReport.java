package com.geodata.dfis.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by rdulitin on 31/05/2019.
 */
@Entity(tableName = "DamageReport")
public class DamageReport {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "FULLNAME")
    private  String fullName;

    @ColumnInfo(name = "DAMAGEID")
    private String damageId;

    @ColumnInfo(name = "DAMAGETYPE")
    private String damageType;

    @ColumnInfo(name = "CONTACTNO")
    private String contactNo;

    @ColumnInfo(name = "DESCRIPTION")
    private String description;

    @ColumnInfo(name = "STATUS")
    private String status;

    @ColumnInfo(name = "ADDRESS")
    private  String address;

    @ColumnInfo(name = "XCOORDINATES")
    private String XCoordinates;

    @ColumnInfo(name = "YCOORDINATES")
    private String YCoordinates;

    @ColumnInfo(name = "IMAGEPICTURE")
    private byte[] imagePic;

    @ColumnInfo(name = "IMAGESTRING")
    private String imageString;

    @ColumnInfo(name = "DATEANDTIME")
    private String dateAndTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public byte[] getImagePic() {
        return imagePic;
    }

    public void setImagePic(byte[] imagePic) {
        this.imagePic = imagePic;
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
