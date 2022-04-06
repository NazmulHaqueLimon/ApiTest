package com.example.apitest.network
import retrofit2.http.*

interface AuthApi : BaseApi {

    @GET("UserInformation/GenerateOtp")
     fun generateOtp2(
        @Field("applicationId")id:Int,
        @Field("contactNo")contact:String
    ): OtpResponce


    @GET("/UserInformation/GenerateOtp")
     fun generateOtp(
        @Query("applicationId")id:Int,
        @Query("contactNo")contact:String
    ): OtpResponce

}