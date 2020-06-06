package com.example.xproject

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface AddfaceService{
    @Multipart
    @POST("/app_addface/")
    fun requestAddface(
        @Part("userId") userId:String,
        @Part imageFile : MultipartBody.Part): Call<String>
}



