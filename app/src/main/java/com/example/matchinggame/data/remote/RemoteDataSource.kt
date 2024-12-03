package com.example.matchinggame.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getImages() =
        apiService.getImages()
}
