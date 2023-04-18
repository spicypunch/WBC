package com.example.wbc.data

class BusStopEntity : ArrayList<BusStopEntity.BusStopItem>() {
    data class BusStopItem(
        val STTN_NM_INFO: String,
        val STTN_ID: String,
        val STTN_MANAGE_ID: String,
        val WGS84_LAT: String,
        val WGS84_LOGT: String
    )
}