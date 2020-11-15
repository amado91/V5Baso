package com.example.v5baso.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.v5baso.R
import com.example.v5baso.model.request.CreateUserRequest
import com.example.v5baso.presenter.UserPresenter
import com.example.v5baso.presenter.UserPresenterImpl
import com.example.v5baso.view.UserView
import com.google.android.material.textfield.TextInputLayout

class CreateUserActivity : AppCompatActivity(), UserView {

    var userPresenter: UserPresenter? = null
    private lateinit var user: TextInputLayout
    private lateinit var pass: TextInputLayout
    private lateinit var text1: TextView
    lateinit var btn_click_me: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        userPresenter = UserPresenterImpl(this)
        initUI()
    }

    fun initUI() {

        btn_click_me = findViewById(R.id.btnLogin)
        text1= findViewById(R.id.textView3)
        user = findViewById(R.id.tilEmail)
        pass = findViewById(R.id.tilPass)

        text1.text = "Crear una cuenta"
        btn_click_me.text = "Crear Usuario"
        btn_click_me.setOnClickListener {
            userPresenter!!.createUser(
                CreateUserRequest(
                    "test@gft.com",
                    "Arturo",
                    "Rodriguez",
                    "mypassword"
                )
            )

        }
    }

    override fun result(result: String?) {
        Log.e("response", result.toString())
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        onDestroy()
    }

    override fun createUser() {
        TODO("Not yet implemented")
    }

}