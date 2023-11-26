package com.marketgame.service

import com.marketgame.Security.UserCustomDetails
import com.marketgame.exception.AuthenticationException
import com.marketgame.repository.CustomerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CrossOrigin


@Service
class UserDetailsCustomService(
    private val customerRepository: CustomerRepository
): UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository.findById(id.toInt())
            .orElseThrow { AuthenticationException("Usuario não encontrado", "999") }
        return UserCustomDetails(customer)
    }
}