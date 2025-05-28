package com.example.domain.mapper

//import com.example.core.extensions.convertToDate
import com.example.data.model.response.tutor.TutorFavoriteModel
import com.example.data.model.response.tutor.TutorModel
import com.example.data.model.response.tutor.TutorsResponse
import com.example.data.model.response.tutor.detail.TutorDetailModel
import com.example.data.model.response.tutor.detail.TutorUserDetailModel
import com.example.domain.entity.tutor.TutorEntity
import com.example.domain.entity.tutor.TutorFavoriteEntity
import com.example.domain.entity.tutor.TutorFavorites
import com.example.domain.entity.tutor.detail.TutorDetail
import com.example.domain.entity.tutor.detail.TutorUserDetail
//import com.example.room.entities.RoomTutorEntity
//import java.util.Date

fun TutorModel.toEntity(): TutorEntity {
    return TutorEntity(
        id = this.id ?: "",
        name = this.name,
        email = this.email,
        bio = this.bio,
        avatar = this.avatar,
        level = this.level,
        country = this.country,
        rating = this.rating,
        specialties = this.specialties,
        userId = this.userId,
    )
}

fun TutorDetailModel.toEntity(): TutorDetail {
    return TutorDetail(
        video = this.video ?: "",
        bio = this.bio ?: "",
        education = this.education ?: "",
        experience = this.experience ?: "",
        profession = this.profession ?: "",
        accent = this.accent ?: "",
        targetStudent = this.targetStudent ?: "",
        interests = this.interests ?: "",
        languages = this.languages ?: "",
        specialties = this.specialties ?: "",
        rating = this.rating ?: 0.0,
        avgRating = this.avgRating ?: 0.0,
        totalFeedback = this.totalFeedback ?: 0,
        isNative = this.isNative ?: false,
        isFavorite = this.isFavorite ?: false,
        youtubeVideoId = this.youtubeVideoId ?: "",
        user = this.user?.toEntity()
    )
}

fun TutorUserDetailModel.toEntity(): TutorUserDetail {
    return TutorUserDetail(
        id = this.id ?: "",
        level = this.level ?: "",
        avatar = this.avatar ?: "",
        name = this.name ?: "",
        country = this.country ?: "",
        language = this.language ?: "",
        isPublicRecord = this.isPublicRecord ?: false,
        caredByStaffId = this.caredByStaffId ?: "",
        studentGroupId = this.studentGroupId ?: "",
        zaloUserId = this.zaloUserId ?: "",
        courses = this.courses?.map {
            it.toEntity()
        } ?: listOf()
    )
}

fun TutorFavoriteModel.toEntity(): TutorFavoriteEntity {
    return TutorFavoriteEntity(
        id = this.id,
        firstId = this.firstId,
        secondId = this.secondId,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    );
}

fun TutorsResponse.toDomain(): List<TutorEntity> {
    return this.tutors.rows.map {
        it.toEntity();
    }
}

fun TutorsResponse.toFavoriteDomain(): TutorFavorites {
    return TutorFavorites(
        count = this.tutors.count,
        tutors = this.tutors.rows.map {
            it.toEntity();
        },
        favoriteTutors = this.favoriteTutor.map {
            it.toEntity();
        }
    )
}

fun TutorModel.toDomain(): TutorEntity {
    return this.toEntity()
}
//
//fun RoomTutorEntity.fromRoomTutorEntity(): TutorEntity {
//    return TutorEntity(
//        id = this.id,
//        level = this.level,
//        email = this.email,
//        google = this.google,
//        facebook = this.facebook,
//        apple = this.apple,
//        avatar = this.avatar,
//        name = this.name,
//        country = this.country,
//        phone = this.phone,
//        language = this.language,
//        birthDay = this.birthDay?.convertToDate(),
//        requestPassword = this.requestPassword,
//        isActivated = this.isActivated,
//        isPhoneActivated = this.isPhoneActivated,
//        requireNote = this.requireNote,
//        timezone = this.timezone,
//        phoneAuth = this.phoneAuth,
//        isPhoneAuthActivated = this.isPhoneAuthActivated,
//        canSendMessage = this.canSendMessage,
//        isPublicRecord = this.isPublicRecord,
//        caredByStaffId = this.caredByStaffId,
//        createdAt = this.createdAt?.convertToDate(),
//        updatedAt = this.updatedAt?.convertToDate(),
//        deletedAt = this.deletedAt,
//        studentGroupId = this.studentGroupId,
//        userId = this.userId,
//        video = this.video,
//        bio = this.bio,
//        education = this.education,
//        experience = this.experience,
//        profession = this.profession,
//        accent = this.accent,
//        targetStudent = this.targetStudent,
//        interests = this.interests,
//        languages = this.languages,
//        specialties = this.specialties,
//        resume = this.resume,
//        rating = this.rating,
//        isNative = this.isNative,
//        price = this.price,
//        isOnline = this.isOnline,
//        isFavorite = this.isFavorite,
//    )
//}
//
//fun TutorEntity.toRoomTutorEntity(): RoomTutorEntity {
//    return RoomTutorEntity(
//        id = this.id,
//        level = this.level,
//        email = this.email,
//        google = this.google,
//        facebook = this.facebook,
//        apple = this.apple,
//        avatar = this.avatar,
//        name = this.name,
//        country = this.country,
//        phone = this.phone,
//        language = this.language,
//        birthDay = this.birthDay?.time,
//        requestPassword = this.requestPassword,
//        isActivated = this.isActivated,
//        isPhoneActivated = this.isPhoneActivated,
//        requireNote = this.requireNote,
//        timezone = this.timezone,
//        phoneAuth = this.phoneAuth,
//        isPhoneAuthActivated = this.isPhoneAuthActivated,
//        canSendMessage = this.canSendMessage,
//        isPublicRecord = this.isPublicRecord,
//        caredByStaffId = this.caredByStaffId,
//        createdAt = this.createdAt?.time,
//        updatedAt = this.updatedAt?.time,
//        deletedAt = this.deletedAt,
//        studentGroupId = this.studentGroupId,
//        userId = this.userId,
//        video = this.video,
//        bio = this.bio,
//        education = this.education,
//        experience = this.experience,
//        profession = this.profession,
//        accent = this.accent,
//        targetStudent = this.targetStudent,
//        interests = this.interests,
//        languages = this.languages,
//        specialties = this.specialties,
//        resume = this.resume,
//        rating = this.rating,
//        isNative = this.isNative,
//        price = this.price,
//        isOnline = this.isOnline,
//        isFavorite = this.isFavorite,
//    )
//}