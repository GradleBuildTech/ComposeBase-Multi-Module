package com.example.feat.main.controller

sealed class MainEvent {
    data class TabSelected(val index: Int) : MainEvent()
}