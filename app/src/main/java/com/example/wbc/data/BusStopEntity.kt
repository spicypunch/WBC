package com.example.wbc.data

class BusStopEntity : ArrayList<BusStopEntity.BusStopItem>() {
    data class BusStopItem(
        val STTN_NM_INFO: String,
        val STTN_ID: Int,
        val STTN_MANAGE_ID: Int,
        val WGS84_LAT: Double,
        val WGS84_LOGT: Double
    )
}