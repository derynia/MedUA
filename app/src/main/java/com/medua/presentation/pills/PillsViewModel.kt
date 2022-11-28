package com.medua.presentation.pills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medua.data.PillToTake
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

    private val revealedPillsToTake = MutableStateFlow(listOf<Int>())
    val revealedPillsToTakeData: StateFlow<List<Int>> = revealedPillsToTake

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

    fun onItemExpanded(pillId: Int) {
        if (revealedPillsToTake.value.contains(pillId)) return
        revealedPillsToTake.value = revealedPillsToTake.value.toMutableList().also { list ->
            list.add(pillId)
        }
    }

    fun onItemCollapsed(pillId: Int) {
        if (!revealedPillsToTake.value.contains(pillId)) return
        revealedPillsToTake.value = revealedPillsToTake.value.toMutableList().also { list ->
            list.remove(pillId)
        }
    }
}