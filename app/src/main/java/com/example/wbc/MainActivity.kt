package com.example.wbc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wbc.databinding.ActivityMainBinding
import com.example.wbc.ui.bookmark.FragmentBookmark
import com.example.wbc.ui.search.FragmentSearch
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(this)
    }

    private val tabTitleArray = arrayOf(
        "정류장 검색",
        "즐겨찾기"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPagerAdapter.addFragment(FragmentSearch())
        viewPagerAdapter.addFragment(FragmentBookmark())

        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

    }
}