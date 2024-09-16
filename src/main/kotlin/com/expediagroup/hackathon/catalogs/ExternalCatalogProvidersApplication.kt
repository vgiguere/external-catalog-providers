package com.expediagroup.hackathon.catalogs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExternalCatalogProvidersApplication

fun main(args: Array<String>) {
	runApplication<ExternalCatalogProvidersApplication>(*args)
}
