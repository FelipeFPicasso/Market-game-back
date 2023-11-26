package com.marketgame.controller

import com.marketgame.controller.request.PostCustomerRequest
import com.marketgame.extension.toCustomerModel
import com.marketgame.extension.toResponse
import com.marketgame.service.CustomerService
import jakarta.validation.Valid
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("loginteste")
class LoginControllerTesteCors (
    val customerService: CustomerService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin("*")
    fun create(): String{
        return "fodase"
    }
}