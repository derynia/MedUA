package com.medua.data

data class PillToTake(
    val pillType: PillType,
    val timesADay: Int,
    val qty: Float,
    val taken: Boolean
)

val mockList = listOf<PillToTake>(
    PillToTake(PillType("Aspirin"), 3, 1f, true),
    PillToTake(PillType("Ceftriaxon"), 2, 1.5f, false),
    PillToTake(PillType("Dexametazone"), 1, 2f, true),
    PillToTake(PillType("Vitamin C"), 3, 1f, false)
)