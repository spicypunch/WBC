package com.example.wbc.ui.bus_info

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbc.listener.BookmarkClickListener
import com.example.wbc.data.entity.BusInfoEntity
import com.example.wbc.databinding.ActivityBusArrivalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusArrivalActivity : AppCompatActivity(), BookmarkClickListener {

    private lateinit var binding: ActivityBusArrivalBinding
    private val busArrivalViewModel: BusArrivalViewModel by viewModels()
    private val adapter by lazy { BusArrivalAdapter(this) }
    private lateinit var stationID: String
    private var busList = mutableListOf<BusInfoEntity>()
    private var map = mutableMapOf<String, String>()
    private var cnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusArrivalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerviewBusArrival.adapter = adapter
        binding.recyclerviewBusArrival.layoutManager = LinearLayoutManager(this)

        /**
         * 정류장 ID(stationID)를 이용해 해당 정류장에 도착 예정인 버스의 목록을 조회한다.
         * 버스 도착 예정 시간을 조회하는 API의 응답 내용에는 버스의 번호가 포함이 안 된다.
         * 버스의 번호 대신 해당 버스가 가진 고유 routeId를 가지기 때문에 routeId로 버스 정보 조회 API 요청을 한다.
         * 그리고 일단 routeID와 도착예정시간1, 도착예정시간2를 BusInfoEntity에 저장해준다.
         */
        stationID = intent.getStringExtra("stationID").toString()
        busArrivalViewModel.getBusArrivalTime(stationID)

        busArrivalViewModel.busArrivalTimeResult.observe(this, Observer {
            if (it.body?.busArrivalList == null) {
                Toast.makeText(this, "도착 예정 버스가 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                for (i in it.body?.busArrivalList!!) {
                    busArrivalViewModel.getBusName(i.routeId)
                    busList.add(BusInfoEntity(i.routeId.toString(), i.predictTime1, i.predictTime2))
                }
            }
        })

        /**
         * 위에서 요청한 버스 정보 조회 API의 결과값을 받아온다.
         * map에 routId와 routeName을 키와 값으로 각각 저장해준다.
         * busList.size == cnt를 비교하는 조건문을 걸지 않을 경우
         * map에 값을 전부 넣기 전에 replaceBusName 함수가 실행되고,
         * NPE이 발생하기 때문에 리스트 사이즈와 맵에 데이터를 저장한 횟수를 비교해 준다.
         */
        busArrivalViewModel.busName.observe(this, Observer {
            if (it.body?.busRouteInfoItem != null) {
                for (i in it.body?.busRouteInfoItem!!) {
                    map.put(i.routeId.toString(), i.routeName)
                    cnt++
                }
            }
            if (busList.size == cnt) {
                replaceBusName()
            }
        })

        busArrivalViewModel.insertResult.observe(this, Observer {
            if (it == false) {
                Toast.makeText(this, "즐겨찾기 추가에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "즐겨찾기 추가에 성공하였습니다.", Toast.LENGTH_SHORT).show()

            }
        })

        binding.fabRefresh.setOnClickListener {
            busList.clear()
            cnt = 0
            busArrivalViewModel.getBusArrivalTime(stationID)
            Toast.makeText(this, "새로고침 하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * busList에 넣었던 routeId를 맵의 키값으로 이용해 routeName(버스 번호)를 조회하고 busList의 busNum에 넣어준다.
     */

    private fun replaceBusName() {
        busList.forEach {
            it.busNum = map[it.busNum]!!
        }
        adapter.submitList(busList)
    }
    override fun onClick(item: BusInfoEntity?) {
        if (item == null) {
            Toast.makeText(this, "로그인을 진행해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            // map에 저장해두었던 routeID를 가져온다.
            busArrivalViewModel.insertMyBookmark(stationID, map.filterValues { it == item.busNum }.keys.first(), item.busNum)
        }
    }
}