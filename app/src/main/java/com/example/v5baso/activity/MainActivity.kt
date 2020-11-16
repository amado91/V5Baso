package com.example.v5baso.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.v5baso.R
import com.example.v5baso.model.request.CreateCardRequest
import com.example.v5baso.presenter.CreateCatalogPresenter
import com.example.v5baso.presenter.CreateCatalogPresenterImpl
import com.example.v5baso.view.UserView
import java.util.*


class MainActivity : AppCompatActivity(), UserView {

    var createCatalogPresenter: CreateCatalogPresenter? = null
    private var locationManager: LocationManager? = null
    lateinit var btnCreate: Button
    lateinit var btnCards: Button
    private var latitud: Double? = null
    private var longitud: Double? = null
    var city: String? = null
    var state: String? = null
    var token: String? = null
    lateinit var progressBar: ProgressBar
    private lateinit var TDD: TextView
    private lateinit var TDC: TextView
    private lateinit var title: TextView
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createCatalogPresenter = CreateCatalogPresenterImpl(this)
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        checkPermissionForLocation(this)
        val bundle = intent.extras
        val dato = bundle!!.getString("userName")
        token = bundle!!.getString("token")

        Toast.makeText(this, "Bienvenido $dato", Toast.LENGTH_LONG).show()

        Log.e("Latitdu", latitud.toString())
        initUI()


    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        progressBar = findViewById(R.id.progress_circular)
        val text1 = findViewById<TextView>(R.id.ubicacion)
        text1.text = "$state $city"

        btnCreate = findViewById(R.id.btnCreate)
        btnCards = findViewById(R.id.btnCards)
        TDD = findViewById(R.id.txtTDD)
        TDC = findViewById(R.id.txtTDC)
        title = findViewById(R.id.textView)

        btnCreate.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            createCatalogPresenter!!.createCatalog(token!!)
        }

        btnCards.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            createCatalogPresenter!!.consultCard(token!!)
        }

        TDD.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            createCatalogPresenter!!.createCard(CreateCardRequest(id!!,"TDD", "Tarjeta de Debito"), token!!)
        }
        TDC.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            createCatalogPresenter!!.createCard(CreateCardRequest(id!!,"TDC", "Tarjeta de Oro"), token!!)
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            latitud = location.latitude
            longitud = location.longitude

            getLocation(location)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    fun getLocation(location: Location) {
        val direccion: List<Address>
        val geocoder = Geocoder(this, Locale.getDefault())

        direccion = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        )
        city = direccion[0].getLocality()
        state = direccion[0].getAdminArea()
    }

    fun checkPermissionForLocation(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                // Show the permission request
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    10
                )
                false
            }
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 10) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    // Request location updates
                    locationManager?.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        0L,
                        0f,
                        locationListener
                    )
                } catch (ex: SecurityException) {
                    Log.d("myTag", "Security Exception, no location available")
                }

            } else {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun result(result: String?) {
        val _id = result
        Log.e("length", _id!!.length.toString())
        if (_id!!.length == 24){
            id = _id
            title.visibility = View.VISIBLE
            TDD.text = " click si quieres una TDC"
            TDC.text = "click si quieres una TDD"
        }
        Toast.makeText(this, "Se créo la cuenta", Toast.LENGTH_LONG).show()
        progressBar.visibility = View.GONE
        btnCreate.visibility = View.INVISIBLE
        btnCards.visibility = View.VISIBLE



    }

    override fun invalidateOperation() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }
}