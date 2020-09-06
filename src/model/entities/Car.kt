package com.manqana.model.entities

import java.io.Serializable

data class Car(
    val model: String,
    val price: Double
) : Serializable{
}