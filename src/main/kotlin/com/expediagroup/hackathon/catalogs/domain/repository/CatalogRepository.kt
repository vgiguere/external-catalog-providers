package com.expediagroup.hackathon.catalogs.domain.repository

import com.expediagroup.hackathon.catalogs.domain.model.Catalog
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.io.File

@Component
class CatalogRepository {

    private lateinit var catalogs: Map<String, Catalog>

    companion object {
        const val BABY_QUIP = "baby-quip"
        const val YAMATO = "yamato"
        val catalogEntries: Map<String, String> = mapOf(
            BABY_QUIP to "/catalogs/baby-quip.json",
            YAMATO to "/catalogs/yamato.json"
        )
    }

    fun getCatalogs(): List<Catalog> {
        return catalogs.values.toList()
    }

    fun getCatalog(catalog: String): Catalog {
        return catalogs[catalog] ?: throw IllegalArgumentException("Catalog $catalog not found")
    }

    @PostConstruct
    fun loadCatalogs() {
        val objectMapper = jacksonObjectMapper()
        val loadedCatalogs = mutableMapOf<String, Catalog>()

        catalogEntries.forEach { (key, path) ->
            val jsonFile = File(this::class.java.getResource(path).file)
            val catalog: Catalog = objectMapper.readValue(jsonFile)
            loadedCatalogs[key] = catalog
        }

        catalogs = loadedCatalogs
    }
}
