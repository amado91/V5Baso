package com.example.v5baso.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.v5baso.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras
        val dato = bundle!!.getString("userName")
        val token = bundle!!.getString("token")

        Toast.makeText(this, "Bienvenido $dato", Toast.LENGTH_LONG).show()
    }
}