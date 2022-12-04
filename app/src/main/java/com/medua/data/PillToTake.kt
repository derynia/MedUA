package com.medua.data

data class PillToTake(
    val id: Int,
    val pillType: PillType,
    val timesADay: Int,
    val qty: Float,
    var taken: Boolean,
    var revealStatus : RevealStatus
)

//Test
val mockList = listOf(
    PillToTake(1, PillType("Aspirin", PillCategory("Pills")), 3, 1f, true, RevealStatus.None),
    PillToTake(2, PillType("Ceftriaxon", PillCategory("Injection")), 2, 1.5f, false, RevealStatus.None),
    PillToTake(3, PillType("Dexametazone", PillCategory("Injection")), 1, 2f, true, RevealStatus.None),
    PillToTake(4, PillType("Vitamin C", PillCategory("Pills")), 3, 1f, false, RevealStatus.None),
    PillToTake(5, PillType("Vitamin C", PillCategory("Pills")), 3, 1f, false, RevealStatus.None),
    PillToTake(6, PillType("Vitamin C", PillCategory("Pills")), 3, 1f, false, RevealStatus.None),
    PillToTake(7, PillType("Vitamin C", PillCategory("Pills")), 3, 1f, false, RevealStatus.None),
)