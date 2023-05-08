package com.jm.wbc.ui.bookmark

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
import com.jm.wbc.databinding.FragmentBookmarkBinding
import com.jm.wbc.listener.BookmarkClickListener
import com.google.firebase.auth.FirebaseAuth
import com.jm.wbc.data.entity.BusInfoEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookmarkFragment() : Fragment(), BookmarkClickListener {

    @Inject
    lateinit var auth: FirebaseAuth

    private lateinit var binding: FragmentBookmarkBinding

    private val adapter by lazy { BookmarkAdapter(this) }

    private val bookmarkViewModel: BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        binding.recyclerviewBookmark.adapter = adapter
        binding.recyclerviewBookmark.layoutManager = LinearLayoutManager(activity)

        if (auth.currentUser == null) {
            Toast.makeText(context, "로그인을 진행해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            bookmarkViewModel.getMyBookmark()
        }

        binding.fabBookmarkRefresh.setOnClickListener {
            if (auth.currentUser == null) {
                Toast.makeText(context, "로그인을 진행해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                bookmarkViewModel.getMyBookmark()
                Toast.makeText(context, "새로고침 하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        bookmarkViewModel.busArrivalTimeResult.observe(viewLifecycleOwner, Observer {
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

    override fun onClick(item: BusInfoEntity?) {
        if (item != null) {
            bookmarkViewModel.deleteBookmark(item.busNum)
        }
    }
}