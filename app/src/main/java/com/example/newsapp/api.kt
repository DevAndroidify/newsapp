package com.example.newsapp

import retrofit2.Call
import retrofit2.Callback

import retrofit2.http.GET
import retrofit2.http.Query


const val baseurl="https://newsapi.org/"
const val apikey="59c0c82103b54256b5dae77350e0c251"
interface api {
    @GET("v2/top-headlines?apiKey=$apikey")
    fun getdata(@Query("country")country:String,@Query("page")page:Int):Call<news>
}