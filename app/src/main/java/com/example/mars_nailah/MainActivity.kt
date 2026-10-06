package com.example.mars_nailah

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mars_nailah.databinding.ActivityMainBinding
import com.example.mars_nailah.pertemuan_4.FourthActivity
import com.example.mars_nailah.pertemuan_5.FifthActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("onCreate", "MainActivity dibuat pertama kali")

        // Tombol menuju FourthActivity
        binding.btnToFourth.setOnClickListener {

            val intent = Intent(this, FourthActivity::class.java)

            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)

            startActivity(intent)
        }

        // Tombol menuju FifthActivity
        binding.btnToFifth.setOnClickListener {

            val intent = Intent(this, FifthActivity::class.java)
            startActivity(intent)
        }

        // Tombol Logout
        binding.btnLogout.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya") { dialog, _ ->

                    val sharedPref =
                        getSharedPreferences("user_pref", MODE_PRIVATE)

                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: MainActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "MainActivity dihapus dari stack")
    }
}