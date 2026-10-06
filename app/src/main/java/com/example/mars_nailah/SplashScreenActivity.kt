package com.example.mars_nailah

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref =
            getSharedPreferences("user_pref", MODE_PRIVATE)

        val isLogin = sharedPref.getBoolean("isLogin", false)

        // Jika sudah login, langsung ke MainActivity
        if (isLogin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Jika belum login, tampilkan Splash
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {

            delay(2000)

            val intent = Intent(
                this@SplashScreenActivity,
                AuthActivity::class.java
            )

            startActivity(intent)
            finish()
        }
    }
}