package com.aisle.project.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aisle.project.R
import com.aisle.project.databinding.ActivitySplashBinding
import com.aisle.project.ui.auth.GetOtp
import java.util.Timer
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_splash)

        startsecondactivity()
    }
    fun startsecondactivity(){
        if (!isDestroyed){

            val tmtask = timerTask {
                if (!isDestroyed) {
                    startActivity(Intent(applicationContext, GetOtp::class.java))
                    finish()
                }
            }
            val timer = Timer()
            timer.schedule(tmtask, 1000)

        }
    }
}