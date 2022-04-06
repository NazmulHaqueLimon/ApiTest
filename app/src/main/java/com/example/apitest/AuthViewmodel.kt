package com.example.apitest

import androidx.lifecycle.*
import com.example.apitest.network.ApiResponse
import com.example.apitest.network.OtpResponce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewmodel @Inject internal constructor(
    private val savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository

):ViewModel() {
    private val _otpResult =MutableLiveData<ApiResponse<OtpResponce>>()
    val otpResult:LiveData<ApiResponse<OtpResponce>> =_otpResult

    fun generateOtp(code:Int,phoneNumber:String){
        viewModelScope.launch {
            _otpResult.value= authRepository.generateOtp(code,phoneNumber)
        }
    }


}