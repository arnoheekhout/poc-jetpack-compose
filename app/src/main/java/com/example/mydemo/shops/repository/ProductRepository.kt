package com.example.mydemo.shops.repository

import android.content.Context
import android.graphics.Bitmap
import com.example.mydemo.R
import com.example.mydemo.helpers.decodeBase64ToBitmap
import com.example.mydemo.helpers.readBase64FromRaw
import com.example.mydemo.shops.models.Product


class ProductRepository(private val context: Context) {

    val productImageMap = mapOf(
        "Galet" to R.raw.galet_base64,
        "Witte blok" to R.raw.witteblok_base64,
        "Tijgerbrood" to R.raw.tijgerbrood_base64,
        "Zonnebrood" to R.raw.zonnebrood_base64,
        "Maya" to R.raw.maya_base64,
        "Boterkoek met rozijnen" to R.raw.boterkoekrozijn_base64,
        "Chocoladekoek" to R.raw.chocoladekoek_base64,
        "Croissant" to R.raw.croissant_base64,
        "Lange suis" to R.raw.langesluis_base64,
        "Kriekenkoek" to R.raw.kriekenkoek_base64,
        "Wit pistolet" to R.raw.witpistolet_base64,
        "Grof pistolet" to R.raw.grofpistolet_base64,
        "Vloerpistolet" to R.raw.vloerpistolet_base64,
        "Frans brood" to R.raw.fransbrood_base64,
        "Zuurdesemstokbrood" to R.raw.zuurdesemstokbrood_base64,
        "Rijsttaart" to R.raw.rijsttaart_base64,
        "Confituurtaart" to R.raw.confituurtaart_base64,
        "Kriekengebak" to R.raw.kriekengebak_base64,
        "Chocoladetaart" to R.raw.chocoladetaart_base64,
        "Appeltaart" to R.raw.appeltaart_base64,
        "Plastiek Zakje" to R.raw.plastiekzakje_base64,
        "Pralines 250g" to R.raw.pralines250g_base64,
        "Pralines 500g" to R.raw.pralines500g_base64,

        )

    fun getProduct(): Map<String, List<Product>> {
        return mapOf(
            "Brood" to listOf(
                Product("Galet", 2.50, productImages["Galet"]),
                Product("Witte blok", 2.75, productImages["Witte blok"]),
                Product("Tijgerbrood", 3.00, productImages["Tijgerbrood"]),
                Product("Zonnebrood", 3.50, productImages["Zonnebrood"]),
                Product("Maya", 2.95, productImages["Maya"])
            ),
            "Ontbijtkoeken" to listOf(
                Product("Boterkoek met rozijnen", 1.50, productImages["Boterkoek met rozijnen"]),
                Product("Chocoladekoek", 1.75, productImages["Chocoladekoek"]),
                Product("Croissant", 1.80, productImages["Croissant"]),
                Product("Lange suis", 1.60, productImages["Lange suis"]),
                Product("Kriekenkoek", 1.90, productImages["Kriekenkoek"])
            ),
            "Pistolets en Franse Broden" to listOf(
                Product("Wit pistolet", 1.20, productImages["Wit pistolet"]),
                Product("Grof pistolet", 1.50, productImages["Grof pistolet"]),
                Product("Vloerpistolet", 1.50, productImages["Vloerpistolet"]),
                Product("Frans brood", 1.50, productImages["Frans brood"]),
                Product("Zuurdesemstokbrood", 1.50, productImages["Zuurdesemstokbrood"])
            ),
            "Patisserie" to listOf(
                Product("Rijsttaart", 2.50, productImages["Rijsttaart"]),
                Product("Confituurtaart", 3.00, productImages["Confituurtaart"]),
                Product("Kriekengebak", 2.00, productImages["Kriekengebak"]),
                Product("Chocoladetaart", 15.00, productImages["Chocoladetaart"]),
                Product("Appeltaart", 15.00, productImages["Appeltaart"])
            ),
            "Plastiek Zakje" to listOf(
                Product("Plastiek Zakje", 0.10, productImages["Plastiek Zakje"])
            ),
            "Other Categories" to listOf(
                Product("Pralines 250g", 10.00, productImages["Pralines 250g"]),
                Product("Pralines 500g", 18.50, productImages["Pralines 500g"])
            )
        )
    }

    fun getProductByName(name: String): Product? {
        return getProduct().values.flatten().find { it.name == name }
    }

    private val productImages: Map<String, Bitmap?> = productImageMap.mapValues { (_, resId) ->
        val base64String = context.readBase64FromRaw(resId)
        base64String?.let { decodeBase64ToBitmap(it) }
    }
}