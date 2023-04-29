package com.example.wbc.ui.map

import android.content.Intent
import android.util.Log
import com.example.wbc.ui.bus_info.BusArrivalActivity
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MarkerEventListener(val context: MapActivity) : MapView.POIItemEventListener {
    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
        // 문자열은 전부 제거한 후 stationID에 저장
        val regex = "\\D+".toRegex()
        val stationID = p1?.itemName?.let { regex.replace(it, "").toInt() }
        Intent(context, BusArrivalActivity::class.java).apply {
            putExtra("stationID", stationID.toString())
        }.run { context.startActivity(this) }
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
    }
}