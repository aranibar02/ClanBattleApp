package com.zetagh.clanbattleapp.viewcontrollers.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

import com.zetagh.clanbattleapp.R
import com.zetagh.clanbattleapp.models.LanCenter
import com.zetagh.clanbattleapp.models.LanCenterResponse
import com.zetagh.clanbattleapp.networking.ClanBattlesApi
import com.zetagh.clanbattleapp.viewcontrollers.adapters.LanCenterAdapter
import kotlinx.android.synthetic.main.fragment_map.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MapFragment : Fragment() {

    private var lanCenters = ArrayList<LanCenter>()
    private lateinit var lanCenterRecyclerView: RecyclerView
    private lateinit var lanCenterAdapter: LanCenterAdapter
    private lateinit var lanCenterLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        lanCenterAdapter = LanCenterAdapter(lanCenters,view.context)
        lanCenterRecyclerView = view.lanCenterRecyclerView
        lanCenterLayoutManager = LinearLayoutManager(view.context)
        lanCenterRecyclerView.adapter = lanCenterAdapter
        lanCenterRecyclerView.layoutManager = lanCenterLayoutManager

        AndroidNetworking.get(ClanBattlesApi.getLanCentersUrl)
                .setPriority(Priority.LOW)
                .setTag(ClanBattlesApi.tag)
                .build()
                .getAsObject(LanCenterResponse::class.java, object :ParsedRequestListener<LanCenterResponse>{
                    override fun onResponse(response: LanCenterResponse) {
                        lanCenters = response.lanCenters!!
                        Log.d(ClanBattlesApi.tag, "Parsed: Found ${lanCenters.size} lanCenters")
                        lanCenterAdapter.lanCenters = lanCenters
                        lanCenterAdapter.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                        Log.d(ClanBattlesApi.tag, anError!!.message)
                    }
                })

        return view
    }


}
