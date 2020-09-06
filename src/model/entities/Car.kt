package com.manqana.model.entities

import java.io.Serializable

data class Car(
    val model: String?,
    val imageUrl: String?,
    val price: Double?
) : Serializable{
}