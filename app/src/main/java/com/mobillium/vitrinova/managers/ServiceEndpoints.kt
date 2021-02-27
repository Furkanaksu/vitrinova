package com.mobillium.vitrinova.managers

import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ServiceEndpoints {


    @GET("discover")
    fun Discover(): Call<String>

}