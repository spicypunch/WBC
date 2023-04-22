package com.example.wbc.data.entity

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class BusArrivalResponse(
    @Element(name = "msgHeader")
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