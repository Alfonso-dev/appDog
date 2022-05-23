package com.example.llamarapires

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.llamarapires.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var  binding: ActivityMainBinding
    lateinit var adapter:DogAdapter
    val dogImage = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searId.setOnQueryTextListener(this)
        initRecycleView()
    }

    private fun initRecycleView(){
        adapter = DogAdapter(dogImage)
        binding.recuclerId.layoutManager = LinearLayoutManager(this)
        binding.recuclerId.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getDogByBreegs("$query/images")
            val pupplies = call.body()
            runOnUiThread{
                if(call.isSuccessful){
                    val images = pupplies?.image ?: emptyList()
                    dogImage.clear()
                    dogImage.addAll(images)
                    adapter.notifyDataSetChanged()
                }else{

                }
            }
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (!p0.isNullOrEmpty()) {
            searchByName(p0.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}