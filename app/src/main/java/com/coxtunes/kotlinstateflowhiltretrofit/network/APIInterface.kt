package com.coxtunes.kotlinstateflowhiltretrofit.network

import com.coxtunes.kotlinstateflowhiltretrofit.model.CategoryBase
import retrofit2.Response
import retrofit2.http.POST


interface APIInterface {

    /** Load Category **/
    @POST("films/cat")
    suspend fun loadCategory(): Response<CategoryBase>
}
