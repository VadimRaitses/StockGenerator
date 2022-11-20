package com.example.springsocket.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
class Stock(
    @JsonProperty("ACT Symbol") val id: String,
    @JsonProperty("Company Name") val companyName: String,
) {

    override fun toString(): String = "Stock(id='$id', companyName='$companyName')"
}



