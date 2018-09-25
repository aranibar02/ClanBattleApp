package com.zetagh.clanbattleapp.viewcontrollers.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zetagh.clanbattleapp.R
import com.zetagh.clanbattleapp.models.Game


class HomeFragment : Fragment() {

    private lateinit var bundle:Bundle
    private lateinit var game:Game

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        bundle = this.arguments!!
        if(arguments != null){
            bundle = this.arguments!!
            game = Game.from(bundle)
            Log.d("ClanBattles","Name of Game: " + game.name)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
