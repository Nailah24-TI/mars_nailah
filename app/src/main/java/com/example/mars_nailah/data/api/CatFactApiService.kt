package com.example.mars_nailah.data.api

import com.example.mars_nailah.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}