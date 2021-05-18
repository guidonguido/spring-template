package it.polito.wa2.lab4mvc.controllers

import it.polito.wa2.lab4mvc.exceptions.NotFoundException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.SQLException
import javax.validation.ValidationException


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {

    // Handles entity not found and validation errors in the service
    @ExceptionHandler(
        ValidationException::class,
        NotFoundException::class)
    protected fun handleValidationException(e: Exception): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    // Handles validation errors in NewTransactionRequestBodyDTO, RegistrationRequestBody
    override fun handleMethodArgumentNotValid(
        e: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return ResponseEntity.badRequest().body(e.fieldErrors.map { it.defaultMessage })
    }

    // Handles request body JSON parse errors
    override fun handleHttpMessageNotReadable(
        e: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return ResponseEntity.badRequest().body(e.message)
    }


    // Handles entity not found and validation errors in the service
    @ExceptionHandler(SQLException::class)
    protected fun handleSQLException(e: SQLException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }
}
