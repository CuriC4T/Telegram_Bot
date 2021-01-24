package com.curic4t.notificationtoserver.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.curic4t.notificationtoserver.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TestTest","TestTest $packageName")
        if(!permissionCheck()) {
            Log.d("TestTest","권한 없음")
            var intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        }else{
            Log.d("TestTest","권한 있음")
        }
        var switch :Switch = noti_toggle
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            val boardcastIntent :Intent = Intent()
            if(isChecked){
                Log.d("TestTest","체크 킴")
                boardcastIntent.action = packageName
                boardcastIntent.putExtra("check","ok")
                sendBroadcast(boardcastIntent)
            }else{
                Log.d("TestTest","체크 끔")
                boardcastIntent.action = packageName
                boardcastIntent.putExtra("check","cancel")
                sendBroadcast(boardcastIntent)

            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("TestTest","파괴")

    }

    private fun permissionCheck() : Boolean{
        val sets = NotificationManagerCompat.getEnabledListenerPackages(this)
        return sets != null  && sets.contains(packageName)

    }
}