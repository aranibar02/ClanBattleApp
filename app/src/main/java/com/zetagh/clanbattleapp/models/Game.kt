package com.zetagh.clanbattleapp.models

import android.os.Bundle

data class Game( val id:Int,
                 val name:String,
                 val description:String,
                 val urlToImage:String,
                 val status:String){
    companion object {
        fun toFrom(bundle:Bundle) : Game {
            return Game(
                    bundle.getInt("id"),
                    bundle.getString("name"),
                    bundle.getString("description"),
                    bundle.getString("urlToImage"),
                    bundle.getString("status")
            )
        }
    }


    fun toBundle(): Bundle {
        val bundle = Bundle()
        with(bundle) {
            bundle.putInt("id", id)
            bundle.putString("name", name)
            bundle.putString("description", description)
            bundle.putString("urlToImage", urlToImage)
            bundle.putString("status", status)
        }
        return bundle
    }

}