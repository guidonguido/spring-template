package it.polito.wa2.lab4mvc.repositories

import it.polito.wa2.lab4mvc.domain.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CrudRepository<Product, Long> {

    fun findByCategory(category: String): Set<Product>
}