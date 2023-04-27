package com.example.wbc.data.entity

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class BusStationResponse(
    @Element(name = "header")
    var header: Header? = null,

    @Element(name = "body")
    var body: Body? = null
)
@Xml(name = "header")
data class Header(
    @PropertyElement(name = "resultCode")
    val resultCode: String?,
    @PropertyElement(name = "resultMsg")
    val resultMsg: String?
)

@Xml(name = "body")
data class Body(
    @Element(name = "items")
    val items: Items?,
    @PropertyElement(name = "numOfRows")
    val numOfRows: Int?,
    @PropertyElement(name = "pageNo")
    val pageNo: Int?,
    @PropertyElement(name = "totalCount")
    val totalCount: Int?
)

@Xml(name = "items")
data class Items(
    @Element(name = "item")
    val item: List<Item>?
)

@Xml
data class Item(
    @PropertyElement(name = "citycode")
    val cityCode: Int?,
    @PropertyElement(name = "gpslati")
    val gpsLati: Double?,
    @PropertyElement(name = "gpslong")
    val gpsLong: Double?,
    @PropertyElement(name = "nodeid")
    val nodeId: String?,
    @PropertyElement(name = "nodenm")
    val nodeNm: String?,
    @PropertyElement(name = "nodeno")
    val nodeNo: Int?
)
