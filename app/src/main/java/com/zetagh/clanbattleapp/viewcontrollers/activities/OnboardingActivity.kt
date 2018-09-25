package com.zetagh.clanbattleapp.viewcontrollers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.zetagh.clanbattleapp.R
import com.zetagh.clanbattleapp.models.Game
import com.zetagh.clanbattleapp.models.GameResponse
import com.zetagh.clanbattleapp.networking.ClanBattlesApi
import com.zetagh.clanbattleapp.viewcontrollers.adapters.GameAdapter
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    private var games = ArrayList<Game>()
    private lateinit var gamesAdapter : GameAdapter
    private lateinit var gamesLayoutManager: RecyclerView.LayoutManager
    private lateinit var gamesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setSupportActionBar(toolbar)
        toolbar.title = "Selecciona tu comunidad"

        gamesAdapter = GameAdapter(games, this)
        gamesLayoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        gamesRecyclerView = this.gamesRecyclerViewLayout
        gamesRecyclerView.adapter = gamesAdapter
        gamesRecyclerView.layoutManager  = gamesLayoutManager


        AndroidNetworking.get(ClanBattlesApi.getGamesUrl)
                .setPriority(Priority.LOW)
                .setTag(ClanBattlesApi.tag)
                .build()
                .getAsObject(GameResponse::class.java, object : ParsedRequestListener<GameResponse>{
                    override fun onResponse(response: GameResponse) {
                        games = response.games!!
                        Log.d(ClanBattlesApi.tag, "Parsed: Found ${games.size} games")
                        gamesAdapter.games = games
                        gamesAdapter.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                        Log.d(ClanBattlesApi.tag, anError!!.message)
                    }

                })
    }
}
