package com.medua.data

data class PillToTake(
    val pillType: PillType,
    val timesADay: Int,
    val qty: Float,
    val taken: Boolean
)
