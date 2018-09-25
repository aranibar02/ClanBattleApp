package com.zetagh.clanbattleapp.viewcontrollers.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zetagh.clanbattleapp.R
import com.zetagh.clanbattleapp.models.Clan
import com.zetagh.clanbattleapp.models.Game
import com.zetagh.clanbattleapp.viewcontrollers.activities.ClanActivity
import com.zetagh.clanbattleapp.viewcontrollers.activities.MainActivity
import kotlinx.android.synthetic.main.item_clan.view.*
import kotlinx.android.synthetic.main.item_game.view.*
import java.util.zip.Inflater

class ClanAdapter(var clans:ArrayList<Clan>, val context: Context) : RecyclerView.Adapter<ClanAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context)
                        .inflate(R.layout.item_clan, parent, false))
    }

    override fun getItemCount(): Int {
        return clans.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val clan = clans.get(position)
        holder.updateFrom(clan)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val clansLayout = view.clansLayout
        fun updateFrom(clan: Clan) {


            clansLayout.setOnClickListener{view->
                val context = view.context
                context.startActivity(
                        Intent(context,
                                ClanActivity::class.java)
                                .putExtras(clan.toBundle()))
            }
        }
    }
}
