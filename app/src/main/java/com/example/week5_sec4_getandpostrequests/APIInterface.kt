package com.example.week5_sec4_getandpostrequests

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("/custom-people/")
    fun getName(): Call<ArrayList<AddNameItem?>?>

    @POST("/custom-people/")
    fun postName(@Body userData: AddNameItem): Call<AddNameItem>

}