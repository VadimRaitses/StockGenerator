package com.vadim.stockgenerator.model

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
class StockPrice(
    @JsonIgnore val index: Int = 0,
    val isin: String = "",
    val price: Double,
) {
    override fun toString(): String = "StockPrice(isin:$isin index:$index price:$price)"
}


