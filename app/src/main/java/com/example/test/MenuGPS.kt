package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.test.databinding.ActivityMenuGpsBinding
import com.google.android.gms.maps.model.CameraPosition

class MenuGPS : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMenuGpsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuGpsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //Generar localizacion
        val santiago = LatLng(-33.459229, -70.645348)
        val localCatedral = LatLng(-33.43753644394986, -70.65637030260355)
        val localMoneda = LatLng(-33.442174393686365, -70.65544708614385)
        val localHuerfanos = LatLng(-33.439792729775725, -70.65595416027361)

        //Agregar marcadores al mapa
        mMap.addMarker(MarkerOptions().position(localCatedral))
        mMap.addMarker(MarkerOptions().position(localMoneda))
        mMap.addMarker(MarkerOptions().position(localHuerfanos))

        //Posicionar camara en la geolocalizacion y mueve la camara a stgo con un zoom de 15
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santiago, 35f))

        // Construye una CameraPosition centrada en Ciisa Republica y anima la cámara a esa posición.
        val cameraPosition = CameraPosition.Builder()
            .target(localHuerfanos) // Establece el centro del mapa
            .zoom(17f) // Establece el zoom
            .bearing(90f) // Establece la orientación de la cámara al este
            .tilt(30f) // Establece la inclinación de la cámara a 30 grados
            .build() // Crea una CameraPosition a partir del constructor
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}