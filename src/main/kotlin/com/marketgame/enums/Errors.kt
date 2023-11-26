package com.marketgame.enums

enum class Errors(val code: String, val message: String) {

    MG001("MG-001", "Invalid Request"),
    MG101("MG-101", "Game [%s] not exists"),
    MG102("MG-102", "Cannot update game with status [%s]"),
    MG201("MG-201", "Customer[%s] not exists" )
}

