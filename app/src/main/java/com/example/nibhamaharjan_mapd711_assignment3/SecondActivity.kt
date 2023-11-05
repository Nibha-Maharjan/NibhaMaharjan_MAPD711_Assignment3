package com.example.nibhamaharjan_mapd711_assignment3

import android.graphics.Camera
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class SecondActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null
    //Location of pizza shops
    var Toronto1 = LatLng(43.6617015,-79.446457)
    var Toronto2 = LatLng(43.6565841,-79.418416)
    var Toronto3 = LatLng(43.651392,-79.4030663)
    var Toronto4 = LatLng(43.6484999,-79.3943435)
    var Toronto5 = LatLng(43.6540471,-79.4115385)
    var Scarborough1 = LatLng(43.7042981,-79.2577949)
    var Scarborough2 = LatLng(43.7637466,-79.2968345)
    var Scarborough3 = LatLng(43.7757196,-79.2675858)
    var Scarborough4 = LatLng(43.7276045,-79.2350958)
    var Scarborough5 = LatLng(43.7425228,-79.3101898)
    var Mississauga1 = LatLng(43.6180043,-79.6009223)
    var Mississauga2 = LatLng(43.607153,-79.6264862)
    var Mississauga3 = LatLng(43.5921874,-79.6456413)
    var Mississauga4 = LatLng(43.592232,-79.6354362)
    var Mississauga5 = LatLng(43.5867579,-79.6194207)
    var NorthYork1 = LatLng(43.7309432,-79.4721234)
    var NorthYork2 = LatLng(43.7270392,-79.4413588)
    var NorthYork3 = LatLng(43.755644,-79.4500272)
    var NorthYork4 = LatLng(43.7622469,-79.4200493)
    var NorthYork5 = LatLng(43.7331903,-79.4604042)
    var Oakville1 = LatLng(43.4447896,-79.6868531)
    var Oakville2 = LatLng(43.5004928,-79.6659359)
    var Oakville3 = LatLng(43.4403059,-79.6799055)
    var Oakville4 = LatLng(43.4487907,-79.6700793)
    var Oakville5 = LatLng(43.442857,-79.6850756)
    //Array
    private var locationArrayList: ArrayList<LatLng>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        title="Pizza Show Location"
        //Getting Data from intent and toasting the selected place name
        val pizzaLocation = intent.getStringExtra("placename")
        Toast.makeText(this, pizzaLocation, Toast.LENGTH_SHORT).show()
        //Map fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Adding Pizza locations to our arraylists
        locationArrayList= ArrayList()
        if (pizzaLocation == "Toronto Downtown"){
            locationArrayList!!.add(Toronto1)
            locationArrayList!!.add(Toronto2)
            locationArrayList!!.add(Toronto3)
            locationArrayList!!.add(Toronto4)
            locationArrayList!!.add(Toronto5)
        }else if(pizzaLocation == "Scarborough"){
            locationArrayList!!.add(Scarborough1)
            locationArrayList!!.add(Scarborough2)
            locationArrayList!!.add(Scarborough3)
            locationArrayList!!.add(Scarborough4)
            locationArrayList!!.add(Scarborough5)
        }else if(pizzaLocation == "Mississauga"){
            locationArrayList!!.add(Mississauga1)
            locationArrayList!!.add(Mississauga2)
            locationArrayList!!.add(Mississauga3)
            locationArrayList!!.add(Mississauga4)
            locationArrayList!!.add(Mississauga5)
        }else if(pizzaLocation == "North York"){
            locationArrayList!!.add(NorthYork1)
            locationArrayList!!.add(NorthYork2)
            locationArrayList!!.add(NorthYork3)
            locationArrayList!!.add(NorthYork4)
            locationArrayList!!.add(NorthYork5)
        }else if(pizzaLocation == "Oakville"){
            locationArrayList!!.add(Oakville1)
            locationArrayList!!.add(Oakville2)
            locationArrayList!!.add(Oakville3)
            locationArrayList!!.add(Oakville4)
            locationArrayList!!.add(Oakville5)
        }

        //Menu
        val mapOptionButton:ImageButton = findViewById(R.id.mapOptionMenu)
        val popupMenu = PopupMenu(this,mapOptionButton)
        popupMenu.menuInflater.inflate(R.menu.map_options,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            changeMap(menuItem.itemId)
            true
        }

        mapOptionButton.setOnClickListener{
            popupMenu.show()
        }


    }
    //Change Map type
    private fun changeMap(itemId: Int){
        when(itemId){
            R.id.normal_map -> mGoogleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
            R.id.satellite_map -> mGoogleMap?.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        val geocoder = Geocoder(this)

        for (i in locationArrayList!!.indices) {
            try {
                val addresses = geocoder.getFromLocation(locationArrayList!![i].latitude, locationArrayList!![i].longitude, 1)
                if (addresses!!.isNotEmpty()) {
                    val address = addresses[0]
                    val markerTitle = address.getAddressLine(0) //Get the first address line
//                    val city = address.locality//Get city
//                    val country = address.countryName//Get country
                    val p=i+1
                    val fullAddress = markerTitle
                    //Pointer Adding
                    mGoogleMap!!.addMarker(MarkerOptions().position(locationArrayList!![i]).title("Pizza store $p").snippet(fullAddress))
                }
            } catch (e: IOException) {
                // Error Handling
                e.printStackTrace()
            }
            //Zoom in onto the pins
            val boundsBuilder = LatLngBounds.builder()
            for (location in locationArrayList!!) {
                boundsBuilder.include(location)
            }
            val bounds = boundsBuilder.build()
            val padding = 100
            val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            mGoogleMap!!.animateCamera(cameraUpdate)
        }
    }
}