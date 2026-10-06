package com.example.mars_nailah

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val Username = findViewById<EditText>(R.id.inputUsername)
        val Password = findViewById<EditText>(R.id.inputPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val sharedPref =
            getSharedPreferences("user_pref", MODE_PRIVATE)

        btnLogin.setOnClickListener {

            val username = Username.text.toString()
            val password = Password.text.toString()

            if (username.isNotEmpty() && username == password) {

                val editor = sharedPref.edit()

                editor.putBoolean("isLogin", true)
                editor.putString("username", username)

                editor.apply()

                val intent = Intent(
                    this,
                    MainActivity::class.java
                )

                startActivity(intent)
                finish()

            } else {

                AlertDialog.Builder(this)
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}