package it.polito.wa2.lab4mvc.dto

import it.polito.wa2.lab4mvc.domain.Product
import java.math.BigDecimal
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ProductDTO(
    val id: Long?,

    @field:NotNull(message = "Name is required")
    val name: String,

    @field:NotNull(message = "Category is required")
    val category: String,

    @field:NotNull(message = "Price is required")
    @field:Positive(message = "Insert a valid price")
    val price: BigDecimal,

    @field:NotNull(message = "Price is required")
    val quantity: Long
)

fun Product.toProductDTO(): ProductDTO =
    ProductDTO(getId(),
               name,
               category,
               price,
               quantity)
