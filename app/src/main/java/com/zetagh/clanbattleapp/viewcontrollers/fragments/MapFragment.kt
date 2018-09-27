package com.zetagh.clanbattleapp.viewcontrollers.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.zetagh.clanbattleapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.SupportMapFragment
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import android.location.LocationManager
import android.content.ContentValues.TAG

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import com.zetagh.clanbattleapp.models.LanCenterResponse
import com.zetagh.clanbattleapp.networking.ClanBattlesApi


/**
 * A simple [Fragment] subclass.
 *
 */
class MapFragment : Fragment(), OnMapReadyCallback  {

    private lateinit var mMap: GoogleMap
    private var latitudes = 0.0
    private var longitudes = 0.0
    private val zoom = 15f
    var locationManager: LocationManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = childFragmentManager
                .findFragmentById(R.id.mapViewFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))



        AndroidNetworking.get(ClanBattlesApi.getLanCentersUrl)
                /*.addQueryParameter("limit", "3")*/
                .setPriority(Priority.LOW)
                .setTag(ClanBattlesApi.tag)
                .build()
                .getAsObject(LanCenterResponse::class.java, object : ParsedRequestListener<LanCenterResponse> {
                    override fun onResponse(BussinesPoints: LanCenterResponse) {


                        pintarBussinesPoints(BussinesPoints)
                        // Log.e(TAG, "XD")
                    }

                    override fun onError(anError: ANError) {
                        Log.e(TAG, "error en el api")
                        // handle error
                    }
                })



        goToLocationZoom(-12.1038972, -76.9634149, zoom)
    }




    private fun getLocation() {

        try {
            // Request location updates
            var location = locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);


        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available");
        }



    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {


            latitudes = location.latitude
            longitudes = location.longitude

            goToLocationZoom(latitudes,longitudes, zoom)
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> getLocation()
                //  PackageManager.PERMISSION_DENIED -> //Tell to user the need of grant permission
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }
    private fun goToLocationZoom(lat: Double, lng: Double, zoom: Float) {

        val latLng = LatLng(lat, lng)
        //  drawMarkerUser(latLng)
        val update = CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        mMap.moveCamera(update)

    }

    private fun pintarBussinesPoints(bussinesPoints: LanCenterResponse) {

        for (i in bussinesPoints.lanCenters!!.indices) {

            val point = LatLng(bussinesPoints.lanCenters[i].latitud,
                    bussinesPoints.lanCenters[i].longitud)
            Log.d("XD", point.latitude.toString()+ point.longitude.toString())
            val markerOptions: MarkerOptions
            markerOptions = MarkerOptions()
            markerOptions.position(point).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))

            //   markerOptions.zIndex(bussinesPoints[i].getId_store())
            // val sydney = LatLng(-34.0, 151.0)
            // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


            mMap.addMarker(markerOptions)
        }
    }

}



