package com.aisle.project.util

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

data class ListData(
    var status: String
)

data class PhoneNumberLoginResponse(
    var status: String
)

data class PhoneNumberLoginRequest(
    var number: String
)


data class VerifyOtpResponse(
    var token: String
)
data class VerifyOtpRequest(
    var number: String,
    var otp: String
)

data class ProfileData(
    val invites: InvitesData,
    val likes: LikesData
)

data class InvitesData(
    val profiles: List<Profile>,
    val totalPages: Int,
    val pendingInvitationsCount: Int
)

data class Profile(
    val generalInformation: GeneralInformation,
    val approvedTime: Double,
    val disapprovedTime: Double,
    val photos: List<Photo>,
    val userInterests: List<Any>,
    val work: Work,
    val preferences: List<Preference>,
    val instagramImages: Any,
    val lastSeenWindow: String,
    val isFacebookDataFetched: Boolean,
    val icebreakers: Any,
    val story: Any,
    val meetup: Any,
    val verificationStatus: String,
    val hasActiveSubscription: Boolean,
    val showConciergeBadge: Boolean,
    val lat: Double,
    val lng: Double,
    val lastSeen: Any,
    val onlineCode: Int,
    val profileDataList: List<ProfileDataList>
)

data class GeneralInformation(
    val dateOfBirth: String,
    val dateOfBirthV1: String,
    val location: Location,
    val drinkingV1: DrinkingV1,
    val firstName: String,
    val gender: String,
    val maritalStatusV1: MaritalStatusV1,
    val refId: String,
    val smokingV1: SmokingV1,
    val sunSignV1: SunSignV1,
    val motherTongue: MotherTongue,
    val faith: Faith,
    val height: Int,
    val cast: Any,
    val kid: Any,
    val diet: Any,
    val politics: Any,
    val pet: Any,
    val settle: Any,
    val mbti: Any,
    val age: Int
)

data class Location(
    val summary: String,
    val full: String
)

data class DrinkingV1(
    val id: Int,
    val name: String,
    val nameAlias: String
)

data class MaritalStatusV1(
    val id: Int,
    val name: String,
    val preferenceOnly: Boolean
)

data class SmokingV1(
    val id: Int,
    val name: String,
    val nameAlias: String
)

data class SunSignV1(
    val id: Int,
    val name: String
)

data class MotherTongue(
    val id: Int,
    val name: String
)

data class Faith(
    val id: Int,
    val name: String
)

data class Work(
    val industryV1: IndustryV1,
    val monthlyIncomeV1: Any,
    val experienceV1: ExperienceV1,
    val highestQualificationV1: HighestQualificationV1,
    val fieldOfStudyV1: FieldOfStudyV1
)

data class IndustryV1(
    val id: Int,
    val name: String,
    val preferenceOnly: Boolean
)

data class ExperienceV1(
    val id: Int,
    val name: String,
    val nameAlias: String
)

data class HighestQualificationV1(
    val id: Int,
    val name: String,
    val preferenceOnly: Boolean
)

data class FieldOfStudyV1(
    val id: Int,
    val name: String
)

data class Preference(
    val answerId: Int,
    val id: Int,
    val value: Int,
    val preferenceQuestion: PreferenceQuestion
)

data class PreferenceQuestion(
    val firstChoice: String,
    val secondChoice: String
)

data class Photo(
    val photo: String,
    val photoId: Int,
    val selected: Boolean,
    val status: String?
)

data class ProfileDataList(
    val question: String,
    val preferences: List<Preference>,
    val invitationType: String
)

data class LikesData(
    val profiles: List<LikedProfile>,
    val canSeeProfile: Boolean,
    val likesReceivedCount: Int
)

data class LikedProfile(
    val firstName: String,
    val avatar: String
)

