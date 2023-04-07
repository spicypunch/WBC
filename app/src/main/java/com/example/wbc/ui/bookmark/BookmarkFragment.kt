package com.example.wbc.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wbc.databinding.FragmentBookmarkBinding

class BookmarkFragment() : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        binding.sectionLabel.text = "즐찾즐찾즐찾즐찾즐찾즐찾즐찾"
        return binding.root
    }
}