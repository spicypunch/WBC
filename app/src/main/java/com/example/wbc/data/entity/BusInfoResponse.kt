package com.example.wbc.data.entity

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class BusInfoResponse(
    @Element(name = "msgHeader")
    var header: BusHeader? = null,

    @Element(name = "msgBody")
    var body: BusBody? = null
)

@Xml(name = "msgHeader")
data class BusHeader(
    @PropertyElement(name = "queryTime")
    val queryTime: String,
    @PropertyElement(name = "resultCode")
    val resultCode: String?,
    @PropertyElement(name = "resultMsg")
    val resultMsg: String?
)

@Xml(name = "msgBody")
data class BusBody(
    @Element(name = "busRouteInfoItem")
    val busRouteInfoItem: List<BusRouteInfoItem>
)

@Xml(name = "busRouteInfoItem")
data class BusRouteInfoItem(
    @PropertyElement(name = "companyId")
    val companyId: String,
    @PropertyElement(name = "companyName")
    val companyName: String,
    @PropertyElement(name = "companyTel")
    val companyTel: String,
    @PropertyElement(name = "districtCd")
    val districtCd: Int,
    @PropertyElement(name = "downFirstTime")
    val downFirstTime: String,
    @PropertyElement(name = "downLastTime")
    val downLastTime: String,
    @PropertyElement(name = "endMobileNo")
    val endMobileNo: Int,
    @PropertyElement(name = "endStationId")
    val endStationId: String,
    @PropertyElement(name = "endStationName")
    val endStationName: String,
    @PropertyElement(name = "peekAlloc")
    val peekAlloc: String,
    @PropertyElement(name = "regionName")
    val regionName: String,
    @PropertyElement(name = "routeId")
    val routeId: Int,
    @PropertyElement(name = "routeName")
    val routeName: String,
    @PropertyElement(name = "routeTypeCd")
    val routeTypeCd: Int,
    @PropertyElement(name = "routeTypeName")
    val routeTypeName: String,
    @PropertyElement(name = "startMobileNo")
    val startMobileNo: Int,
    @PropertyElement(name = "startStationId")
    val startStationId: Int,
    @PropertyElement(name = "startStationName")
    val startStationName: String,
    @PropertyElement(name = "upFirstTime")
    val upFirstTime: String,
    @PropertyElement(name = "upLastTime")
    val upLastTime: String,
    @PropertyElement(name = "nPeekAlloc")
    val nPeekAlloc: Int,
)