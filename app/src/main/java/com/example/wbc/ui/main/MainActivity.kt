package com.example.wbc.ui.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.wbc.R
import com.example.wbc.databinding.ActivityMainBinding
import com.example.wbc.ui.bookmark.BookmarkFragment
import com.example.wbc.ui.login.login.LoginActivity
import com.example.wbc.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

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
            if (auth.currentUser != null) {
                logoutDialog()
            } else {
                loginDialog()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (auth.currentUser != null) {
            mainViewModel.getProfileImage()
        }

        mainViewModel.uri.observe(this, Observer {
            if (it != null) {
                Glide.with(this).load(it).circleCrop().into(binding.imageProfile)
            }
        })
    }

    private fun logoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("로그아웃")
            setMessage("로그아웃 하시겠습니까?")
            setNegativeButton("취소", null)
            setPositiveButton("네", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    auth.signOut()
                    setBasicImage()
                }
            })
            show()
        }
    }

    private fun loginDialog() {
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

    private fun setBasicImage() {
        Glide.with(this).load(R.drawable.basic_profile).circleCrop().into(binding.imageProfile)
    }
}