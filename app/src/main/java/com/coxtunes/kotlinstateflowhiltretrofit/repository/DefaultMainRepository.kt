package com.coxtunes.kotlinstateflowhiltretrofit.repository

import com.coxtunes.kotlinstateflowhiltretrofit.model.CategoryBase
import com.coxtunes.kotlinstateflowhiltretrofit.network.APIInterface
import com.nexgen.tbl.base.utils.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: APIInterface
) : MainRepository {
    override suspend fun loadCategory(): Resource<CategoryBase> {
        return try {
            val response = api.loadCategory()
            val result = response.body()
            if (result != null && response.isSuccessful) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Something Wrong ! Please try again")
        }
    }


}