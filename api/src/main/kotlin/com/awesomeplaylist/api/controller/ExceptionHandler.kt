package com.awesomeplaylist.api.controller

import com.awesomeplaylist.api.service.NotFoundException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {
    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(NotFoundException::class)
    fun handleDomainError(exception: NotFoundException, request: WebRequest): ResponseEntity<Any> {
        logger.info(exception) { exception.message }

        return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
    }
}