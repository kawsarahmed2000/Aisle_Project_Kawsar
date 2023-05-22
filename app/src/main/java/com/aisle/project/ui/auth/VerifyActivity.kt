package com.aisle.project.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.aisle.project.R
import com.aisle.project.data.network.ApiService
import com.aisle.project.data.network.retrofit
import com.aisle.project.data.repo.Repository
import com.aisle.project.databinding.ActivityVerifyBinding
import com.aisle.project.ui.home.Home
import com.aisle.project.ui.viewmodel.ViewModel
import com.aisle.project.util.toast

class VerifyActivity : AppCompatActivity() {
    private lateinit var binding:ActivityVerifyBinding
    private lateinit var viewModel: ViewModel
    private lateinit var sharedPreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_verify)

        val apiService = retrofit.create(ApiService::class.java)
        val loginRepository = Repository(apiService)
        viewModel = ViewModel(loginRepository)

        sharedPreference=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val number = intent.getStringExtra("number")
        startTimer()
        binding.editMobileNumber.setOnClickListener {
            onBackPressed()
        }
        binding.getOtpLevel.text=number

        binding.continueBtn.setOnClickListener {
//            startActivity(Intent(this,Home::class.java))
            val otp=binding.otp.text.toString().trim()
            if (otp.isNotEmpty()&&otp.length>=4){
                binding.progress.visibility= View.VISIBLE
                if (number != null) {
                    viewModel.verifyOtp(number,otp)
                }
            }else{
                toast("Enter correct otp")
            }
        }

        binding.counterLevel.setOnClickListener {
            if (binding.counterLevel.text=="Resend OTP"){
                Toast.makeText(applicationContext, "Rensended", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.verifyOtpResponse.observe(this) { response ->
            try {
                Log.e("Logcat",response.toString())
//                toast(response.status)
                if (response.token!=""){
                    binding.progress.visibility= View.INVISIBLE
                    val editor: SharedPreferences.Editor = sharedPreference.edit()
                    editor.putString("token", response.token)
                    editor.putString("number", number)
                    editor.apply()
                    val intent=Intent(this,Home::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }else{
                    binding.progress.visibility= View.INVISIBLE
                    toast(response.toString())
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

    private fun startTimer() {
        val counterTextView=binding.counterLevel
        val countDownTimer: CountDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                val time = String.format("%02d:%02d", minutes, seconds)
                counterTextView.text = time
            }
            override fun onFinish() {
                counterTextView.text = "Resend OTP"
            }
        }
        countDownTimer.start()
    }
}