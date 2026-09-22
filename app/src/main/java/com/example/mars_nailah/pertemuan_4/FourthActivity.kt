package com.example.mars_nailah.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mars_nailah.MainActivity
import com.example.mars_nailah.databinding.ActivityFourthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // =========================
        // SNACKBAR
        // =========================

        binding.btnShowSnackbar.setOnClickListener {

            Snackbar.make(
                binding.root,
                "Ini adalah Snackbar",
                Snackbar.LENGTH_SHORT
            )
                .setAction("Tutup") {
                    Log.e(
                        "Info Snackbar",
                        "Snackbar ditutup"
                    )
                }
                .show()
        }

        // =========================
        // ALERT DIALOG
        // =========================

        binding.btnShowAlertDialog.setOnClickListener {

            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->

                    dialog.dismiss()

                    Log.e(
                        "Info Dialog",
                        "Anda memilih Ya!"
                    )
                }
                .setNegativeButton("Batal") { dialog, _ ->

                    dialog.dismiss()

                    Log.e(
                        "Info Dialog",
                        "Anda memilih Tidak!"
                    )
                }
                .show()
        }

        // =========================
        // BUTTON DALAM CARD
        // =========================

        binding.btn1.setOnClickListener {

            Log.e(
                "Info Card",
                "Tombol 1 pada Card diklik"
            )
        }

        // =========================
        // TOMBOL KEMBALI KE MAIN
        // =========================

        binding.btnKembali.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onStart() {
        super.onStart()

        Log.e(
            "onStart",
            "onStart: FourthActivity terlihat di layar"
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e(
            "onDestroy",
            "FourthActivity dihapus dari stack"
        )
    }
}