
package com.example.mars_nailah.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mars_nailah.AuthActivity
import com.example.mars_nailah.Home.pertemuan_2.SecondActivity
import com.example.mars_nailah.Home.pertemuan_3.ThirdActivity
import com.example.mars_nailah.Home.pertemuan_4.FourthActivity
import com.example.mars_nailah.Home.pertemuan_5.FifthActivity
import com.example.mars_nailah.Home.pertemuan_6.SixthActivity
import com.example.mars_nailah.databinding.FragmentHomeBinding


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
