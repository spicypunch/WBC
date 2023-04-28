package com.example.wbc

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.wbc.databinding.ActivityMainBinding
import com.example.wbc.ui.bookmark.BookmarkFragment
import com.example.wbc.ui.login.login.LoginActivity
import com.example.wbc.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        viewPagerAdapter.addFragment(SearchFragment())
        viewPagerAdapter.addFragment(BookmarkFragment())

        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        binding.imageProfile.setOnClickListener {
            Log.e("click", "click")
            createDialog()
        }
    }

    private fun createDialog() {
        Log.e("click2", "click2")
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("로그인")
            setMessage("로그인 창으로 이동합니다.")
            setNegativeButton("취소", null)
            setPositiveButton("네", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Intent(this@MainActivity, LoginActivity::class.java).run {
                        startActivity(this)
                    }
                }
            })
            show()
        }
    }
}