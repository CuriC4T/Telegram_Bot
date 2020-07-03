package com.curic4t.restful

import com.curic4t.dto.WeatherDTO
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder
import java.net.URLEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit


//
//class SearchWeather() {
//    val weatherService: WeatherService
//    val API_KEY: String = "c494f5686f7ffd79ca1b212f32469cd6"
//
//    init {
//        val retrofit: Retrofit = Retrofit.Builder()
//                .baseUrl("http://api.openweathermap.org/")
//                .addConverterFactory(GsonConverterFactory.create()
//                ).build()
//        weatherService = retrofit.create(WeatherService::class.java)
//
//    }
//
//    fun retrofitWeather(zip: Int,countryCode:Int): Call<WeatherDTO> {
//        //
//        var contesnt: Call<WeatherDTO> = weatherService.getWeather(zip,countryCode, API_KEY)
//        return contesnt
//    }
//}

class SearchWeather() {
    val weatherService: WeatherService
    val API_KEY: String =
            "Oe2eUSXxdo2u2M1DYk7gBvyZ6Kq3PEwtHpSq17ZhPY0F8p%2FcavEl%2B1imSCINJL640sWfzxFJ8458XWtpiDY1Zw%3D%3D"
    val API_KEY_Encode: String = URLEncoder.encode(API_KEY, "utf-8")

    init {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)
                ).build()
        weatherService = retrofit.create(WeatherService::class.java)

    }

    //    @Query("serviceKey") serviceKey: String
//    @Query("ageNo") ageNo: Int
//    @Query("numOfRows") numOfRows: Int
//    @Query("dataType") dataType: String
//    @Query("base_date") base_date: String
//    @Query("base_time") base_time: String
//    @Query("nx") nx: Int
//    @Query("ny") ny: Int
    fun retrofitWeather(pageNum: Int, nx: Int, ny: Int): Call<WeatherDTO> {
        //
        val data = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val current = data.format(formatter)
        println(current)
        val time = "0200"

        var contesnt: Call<WeatherDTO> = weatherService.getWeather(
                URLDecoder.decode(API_KEY, "utf-8"),
                pageNum,
                19,
                "JSON",
                current,
                time,
                nx,
                ny
        )
        return contesnt
    }

}