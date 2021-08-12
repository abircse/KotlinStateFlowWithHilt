package com.coxtunes.kotlinstateflowhiltretrofit.model

data class CategoryBase(
    val `data`: List<Data>,
    val file_url_root: String,
    val success: Boolean
)