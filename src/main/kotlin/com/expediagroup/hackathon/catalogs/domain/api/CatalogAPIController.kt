package com.expediagroup.hackathon.catalogs.domain.api

import com.expediagroup.hackathon.catalogs.domain.model.Catalog
import com.expediagroup.hackathon.catalogs.domain.model.Category
import com.expediagroup.hackathon.catalogs.domain.model.Product
import com.expediagroup.hackathon.catalogs.domain.service.CatalogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/")
class CatalogApiController(private val catalogService: CatalogService) {


    @GetMapping("catalogs")
    fun getCatalogs(): List<Catalog> {
        return catalogService.getAllCatalogs()
    }

    @GetMapping("{catalog}/products")
    fun getAllProducts(@PathVariable catalog: String): List<Product> {
        return catalogService.getAllProducts(catalog)
    }

    @GetMapping("{catalog}/categories")
    fun getAllCategories(@PathVariable catalog: String): List<Category> {
        return catalogService.getAllCategories(catalog)
    }


    // Endpoint to get details about a specific product
    @GetMapping("{catalog}/product/{id}")
    fun getProductDetails(@PathVariable catalog: String, @PathVariable id: String): Product {
        return catalogService.getProductDetails(catalog, id)
            ?: throw ProductNotFoundException("Product with id $id not found")
    }
}

class ProductNotFoundException(message: String) : RuntimeException(message)