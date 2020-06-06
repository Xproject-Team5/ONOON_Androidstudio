package com.example.xproject

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface CamaraSerivice{
    @Multipart
    @POST("/app_pictures/")
    fun post_Porfile_Requestart(
        @Part("userId") userId: String,
        @Part imageFile : MultipartBody.Part): Call<String>
        //@Field("userId") userId: String,
        //@Field imageFile : MultipartBody.Part): Call<String>
}