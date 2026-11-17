
package com.example.mars_nailah.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mars_nailah.AuthActivity
import com.example.mars_nailah.Home.pertemuan_10.TenthActivity
import com.example.mars_nailah.Home.pertemuan_2.SecondActivity
import com.example.mars_nailah.Home.pertemuan_3.ThirdActivity
import com.example.mars_nailah.Home.pertemuan_4.FourthActivity
import com.example.mars_nailah.Home.pertemuan_5.FifthActivity
import com.example.mars_nailah.Home.pertemuan_6.SixthActivity
import com.example.mars_nailah.Home.pertemuan_9.NinthActivity
import com.example.mars_nailah.Home.photo.PhotoAdapter
import com.example.mars_nailah.data.api.CatFactApiClient
import com.example.mars_nailah.data.api.PhotoApiClient
import com.example.mars_nailah.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengatur Toolbar
        (requireActivity() as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.apply {
                title = "Home"
                subtitle = null
            }
        }

        // Pertemuan 2
        binding.btnToSecond.setOnClickListener {
            startActivity(
                Intent(requireContext(), SecondActivity::class.java)
            )
        }

        // Pertemuan 3
        binding.btnToThird.setOnClickListener {
            startActivity(
                Intent(requireContext(), ThirdActivity::class.java)
            )
        }

        // Pertemuan 4
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Pertemuan 5
        binding.btnToFifth.setOnClickListener {
            startActivity(
                Intent(requireContext(), FifthActivity::class.java)
            )
        }

        // Pertemuan 6
        binding.btnToSixth.setOnClickListener {
            startActivity(
                Intent(requireContext(), SixthActivity::class.java)
            )
        }

        // Pertemuan 9
        binding.btnPertemuan9.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }

        // Pertemuan 10
        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }

        // Tombol Refresh Cat Fact
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        // Mengambil fakta kucing saat halaman Home dibuka
        loadCatFact()
        loadPhoto()

        // Logout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah kamu yakin ingin logout?")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Logout") { _, _ ->

                    val sharedPref = requireContext()
                        .getSharedPreferences(
                            "user_pref",
                            Context.MODE_PRIVATE
                        )

                    sharedPref.edit().clear().apply()

                    val intent = Intent(
                        requireContext(),
                        AuthActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                    requireActivity().finish()
                }
                .show()
        }
    }

    // Fungsi mengambil fakta kucing dari API
    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                binding.tvCatFact.text = "Memuat fakta kucing..."

                val response = CatFactApiClient.apiService.getCatFact()

                if (_binding != null) {
                    binding.tvCatFact.text = "\"${response.fact}\""
                }

            } catch (e: Exception) {
                if (_binding != null) {
                    binding.tvCatFact.text =
                        "Gagal mengambil fakta kucing."
                }
            }
        }
    }
    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter

                /** List Tampil Vertical*/
                //binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())

                /** List Tampil Horizontal */
                //binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                /** List Tampil Grid */
                binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
