package it.polito.wa2.lab4mvc.controllers

import it.polito.wa2.lab4mvc.dto.ProductDTO
import it.polito.wa2.lab4mvc.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@RestController
@RequestMapping("/warehouse")
@Validated
class ProductController(val productService: ProductService) {

    @PostMapping("/products")
    fun addProduct(
            @RequestBody
            @Valid
            bodyDTO: ProductDTO
    ): ResponseEntity<ProductDTO> {

        val newProduct = productService.addProduct(bodyDTO.name,
                                                   bodyDTO.category,
                                                   bodyDTO.price,
                                                   bodyDTO.quantity)

        return ResponseEntity(newProduct, HttpStatus.CREATED)
    }

    @PatchMapping("/products/{productId}")
    fun updateQuantity(
        @PathVariable
        @Positive(message = "Insert a valid productId")
        productId: Long,

        @RequestBody
        @NotNull(message = "Insert a valid quantity")
        bodyVal: Long? = null
    ): ResponseEntity<ProductDTO> {
        val newProduct = productService.updateProductQuantity(productId, bodyVal!!)
        return ResponseEntity(newProduct, HttpStatus.OK)
    }

    @GetMapping("/products/{productId}")
    fun getProduct(
        @PathVariable
        @Positive(message = "Insert a valid productId")
        productId: Long
    ): ResponseEntity<ProductDTO> {
        val product = productService.getProduct(productId)
        return ResponseEntity(product, HttpStatus.OK)
    }

    @GetMapping("/products", produces = ["application/json"])
    fun getProducts(): ResponseEntity<Set<ProductDTO>> {
        val products = productService.getProducts()
        return ResponseEntity(products, HttpStatus.OK)
    }

    @GetMapping("/productsByCategory")
    fun getProductsByCategory(@RequestParam
                              @NotNull(message = "'category' string is required")
                              category: String? = null,): ResponseEntity<Set<ProductDTO>> {
        val products =  productService.getProductsByCategory(category!!)

        return ResponseEntity(products, HttpStatus.OK)


    }
}