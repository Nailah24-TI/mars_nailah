
package com.example.mars_nailah.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mars_nailah.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    // Daftar bahasa pemrograman
    private val dataList = listOf(
        "Kotlin",
        "Java",
        "Python",
        "C++",
        "JavaScript",
        "Dart",
        "Swift",
        "Go",
        "Ruby",
        "R",
        "PHP",
        "C#",
        "TypeScript",
        "Shell",
        "SQL",
        "Perl",
        "Rust",
        "Scala",
        "Haskell",
        "Lua",
        "Erlang",
        "Prolog",
        "Assembly",
        "Objective-C",
        "VBA"
    )

    // Daftar bahasa pemrograman dengan deskripsi
    private val dataListWithDesc = listOf(
        mapOf(
            "title" to "Kotlin",
            "desc" to "Bahasa untuk Android modern"
        ),
        mapOf(
            "title" to "Java",
            "desc" to "Bahasa OOP yang populer"
        ),
        mapOf(
            "title" to "Python",
            "desc" to "Bahasa yang mudah dipahami"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengatur Toolbar
        binding.toolbar.title = "More"

//
//        // ADAPTER 1: ArrayAdapter
//        // Menggunakan simple_list_item_1
         val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            dataList
        )

        binding.listViewItems.adapter = adapter

        // Aksi ketika item diklik
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataList[position]

            Toast.makeText(
                requireContext(),
                "Kamu memilih: $selectedItem",
                Toast.LENGTH_SHORT
            ).show()
        }

        /*
        ADAPTER 2: SimpleAdapter
        Menggunakan simple_list_item_2

        Untuk mencoba SimpleAdapter, komentari kode ArrayAdapter
        di atas dan gunakan kode SimpleAdapter di bawah ini.
        */


//        val adapterWithDesc = SimpleAdapter(
//            requireContext(),
//            dataListWithDesc,
//            android.R.layout.simple_list_item_2,
//            arrayOf("title", "desc"),
//            intArrayOf(
//                android.R.id.text1,
//                android.R.id.text2
//            )
//        )
//
//        binding.listViewItems.adapter = adapterWithDesc
//
//        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
//            val selectedItem = dataListWithDesc[position]
//            val title = selectedItem["title"]
//            val desc = selectedItem["desc"]
//
//            Toast.makeText(
//                requireContext(),
//                "Kamu memilih: $title ($desc)",
//                Toast.LENGTH_SHORT
//            ).show()
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
