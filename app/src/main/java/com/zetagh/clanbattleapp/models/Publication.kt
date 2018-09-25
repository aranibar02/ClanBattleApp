package com.zetagh.clanbattleapp.models

import android.os.Bundle

data class Publication(
    val id:Int? = null,
    val gamerId:Int? = null,
    val gameId:Int? = null,
    val title:String? = null,
    val description:String? = null,
    val urlToImage:String? = null ,
    val publicationDate:String? = null,
    val status:String? = null
){
    companion object {
        fun from(bundle: Bundle):Publication {
            return Publication(
                    bundle.getInt("id"),
                    bundle.getInt("gamerId"),
                    bundle.getInt("gameId"),
                    bundle.getString("title"),
                    bundle.getString("description"),
                    bundle.getString("urlToImage"),
                    bundle.getString("publicationDate"),
                    bundle.getString("status")
            )
        }
    }

    fun toBundle(){
        val bundle = Bundle()
        with(bundle){
            putInt("id",id!!)
            putInt("gamerId",gamerId!!)
            putInt("gameId",gameId!!)
            putString("title",title)
            putString("description",description)
            putString("urlToImage",urlToImage)
            putString("publicationDate",publicationDate)
            putString("status",status)
        }
    }
}