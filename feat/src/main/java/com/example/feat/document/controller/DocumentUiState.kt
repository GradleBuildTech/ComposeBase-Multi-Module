package com.example.feat.document.controller

import com.example.domain.entity.BookingInfoEntity
import com.example.domain.entity.TutorEntity
import com.example.domain.entity.TutorFavoriteEntity

data class DocumentUiState (
    val bookingInfo : BookingInfoEntity? = null,
    val isLoading: Boolean = false,
    val totalTime: Int = 0,
    val tutors: List<TutorEntity> = emptyList(),
    val favoriteTutors: List<TutorFavoriteEntity> = emptyList()
){
    override fun equals(other: Any?): Boolean {
        // check data tutors
        if (other is DocumentUiState) {
            if (tutors.size != other.tutors.size) return false
            for (i in tutors.indices) {
                if (tutors[i] != other.tutors[i]) return false
            }
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = bookingInfo?.hashCode() ?: 0
        result = 31 * result + isLoading.hashCode()
        result = 31 * result + totalTime
        result = 31 * result + tutors.hashCode()
        result = 31 * result + favoriteTutors.hashCode()
        return result
    }
}