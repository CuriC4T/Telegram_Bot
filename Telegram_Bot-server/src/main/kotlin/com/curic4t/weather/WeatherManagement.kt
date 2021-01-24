package com.curic4t.weather

import com.curic4t.dto.WeatherDTO
import com.curic4t.restful.WeatherManager
import org.json.JSONArray
import org.json.JSONObject

class WeatherManagement {
    val weatherManager: WeatherManager = WeatherManager()

    var item: JSONArray = JSONArray()
    var TMN: String? = null
    var TMX: String? = null
    var POP: MutableList<String> = mutableListOf<String>()
    var T3H: MutableList<String> = mutableListOf<String>()
    var content: WeatherDTO? = null
    fun getWeatherForTelegram(where: String, nx: Int, ny: Int): String {

        //todo
        //get x,y by where

        for (pageNum in 1..4) {
            content = weatherManager.getWeatherData(pageNum, 58, 125)
            reFormWeather(content)
        }
        content = null

        if (T3H.size == 7 && POP.size == 7) {
            return """
            --------$where 날씨--------
                아침최저기온 : $TMN°C
                낮최고기온   : $TMX°C
            ---------------------------
                            
                         기온      강수확률     
            06시     ${T3H[0]}°C      ${POP[0]}%
            09시     ${T3H[1]}°C      ${POP[0]}%
            12시     ${T3H[2]}°C      ${POP[0]}%
            15시     ${T3H[3]}°C      ${POP[0]}%
            18시     ${T3H[4]}°C      ${POP[0]}%
            21시     ${T3H[5]}°C      ${POP[0]}%
            24시     ${T3H[6]}°C      ${POP[0]}%
        """.trimIndent()
        } else {
            return "ERROR"
        }
    }

    fun reFormWeather(content: WeatherDTO?) {


        var jObject: JSONObject = JSONObject(content)
        var response: JSONObject = jObject.getJSONObject("response")
        var header: JSONObject = response.getJSONObject("header")
        var body: JSONObject = response.getJSONObject("body")

        var resultCode = header.getString("resultCode")

        if (resultCode.compareTo("00") == 0) {
            var temp: JSONObject

            var baseDate: String
            var baseTime: String
            var category: String
            var fcstDate: String
            var fcstTime: String
            var fcstValue: String
            var nx: Int
            var ny: Int
            var numOfRows = body.getInt("numOfRows")
            var items = body.getJSONObject("items")
            item = items.getJSONArray("item")
            println(numOfRows)



            for (i in 0 until numOfRows) {

                println(i)

                temp = item.getJSONObject(i)
                fcstTime = temp.getString("fcstTime")
                category = temp.getString("category")
                fcstValue = temp.getString("fcstValue")
                if(fcstTime.compareTo("0300")==0)break

                when (category) {
                    "TMN" -> {
                        TMN = fcstValue
                    }
                    "TMX" -> {
                        TMX = fcstValue
                    }
                    "POP" -> {
                        POP.add(fcstValue)
                    }
                    "T3H" -> {
                        T3H.add(fcstValue)
                    }

                }


            }

            TMX=TMX ?: T3H.maxBy { it }
            TMN=TMN ?: T3H.minBy { it }
        }

    }

    fun resetComponents() {
        item = JSONArray()
        TMN = null
        TMX = null
        POP = mutableListOf()
        T3H = mutableListOf()
        content = null
    }

}

