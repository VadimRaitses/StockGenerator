package com.vadim.stockgenerator.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
class Stock(
    @JsonProperty("ACT Symbol") val isin: String,
    @JsonProperty("Company Name") val companyName: String,
    @JsonProperty("Stock State") var stockStatus: StockStatus? = StockStatus.ADD
) {

    override fun toString(): String = "Stock(isin='$isin', companyName='$companyName')"
}



