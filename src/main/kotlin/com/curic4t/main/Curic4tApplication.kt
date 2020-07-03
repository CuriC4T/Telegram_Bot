package com.curic4t.main

import com.curic4t.telegram.TelegramBot
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationEventPublisher
import org.telegram.telegrambots.ApiContextInitializer


@SpringBootApplication
class Curic4tApplication
val BOT_NAME: String = "curic4t_bot"
val TOKEN: String = "888289672:AAFEh_x7jMaxcYhL7lPyz4-Br0Y8r-bVrhY"
fun main(args: Array<String>) {
    ApiContextInitializer.init();

    val telegramBot: TelegramBot = TelegramBot(BOT_NAME,TOKEN)
    telegramBot.settingBot()
    //runApplication<Curic4tApplication>(*args)

    SpringApplication.run(Curic4tApplication::class.java, *args)


}

