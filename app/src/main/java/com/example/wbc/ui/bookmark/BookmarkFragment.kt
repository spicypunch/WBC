package com.example.wbc.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbc.data.entity.BusInfoEntity
import com.example.wbc.databinding.FragmentBookmarkBinding
import com.example.wbc.listener.BookmarkClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment() : Fragment(), BookmarkClickListener {

    private lateinit var binding: FragmentBookmarkBinding

    private val adapter by lazy { BookmarkAdapter(this) }

    private val bookmarkViewModel: BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        binding.recyclerviewBookmark.adapter = adapter
        binding.recyclerviewBookmark.layoutManager = LinearLayoutManager(activity)

        bookmarkViewModel.getMyBookmark()

        return binding.root
    }

    override fun onClick(item: BusInfoEntity) {

    }
}