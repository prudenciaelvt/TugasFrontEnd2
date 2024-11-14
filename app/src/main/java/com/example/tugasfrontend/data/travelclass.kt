package com.example.tugasfrontend.data

data class Travel (
    val id: Int,
    val name: String,
    val hasImage: Boolean,
    val imageUrl: String,
    val rating: Double,
    val location: String,
    val specificLocation: String,
    val description: String,
    val priceTicket: String,
    val activities: List<String>,
    val category: String
)
