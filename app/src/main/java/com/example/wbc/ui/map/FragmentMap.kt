package com.example.wbc.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wbc.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapView

class FragmentMap() : Fragment() {

    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {

        val mapView = MapView(activity)

        val mapViewContainer = binding.mapView as ViewGroup

        mapViewContainer.addView(mapView)
        super.onResume()
    }
}