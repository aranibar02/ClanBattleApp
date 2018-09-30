package com.zetagh.clanbattleapp.networking

class ClanBattlesApi {
    companion object {
        private val baseUrl = "http://clanbattles.somee.com/clanbattles/"
        val getGameUrl = "$baseUrl/v1/games/"
        val getLanCentersUrl = "$baseUrl/v1/lancenters"
        fun getPublicationByGamer(gameId:Int):String{return "$getGameUrl/$gameId/publications"}
        fun getClanUrl(gameId: Int): String {return "$getGameUrl/$gameId/clans"}
        val tag = "ClanBattles"
    }
}