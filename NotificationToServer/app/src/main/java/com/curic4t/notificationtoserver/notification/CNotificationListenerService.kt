package com.curic4t.notificationtoserver.notification

import android.app.Notification
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.curic4t.notificationtoserver.server.UDPserver

class CNotificationListenerService : NotificationListenerService() {

    val cancelBoardcastReceiver:CancelBoardcastReceiver =CancelBoardcastReceiver()

    var check: Boolean = false
    final val PACKAGE_NAME ="com.nshc.notificationtoserver"
    var hostname :String =""
    var port : Int = 0
    lateinit var udpServer:UDPserver;

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)

        val notification: Notification = sbn.notification
        val extra: Bundle = notification.extras
        val title: String? = extra.getString(Notification.EXTRA_TITLE)
        val text: CharSequence? = extra.getCharSequence(Notification.EXTRA_TEXT)
        val subText: CharSequence? = extra.getCharSequence(Notification.EXTRA_SUB_TEXT)
        val smallIcon: Icon? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notification.smallIcon
        } else {
            TODO("VERSION.SDK_INT < M")
            null
        }
        if(check){
            Log.d(
                "TESTTEST", "onNotificationPosted ~ " +
                        " packageName: " + sbn.packageName +
                        " id: " + sbn.getId() +
                        " postTime: " + sbn.postTime +
                        " title: " + title +
                        " text : " + text +
                        " subText: " + subText
            )

            if(checkMessage(sbn.packageName)==1){
                var message = sbn.packageName +"#"+title
                Log.d("TESTTEST","send Message : $message")

                udpServer.sendMessage(message)

            }

        }else{
            Log.d("TESTTEST","false fasle ")
        }
        //packageName으로 구분 title이 이름 text가 내용
    }

    override fun onCreate() {
        super.onCreate()
        val filter :IntentFilter = IntentFilter()
        filter.addAction(PACKAGE_NAME)
        registerReceiver(cancelBoardcastReceiver ,filter)
        hostname = "www.azunyan.co.kr"
        port = 25456
        udpServer = UDPserver(hostname,port)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        val checkIntent = intent?.getStringExtra("check")
        if(checkIntent.equals("ok")){
            check =true
        }else if (checkIntent.equals("cancel")){
            check=false
        }

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(cancelBoardcastReceiver)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
    }

    fun checkMessage(msg:String) : Int{
        var result = 0
        when(msg){
            "com.android.systemui"-> result= 0
            else->result = 1
        }
        return result
    }

    inner class CancelBoardcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("TestTest","브로드캐스트 받음")

            var action = intent?.action
            if(action.equals(PACKAGE_NAME)){
                when (intent?.getStringExtra("check")){
                    "cancel"->{
                        Log.d("TestTest","꺼짐")
                        udpServer.sendMessage("com.kakao.talk#TEST")
                        Log.d("TestTest","테스트 메세지 전송")

                        check=false
                    }
                    "ok"->{
                        Log.d("TestTest","켜짐")
                        check=true
                    }
                }
            }

        }

    }
}