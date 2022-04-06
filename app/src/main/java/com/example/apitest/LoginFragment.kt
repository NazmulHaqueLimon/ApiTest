package com.example.apitest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.apitest.databinding.FragmentLoginBinding
import com.example.apitest.network.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var loginBinding: FragmentLoginBinding
    private val authViewmodel : AuthViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        loginBinding = FragmentLoginBinding.inflate(inflater,container,false).apply {
            getOtp.setOnClickListener{
                lifecycleScope.launch {
                    authViewmodel.generateOtp(1, "01688656198"
                    )
                }
            }
        }
        authViewmodel.otpResult.observe(viewLifecycleOwner){
            when(it){
                is ApiResponse.Success->{
                    Log.d("msg",it.value.code.toString())
                }
            }
        }

        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}