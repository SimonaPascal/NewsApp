package com.example.newsapp.maps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray
import org.json.JSONObject

//import com.example.newsapp.maps.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    val TAG = "TAGOVI"
    private lateinit var currentLatLng: LatLng

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

        setUpMap()
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                currentLatLng = LatLng(location.latitude, location.longitude)
                placeMeOnMap(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                putMarkers(currentLatLng)
            }
        }
    }

    private fun placeMeOnMap(currentLatLng: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLng)
        markerOptions.title("$currentLatLng")
        mMap.addMarker(markerOptions)
    }

    private fun placeHospitalOnMap(name: String, lat: Double, lon:Double,rating:Double,address:String) {
        currentLatLng = LatLng(lat,lon)
        val markerOptions = MarkerOptions().position(currentLatLng)
        markerOptions.title("$name "+"$address " + "$rating")
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital2))
        mMap.addMarker(markerOptions)
    }

    private fun putMarkers(userlatlng: LatLng) {

        Log.i(TAG, userlatlng.toString())

        val queue = Volley.newRequestQueue(this)
        val url =
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                    "location=${userlatlng.latitude},${userlatlng.longitude}" +
                    "&radius=3000" +
                    "&type=hospital" +
                    "&key=AIzaSyC9zGh0See4lBXL7N24wdCslZfVEE-v-r8"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                parseResponse(response)
            },
            { Toast.makeText(applicationContext,"There was an error with the Google Maps",Toast.LENGTH_SHORT).show()  })

        queue.add(stringRequest)
    }

    private fun parseResponse(response: String?) {

        val jsonObject = JSONObject(response)

        val locationsString = jsonObject.getString("results")
        val locationsArray = JSONArray(locationsString)
        if (locationsArray != null) {
            for (i in 0 until locationsArray.length()) {

                try {
                    var item = locationsArray.getJSONObject(i)
                    var name = item.getString("name")
                    var lat =
                        item.getJSONObject("geometry").getJSONObject("location").getDouble("lat")
                    var lon =
                        item.getJSONObject("geometry").getJSONObject("location").getDouble("lng")
                    var rating: Double = try {
                        item.getDouble("rating")
                    } catch (e: java.lang.Exception) {
                        -1.0
                    }
                    var address = item.getString("vicinity")

                    placeHospitalOnMap(
                            name,
                            lat,
                            lon,
                            rating,
                            address
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }


    }

    override fun onMarkerClick(p0: Marker?) = false


}