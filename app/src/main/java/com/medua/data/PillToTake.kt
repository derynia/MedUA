package com.medua.data

data class PillToTake(
    val id: Int,
    val pillType: PillType,
    val timesADay: Int,
    val qty: Float,
    val taken: Boolean
)

//Test
val mockList = listOf<PillToTake>(
    PillToTake(1, PillType("Aspirin"), 3, 1f, true),
    PillToTake(2, PillType("Ceftriaxon"), 2, 1.5f, false),
    PillToTake(3, PillType("Dexametazone"), 1, 2f, true),
    PillToTake(4, PillType("Vitamin C"), 3, 1f, false)
)