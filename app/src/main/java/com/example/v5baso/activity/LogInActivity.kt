package com.example.v5baso.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.v5baso.R
import com.example.v5baso.model.request.LoginUserRequest
import com.example.v5baso.model.response.BodyUserResponse
import com.example.v5baso.presenter.LoginUserPresenter
import com.example.v5baso.presenter.LoginUserPresenterImpl
import com.example.v5baso.view.UserView
import com.google.gson.Gson

class LogInActivity : AppCompatActivity(), UserView {

    var loginUserPresenter: LoginUserPresenter? = null
    private lateinit var user: EditText
    private lateinit var pass: EditText
    lateinit var btn_click_me: Button
    private lateinit var text1: TextView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        loginUserPresenter = LoginUserPresenterImpl(this)
        initUI()
    }

    private fun initUI() {
        btn_click_me = findViewById(R.id.btnLogin2)
        user = findViewById(R.id.userLogin)
        pass = findViewById(R.id.passlogin)
        text1 = findViewById(R.id.textView3)
        progressBar = findViewById(R.id.progress_circular)

        text1.text = "Inicia Sesiòn"
        btn_click_me.text = "LogIn"

        btn_click_me.setOnClickListener {
            if (user.text.isNotEmpty()
                && pass.text.isNotEmpty()
            ) {
                progressBar.visibility = View.VISIBLE
                loginUserPresenter!!.loginUser(
                    LoginUserRequest(
                        user.text.toString().trim(),
                        pass.text.toString().trim()
                    )
                )
            } else {
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun result(result: String?) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
        val json = result
        val gson = Gson()

        val response: BodyUserResponse = gson.fromJson(json, BodyUserResponse::class.java)

        Log.e("Body", response.id)
        progressBar.visibility = View.INVISIBLE
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun invalidateOperation() {
        progressBar.visibility = View.INVISIBLE
        Toast.makeText(this, "Login Invalido", Toast.LENGTH_LONG).show()
    }
}