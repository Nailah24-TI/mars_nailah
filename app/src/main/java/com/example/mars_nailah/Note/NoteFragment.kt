
package com.example.mars_nailah.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mars_nailah.database.AppDatabase
import com.example.mars_nailah.data.entity.NoteEntity
import com.example.mars_nailah.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase

    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pengaturan Toolbar
        (requireActivity() as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolbar)

            supportActionBar?.apply {
                title = "Note"
                subtitle = null
            }
        }

        // Inisialisasi Database
        db = AppDatabase.getInstance(requireContext())

        // Inisialisasi Adapter
        adapter = NoteAdapter(notes, this)

        // Pengaturan RecyclerView
        binding.rvNotes.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvNotes.adapter = adapter

        // Garis pemisah antar item
        val divider = DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        )

        binding.rvNotes.addItemDecoration(divider)

        // FAB membuka NoteFormActivity
        binding.fabAddNote.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    NoteFormActivity::class.java
                )
            )
        }

        fetchNotes()
    }

    // Mengambil semua data Note
    private fun fetchNotes() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val data = db.noteDao().getAll()

                notes.clear()
                notes.addAll(data)

                adapter.notifyDataSetChanged()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Menghapus data Note
    fun deleteNote(note: NoteEntity) {
        lifecycleScope.launch {
            try {
                db.noteDao().delete(note)

                fetchNotes()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Memperbarui daftar ketika kembali dari Form
    override fun onResume() {
        super.onResume()

        if (::db.isInitialized && ::adapter.isInitialized) {
            fetchNotes()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
