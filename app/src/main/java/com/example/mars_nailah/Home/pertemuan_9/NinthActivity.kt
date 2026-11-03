package com.example.mars_nailah.Home.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mars_nailah.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Back pada Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        // Listener untuk ChipGroup
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->

            val selectedChipId = checkedIds.firstOrNull()

            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)

                Toast.makeText(
                    this,
                    "Filter: ${chip.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Tombol Login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()

            if (email.isEmpty()) {
                binding.textInputLayout.error = "Email tidak boleh kosong"
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.textInputLayout.error = "Format email tidak valid"
            } else {
                binding.textInputLayout.error = null

                Toast.makeText(
                    this,
                    "Email berhasil dimasukkan: $email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}