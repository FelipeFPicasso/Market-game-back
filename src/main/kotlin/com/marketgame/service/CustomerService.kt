package com.marketgame.service

import com.marketgame.enums.CustomerStatus
import com.marketgame.enums.Errors
import com.marketgame.enums.Role
import com.marketgame.exception.NotFoundException
import com.marketgame.model.CustomerModel
import com.marketgame.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService (
    val customerRepository: CustomerRepository,
    val gameService: GameService,
    private val bCrypt: BCryptPasswordEncoder
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel){
        val customerCopy = customer.copy(
            roles = setOf(Role.CUSTOMER),
            password = bCrypt.encode(customer.password)
        )
        customerRepository.save(customerCopy)
    }

    fun findById(id:Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{NotFoundException(Errors.MG201.message.format(id), Errors.MG201.code )}
    }

    fun update(customer: CustomerModel) {

        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int){
        val customer = findById(id)
        gameService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}
