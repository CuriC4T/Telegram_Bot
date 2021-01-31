package com.curic4t.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
class MainController{

    @GetMapping("/index")
    fun index1():String = "hi"

    @GetMapping(value = ["/"]) //@RequestMapping(value="/",method=RequestMethod.GET)
    fun hello(): String? {
        return "Hello World!!"
    }

//    @GetMapping(value = ["/weather"]) //@RequestMapping(value="/",method=RequestMethod.GET)
//    fun getWeather(@RequestParam(value = "id") id:String)

    @GetMapping("/food")
    @ResponseBody
    fun getFoos(@RequestParam id: List<String?>): String? {
        return "IDs are $id"
    }

}