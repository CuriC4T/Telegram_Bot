package com.curic4t.util

import kotlin.random.Random

class UtilFunction {
    fun getChoose(list:List<String>):String{
        if(list.size==1)return "Check Parameter"
        else{
            // ㅁㅁ ㅁㅁ ㅃ
            var random = Random( System.currentTimeMillis())
            var randomIndex=random.nextInt(list.size-1)+1
            return list.get(randomIndex)
        }


    }
}