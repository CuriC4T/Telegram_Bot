package com.curic4t.udp

import com.curic4t.main.BOT_NAME
import com.curic4t.main.TOKEN
import com.curic4t.telegram.TelegramBot
import java.net.DatagramPacket
import java.net.DatagramSocket

class Server(port: Int) {
    private var datagramSocket: DatagramSocket = DatagramSocket(port)
    private var listQueue: ArrayList<String>? = null

    val telegramBot: TelegramBot = TelegramBot(BOT_NAME, TOKEN)

    public fun service() {
        var msg: String
        var byteArray: ByteArray = ByteArray(518)
        telegramBot.settingBot()
        while (true) {
            var datagramPacket: DatagramPacket = DatagramPacket(byteArray, byteArray.size)
            println("hello");

            datagramSocket?.receive(datagramPacket)
            msg = byteArray.toString()
            checkMessage(msg);
            Thread.sleep(1000)

    }
    }

    public fun checkMessage(msg: String) {
        var msgArgs = msg.split("#")

        when (msgArgs[0]) {

            "com.kakao.talk" -> {

                telegramBot.sendMessage("카카오톡 메세지")
            }
            else ->
                telegramBot.sendMessage(msg[1]+" 알림 메세지")

        }
    }
}
