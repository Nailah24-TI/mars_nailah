package com.example.mars_nailah.pertemuan_3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat
import com.example.mars_nailah.R
import com.example.mars_nailah.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            insets
        }

        binding.btnKirim.setOnClickListener {

            val noTujuan = binding.inputNoTujuan.text.toString()

            Toast.makeText(
                this,
                "Pesan berhasil dikirim ke $noTujuan",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}