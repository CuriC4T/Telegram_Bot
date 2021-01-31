package com.curic4t.telegram

import com.curic4t.dto.WeatherDTO
import com.curic4t.restful.WeatherManager
import com.curic4t.util.UtilFunction
import com.curic4t.weather.WeatherManagement
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update


@Component
class TelegramBotSetting(val BOT_NAME: String, val TOKEN: String) : TelegramLongPollingBot() {
    //private val BOT_NAME: String = "curic4t_bot"
    //private val TOKEN: String = "888289672:AAFEh_x7jMaxcYhL7lPyz4-Br0Y8r-bVrhY"
    val utilFunction:UtilFunction= UtilFunction()
    val ORDER_LIST: List<String> = listOf("/start","/help","/weather", "/choose")
    var chatId: Long = 0
    override fun getBotUsername(): String = BOT_NAME

    override fun getBotToken(): String = TOKEN

    override fun onUpdateReceived(update: Update?) {

        var order: String = ""
        update?.run {
            println(update.getMessage().getFrom().getId()); //get ID 는 suer id
            println(update.getMessage().getFrom().getFirstName()); //get ID 는 suer id
            println(update.getMessage().getFrom().getLastName()); //get ID 는 suer id
            println(update.getMessage().getChatId());  // 채팅방의 ID
            println(update.getMessage().getText());  // 받은 TEXT
            //println(update.getMessage().getReplyToMessage().getText());  // bot이 물어 본 받은 TEXT 사용자가 봇에게 답장 할때 사용    }
            chatId = update.getMessage().getChatId()
            order = update.getMessage().getText()
            var orderMessages =order.split(" ")
            when (orderMessages[0]) {

                "/hello" -> {

                    sendMessage(chatId, order)


                }
                "/start" -> {

                    sendMessage(chatId, """Hello There
                        |
                    """.trimMargin()+ORDER_LIST.toString())
                }
                "/help" -> {

                    sendMessage(chatId, """Hello There
                        |
                    """.trimMargin()+ORDER_LIST.toString())
                }
                "/weather" -> {

                    val weatherManagement: WeatherManagement =WeatherManagement();
                    sendMessage(chatId,weatherManagement.getWeatherForTelegram("가산",58,125))
                    weatherManagement.resetComponents()
                }
                "/choose"->{

                    var answer:String=utilFunction.getChoose(orderMessages)
                    sendMessage(chatId, answer)

                }
                else -> {
                    sendMessage(chatId, "There is no such order.\n"+ORDER_LIST.toString())
                }


            }

        }

    }

    public fun sendMessage(chatId: Long, message: String) {
        message?.let {
            if(message.compareTo("ERROR")!=0){
                var sendMessage: SendMessage = SendMessage()
                        .setChatId(chatId)
                        .setText(message)

                println(chatId)
                println(message)

                execute(sendMessage)
            }

        }

    }
    public fun sendMessage(message: String) {
        message?.let {
            if(message.compareTo("ERROR")!=0 && !chatId.equals(0)){
                var sendMessage: SendMessage = SendMessage()
                        .setChatId(chatId)
                        .setText(message)

                println(chatId)
                println(message)

                execute(sendMessage)
            }

        }

    }
}