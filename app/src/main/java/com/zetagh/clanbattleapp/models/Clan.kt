package com.zetagh.clanbattleapp.models

import android.os.Bundle
import java.io.Serializable

data class Clan( val id:Int,
                 val game:Game,
                 val name:String,
                 val rating:Int,
                 val win:Int,
                 val lose:Int,
                 val winRate:Double,
                 val status:String){
    companion object {
        fun from(bundle:Bundle) : Clan {
            return Clan(
                    bundle.getInt("id"),
                    Game.from(bundle.getBundle("game")),
                    bundle.getString("name"),
                    bundle.getInt("rating"),
                    bundle.getInt("win"),
                    bundle.getInt("lose"),
                    bundle.getDouble("winRate"),
                    bundle.getString("status")
            )
        }
    }


    fun toBundle(): Bundle {
        val bundle = Bundle()
        with(bundle) {
            bundle.putInt("id", id)
            bundle.putBundle("game",game.toBundle())
            bundle.putString("name", name)
            bundle.putInt("rating",rating)
            bundle.putInt("win",win)
            bundle.putInt("lose",lose)
            bundle.putDouble("winRate",winRate)
            bundle.putString("status", status)
        }
        return bundle
    }

}