package it.polito.wa2.lab4mvc.exceptions

import java.lang.RuntimeException

class ProductQuantityUnavailableException(override val message: String?): RuntimeException(message) {
}