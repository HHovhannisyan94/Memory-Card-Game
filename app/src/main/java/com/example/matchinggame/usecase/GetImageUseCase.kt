package com.example.matchinggame.usecase

import com.example.matchinggame.data.Repository
import javax.inject.Inject

class GetImageUseCase @Inject constructor(private val repository: Repository) {
    suspend  fun getImages() = repository.getImage()
}