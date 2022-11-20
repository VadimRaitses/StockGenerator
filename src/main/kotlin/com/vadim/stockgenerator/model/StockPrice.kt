package com.vadim.stockgenerator.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
class StockPrice(
    val index: Int,
    val price: Double,
    ) {

    override fun toString(): String = "StockPrice(index:$index price:$price)"

}


