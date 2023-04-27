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
        Intent(context, BusArrivalActivity::class.java).apply {
            putExtra("stationID", p1?.itemName?.takeLast(9))
        }.run { context.startActivity(this) }
        Log.e("test", p1!!.itemName)
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
    }
}