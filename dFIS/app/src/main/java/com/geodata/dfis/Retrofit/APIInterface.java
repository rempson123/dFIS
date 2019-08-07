package com.geodata.dfis.Retrofit;


import com.geodata.dfis.Model.RegisterInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by dnherrera on 12/6/2017.
 */

public interface APIInterface {

    @FormUrlEncoded
    @POST("api/Damage/")
    Call<RegisterInfo> createReports(@Field("fullName") String fullname,
                                     @Field("damageId") String damageid,
                                     @Field("damageType") String damagetype,
                                     @Field("contactNo") String contactno,
                                     @Field("description") String description,
                                     @Field("address") String address,
                                     @Field("xCoordinate") String xcoor,
                                     @Field("yCoordinate") String ycoor,
                                     @Field("imageString") String imagestring,
                                     @Field("dateAndTime") String dateandtime);
}
