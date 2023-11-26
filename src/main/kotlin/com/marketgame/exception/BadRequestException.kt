package com.marketgame.exception

class BadRequestException(override val message: String, val errorCode: String) :Exception() {

}