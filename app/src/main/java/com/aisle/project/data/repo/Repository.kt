package com.aisle.project.data.repo

import androidx.lifecycle.MutableLiveData
import com.aisle.project.data.network.ApiService
import com.aisle.project.util.PhoneNumberLoginRequest
import com.aisle.project.util.PhoneNumberLoginResponse
import com.aisle.project.util.ProfileData
import com.aisle.project.util.VerifyOtpRequest
import com.aisle.project.util.VerifyOtpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository(private val apiService: ApiService) {
    fun login(number: String): MutableLiveData<PhoneNumberLoginResponse?> {
        val liveData = MutableLiveData<PhoneNumberLoginResponse?>()
        val request = PhoneNumberLoginRequest(number)
        apiService.login(request).enqueue(object : Callback<PhoneNumberLoginResponse> {
            override fun onResponse(call: Call<PhoneNumberLoginResponse>, response: Response<PhoneNumberLoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    liveData.value = loginResponse
                } else {
                    liveData.value = null
                }
            }

            override fun onFailure(call: Call<PhoneNumberLoginResponse>, t: Throwable) {
                liveData.value = null
            }
        })
        return liveData
    }
    fun verifyOtp(number: String,otp:String): MutableLiveData<VerifyOtpResponse?> {
        val liveData = MutableLiveData<VerifyOtpResponse?>()
        val request = VerifyOtpRequest(number,otp)
        apiService.verifyOtp(request).enqueue(object : Callback<VerifyOtpResponse> {
            override fun onResponse(call: Call<VerifyOtpResponse>, response: Response<VerifyOtpResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    liveData.value = loginResponse
                } else {
                    liveData.value = null
                }
            }

            override fun onFailure(call: Call<VerifyOtpResponse>, t: Throwable) {
                liveData.value = null
            }
        })
        return liveData
    }
    fun profileList(token:String): MutableLiveData<ProfileData?> {
        val liveData = MutableLiveData<ProfileData?>()
        apiService.profileList(token).enqueue(object : Callback<ProfileData> {
            override fun onResponse(call: Call<ProfileData>, response: Response<ProfileData>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    liveData.value = loginResponse
                } else {
                    liveData.value = null
                }
            }

            override fun onFailure(call: Call<ProfileData>, t: Throwable) {
                liveData.value = null
            }
        })
        return liveData
    }
}
