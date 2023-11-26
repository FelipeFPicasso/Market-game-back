package com.marketgame.events

import com.marketgame.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent (
    source: Any,
    val purchaseModel: PurchaseModel
):ApplicationEvent(source)