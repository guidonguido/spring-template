package it.polito.wa2.lab4mvc.services

import it.polito.wa2.lab4mvc.domain.Product
import it.polito.wa2.lab4mvc.dto.ProductDTO
import it.polito.wa2.lab4mvc.dto.toProductDTO
import it.polito.wa2.lab4mvc.exceptions.NotFoundException
import it.polito.wa2.lab4mvc.exceptions.ProductQuantityUnavailableException
import it.polito.wa2.lab4mvc.repositories.ProductRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ProductServiceImpl(private val productRepository: ProductRepository): ProductService {

    override fun addProduct(name: String,
                                    category: String,
                                    price: BigDecimal,
                                    quantity: Long): ProductDTO{
        val product = Product(name, category, price, quantity)

        val savedProd = productRepository.save(product)
        print("Saved product: {id: ${savedProd.getId()}}")

        return savedProd.toProductDTO()
    }

    override fun updateProductQuantity(productId: Long, quantity: Long): ProductDTO {
        val productOpt = productRepository.findById(productId)

        if(productOpt.isEmpty) throw NotFoundException("Requested productId not found on DB")

        val product = productOpt.get()

        if( quantity < 0 && product.quantity < -quantity )
            throw ProductQuantityUnavailableException("Required quantity is not available")

        product.quantity = product.quantity + quantity

        return productRepository.save(product).toProductDTO()
    }

    override fun getProduct(productId: Long): ProductDTO {
        val product = productRepository.findById(productId)

        if(product.isEmpty) throw NotFoundException("Requested productId not found on DB")

        return product.get().toProductDTO()
    }

    override fun getProducts(): Set<ProductDTO> {

        return productRepository.findAll()
            .map { it.toProductDTO() }.toSet()
    }

    override fun getProductsByCategory(category: String): Set<ProductDTO> {
        return productRepository.findByCategory(category)
            .map { it.toProductDTO() }.toSet()
    }

}