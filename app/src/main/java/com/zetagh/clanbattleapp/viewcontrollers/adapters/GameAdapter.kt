package com.zetagh.clanbattleapp.viewcontrollers.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zetagh.clanbattleapp.R
import com.zetagh.clanbattleapp.models.Game
import kotlinx.android.synthetic.main.item_game.view.*
import java.util.zip.Inflater

class GameAdapter(var games:ArrayList<Game>, val context: Context) : RecyclerView.Adapter<GameAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(
                LayoutInflater.from(context)
                        .inflate(R.layout.item_game, parent, false))
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val game = games.get(position)
        holder.updateFrom(game)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val pictureImageView = view.pictureImageView
        fun updateFrom(game: Game) {
                with(pictureImageView){
                    pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher)
                    pictureImageView.setErrorImageResId(R.mipmap.ic_launcher)
                    pictureImageView.setImageUrl(game.urlToImage)
                }
            }
        }
    }