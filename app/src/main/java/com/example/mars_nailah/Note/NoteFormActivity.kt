
package com.example.mars_nailah.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mars_nailah.data.entity.NoteEntity
import com.example.mars_nailah.database.AppDatabase
import com.example.mars_nailah.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Tambah Note"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Inisialisasi Database
        db = AppDatabase.getInstance(this)

        // Tombol Save
        binding.btnSaveNote.setOnClickListener {

            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            if (title.isNotBlank() && content.isNotBlank()) {

                lifecycleScope.launch {
                    try {
                        val note = NoteEntity(
                            title = title,
                            content = content,
                            createdAt = System.currentTimeMillis()
                        )

                        // Insert data ke Room Database
                        db.noteDao().insert(note)

                        Toast.makeText(
                            this@NoteFormActivity,
                            "Note berhasil disimpan!",
                            Toast.LENGTH_SHORT
                        ).show()

                        finish()

                    } catch (e: Exception) {
                        Toast.makeText(
                            this@NoteFormActivity,
                            "Gagal menyimpan: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            } else {
                Toast.makeText(
                    this,
                    "Isi semua kolom!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
