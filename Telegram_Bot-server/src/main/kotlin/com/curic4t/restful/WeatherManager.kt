package com.curic4t.restful

import com.curic4t.dto.Response
import com.curic4t.dto.WeatherDTO
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call

//
//class WeatherManager() {
//    val api: SearchWeather
//    var weather: JSONArray = JSONArray()
//    var main_weather = ""
//    var description = ""
//
//    var temp: Double = 0.0
//    var temp_min: Double = 0.0
//    var temp_max: Double = 0.0
//    var humidity: Double = 0.0
//
//    init {
//        api = SearchWeather()
//    }
//
//    //
//    fun getWeatherData(): WeatherDTO? {
//        val call = api.retrofitWeather(85020, 24)
////1835847
//        val response = call.execute()
//        var content: WeatherDTO? = null;
//        if (response.isSuccessful) {
//            content = response.body()
//        }
//        return content
//    }
//
//    fun reFormWeather(content: WeatherDTO?): String {
//        var jObject: JSONObject = JSONObject(content)
//        weather = jObject.getJSONArray("weather")
//        var jsonarray = weather.getJSONObject(0)
//        main_weather = jsonarray.getString("main")
//        description = jsonarray.getString("description")
//
//        var main = jObject.getJSONObject("main")
//        temp = (main.getDouble("temp") - 273.15)
//        temp = String.format("%.2f", temp).toDouble()
//        temp_min = (main.getDouble("temp_min") - 273.15)
//        temp_min = String.format("%.2f", temp_min).toDouble()
//        temp_max = (main.getDouble("temp_max") - 273.15)
//        temp_max = String.format("%.2f", temp_max).toDouble()
//        humidity = main.getDouble("humidity")
//
//        return """
//            ----서울 날씨----
//            날씨: $main_weather
//            설명: $description
//            ----------------
//            온도: $temp 도
//            최저온도: $temp_min 도
//            최고온도: $temp_max 도
//            습도: $humidity %
//        """.trimIndent()
//    }
//}
class WeatherManager() {
    val api: SearchWeather
    lateinit var call: Call<WeatherDTO>
    lateinit var response : retrofit2.Response<WeatherDTO>

    init {
        api = SearchWeather()
    }

    fun getWeatherData(pageNum:Int,nx: Int, ny: Int): WeatherDTO? {
        call = api.retrofitWeather(pageNum,nx, ny)
        println(call.request().url().toString())
        response = call.execute()

        var content: WeatherDTO? = null;
        if (response.isSuccessful) {
            content = response.body()
        } else {
            println("null")
        }

        return content

    }
}
/*
     * 항목값	항목명	단위
     * POP	강수확률	 %
     * PTY	강수형태	코드값
     * R06	6시간 강수량	범주 (1 mm)
     * REH	습도	 %
     * S06	6시간 신적설	범주(1 cm)
     * SKY	하늘상태	코드값
     * T3H	3시간 기온	 ℃
     * TMN	아침 최저기온	 ℃
     * TMX	낮 최고기온	 ℃
     * UUU	풍속(동서성분)	 m/s
     * VVV	풍속(남북성분)	 m/s
     * WAV	파고	 M
     * VEC	풍향	 m/s
     * WSD	풍속	1
*/

