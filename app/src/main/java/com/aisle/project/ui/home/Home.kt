package com.aisle.project.ui.home

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aisle.project.R
import com.aisle.project.adapter.Adapter
import com.aisle.project.data.network.ApiService
import com.aisle.project.data.network.retrofit
import com.aisle.project.data.repo.Repository
import com.aisle.project.databinding.ActivityHomeBinding
import com.aisle.project.ui.viewmodel.ViewModel
import com.aisle.project.util.ListData
import com.aisle.project.util.Photo
import com.aisle.project.util.Profile
import com.aisle.project.util.toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    private lateinit var viewModel: ViewModel
    private lateinit var sharedPreference: SharedPreferences
    private lateinit var adapter: Adapter
    private lateinit var RecyclerView: RecyclerView
    private lateinit var photoData: List<Photo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home)

        val apiService = retrofit.create(ApiService::class.java)
        val loginRepository = Repository(apiService)
        viewModel = ViewModel(loginRepository)

        sharedPreference=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val token = sharedPreference.getString("token","")
        if (token != null) {
            viewModel.profileList(token)
        }
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item1 -> {
                    // Handle item 1 selection
//                    Toast.makeText(applicationContext, "1", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_item2 -> {
                    // Handle item 2 selection
//                    Toast.makeText(applicationContext, "2", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_item3 -> {
                    // Handle item 3 selection
//                    Toast.makeText(applicationContext, "3", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_item4 -> {
                    // Handle item 4 selection
//                    Toast.makeText(applicationContext, "4", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        photoData = arrayListOf()

        RecyclerView = binding.recyclerview
        RecyclerView.layoutManager =
            GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        RecyclerView.setHasFixedSize(true)

        viewModel.profileListResponse.observe(this) { response ->
            try {
                Log.e("Logcat",response.toString())
                binding.progress.visibility= View.GONE
                val d:List<Profile> =response.invites.profiles
                for (profile in d) {
                    val photos: List<Photo> = profile.photos
                    photoData=photos
                }
                adapter = Adapter(photoData)
                RecyclerView.adapter = adapter
                binding.progress.visibility = View.INVISIBLE
                adapter.setOnClickListener(object :
                    Adapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val clickedData = photoData[position]
                    }

                })

                adapter.notifyDataSetChanged()
            } catch (e: Exception) {
                binding.progress.visibility= View.GONE
                Log.e("Logcat", "Exception occurred: ${e.message}")
                toast("An error occurred")
            } catch (e: Error) {
                binding.progress.visibility= View.GONE
                Log.e("Logcat", "Error occurred: ${e.message}")
                toast("An error occurred")
            }
        }

    }
}