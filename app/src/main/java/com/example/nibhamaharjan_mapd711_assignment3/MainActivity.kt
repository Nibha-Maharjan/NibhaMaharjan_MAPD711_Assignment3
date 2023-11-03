package com.example.nibhamaharjan_mapd711_assignment3

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.nibhamaharjan_mapd711_assignment3.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title="Pizza Choose Location"

        val listView = findViewById<ListView>(R.id.listView)

        val names = arrayOf("Toronto Downtown","Scarborough","Mississauga","North York","Oakville")

        val arrayAdapter:ArrayAdapter<String> = ArrayAdapter(
            this,android.R.layout.simple_list_item_1,names
        )
        listView.adapter=arrayAdapter

        listView.setOnItemClickListener { adapterview, view, i, l ->
            val name = names[i]
            val placeName = name.toString()
            startActivity(
                //Starting intent to SecondActivity
                Intent(this,SecondActivity::class.java)
                    //Using key value pairs to pass the data into the second activity
                    .putExtra("placename",placeName.toString())

            )

        }
    }
}