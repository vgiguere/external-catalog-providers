package com.expediagroup.hackathon.catalogs.domain.model

data class Catalog(
    val id: String,
    val name: String,
    val categories: List<Category>
)