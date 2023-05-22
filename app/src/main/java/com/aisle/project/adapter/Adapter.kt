package com.aisle.project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aisle.project.R
import com.aisle.project.util.Photo
import com.makeramen.roundedimageview.RoundedImageView

class Adapter(private val List: List<Photo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        private lateinit var mListener : onItemClickListener
        interface onItemClickListener {
            fun onItemClick(position: Int)
        }
        fun setOnClickListener(listener: onItemClickListener){
            mListener = listener
        }

        inner class ViewHolder(itemView: View, listener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
            val image: RoundedImageView = itemView.findViewById(R.id.image)
            val text: TextView = itemView.findViewById(R.id.text)
            init {
                itemView.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
            }
            // add more views as needed
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return ViewHolder(view,mListener)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val workDiary = List[position]
        }

        override fun getItemCount() = List.size
    }
