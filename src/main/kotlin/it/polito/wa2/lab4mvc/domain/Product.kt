package it.polito.wa2.lab4mvc.domain

import java.math.BigDecimal
import javax.persistence.Entity

@Entity
class Product (
    val name: String,
    val category: String,
    val price: BigDecimal,
    var quantity: Long
): EntityBase<Long>()