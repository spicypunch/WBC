package com.jm.wbc.data.entity

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class BusArrivalResponse(
    @Element(name = "msgHeader")
    var header: MsgHeader? = null,

    @Element(name = "msgBody")
    var body: MsgBody? = null
)

@Xml(name = "msgHeader")
data class MsgHeader(
    @PropertyElement(name = "queryTime")
    val queryTime: String,
    @PropertyElement(name = "resultCode")
    val resultCode: String?,
    @PropertyElement(name = "resultMsg")
    val resultMsg: String?
)

@Xml(name = "msgBody")
data class MsgBody(
    @Element(name = "busArrivalList")
    val busArrivalList: List<BusArrivalList>
)

@Xml(name = "busArrivalList")
data class BusArrivalList(
    @PropertyElement(name = "flag")
    val flag: String,
    @PropertyElement(name = "locationNo1")
    val locationNo1: Int,
    @PropertyElement(name = "locationNo2")
    val locationNo2: Int,
    @PropertyElement(name = "lowPlate1")
    val lowPlate1: Int,
    @PropertyElement(name = "lowPlate2")
    val lowPlate2: Int,
    @PropertyElement(name = "plateNo1")
    val plateNo1: String,
    @PropertyElement(name = "plateNo2")
    val plateNo2: String,
    @PropertyElement(name = "predictTime1")
    val predictTime1: Int,
    @PropertyElement(name = "predictTime2")
    val predictTime2: Int,
    @PropertyElement(name = "remainSeatCnt1")
    val remainSeatCnt1: Int,
    @PropertyElement(name = "remainSeatCnt2")
    val remainSeatCnt2: Int,
    @PropertyElement(name = "routeId")
    val routeId: Int,
    @PropertyElement(name = "staOrder")
    val staOrder: Int,
    @PropertyElement(name = "stationId")
    val stationId: Int,
)