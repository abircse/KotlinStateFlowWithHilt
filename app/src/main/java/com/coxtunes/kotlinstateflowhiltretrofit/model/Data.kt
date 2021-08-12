package com.coxtunes.kotlinstateflowhiltretrofit.model

import java.io.Serializable

data class Data(
    val id: Int,
    val cat_title: String,
    val cat_slug: String,
    val cat_image: String,
    val status: Int,
    val created_at: Any,
    val updated_at: Any
) : Serializable