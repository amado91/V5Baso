package com.example.v5baso.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.v5baso.R
import com.example.v5baso.model.request.CreateUserRequest
import com.example.v5baso.presenter.UserPresenter
import com.example.v5baso.presenter.UserPresenterImpl
import com.example.v5baso.view.UserView


class CreateUserActivity : AppCompatActivity(), UserView {

    var userPresenter: UserPresenter? = null
    private lateinit var user: EditText
    private lateinit var pass: EditText
    private lateinit var email: EditText
    private lateinit var lastName: EditText
    private lateinit var text1: TextView
    lateinit var btn_click_me: Button
    lateinit var progressBar: ProgressBar
    var logginGreat: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_user_activity)
        userPresenter = UserPresenterImpl(this)
        val sh = getSharedPreferences(
            "MySharedPref",
            MODE_PRIVATE
        )

        logginGreat = sh.getBoolean("login",false)
        if (logginGreat){
            loginSucces()
        }else {
            initUI()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {

        btn_click_me = findViewById(R.id.btnLogin)
        text1 = findViewById(R.id.textView3)
        user = findViewById(R.id.nombre)
        pass = findViewById(R.id.password)
        lastName = findViewById(R.id.apellido)
        email = findViewById(R.id.correo)
        progressBar = findViewById(R.id.progress_circular)

        text1.text = "Crear una cuenta"
        btn_click_me.text = "Crear Usuario"
        btn_click_me.setOnClickListener {
            if (email.text.isNotEmpty()
                && user.text.isNotEmpty()
                && lastName.text.isNotEmpty()
                && pass.text.isNotEmpty()){
                progressBar.visibility = View.VISIBLE
                userPresenter!!.createUser(
                    CreateUserRequest(
                        email.text.toString(),
                        user.text.toString(),
                        lastName.text.toString(),
                        pass.text.toString()
                    )
                )
            }else {
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun result(result: String?) {
        val sharedPreferences = getSharedPreferences(
            "MySharedPref",
            MODE_PRIVATE
        )
        val myEdit = sharedPreferences.edit()
        myEdit.putBoolean("login", true)

        myEdit.apply()
        progressBar.visibility = View.INVISIBLE
        loginSucces()
        onDestroy()
    }

    override fun createUser() {
        Toast.makeText(this, "No fue exitoso intente nuevamente", Toast.LENGTH_LONG).show()
    }


    private fun loginSucces() {
        Toast.makeText(this, "exitoso la creación de usuario", Toast.LENGTH_LONG).show()
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }

}