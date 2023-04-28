package com.example.wbc.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbc.ItemClickListener
import com.example.wbc.databinding.FragmentSearchBinding
import com.example.wbc.data.entity.SearchHistoryEntity
import com.example.wbc.ui.map.MapActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment() : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentSearchBinding

    private val adapter by lazy { SearchHistoryAdapter(this) }

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.recyclerviewHome.adapter = adapter
        binding.recyclerviewHome.layoutManager = LinearLayoutManager(activity)

        searchViewModel.getHistory()

        binding.fabMap.setOnClickListener {
            Intent(context, MapActivity::class.java).run {
                startActivity(this)
            }
        }

        binding.btnSearch.setOnClickListener {
            if (binding.editSearch.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "빈칸을 채워주세요", Toast.LENGTH_SHORT).show()
            } else {
                Intent(context, MapActivity::class.java).apply {
                    putExtra("address", binding.editSearch.text.toString())
                }.run { startActivity(this) }
                searchViewModel.insertHistory(binding.editSearch.text.toString())
            }
        }

        binding.tvDeleteAllHistory.setOnClickListener {
            searchViewModel.deleteAllHistory()
        }

        searchViewModel.items.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        searchViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

    override fun onClick(item: SearchHistoryEntity) {
        searchViewModel.deleteHistory(item)
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
}