package com.jm.wbc.listener

import com.jm.wbc.data.entity.SearchHistoryEntity

interface SearchHistoryClickListener {

    fun onClick(item: SearchHistoryEntity)
}