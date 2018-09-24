package com.zetagh.clanbattleapp.networking

class ClanBattlesApi(){
    companion object {
        private val baseUrl = "http://clanbattles.somee.com/clanbattles"
        val getGameUrl = "$baseUrl/v1/games"
        val tag = "ClanBattlesApp"
    }
}