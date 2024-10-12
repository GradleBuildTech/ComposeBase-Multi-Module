package com.example.feat.main.controller

sealed class MainEvent {
    data class ChangeTab(val index: Int) : MainEvent()
}