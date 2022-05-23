package com.example.llamarapires

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getDogByBreegs(@Url url:String):Response<DogResponse>
}