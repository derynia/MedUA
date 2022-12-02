package com.medua.data

data class PillToTake(
    val id: Int,
    val pillType: PillType,
    val timesADay: Int,
    val qty: Float,
    val taken: Boolean,
    var revealStatus : RevealStatus
)

//Test
val mockList = listOf(
    PillToTake(1, PillType("Aspirin"), 3, 1f, true, RevealStatus.None),
    PillToTake(2, PillType("Ceftriaxon"), 2, 1.5f, false, RevealStatus.None),
    PillToTake(3, PillType("Dexametazone"), 1, 2f, true, RevealStatus.None),
    PillToTake(4, PillType("Vitamin C"), 3, 1f, false, RevealStatus.None)
)