package com.aisle.project.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aisle.project.data.repo.Repository
import com.aisle.project.util.PhoneNumberLoginResponse
import com.aisle.project.util.ProfileData
import com.aisle.project.util.VerifyOtpResponse
import okhttp3.Response

class ViewModel(private val authRepository: Repository) : ViewModel() {
    private val _loginResponse = MutableLiveData<PhoneNumberLoginResponse>()
    val loginResponse: LiveData<PhoneNumberLoginResponse>
        get() = _loginResponse
    fun login(number: String) {
        authRepository.login(number).observeForever { response ->
            _loginResponse.value = response
        }
    }
    private val _verifyOtpResponse = MutableLiveData<VerifyOtpResponse>()
    val verifyOtpResponse: LiveData<VerifyOtpResponse>
        get() = _verifyOtpResponse
    fun verifyOtp(number: String,otp:String) {
        authRepository.verifyOtp(number,otp).observeForever { response ->
            _verifyOtpResponse.value = response
        }
    }
    private val _profileListResponse = MutableLiveData<ProfileData>()
    val profileListResponse: LiveData<ProfileData>
        get() = _profileListResponse
    fun profileList(token: String) {
        authRepository.profileList(token).observeForever { response ->
            _profileListResponse.value = response
        }
    }
}
