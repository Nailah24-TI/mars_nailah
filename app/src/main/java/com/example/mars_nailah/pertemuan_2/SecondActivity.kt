
package com.example.mars_nailah.pertemuan_2

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mars_nailah.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_second)

        // Mengaktifkan Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.apply {
            title = "Activity Second"
            subtitle = "Pertemuan 2"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Mengatur system bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // Inisialisasi komponen
        val inputNama: EditText = findViewById(R.id.inputNama)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        // Tombol Submit
        btnSubmit.setOnClickListener {

            // Mengambil value dari inputNama
            val nama = inputNama.text

            // Menampilkan di Logcat
            Log.e(
                "Klik btnSubmit",
                "Tombol berhasil ditekan. Isi dari inputNama = $nama"
            )

            // Menampilkan Toast
            Toast.makeText(
                this,
                "Anda telah melakukan klik pada tombol Submit",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Tombol Back pada Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
