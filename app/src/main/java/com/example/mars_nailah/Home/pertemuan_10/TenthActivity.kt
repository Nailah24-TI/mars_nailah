package com.example.mars_nailah.Home.pertemuan_10

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mars_nailah.R
import com.example.mars_nailah.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur tampilan status bar
        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.statusBarColor = Color.rgb(103, 58, 183)
        window.navigationBarColor = Color.rgb(247, 247, 252)

        // Mengatur jarak konten dari status bar dan navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                0,
                systemBars.top,
                0,
                systemBars.bottom
            )

            insets
        }

        // Tombol kembali pada Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Inisialisasi Adapter
        val tabsAdapter = TenthTabsAdapter(this)

        // Hubungkan Adapter dengan ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // Hubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->

            when (position) {

                0 -> {
                    tab.text = "Tab A"

                    // Icon Tab A
                    tab.icon = ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_home
                    )

                    // Badge berupa titik
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }

                1 -> {
                    tab.text = "Tab B"

                    // Icon Tab B
                    tab.icon = ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_home
                    )

                    // Badge dengan nomor
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
            }

        }.attach()
    }
}
