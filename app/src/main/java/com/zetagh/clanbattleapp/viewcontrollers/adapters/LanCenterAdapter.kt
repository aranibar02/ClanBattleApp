package com.zetagh.clanbattleapp.viewcontrollers.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zetagh.clanbattleapp.R
import com.zetagh.clanbattleapp.models.LanCenter

class LanCenterAdapter(var lanCenters:ArrayList<LanCenter>, val context: Context) : RecyclerView.Adapter<LanCenterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context)
                        .inflate(R.layout.item_lancenter, parent, false))
    }

    override fun getItemCount(): Int {
        return lanCenters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lanCenter = lanCenters.get(position)
        holder.updateFrom(lanCenter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun updateFrom(lanCenter: LanCenter) {

        }
    }
}