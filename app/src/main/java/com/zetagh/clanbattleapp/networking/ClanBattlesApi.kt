package com.zetagh.clanbattleapp.networking

class ClanBattlesApi(){
    companion object {
        private val baseUrl = "http://clanbattles.somee.com/clanbattles"
        val getGamesUrl = "$baseUrl/v1/games"
        val tag = "ClanBattlesApp"
        val getLanCentersUrl = "$baseUrl/v1/lancenters"
        fun getClanUrl(gameId: Int): String {return "$getGamesUrl/$gameId/clans"}
        fun getPublicationsByGameUrl(gameId: Int): String {return "$getGamesUrl/$gameId/publications"}
    }
}