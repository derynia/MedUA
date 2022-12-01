package com.medua.presentation.pills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medua.data.PillToTake
import com.medua.data.RevealStatus
import com.medua.data.RevealStatus.Left.getOppositeDirection
import com.medua.data.mockList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PillsViewModel @Inject constructor(
) : ViewModel() {

    private val pillsToTake = MutableStateFlow(listOf<PillToTake>())
    val pillsToTakeData: StateFlow<List<PillToTake>> = pillsToTake

    private val movedPillsToTake = MutableStateFlow(listOf<Pair<PillToTake, RevealStatus>>())
    val movedPillsToTakeData: StateFlow<List<Pair<PillToTake, RevealStatus>>> = movedPillsToTake

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                pillsToTake.emit(mockList)
            }
        }
    }

    fun onItemMoved(pillToTake: PillToTake, revealStatus: RevealStatus) {
        if (movedPillsToTake.value.contains(pillToTake to revealStatus)) return
        movedPillsToTake.value = movedPillsToTake.value.toMutableList().also { list ->
            list.remove(pillToTake to revealStatus.getOppositeDirection())
            if (revealStatus != RevealStatus.None) {
                list.add(pillToTake to revealStatus)
            }
        }
    }

    fun onCollapsed(pillToTake: PillToTake) {
        val item = movedPillsToTake.value.firstOrNull { lookItem -> lookItem.first == pillToTake }
            ?: return
        movedPillsToTake.value = movedPillsToTake.value.toMutableList().also { list ->
            list.remove(item)
        }
    }

}