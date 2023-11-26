package com.marketgame.controller.mapper

import com.marketgame.controller.request.PostPurchaseRequest
import com.marketgame.model.PurchaseModel
import com.marketgame.service.CustomerService
import com.marketgame.service.GameService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val gameService: GameService,
    private val customerService: CustomerService
) {
    fun toModel(request: PostPurchaseRequest): PurchaseModel{
        val customer = customerService.findById(request.customerId)
        val games = gameService.findAllByIds(request.gameIds)

        return PurchaseModel(
            customer = customer,
            games = games.toMutableList(),
            price = games.sumOf { it.price }

        )
    }


}