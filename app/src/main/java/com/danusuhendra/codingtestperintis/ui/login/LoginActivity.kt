package com.danusuhendra.codingtestperintis.ui.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.danusuhendra.codingtestperintis.R
import com.danusuhendra.codingtestperintis.data.PrefHelper
import com.danusuhendra.codingtestperintis.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val prefHelper = PrefHelper(this)
        prefHelper.password = "12345"
        prefHelper.username = "Admin"

        val btn = findViewById<Button>(R.id.btnLogin)
        val edtUsername = findViewById<EditText>(R.id.edtName)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)


        btn.setOnClickListener {
            if (prefHelper.username == edtUsername.text.toString() && prefHelper.password == edtPassword.text.toString()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val snack =
                    Snackbar.make(it, "Email dan password salah!", Snackbar.LENGTH_SHORT)
                        .setTextColor(Color.RED)
                snack.view.setBackgroundColor(Color.WHITE)
                snack.show()
            }
        }
    }
}