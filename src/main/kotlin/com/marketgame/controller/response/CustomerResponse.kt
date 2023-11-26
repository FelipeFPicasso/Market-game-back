package com.marketgame.controller.response

import com.marketgame.enums.CustomerStatus

data class CustomerResponse (

    var id: Int? = null,

    var name: String,

    var email: String,

    var status: CustomerStatus
)
