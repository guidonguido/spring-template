package it.polito.wa2.lab4mvc

import it.polito.wa2.lab4mvc.domain.Product
import it.polito.wa2.lab4mvc.repositories.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Lab4MvcApplication{

    fun randomName(len: Int): String {
        val random = java.util.Random()
        val vowels = listOf("a", "e", "i", "o", "u", "y")
        val consonants = listOf(
            "b", "c", "d", "f", "g", "h", "j", "k",
            "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"
        )
        val l = mutableListOf<String>()
        for (i in 0 until len) {
            if (i % 2 == 0)
                l.add(vowels[random.nextInt(vowels.size)])
            else
                l.add(consonants[random.nextInt(consonants.size)])
        }
        return l.joinToString("")
    }

    fun randomCategory(): String{
        val random = java.util.Random()

        val categories = listOf(
            "Food",
            "Appliances",
            "Apps&Games",
            "Arts",
            "AutomotiveParts&Accessories",
            "Baby",
            "Beauty&PersonalCare",
            "Books",
            "CDs&Vinyl",
            "CellPhones",
            "Clothing",
            "Collectibles",
            "Computers",
            "Electronics",
            "Garden&Outdoor",
            "Grocery",
            "Handmade",
            "Health",
            "Home",
            "Industrial",
            "Kindle",
            "Luggage",
            "Movies",
            "Musical",
            "Office",
            "Pet",
            "Sports",
            "Tool",
            "Toys",
            "Videotapes")

        return categories[random.nextInt(categories.size)]
    }

    /*@Bean
    fun populateDB(
        productRepository: ProductRepository,
   ): CommandLineRunner {
       return CommandLineRunner {
           val random = java.util.Random()

           val products = mutableSetOf<Product>()

           for( i in 0..2000)
               products.add(Product(
                   randomName(10),
                   randomCategory(),
                   (i / 100.0).toBigDecimal(),
                    i*3L))


           productRepository
               .saveAll(products)

       }
   }

     */


}

fun main(args: Array<String>) {
    runApplication<Lab4MvcApplication>(*args)
}
