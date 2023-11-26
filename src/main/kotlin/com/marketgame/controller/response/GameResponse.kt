package com.marketgame.controller.response

import com.marketgame.enums.GameStatus
import com.marketgame.model.CustomerModel
import java.math.BigDecimal

data class GameResponse (

    var id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var customer: CustomerModel? = null,

    var status: GameStatus? = null
)
