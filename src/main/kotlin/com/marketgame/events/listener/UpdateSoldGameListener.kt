package com.marketgame.events.listener

import com.marketgame.events.PurchaseEvent
import com.marketgame.service.GameService
import com.marketgame.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UpdateSoldGameListener(
    private val gameService: GameService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        println("Atualizando status dos games")

        gameService.purchase(purchaseEvent.purchaseModel.games)
    }
}