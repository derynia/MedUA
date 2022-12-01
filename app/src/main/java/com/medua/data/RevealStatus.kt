package com.medua.data

sealed class RevealStatus {
    object Left : RevealStatus() {
        override fun getOppositeDirection() = Right
    }

    object Right: RevealStatus() {
        override fun getOppositeDirection() = Left

    }

    object None: RevealStatus() {
        override fun getOppositeDirection() = None
    }

    abstract fun getOppositeDirection() : RevealStatus
}
