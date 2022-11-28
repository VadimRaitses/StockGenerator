package com.vadim.stockgenerator.model

import com.fasterxml.jackson.annotation.JsonIgnore

class Message(val msgType: String, val data: Any, @JsonIgnore val price: String=""){
}

