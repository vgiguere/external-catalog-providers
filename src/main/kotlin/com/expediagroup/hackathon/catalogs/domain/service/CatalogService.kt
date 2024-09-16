package com.expediagroup.hackathon.catalogs.domain.service

import com.expediagroup.hackathon.catalogs.domain.model.Catalog
import com.expediagroup.hackathon.catalogs.domain.model.Category
import com.expediagroup.hackathon.catalogs.domain.model.Product
import com.expediagroup.hackathon.catalogs.domain.repository.CatalogRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class CatalogService(private val catalogRepository: CatalogRepository) {

    fun getAllCatalogs(): List<Catalog> = catalogRepository.getCatalogs().map { it.copy(categories = emptyList()) }


    fun getAllProducts(catalog: String): List<Product> {
        catalogRepository.getCatalog(catalog).let { catalog ->
            return catalog.categories.flatMap { it.products }
        }
    }

    fun getProductsInCategory(catalog: String, categoryId: String): List<Product> {
        val catalogData = catalogRepository.getCatalog(catalog)

        val category = catalogData.categories.find { it.id == categoryId }
            ?: throw IllegalArgumentException("Category with id $categoryId not found in catalog $catalog")

        return category.products
    }

    fun getAllCategories(catalog: String): List<Category> =
        catalogRepository.getCatalog(catalog).categories

    fun getProductDetails(catalog: String, productId: String): Product? {
        return getAllProducts(catalog).find { it.id == productId }
    }
}