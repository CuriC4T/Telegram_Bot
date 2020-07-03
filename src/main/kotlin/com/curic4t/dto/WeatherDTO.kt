package com.curic4t.dto

import com.google.gson.annotations.SerializedName

//data class WeatherDTO(
//        @SerializedName("coord")
//        val coord: Coord,
//
//        @SerializedName("weather")
//        val weather: List<Weather>,
//
//        @SerializedName("base")
//        val base: String,
//
//        @SerializedName("main")
//        val main: Main,
//
//        @SerializedName("visibility")
//        val visibility: Int,
//
//        @SerializedName("wind")
//        val wind: Wind,
//
//        @SerializedName("clouds")
//        val clouds: Clouds,
//
//        @SerializedName("dt")
//        val dt: Int,
//
//        @SerializedName("sys")
//        val sys: Sys,
//
//        @SerializedName("timezone")
//        val timezone: Int,
//
//        @SerializedName("id")
//        val id: Int,
//
//        @SerializedName("name")
//        val name: String,
//
//        @SerializedName("cod")
//        val cod: Int
//
//
//)
//
//data class Coord(
//        @SerializedName("coord")
//        val lon: Double,
//
//        @SerializedName("lat")
//        val lat: Double
//)
//
//data class Weather(
//        @SerializedName("id")
//        val id: Int,
//
//        @SerializedName("main")
//        val main: String,
//
//        @SerializedName("description")
//        val description: String,
//
//        @SerializedName("icon")
//        val icon: String
//)
//
//data class Main(
//        @SerializedName("temp")
//        val temp: Double,
//
//        @SerializedName("feels_like")
//        val feels_like: Double,
//
//        @SerializedName("temp_min")
//        val temp_min: Double,
//
//        @SerializedName("temp_max")
//        val temp_max: Double,
//
//        @SerializedName("pressure")
//        val pressure: Double,
//
//        @SerializedName("humidity")
//        val humidity: Double
//)
//
//data class Wind(
//        @SerializedName("speed")
//        val speed: Double,
//
//        @SerializedName("deg")
//        val deg: Int
//)
//
//data class Clouds(
//        @SerializedName("all")
//        val all: Int
//)
//
//data class Sys(
//        @SerializedName("type")
//        val type: Int,
//
//        @SerializedName("id")
//        val id: Int,
//
//        @SerializedName("message")
//        val message: Double,
//
//        @SerializedName("country")
//        val country: String,
//
//        @SerializedName("sunrise")
//        val sunrise: Int,
//
//        @SerializedName("sunset")
//        val sunset: Int
//)
data class WeatherDTO(
        @SerializedName("response")
        val response: Response
)

data class Response(
        @SerializedName("header") val header: Header,
        @SerializedName("body") val body: Body
)

data class Header(
        @SerializedName("resultCode") val resultCode: String,
        @SerializedName("resultMsg") val resultMsg: String
)

data class Body(
        @SerializedName("dataType") val dataType: String,
        @SerializedName("items") val items:Items,
        @SerializedName("pageNo") val pageNo: Int,
        @SerializedName("numOfRows") val numOfRows: Int,
        @SerializedName("totalCount") val totalCount: Int


        )
data class Items(
        @SerializedName("item") val item: List<Item>
)
data class Item(
        @SerializedName("baseDate") val baseDate: String,
        @SerializedName("baseTime") val baseTime: String,
        @SerializedName("category") val category: String,
        @SerializedName("fcstDate") val fcstDate: String,
        @SerializedName("fcstTime") val fcstTime: String,
        @SerializedName("fcstValue") val fcstValue: String,
        @SerializedName("nx") val nx: Int,
        @SerializedName("ny") val ny: Int

)