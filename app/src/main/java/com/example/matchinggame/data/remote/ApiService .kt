package com.example.matchinggame.data.remote

import com.example.matchinggame.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET


interface ApiService   {
    @GET(Constants.IMG_SIZE)
    suspend fun getImages(): Response<ResponseBody>
}
