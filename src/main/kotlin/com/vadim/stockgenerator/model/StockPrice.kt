package com.vadim.stockgenerator.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
class StockPrice(
    val index: Int = 0,
    private val isin: String = "",
    private val price: Double,
) {
    override fun toString(): String = "StockPrice(isin:$isin index:$index price:$price)"
}


