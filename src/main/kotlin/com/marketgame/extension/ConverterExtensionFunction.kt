package com.marketgame.extension

import com.marketgame.controller.request.PostCustomerRequest
import com.marketgame.controller.request.PostGameRequest
import com.marketgame.controller.request.PutCustomerRequest
import com.marketgame.controller.request.PutGameRequest
import com.marketgame.controller.response.CustomerResponse
import com.marketgame.controller.response.GameResponse
import com.marketgame.enums.CustomerStatus
import com.marketgame.enums.GameStatus
import com.marketgame.model.CustomerModel
import com.marketgame.model.GameModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO, password = this.password)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status, password = previousValue.password)
}

fun PostGameRequest.toGameModel(customer: CustomerModel): GameModel{
    return GameModel(
        name = this.name,
        price = this.price,
        status = GameStatus.ATIVO,
        customer = customer
    )
}

fun PutGameRequest.toGameModel(previousValue: GameModel): GameModel{
    return GameModel(
        id = previousValue.id,
        name = this.name?: previousValue.name,
        price = this.price?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun GameModel.toResponse(): GameResponse {
    return GameResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}

