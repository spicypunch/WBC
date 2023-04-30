package com.example.wbc.ui.bookmark

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

        binding.fabBookmarkRefresh.setOnClickListener {
            bookmarkViewModel.getMyBookmark()
        }

        bookmarkViewModel.getMyBookmark()

        bookmarkViewModel.busArrivalTimeResult.observe(viewLifecycleOwner, Observer {
            Log.e("list", it.toString())
            adapter.submitList(it)
        })

        bookmarkViewModel.deleteResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "삭제 완료되었습니다.", Toast.LENGTH_SHORT).show()
                bookmarkViewModel.getMyBookmark()
            } else {
                Toast.makeText(context, "삭제 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    override fun onClick(item: BusInfoEntity) {
        bookmarkViewModel.deleteBookmark(item.busNum)
    }
}