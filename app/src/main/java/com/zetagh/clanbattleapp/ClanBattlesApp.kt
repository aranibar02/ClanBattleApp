package com.zetagh.clanbattleapp

import android.app.Application
import com.androidnetworking.AndroidNetworking

class ClanBattlesApp : Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(getApplicationContext())
    }
}