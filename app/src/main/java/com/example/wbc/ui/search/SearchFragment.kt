package com.example.wbc.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbc.databinding.FragmentSearchBinding
import com.example.wbc.ui.map.MapActivity

class SearchFragment() : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val adapter by lazy { SearchHistoryAdapter() }

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.recyclerviewHome.adapter = adapter
        binding.recyclerviewHome.layoutManager = LinearLayoutManager(activity)

        binding.fabMap.setOnClickListener {
            Intent(context, MapActivity::class.java).run {
                startActivity(this)
            }
        }

        binding.btnSearch.setOnClickListener {
            if (binding.editSearch.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "빈칸을 채워주세요", Toast.LENGTH_SHORT).show()
            } else {
                Intent(context, MapActivity::class.java).run {
                    putExtra("address", binding.editSearch.text.toString())
                    startActivity(this)
                }
            }
        }

        return binding.root
    }
}