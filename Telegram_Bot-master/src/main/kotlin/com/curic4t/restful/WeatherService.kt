package com.curic4t.restful

import com.curic4t.dto.WeatherDTO
import retrofit2.Call;
import retrofit2.http.*
//
//interface WeatherService {
//    @Headers("accept: application/json",
//
//            "content-type: application/json")
//    @GET("data/2.5/weather")
//    fun getWeather(
//            @Query("zip") zip: Int,
//            @Query("zip") countryCode: Int,
//            @Query("appid") API_KEY: String)
//            : Call<WeatherDTO>
//
//}

interface WeatherService{
//    @Headers("accept: application/json",
//            "content-type: application/json")
@Headers("Content-Type: application/x-www-form-urlencoded")
@GET("1360000/VilageFcstInfoService/getVilageFcst")
    fun getWeather(
            @Query("serviceKey") serviceKey: String,
            @Query("pageNo") pageNo: Int,
            @Query("numOfRows") numOfRows: Int,
            @Query("dataType") dataType: String,
            @Query("base_date") base_date: String,
            @Query("base_time") base_time: String,
            @Query("nx") nx: Int,
            @Query("ny") ny: Int)
            : Call<WeatherDTO>
}