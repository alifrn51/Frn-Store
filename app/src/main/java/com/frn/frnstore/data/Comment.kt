package com.frn.frnstore.data

data class Comment(
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val author: Author
)
