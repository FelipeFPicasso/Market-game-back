package com.marketgame


import com.marketgame.controller.request.PostCustomerRequest
import com.marketgame.controller.request.PutCustomerRequest
import com.marketgame.controller.response.CustomerResponse
import com.marketgame.extension.toCustomerModel
import com.marketgame.extension.toResponse
import com.marketgame.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
@CrossOrigin("*")
class customerController (
    val customerService: CustomerService
){


@GetMapping
fun getAll(@RequestParam name: String?): List<CustomerResponse> {
    return customerService.getAll(name).map { it.toResponse() }
}

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
fun create(@RequestBody @Valid customer: PostCustomerRequest){
        customerService.create(customer.toCustomerModel())
}

@GetMapping("/{id}")
@PreAuthorize("hasRole('ROLE_ADMIN') || #id == authentication.principal.id")
fun getCustomer(@PathVariable id:Int): CustomerResponse {
    return customerService.findById(id).toResponse()
}



@PutMapping("/{id}")
@PreAuthorize("hasRole('ROLE_ADMIN') || #id == authentication.principal.id")
@ResponseStatus(HttpStatus.NO_CONTENT)
fun update(@PathVariable id:Int, @RequestBody @Valid customer: PutCustomerRequest) {
    val customerSaved = customerService.findById(id)
    customerService.update(customer.toCustomerModel(customerSaved))
}
@DeleteMapping("/{id}")
@PreAuthorize("hasRole('ROLE_ADMIN') || #id == authentication.principal.id")
@ResponseStatus(HttpStatus.NO_CONTENT)
fun delete(@PathVariable id:Int){
    customerService.delete(id)

}



}