package com.curic4t.udp

import com.curic4t.telegram.TelegramBot
import jdk.nashorn.internal.objects.ArrayIterator
import java.net.DatagramPacket
import java.net.DatagramSocket

class Server(port:Int, telegramBot: TelegramBot){
    private var datagramSocket:DatagramSocket = DatagramSocket(port)
    private var listQueue:ArrayList<String>?=null
    private var byteArray:ByteArray = ByteArray(128)

    private var telegramBot:TelegramBot = telegramBot

    public fun service(){
        var msg :String
        telegramBot.settingBot()
        while(true){
            var datagramPacket:DatagramPacket = DatagramPacket(byteArray,byteArray.size)

            datagramSocket?.receive(datagramPacket)
            msg = byteArray.toString()

            Thread.sleep(1000)

        }
    }
}
