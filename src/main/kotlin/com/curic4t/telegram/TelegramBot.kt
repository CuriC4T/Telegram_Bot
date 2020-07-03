package com.curic4t.telegram

import org.telegram.telegrambots.meta.TelegramBotsApi


class TelegramBot(val BOT_NAME:String , val TOKEN:String) {
    private val botsApi: TelegramBotsApi
    private val telegramBotSetting: TelegramBotSetting
    init {
        botsApi = TelegramBotsApi()
        telegramBotSetting= TelegramBotSetting(BOT_NAME,TOKEN)
    }

    public fun settingBot(){
        botsApi.registerBot(telegramBotSetting)
    }


}