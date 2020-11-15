package com.example.v5baso.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import com.example.v5baso.R
import com.example.v5baso.model.request.LoginUserRequest
import com.example.v5baso.model.response.BodyUserResponse
import com.example.v5baso.presenter.LoginUserPresenter
import com.example.v5baso.presenter.LoginUserPresenterImpl
import com.example.v5baso.view.UserView
import com.google.gson.Gson
import org.apache.commons.codec.binary.Base64

class LogInActivity : AppCompatActivity(), UserView {



    var loginUserPresenter: LoginUserPresenter? = null
    var response: BodyUserResponse? = null
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
                makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun result(result: String?) {
        val split_string: List<String> = result!!.split(".")

        val base64EncodedHeader = split_string[0]
        val responseBody = split_string[1]
        val base64EncodedSignature = split_string[2]
        val base64Url = Base64(true)
        val body = String(base64Url.decode(responseBody))
        println("JWT Body : $body")
        val gson = Gson()

        response = gson.fromJson(body, BodyUserResponse::class.java)
        progressBar.visibility = View.INVISIBLE
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("userName",response!!.firstname+" "+response!!.lastname)
        intent.putExtra("token", result)
        startActivity(intent)
    }

    override fun invalidateOperation() {
        progressBar.visibility = View.INVISIBLE
        makeText(this, "Login Invalido", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onDestroy()
    }
}