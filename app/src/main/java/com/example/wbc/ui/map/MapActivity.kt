package com.example.wbc.ui.map

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wbc.databinding.ActivityMapBinding
import com.example.wbc.di.coroutine.IoDispatcher
import com.example.wbc.ui.search.SearchViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MapActivity () : AppCompatActivity() {
    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

    private lateinit var binding: ActivityMapBinding

    private val mapViewModel: MapViewModel by viewModels()

    private val searchViewModel: SearchViewModel by viewModels()

    // 위도
    private var latitude: Double = 0.0
    // 경도
    private var longitude: Double = 0.0

    private val mapView by lazy {
        MapView(this)
    }

    private val marker = MapPOIItem()

    private val permissionList = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
    )

    private val requestMultiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            result.forEach {
                if (!it.value) {
                    Toast.makeText(this, "${it.key}권한 허용 필요", Toast.LENGTH_SHORT).show()
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 권한 확인
        requestMultiplePermission.launch(permissionList)

        // 현재 위치
        getMyLocation()

        // 현재 위치 근방의 버스 정류소 출력
        mapViewModel.getBusStationInfo(latitude, longitude)

        // Search Fragment에서 넘어온 값 처리
        if (intent.hasExtra("address")) {
            val address = intent.getStringExtra("address")
            if (address != null) {
                mapViewModel.searchLocation(address, latitude, longitude)
                setMarker(latitude, longitude, address)
            }

        }

        // 카카오맵 띄우기
        val mapViewContainer = binding.mapView as ViewGroup
        mapViewContainer.addView(mapView)

        // 현재 위치로 이동 버튼
        binding.fabMap.setOnClickListener {
            getMyLocation()
        }

        // 검색 버튼
        binding.btnSearch.setOnClickListener {
            if (binding.editSearch.text.toString().isBlank()) {
                Toast.makeText(this, "주소를 입력해주세요", Toast.LENGTH_SHORT).show()
                mapViewModel.getBusArrivalTime("GGB206000108")
            } else {
                mapViewModel.searchLocation(binding.editSearch.text.toString(), latitude, longitude)
                // 검색 기록 추가
                searchViewModel.insertHistory(binding.editSearch.text.toString())
            }
        }



        // 검색 결과
        mapViewModel.searchResult.observe(this, androidx.lifecycle.Observer {
            if (it.documents.isEmpty()) {
                Toast.makeText(this, "2km 근방에 찾으신 시설이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                setMarker(it.documents[0].y.toDouble(), it.documents[0].x.toDouble(), it.documents[0].place_name)
                mapViewModel.getBusStationInfo(it.documents[0].y.toDouble(), it.documents[0].x.toDouble())
            }
        })

        // 버스 정류장 출력
        mapViewModel.busStationResult.observe(this, androidx.lifecycle.Observer {
            if (it.body?.items?.item?.isEmpty() == true) {
                Toast.makeText(this, "근처에 버스 정류장이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                for (i in it.body?.items?.item!!) {
                    setMarker(i.gpsLati!!, i.gpsLong!!, i.nodeNm!!)
                }
            }
        })

        mapViewModel.busArrivalTimeResult.observe(this, androidx.lifecycle.Observer {
            Log.e("result", it.toString())
        })
    }

    private fun getMyLocation() {
        val locationProvider = LocationProvider(this)
        latitude = locationProvider.getLocationLatitude()
        longitude = locationProvider.getLocationLongitude()

        setMarker(latitude, longitude, "현재 위치")
    }

    // 지도에 마커 생성
    private fun setMarker(latitude: Double, longitude: Double, title: String) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
        marker.itemName = title
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)

        if (title != "현재 위치") {
            marker.markerType = MapPOIItem.MarkerType.BluePin
            marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        } else {
            marker.markerType = MapPOIItem.MarkerType.RedPin
        }

        mapView.addPOIItem(marker)
    }
}