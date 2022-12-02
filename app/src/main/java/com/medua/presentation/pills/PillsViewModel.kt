package com.medua.presentation.pills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medua.data.PillToTake
import com.medua.data.RevealStatus
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

    private val movedPillsToTake = MutableStateFlow(listOf<Pair<Int, RevealStatus>>())
    val movedPillsToTakeData: StateFlow<List<Pair<Int, RevealStatus>>> = movedPillsToTake

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
        val item = movedPillsToTake.value.firstOrNull { lookItem -> lookItem.first == pillToTake.id }
        if (item != null && item.second == revealStatus) return

        movedPillsToTake.value = movedPillsToTake.value.toMutableList().also { list ->
            list.remove(item)
            if (revealStatus != RevealStatus.None) {
                list.add(pillToTake.id to revealStatus)
                pillToTake.revealStatus = revealStatus
            }
        }
    }

    fun onCollapsed(pillToTake: PillToTake) {
        val item = movedPillsToTake.value.firstOrNull { lookItem -> lookItem.first == pillToTake.id }
            ?: return
        movedPillsToTake.value = movedPillsToTake.value.toMutableList().also { list ->
            list.remove(item)
            pillToTake.revealStatus = RevealStatus.None
        }
    }

    fun acceptPill(pillToTake: PillToTake) {
        pillToTake.taken = true
        onCollapsed(pillToTake)
    }

    fun forgotPill(pillToTake: PillToTake) {
        pillToTake.taken = false
        onCollapsed(pillToTake)
    }
}