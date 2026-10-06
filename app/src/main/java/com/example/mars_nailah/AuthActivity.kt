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

        // SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Cek apakah user sudah login
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        btnLogin.setOnClickListener {

            val username = Username.text.toString()
            val password = Password.text.toString()

            if (username.isNotEmpty() && username == password) {

                // Simpan status login dan username
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                // Masuk ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
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