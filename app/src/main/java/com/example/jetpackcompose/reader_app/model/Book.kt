package com.example.jetpackcompose.reader_app.model

data class Book(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
) {
}