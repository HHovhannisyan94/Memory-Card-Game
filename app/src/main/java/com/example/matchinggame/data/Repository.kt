package com.example.matchinggame.data

import com.example.matchinggame.data.remote.RemoteDataSource
import com.example.matchinggame.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) : BaseApiResponse() {
    suspend fun getImage(): Flow<NetworkResult<ResponseBody>> {
        return flow {
            for (i in 0..7) {
                emit(safeApiCall { remoteDataSource.getImages() })
            }
        }.flowOn(Dispatchers.IO)
    }
}