package com.betterise.maladiecorona.managers

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*


/**
 * Created by Alexandre on 01/07/20.
 */
class GeolocManager {

    companion object {
        const val GEOLOC_CODE = 2111
    }

    interface GeolocListener {
        fun onLocation(location : Location?)
        fun onLocationFailed()
    }

    /***
     * Requesting location
     */
    fun requestLastGeoloc(activity: AppCompatActivity) : Boolean {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                GEOLOC_CODE
            )
            return false
        }
        return true
    }

    @SuppressLint("MissingPermission")
    fun getLastGeoloc(context: Context, listener : GeolocListener){

        LocationServices.getFusedLocationProviderClient(context).lastLocation
            .addOnSuccessListener { location : Location? ->
                listener.onLocation(location)
            }
            .addOnFailureListener { e -> Log.e("GEOLOC","${e.message}") }


    }


    var locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return

            if (locationResult.locations.isNotEmpty())
                geolocListener?.onLocation(locationResult.locations.first())

            stopUpdates()
        }
    }

    var client : FusedLocationProviderClient? = null

    private fun stopUpdates(){
        client?.removeLocationUpdates(locationCallback)
    }

    var geolocListener : GeolocListener? = null

    @SuppressLint("MissingPermission")
    fun startLocationUpdate(context: Context,listener : GeolocListener){
        geolocListener = listener
        client = LocationServices
            .getFusedLocationProviderClient(context)

        client?.requestLocationUpdates(LocationRequest(),
            locationCallback,
            Looper.getMainLooper()
        )

    }
}