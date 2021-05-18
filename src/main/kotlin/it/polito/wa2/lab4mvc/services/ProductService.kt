package it.polito.wa2.lab4mvc.services

import it.polito.wa2.lab4mvc.dto.ProductDTO
import java.math.BigDecimal

interface ProductService {
     fun addProduct(name: String, category: String, price: BigDecimal, quantity: Long): ProductDTO

     fun updateProductQuantity(productId: Long, quantity: Long): ProductDTO

     fun getProduct(productId: Long): ProductDTO

     fun getProducts(): Set<ProductDTO>

     fun getProductsByCategory(category: String): Set<ProductDTO>
}