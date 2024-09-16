package com.expediagroup.hackathon.catalogs.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val price: Double,
    val detailedInfo: DetailedInfo? = null
)