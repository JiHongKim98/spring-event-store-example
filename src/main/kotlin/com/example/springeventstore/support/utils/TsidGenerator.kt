package com.example.springeventstore.support.utils

import com.github.f4b6a3.tsid.TsidCreator

object TsidGenerator {
    fun generateIdToLong(): Long = TsidCreator.getTsid().toLong()
}
