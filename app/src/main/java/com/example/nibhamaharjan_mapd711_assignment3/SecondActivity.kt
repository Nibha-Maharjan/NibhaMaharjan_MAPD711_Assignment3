package com.example.nibhamaharjan_mapd711_assignment3

import android.graphics.Camera
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
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class SecondActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null
    var Toronto1 = LatLng(43.6553892,-79.4127112)
    var Toronto2 = LatLng(43.655948,-79.4205218)
    var Toronto3 = LatLng(43.6531535,-79.4134837)
    var Toronto4 = LatLng(43.6509195,-79.381608)
    var Toronto5 = LatLng(43.6540339,-79.4069829)
    var Scarborough1 = LatLng(43.6985626,-79.2935878)



    private var locationArrayList: ArrayList<LatLng>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        title="Pizza Show Location"

        val pizzaLocation = intent.getStringExtra("placename")
        Toast.makeText(this, pizzaLocation, Toast.LENGTH_SHORT).show()
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationArrayList= ArrayList()
        if (pizzaLocation == "Toronto Downtown"){
            locationArrayList!!.add(Toronto1)
            locationArrayList!!.add(Toronto2)
            locationArrayList!!.add(Toronto3)
            locationArrayList!!.add(Toronto4)
            locationArrayList!!.add(Toronto5)
        }else if(pizzaLocation == "Scarborough"){
            locationArrayList!!.add(Scarborough1)
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
        mGoogleMap=googleMap
        for (i in locationArrayList!!.indices){
            mGoogleMap!!.addMarker(MarkerOptions().position(locationArrayList!![i]).title("PizzaShop"+(i+1)))
            mGoogleMap!!.animateCamera(CameraUpdateFactory.zoomTo(100.0f))
            mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList!!.get(i)))
        }
    }
}