package com.example.llamarapires

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.llamarapires.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(View: View) : RecyclerView.ViewHolder(View) {

    private val binding = ItemDogBinding.bind(View)
    fun bind(image:String){
        Picasso.get().load(image).into(binding.ivDog)
    }
}