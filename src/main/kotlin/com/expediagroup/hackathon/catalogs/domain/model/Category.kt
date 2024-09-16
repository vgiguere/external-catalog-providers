package com.expediagroup.hackathon.catalogs.domain.model

data class Category(
    val id: String,
    val name: String,
    val imageUrl: String,
    val products: List<Product>
)