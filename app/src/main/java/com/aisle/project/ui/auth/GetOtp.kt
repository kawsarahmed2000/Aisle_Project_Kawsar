package com.aisle.project.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.aisle.project.R
import com.aisle.project.data.network.ApiService
import com.aisle.project.data.network.retrofit
import com.aisle.project.data.repo.Repository
import com.aisle.project.databinding.ActivityGetOtpBinding
import com.aisle.project.ui.viewmodel.ViewModel
import com.aisle.project.util.toast

class GetOtp : AppCompatActivity() {
    private lateinit var binding: ActivityGetOtpBinding
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_get_otp)

        val apiService = retrofit.create(ApiService::class.java)
        val loginRepository = Repository(apiService)
        viewModel = ViewModel(loginRepository)

        binding.phoneNumber.requestFocus()
        binding.continueBtn.setOnClickListener {
            val phn = binding.phoneNumber.text.toString().trim()
            if (phn.isNotEmpty()) {
                binding.progress.visibility= View.VISIBLE
                val number =
                    binding.countryCode.text.toString().trim() + phn
                        .trim()
                viewModel.login(number)
            }
        }


        viewModel.loginResponse.observe(this) { response ->
            try {
                Log.e("Logcat",response.toString())
//                toast(response.status)
                if (response.status=="true"){
                    binding.progress.visibility= View.INVISIBLE
                    val intent=Intent(this,VerifyActivity::class.java)
                    intent.putExtra("number",binding.countryCode.text.toString().trim()+binding.phoneNumber.text.toString().trim())
                    startActivity(intent)
                }else{
                    binding.progress.visibility= View.INVISIBLE
                    toast(response.status)
                }
            } catch (e: Exception) {
                binding.progress.visibility= View.INVISIBLE
                Log.e("Logcat", "Exception occurred: ${e.message}")
                toast("An error occurred")
            } catch (e: Error) {
                binding.progress.visibility= View.INVISIBLE
                Log.e("Logcat", "Error occurred: ${e.message}")
                toast("An error occurred")
            }
        }

    }
}