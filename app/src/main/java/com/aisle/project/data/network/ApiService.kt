package com.aisle.project.data.network

import com.aisle.project.util.PhoneNumberLoginRequest
import com.aisle.project.util.PhoneNumberLoginResponse
import com.aisle.project.util.ProfileData
import com.aisle.project.util.VerifyOtpRequest
import com.aisle.project.util.VerifyOtpResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {
    @POST("phone_number_login")
    fun login(@Body request: PhoneNumberLoginRequest): Call<PhoneNumberLoginResponse>

    @POST("verify_otp")
    fun verifyOtp(@Body request: VerifyOtpRequest): Call<VerifyOtpResponse>

    @GET("test_profile_list")
    fun profileList(
        @Header("Authorization") token: String
    ): Call<ProfileData>

}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://testa2.aisle.co/V1/users/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

