package com.example.mydemo.shops.repository

import com.example.mydemo.shops.models.Product

class ProductRepository {
    private val categories = mapOf(
        "Brood" to listOf(
            Product("Galet", 2.50),
            Product("Witte blok", 2.75),
            Product("Tijgerbrood", 3.00),
            Product("Zonnebrood", 3.50),
            Product("Maya", 2.95)
        ),
        "Ontbijtkoeken" to listOf(
            Product("Boterkoek met rozijnen", 1.50),
            Product("Chocoladekoek", 1.75),
            Product("Croissant", 1.80),
            Product("Lange suis", 1.60),
            Product("Kriekenkoek", 1.90)
        ),
        "Pistolets en Franse Broden" to listOf(
            Product("Wit pistolet", 1.20),
            Product("Grof pistolet Brood", 1.50),
            Product("Vloerpistolet", 1.50),
            Product("Frans brood", 1.50),
            Product("Zuurdesemstokbrood", 1.50)
        ),
        "Patisserie" to listOf(
            Product("Rijsttaart", 2.50),
            Product("Confituurtaart", 3.00),
            Product("Kriekengebak", 2.00),
            Product("Chocoladetaart", 15.00),
            Product("Appeltaart", 15.00)
        ),
        "Plastiek Zakje" to listOf(
            Product("Plastiek Zakje", 0.10)
        ),
        "Other Categories" to listOf(
            Product("Pralines 250g", 10.00),
            Product("Pralines 500g", 18.50)
        )
    )

    fun getCategories(): Map<String, List<Product>> = categories

    fun getProductByName(name: String): Product? {
        return categories.values.flatten().find { it.name == name }
    }
}