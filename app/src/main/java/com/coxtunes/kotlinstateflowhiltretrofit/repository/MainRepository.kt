package com.coxtunes.kotlinstateflowhiltretrofit.repository

import com.coxtunes.kotlinstateflowhiltretrofit.model.CategoryBase
import com.nexgen.tbl.base.utils.Resource

interface MainRepository {
    suspend fun loadCategory(): Resource<CategoryBase>
}